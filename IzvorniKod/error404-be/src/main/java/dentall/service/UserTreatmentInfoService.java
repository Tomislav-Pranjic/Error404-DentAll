package dentall.service;

import dentall.domain.UserTreatmentInfo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface UserTreatmentInfoService {
    List<UserTreatmentInfo> listAll();

    UserTreatmentInfo createTreatment(UserTreatmentInfo treatment);

    int countTreatmentsWithUserAndDate(Long userId, Date date);

    List<UserTreatmentInfo> findAllByMissingArrivalAndDepartureInfo();

    List<UserTreatmentInfo> findAllByMissingAccommodationAndDriverInfo();
}
