package dentall.dao;

import dentall.domain.Accommodation;
import dentall.domain.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAllByApartmentTypeOrderByNoOfStarsDesc(AccommodationType type);

    List<Accommodation> findAllByOrderByNoOfStarsDesc();
}