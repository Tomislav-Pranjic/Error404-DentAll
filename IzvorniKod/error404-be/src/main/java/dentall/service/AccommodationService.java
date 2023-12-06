package dentall.service;

import dentall.domain.Accommodation;
import dentall.domain.Admin;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> listAll();

    Accommodation createAccommodation(Long typeId, Integer stars, Long addressId, Boolean owner, String availableUntil);
}
