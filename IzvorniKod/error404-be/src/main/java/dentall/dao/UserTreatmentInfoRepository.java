package dentall.dao;

import dentall.domain.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface UserTreatmentInfoRepository extends JpaRepository<UserTreatmentInfo, Long>{
    int countUserTreatmentInfosByMedUserAndTreatmentDate(@NotNull MedUser medUser, @NotNull Date treatmentDate);

    List<UserTreatmentInfo> findAllByArrivalAddressIsNullOrArrivalDateIsNullOrDepartureAddressIsNullOrDepartureDateIsNull();

    List<UserTreatmentInfo> findAllByAccommodationIsNullAndArrivalDriverIsNullAndDepartureDriverIsNull();

    List<UserTreatmentInfo> findAllByArrivalDateOrDepartureDateOrTreatmentDate(Date arrivalDate, Date departureDate, Date treatmentDate);

    List<UserTreatmentInfo> findAllByArrivalDateOrDepartureDateOrTreatmentDateAndArrivalDriverOrDepartureDriver(Date arrivalDate, Date departureDate, Date treatmentDate, Driver arrivalDriver, Driver departureDriver);

    @Query("SELECT COUNT(uti) FROM dentall.domain.UserTreatmentInfo uti WHERE (uti.arrivalDate = ?1 OR uti.departureDate = ?1 OR uti.treatmentDate = ?1) AND (uti.arrivalDriver = ?2 OR uti.departureDriver = ?2)")
    int countAllInfoForDriverOnDate(@Param("1") Date date, @Param("2") Driver driver);

    @Query("SELECT COUNT(uti) FROM dentall.domain.UserTreatmentInfo uti WHERE uti.accommodation = ?1 AND ((uti.arrivalDate BETWEEN ?2 AND ?3) OR (uti.departureDate BETWEEN ?2 AND ?3))")
    int countAllInfoForAccommodationBetweenDates(@Param("1") Accommodation accommodation, @Param("2") Date arrivalDate, @Param("3") Date departureDate);

    @Query("SELECT uti FROM dentall.domain.UserTreatmentInfo uti WHERE uti.accommodation = ?1 AND ((uti.arrivalDate BETWEEN ?2 AND ?3) OR (uti.departureDate BETWEEN ?2 AND ?3))")
    List<UserTreatmentInfo> findAllInfoForAccommodationBetweenDates(@Param("1") Accommodation accommodation, @Param("2") Date arrivalDate, @Param("3") Date departureDate);
}
