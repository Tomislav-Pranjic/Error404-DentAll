package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.MedUserRepository;
import dentall.domain.AccommodationType;
import dentall.domain.Address;
import dentall.domain.MedUser;
import dentall.service.AccommodationTypeService;
import dentall.service.AddressService;
import dentall.service.MedUserService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class MedUserServiceJpa implements MedUserService {

    @Autowired
    private MedUserRepository medUserRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AccommodationTypeService accTypeService;

    @Override
    public List<MedUser> listAll(){
        return medUserRepository.findAll();
    }

    @Override
    public MedUser createMedUser(String firstName, String lastName, String email, String phoneNumber, Long accTypePreferenceId, String dateOfBirth) {
        Assert.hasText(firstName, "First name must be provided.");
        Assert.isTrue(firstName.length() <= 32, "First name must not be longer than 32 characters.");

        Assert.hasText(lastName, "Last name must be provided.");
        Assert.isTrue(lastName.length() <= 32, "Last name must not be longer than 32 characters.");

        Assert.hasText(email, "Email must be provided.");
        Assert.isTrue(email.length() <= 64, "Email must not be longer than 64 characters.");
        Assert.isTrue(email.matches(Error404BeApplication.EMAIL_FORMAT), "Email must be in valid format.");

        Assert.hasText(phoneNumber, "Phone number must be provided.");
        phoneNumber = phoneNumber.replaceAll(" ", "");
        Assert.isTrue(phoneNumber.matches(Error404BeApplication.PHONE_NUMBER_FORMAT), "Phone number must be in valid format.");

//        Assert.hasText(arrivalDate, "Arrival date must be provided.");
//        DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);
//
//        Date sqlArrivalDate;
//
//        try {
//            sqlArrivalDate = new Date(df.parse(arrivalDate).getTime());
//
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Available until must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
//        }
//
//        Assert.notNull(arrivalAddressId, "Arrival address must be provided.");
//
//        Optional<Address> arrivalAddress = addressService.findById(arrivalAddressId);
//        if(arrivalAddress.isEmpty()){
//            throw new ItemNotFoundException("Address with id '" + arrivalAddressId + "' does not exist.");
//        }
//
//        Assert.hasText(departureDate, "Departure date must be provided.");
//
//        Date sqlDepartureDate;
//
//        try {
//            sqlDepartureDate = new Date(df.parse(departureDate).getTime());
//
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Available until must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
//        }
//
//        Assert.notNull(departureAddressId, "Departure address must be provided.");
//
//        Optional<Address> departureAddress = addressService.findById(departureAddressId);
//        if(departureAddress.isEmpty()){
//            throw new ItemNotFoundException("Address with id '" + departureAddressId + "' does not exist.");
//        }

        Assert.notNull(accTypePreferenceId, "Accommodation type preference must be provided.");

        Optional<AccommodationType> typePreference = accTypeService.findById(accTypePreferenceId);
        if(typePreference.isEmpty()){
            throw new ItemNotFoundException("Accommodation type with id '" + accTypePreferenceId + "' does not exist.");
        }

        Assert.hasText(dateOfBirth, "Date of birth must be provided.");
        DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

        Date sqlDateOfBirth;

        try {
            sqlDateOfBirth = new Date(df.parse(dateOfBirth).getTime());

        } catch (Exception e) {
            throw new IllegalArgumentException("Date of birth must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
        }


        MedUser user = new MedUser(firstName, lastName, email, phoneNumber, typePreference.get(), sqlDateOfBirth);
        return medUserRepository.save(user);
    }

    @Override
    public Optional<MedUser> getMedUserByRemoteId(Long remoteId) {
        return medUserRepository.findByRemoteUserId(remoteId);
    }

    @Override
    public MedUser createMedUser(MedUser medUser) {
        return medUserRepository.save(medUser);
    }

}
