package dentall.dao;

import dentall.domain.UserTreatmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTreatmentInfoRepository extends JpaRepository<UserTreatmentInfo, Long>{
}
