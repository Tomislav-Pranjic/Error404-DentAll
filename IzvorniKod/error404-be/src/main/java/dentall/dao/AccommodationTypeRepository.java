package dentall.dao;

import dentall.domain.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {

    Optional<AccommodationType> findByTypeId(Long typeId);
}
