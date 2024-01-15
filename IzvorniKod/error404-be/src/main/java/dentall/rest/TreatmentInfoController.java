package dentall.rest;

import dentall.domain.UserTreatmentInfo;
import dentall.service.UserTreatmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/treatmentInfo")
@PreAuthorize("hasRole('ROLE_KORISNICKI') or hasRole('ROLE_SMJESTAJNI')")
public class TreatmentInfoController {
    @Autowired
    private UserTreatmentInfoService treatmentInfoService;

    @GetMapping("")
    public List<UserTreatmentInfo> listAll() {
        return treatmentInfoService.listAll();
    }
}
