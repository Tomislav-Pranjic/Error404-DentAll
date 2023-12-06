package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.AccommodationRepository;
import dentall.domain.Accommodation;
import dentall.domain.AccommodationType;
import dentall.domain.Address;
import dentall.service.AccommodationService;
import dentall.service.AccommodationTypeService;
import dentall.service.AddressService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceJpa implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepo;

    @Autowired
    private AccommodationTypeService accTypeService;

    @Autowired
    private AddressService addressService;

    @Override
    public List<Accommodation> listAll(){
        return accommodationRepo.findAll();
    }

    @Override
    public Accommodation createAccommodation(Long typeId, Integer stars, Long addressId, Boolean owner, String availableUntil) {
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
        accommodation.setOwner(owner);

        if(!owner){
            Assert.hasText(availableUntil, "Available until must be defined.");

            DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

            try {
                Date date = new Date(df.parse(availableUntil).getTime());
                accommodation.setAvailableUntil(date);

            } catch (Exception e) {
                throw new IllegalArgumentException("Available until must be in format '"+ Error404BeApplication.DATE_FORMAT +"'.");
            }
        }

        return accommodationRepo.save(accommodation);
    }
}
