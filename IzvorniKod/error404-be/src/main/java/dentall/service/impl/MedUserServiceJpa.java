package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.MedUserRepository;
import dentall.domain.AccommodationType;
import dentall.domain.Address;
import dentall.domain.MedUser;
import dentall.rest.MedUserController;
import dentall.rest.dto.CreateMedUserDTO;
import dentall.service.AccommodationTypeService;
import dentall.service.AddressService;
import dentall.service.MedUserService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

        Assert.isTrue(userNameSurnameAndDateOfBirthAreFree(firstName, lastName, sqlDateOfBirth), "User with name '" + firstName + "' and surname '" + lastName + "' and date of birth '" + dateOfBirth + "' already exists.");
        Assert.isTrue(emailIsFree(email), "Email is already taken.");
        Assert.isTrue(phoneNumberIsFree(phoneNumber), "Phone number is already taken.");

        MedUser user = new MedUser(firstName, lastName, email, phoneNumber, typePreference.get(), sqlDateOfBirth);
        return medUserRepository.save(user);
    }

    @Override
    public Optional<MedUser> getMedUserByRemoteId(Long remoteId) {
        return medUserRepository.findByRemoteUserId(remoteId);
    }

    @Override
    public MedUser createMedUser(MedUser medUser) {
        Assert.isTrue(userNameSurnameAndDateOfBirthAreFree(medUser.getName(), medUser.getSurname(), medUser.getDateOfBirth()), "User with name '" + medUser.getName() + "' and surname '" + medUser.getSurname() + "' and date of birth '" + medUser.getDateOfBirth() + "' already exists.");
        Assert.isTrue(emailIsFree(medUser.getEmail()), "Email is already taken.");
        Assert.isTrue(phoneNumberIsFree(medUser.getPhoneNumber()), "Phone number is already taken.");

        return medUserRepository.save(medUser);
    }

    @Override
    public MedUser updateMedUser(Long id, CreateMedUserDTO dto) {
        MedUser medUser = medUserRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Med user with id '" + id + "' does not exist."));

        if(dto.getFirstName() != null){
            Assert.hasText(dto.getFirstName(), "First name must be provided.");
            Assert.isTrue(dto.getFirstName().length() <= 32, "First name must not be longer than 32 characters.");

            Assert.isTrue(userNameSurnameAndDateOfBirthAreFree(dto.getFirstName(), medUser.getSurname(), medUser.getDateOfBirth()), "User with name '" + dto.getFirstName() + "' and surname '" + medUser.getSurname() + "' and date of birth '" + medUser.getDateOfBirth() + "' already exists.");
            medUser.setName(dto.getFirstName());
        }

        if(dto.getLastName() != null){
            Assert.hasText(dto.getLastName(), "Last name must be provided.");
            Assert.isTrue(dto.getLastName().length() <= 32, "Last name must not be longer than 32 characters.");

            Assert.isTrue(userNameSurnameAndDateOfBirthAreFree(medUser.getName(), dto.getLastName(), medUser.getDateOfBirth()), "User with name '" + medUser.getName() + "' and surname '" + dto.getLastName() + "' and date of birth '" + medUser.getDateOfBirth() + "' already exists.");
            medUser.setSurname(dto.getLastName());
        }

        if(dto.getEmail() != null){
            Assert.hasText(dto.getEmail(), "Email must be provided.");
            Assert.isTrue(dto.getEmail().length() <= 64, "Email must not be longer than 64 characters.");
            Assert.isTrue(dto.getEmail().matches(Error404BeApplication.EMAIL_FORMAT), "Email must be in valid format.");

            Assert.isTrue(emailIsFree(dto.getEmail()), "Email is already taken.");
            medUser.setEmail(dto.getEmail());
        }

        if(dto.getPhoneNumber() != null){
            Assert.hasText(dto.getPhoneNumber(), "Phone number must be provided.");
            String phoneNumber = dto.getPhoneNumber().replaceAll(" ", "");
            Assert.isTrue(phoneNumber.matches(Error404BeApplication.PHONE_NUMBER_FORMAT), "Phone number must be in valid format.");

            Assert.isTrue(phoneNumberIsFree(phoneNumber), "Phone number is already taken.");
            medUser.setPhoneNumber(phoneNumber);
        }

        if(dto.getAccTypePrefId() != null){
            AccommodationType accType = accTypeService.findById(dto.getAccTypePrefId()).orElseThrow(() -> new ItemNotFoundException("Accommodation type with id '" + dto.getAccTypePrefId() + "' does not exist."));
            medUser.setAccommodationPreference(accType);
        }

        return medUserRepository.saveAndFlush(medUser);
    }

    @Override
    public void deleteMedUser(Long id) {
        MedUser medUser = medUserRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Med user with id '" + id + "' does not exist."));

        medUserRepository.delete(medUser);
    }

    private boolean emailIsFree(String email){
        return medUserRepository.findMedUserByEmail(email).isEmpty();
    }

    private boolean phoneNumberIsFree(String phoneNumber) {
        return medUserRepository.findMedUserByPhoneNumber(phoneNumber).isEmpty();
    }

    private boolean userNameSurnameAndDateOfBirthAreFree(String name, String surname, java.util.Date dateOfBirth) {
        return medUserRepository.findByNameAndSurnameAndDateOfBirth(name, surname, dateOfBirth).isEmpty();
    }
}
