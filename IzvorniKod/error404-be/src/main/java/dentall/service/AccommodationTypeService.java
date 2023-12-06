package dentall.service;

import dentall.domain.AccommodationType;

import java.util.List;
import java.util.Optional;

public interface AccommodationTypeService {

    List<AccommodationType> listAll();

    Optional<AccommodationType> findById(Long id);

    AccommodationType createAccommodationType(String typeName, Integer typeSize, Integer typeBedNumber);
}
