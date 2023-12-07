package dentall.service;

import dentall.domain.Accommodation;
import dentall.domain.Admin;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> listAll();

    Accommodation getAccommodation(Long id);

    Accommodation createAccommodation(Long typeId, Integer stars, Long addressId, String owner, String availableUntil, Integer noOfBeds);

    Accommodation updateAccommodation(Long id, Long typeId, Integer stars, Long addressId, String owner, String availableUntil, Integer noOfBeds);
}
