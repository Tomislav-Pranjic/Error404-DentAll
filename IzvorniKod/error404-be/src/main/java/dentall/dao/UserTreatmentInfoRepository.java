package dentall.dao;

import dentall.domain.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface UserTreatmentInfoRepository extends JpaRepository<UserTreatmentInfo, Long>{
    int countUserTreatmentInfosByMedUserAndTreatmentDate(@NotNull MedUser medUser, @NotNull Date treatmentDate);

    List<UserTreatmentInfo> findAllByArrivalAddressOrArrivalDateOrDepartureAddressOrDepartureDate(Address arrivalAddress, Date arrivalDate, Address departureAddress, Date departureDate);

    List<UserTreatmentInfo> findAllByAccommodationAndArrivalDriverAndDepartureDriver(Accommodation accommodation, Driver arrivalDriver, Driver departureDriver);

    List<UserTreatmentInfo> findAllByArrivalDateOrDepartureDateOrTreatmentDate(Date arrivalDate, Date departureDate, Date treatmentDate);

    List<UserTreatmentInfo> findAllByArrivalDateOrDepartureDateOrTreatmentDateAndArrivalDriverOrDepartureDriver(Date arrivalDate, Date departureDate, Date treatmentDate, Driver arrivalDriver, Driver departureDriver);

    int countAllByArrivalDateOrDepartureDateOrTreatmentDateAndArrivalDriverOrDepartureDriver(Date arrivalDate, Date departureDate, Date treatmentDate, Driver arrivalDriver, Driver departureDriver);
}
