package dentall.service;

import dentall.domain.Accommodation;
import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.TreatmentInfoPatchDTO;

import java.sql.Date;
import java.util.List;

public interface UserTreatmentInfoService {
    List<UserTreatmentInfo> listAll();

    UserTreatmentInfo createTreatment(UserTreatmentInfo treatment);

    int countTreatmentsWithUserAndDate(Long userId, Date date);

    List<UserTreatmentInfo> findAllByMissingArrivalAndDepartureInfo();

    List<UserTreatmentInfo> findAllByMissingAccommodationAndDriverInfo();

    UserTreatmentInfo updateTreatment(UserTreatmentInfo treatment);

    boolean isAccommodationFreeBetweenDates(Accommodation accommodation, Date arrivalDate, Date departureDate);

    UserTreatmentInfo updateTreatmentInfo(Long id, TreatmentInfoPatchDTO dto);
}
