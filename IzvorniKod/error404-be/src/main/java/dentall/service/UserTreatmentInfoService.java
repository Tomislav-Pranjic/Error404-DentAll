package dentall.service;

import dentall.domain.UserTreatmentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserTreatmentInfoService {
    List<UserTreatmentInfo> listAll();
}
