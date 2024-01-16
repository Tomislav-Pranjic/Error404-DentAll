package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.MedUserRepository;
import dentall.dao.UserTreatmentInfoRepository;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.service.UserTreatmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserTreatmentInfoServiceJpa implements UserTreatmentInfoService {

    @Autowired
    private UserTreatmentInfoRepository treatmentInfoRepository;

    @Autowired
    private MedUserRepository medUserRepository;

    @Override
    public List<UserTreatmentInfo> listAll() {
        return treatmentInfoRepository.findAll();
    }

    @Override
    public UserTreatmentInfo createTreatment(UserTreatmentInfo treatment) {
        return treatmentInfoRepository.save(treatment);
    }

    @Override
    public int countTreatmentsWithUserAndDate(Long userId, Date date) {
        MedUser user = medUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error while counting treatments(User not found)"));

        return treatmentInfoRepository.countUserTreatmentInfosByMedUserAndTreatmentDate(user, date);
    }

    @Override
    public List<UserTreatmentInfo> findAllByMissingArrivalAndDepartureInfo() {
        return treatmentInfoRepository.findAllByArrivalAddressOrArrivalDateOrDepartureAddressOrDepartureDate(null, null, null, null);
    }

    @Override
    public List<UserTreatmentInfo> findAllByMissingAccommodationAndDriverInfo() {
        return treatmentInfoRepository.findAllByAccommodationAndArrivalDriverAndDepartureDriver(null, null, null);
    }
}
