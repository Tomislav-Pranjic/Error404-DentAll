package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.DriverRepository;
import dentall.dao.UserTreatmentInfoRepository;
import dentall.domain.Driver;
import dentall.domain.UserTreatmentInfo;
import dentall.domain.Vehicle;
import dentall.rest.dto.CreateDriverDTO;
import dentall.rest.dto.CreateDriverWithVehicleDTO;
import dentall.rest.dto.DriverWorkInfoDTO;
import dentall.service.DriverService;
import dentall.service.VehicleService;
import dentall.service.exceptions.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverServiceJpa implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserTreatmentInfoRepository userTreatmentInfoRepository;

    private final Logger logger = LoggerFactory.getLogger(DriverServiceJpa.class);

    @Override
    public List<Driver> listAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver createDriver(String name, String surname, String email, String phoneNumber, String vehicleRegistration, String workStartTime, String workingDays) {
        Assert.hasText(name, "Name must be set.");
        Assert.isTrue(name.length() <= 16, "Name must not be longer than 16 characters.");

        Assert.hasText(surname, "Surname must be set.");
        Assert.isTrue(surname.length() <= 16, "Surname must not be longer than 16 characters.");

        Assert.hasText(email, "Email must be set.");
        Assert.isTrue(email.length() <= 64, "Email must not be longer than 64 characters.");
        Assert.isTrue(email.matches(Error404BeApplication.EMAIL_FORMAT), "Email must be in valid format.");

        Assert.hasText(phoneNumber, "Phone number must be set.");
        phoneNumber = phoneNumber.replaceAll(" ", "");
        Assert.isTrue(phoneNumber.matches(Error404BeApplication.PHONE_NUMBER_FORMAT), "Phone number must be in valid format.");

        if(driverCanBeCreated(name, surname, email, phoneNumber)){
            logger.info("Creating driver with name '" + name + "' and surname '" + surname + "' and email '" + email + "' and phone number '" + phoneNumber + "'.");

        }else {
            logger.error("Driver with name '" + name + "' and surname '" + surname + "' and email '" + email + "' and phone number '" + phoneNumber + "' already exists.");
            throw new IllegalArgumentException("Driver with name '" + name + "' and surname '" + surname + "' and email '" + email + "' and phone number '" + phoneNumber + "' already exists.");
        }

        Assert.isTrue(vehicleRegistration.matches(Error404BeApplication.REGISTRATION_FORMAT), "Vehicle registration must be in valid format.");
        Optional<Vehicle> vehicle = vehicleService.findByRegistration(vehicleRegistration);
        if (vehicle.isEmpty()) {
            throw new ItemNotFoundException("Vehicle with registration '" + vehicleRegistration + "' does not exist.");
        }

        Assert.hasText(workStartTime, "Work start time must be set.");
        Assert.isTrue(workStartTime.matches(Error404BeApplication.TIME_FORMAT), "Work start time must be in valid format(HH:mm).");
        Time time = Time.valueOf(workStartTime + ":00");

        Assert.hasText(workingDays, "Working days must be set.");
        Assert.isTrue(workingDays.matches(Error404BeApplication.WORKING_DAYS_FORMAT), "Working days must be in valid format(NPUSCEB).");

        Driver driver = new Driver(name, surname, email, phoneNumber, vehicle.get(), time, workingDays);
        return driverRepository.save(driver);
    }

    @Override
    public DriverWorkInfoDTO getFreeDriverForDate(String date) {
        DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);
        Date sqlDate;
        try {
            sqlDate = new Date(df.parse(date).getTime());
        } catch (Exception e) {
            logger.error("Error while parsing date: " + e.getMessage());
            return null;
        }

        List<UserTreatmentInfo> treatmentsForThatDay = userTreatmentInfoRepository.findAllByArrivalDateOrDepartureDateOrTreatmentDate(sqlDate, sqlDate, sqlDate);
        List<Driver> drivers = driverRepository.findAll();
        List<Driver> notTotallyFreeDrivers = new ArrayList<>();

        for (UserTreatmentInfo treatment : treatmentsForThatDay) {
            if(drivers.remove(treatment.getArrivalDriver()))
                notTotallyFreeDrivers.add(treatment.getArrivalDriver());

            if(drivers.remove(treatment.getDepartureDriver()))
                notTotallyFreeDrivers.add(treatment.getDepartureDriver());
        }
        if(!drivers.isEmpty()){
            return new DriverWorkInfoDTO(drivers.get(0), 0);
        }

        Driver driverWithMinWorkThatDay = notTotallyFreeDrivers.remove(0);
        int numOfWorkOfMinDriver = userTreatmentInfoRepository.countAllInfoForDriverOnDate(sqlDate, driverWithMinWorkThatDay);

        for(Driver driver : notTotallyFreeDrivers) {
            int workThatDay = userTreatmentInfoRepository.countAllInfoForDriverOnDate(sqlDate, driver);

            if(workThatDay < numOfWorkOfMinDriver) {
                driverWithMinWorkThatDay = driver;
                numOfWorkOfMinDriver = workThatDay;
            }
        }

        if(numOfWorkOfMinDriver < 4)
            return new DriverWorkInfoDTO(driverWithMinWorkThatDay, numOfWorkOfMinDriver);
        else
            return null;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        return null;
    }

    @Override
    public Driver updateDriver(Long id, CreateDriverDTO dto) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Driver with id '" + id + "' does not exist."));

        if(dto.getFirstName() != null) {
            Assert.isTrue(dto.getFirstName().length() <= 16, "Name must not be longer than 16 characters.");
            driver.setName(dto.getFirstName());
        }

        if(dto.getLastName() != null) {
            Assert.isTrue(dto.getLastName().length() <= 16, "Surname must not be longer than 16 characters.");
            driver.setSurname(dto.getLastName());
        }

        if(dto.getEmail() != null) {
            Assert.isTrue(dto.getEmail().length() <= 64, "Email must not be longer than 64 characters.");
            Assert.isTrue(dto.getEmail().matches(Error404BeApplication.EMAIL_FORMAT), "Email must be in valid format.");
            driver.setEmail(dto.getEmail());
        }

        if(dto.getPhoneNumber() != null) {
            String phoneNumber = dto.getPhoneNumber().replaceAll(" ", "");
            Assert.isTrue(phoneNumber.matches(Error404BeApplication.PHONE_NUMBER_FORMAT), "Phone number must be in valid format.");
            driver.setPhoneNumber(phoneNumber);
        }

        if(dto.getVehicleReg() != null) {
            Assert.isTrue(dto.getVehicleReg().matches(Error404BeApplication.REGISTRATION_FORMAT), "Vehicle registration must be in valid format.");
            Optional<Vehicle> vehicle = vehicleService.findByRegistration(dto.getVehicleReg());
            if (vehicle.isEmpty()) {
                throw new ItemNotFoundException("Vehicle with registration '" + dto.getVehicleReg() + "' does not exist.");
            }
            driver.setVehicle(vehicle.get());
        }

        if(dto.getWorkStartTime() != null) {
            Assert.isTrue(dto.getWorkStartTime().matches(Error404BeApplication.TIME_FORMAT), "Work start time must be in valid format(HH:mm).");
            Time time = Time.valueOf(dto.getWorkStartTime() + ":00");
            driver.setWorkStartTime(time);
        }

        if(dto.getWorkingDays() != null) {
            Assert.isTrue(dto.getWorkingDays().matches(Error404BeApplication.WORKING_DAYS_FORMAT), "Working days must be in valid format(NPUSCEB).");
            driver.setWorkingDays(dto.getWorkingDays());
        }

        return driverRepository.saveAndFlush(driver);
    }

    @Override
    public Driver createDriver(CreateDriverWithVehicleDTO driver) {
        Vehicle vehicle = driver.getVehicle();

        Assert.notNull(vehicle, "Vehicle must be set.");
        Assert.isTrue(vehicle.getRegistration().matches(Error404BeApplication.REGISTRATION_FORMAT), "Vehicle registration must be in valid format.");

        Optional<Vehicle> vehicleOptional = vehicleService.findByRegistration(vehicle.getRegistration());

        if (vehicleOptional.isPresent()) {
            logger.info("Vehicle with registration '" + vehicle.getRegistration() + "' already exists, using it.");
        }else{
            logger.info("Vehicle with registration '" + vehicle.getRegistration() + "' does not exist, creating new one.");
            vehicle = vehicleService.createVehicle(vehicle.getRegistration(), vehicle.getModel(), vehicle.getColor(), vehicle.getCapacity());
        }

        return createDriver(driver.getFirstName(), driver.getLastName(), driver.getEmail(), driver.getPhoneNumber(), vehicle.getRegistration(), driver.getWorkStartTime(), driver.getWorkingDays());
    }

    private boolean driverCanBeCreated(String name, String surname, String email, String phoneNumber) {
        Optional<Driver> driver = driverRepository.findDriverByNameAndSurnameAndPhoneNumber(name, surname, phoneNumber);

        Assert.isTrue(driver.isEmpty(), "Driver with name '" + name + "' and surname '" + surname + "' and phone number '" + phoneNumber + "' already exists.");

        driver = driverRepository.findDriverByNameAndSurnameAndEmail(name, surname, email);

        Assert.isTrue(driver.isEmpty(), "Driver with name '" + name + "' and surname '" + surname + "' and email '" + email + "' already exists.");

        driver = driverRepository.findDriverByPhoneNumber(phoneNumber);

        Assert.isTrue(driver.isEmpty(), "Driver with phone number '" + phoneNumber + "' already exists.");

        driver = driverRepository.findDriverByEmail(email);

        Assert.isTrue(driver.isEmpty(), "Driver with email '" + email + "' already exists.");

        return true;
    }
}