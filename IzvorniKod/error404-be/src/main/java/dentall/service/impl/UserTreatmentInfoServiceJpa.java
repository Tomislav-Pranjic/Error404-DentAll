package dentall.service.impl;

import dentall.dao.UserTreatmentInfoRepository;
import dentall.domain.UserTreatmentInfo;
import dentall.service.UserTreatmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTreatmentInfoServiceJpa implements UserTreatmentInfoService {

    @Autowired
    private UserTreatmentInfoRepository treatmentInfoRepository;

    @Override
    public List<UserTreatmentInfo> listAll() {
        return treatmentInfoRepository.findAll();
    }
}
