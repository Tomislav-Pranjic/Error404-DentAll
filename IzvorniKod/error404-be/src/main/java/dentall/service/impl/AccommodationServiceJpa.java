package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.AccommodationRepository;
import dentall.domain.*;
import dentall.service.AccommodationService;
import dentall.service.AccommodationTypeService;
import dentall.service.AddressService;
import dentall.service.UserTreatmentInfoService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccommodationServiceJpa implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepo;

    @Autowired
    private AccommodationTypeService accTypeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserTreatmentInfoService userTreatmentInfoService;

    @Override
    public List<Accommodation> listAll(){
        return accommodationRepo.findAll();
    }

    @Override
    public Accommodation getAccommodation(Long id) {
        return accommodationRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Accommodation with id '" + id + "' does not exist."));
    }

    @Override
    public Accommodation createAccommodation(Long typeId, Integer stars, Long addressId, String owner, String availableUntil, Integer noOfBeds) {
        Accommodation accommodation = new Accommodation();

        Optional<AccommodationType> type = accTypeService.findById(typeId);
        if(type.isEmpty()){
            throw new ItemNotFoundException("Accommodation type with id '" + typeId + "' does not exist.");
        }

        accommodation.setApartmentType(type.get());

        Assert.isTrue(((stars >= 1) && (stars <= 5)), "Stars must be between 1 and 5.");
        accommodation.setNoOfStars(stars);

        Optional<Address> address = addressService.findById(addressId);

        if(address.isEmpty()){
            throw new ItemNotFoundException("Address with id '" + addressId + "' does not exist.");
        }

        accommodation.setAddress(address.get());

        Assert.notNull(owner, "Ownership must be defined.");
        Assert.isTrue(owner.matches("True|False"), "Ownership must be either 'True' or 'False'.");

        accommodation.setOwner(owner.equals("True"));

        if(!accommodation.getOwner()){
            Assert.hasText(availableUntil, "Available until must be defined.");

            DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

            try {
                Date date = new Date(df.parse(availableUntil).getTime());
                accommodation.setAvailableUntil(date);

            } catch (Exception e) {
                throw new IllegalArgumentException("Available until must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
            }
        }

        Assert.isTrue((noOfBeds > 0), "Number of beds must be greater than 0.");
        accommodation.setNoOfBeds(noOfBeds);

        return accommodationRepo.save(accommodation);
    }

    @Override
    public Accommodation updateAccommodation(Long id, Long typeId, Integer stars, Long addressId, String owner, String availableUntil, Integer noOfBeds) {
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Accommodation with id '" + id + "' does not exist."));

        if(typeId != null){
            Optional<AccommodationType> type = accTypeService.findById(typeId);
            if(type.isEmpty()){
                throw new ItemNotFoundException("Accommodation type with id '" + typeId + "' does not exist.");
            }

            accommodation.setApartmentType(type.get());
        }

        if(stars != null){
            Assert.isTrue(((stars >= 1) && (stars <= 5)), "Stars must be between 1 and 5.");
            accommodation.setNoOfStars(stars);
        }

        if(addressId != null){
            Optional<Address> address = addressService.findById(addressId);

            if(address.isEmpty()){
                throw new ItemNotFoundException("Address with id '" + addressId + "' does not exist.");
            }

            accommodation.setAddress(address.get());
        }

        if(owner != null){
            Assert.isTrue(owner.matches("True|False"), "Ownership must be either 'True' or 'False'.");

            accommodation.setOwner(owner.equals("True"));
        }

        if(availableUntil != null){
            Assert.hasText(availableUntil, "Available until must be defined.");

            DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

            try {
                Date date = new Date(df.parse(availableUntil).getTime());
                accommodation.setAvailableUntil(date);

            } catch (Exception e) {
                throw new IllegalArgumentException("Available until must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
            }
        }

        if(noOfBeds != null){
            Assert.isTrue((noOfBeds > 0), "Number of beds must be greater than 0.");
            accommodation.setNoOfBeds(noOfBeds);
        }

        return accommodationRepo.save(accommodation);
    }

    @Override
    public Accommodation getAccommodationForUserBetweenDates(MedUser user, Date arrivalDate, Date departureDate) {
        AccommodationType preferance = user.getAccommodationPreference();

        Accommodation freeAccommodation;

        if(preferance != null) {
            List<Accommodation> preferedAccommodations = accommodationRepo.findAllByApartmentTypeOrderByNoOfStarsDesc(preferance);

            freeAccommodation = checkForFreeAccommodation(arrivalDate, departureDate, preferedAccommodations);
            if (freeAccommodation != null) return freeAccommodation;
        }
        // Ako smo ovdje onda nije pronađen smještaj koji odgovara korisnikovim preferencama
        // pa se traži bilo koji slobodan smještaj. Pretraživat ćemo ih od onog s najviše zvjezdica prema dolje.

        List<Accommodation> allAccommodations = accommodationRepo.findAllByOrderByNoOfStarsDesc();

        freeAccommodation = checkForFreeAccommodation(arrivalDate, departureDate, allAccommodations);

        return freeAccommodation;
    }

    @Override
    public void deleteAccommodation(Long id) {
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Accommodation with id '" + id + "' does not exist."));

        accommodationRepo.delete(accommodation);
    }

    private Accommodation checkForFreeAccommodation(Date arrivalDate, Date departureDate, List<Accommodation> accommodationList) {
        for(Accommodation accommodation : accommodationList) {
            if (accommodation.getAvailableUntil() != null) {
                if (accommodation.getAvailableUntil().after(departureDate)) {
                    if(userTreatmentInfoService.isAccommodationFreeBetweenDates(accommodation, arrivalDate, departureDate)){
                        return accommodation;
                    }
                }
            }else{
                if(userTreatmentInfoService.isAccommodationFreeBetweenDates(accommodation, arrivalDate, departureDate)){
                    return accommodation;
                }
            }
        }
        return null;
    }
}
