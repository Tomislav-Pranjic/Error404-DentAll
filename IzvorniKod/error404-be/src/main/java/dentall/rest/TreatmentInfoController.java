package dentall.rest;

import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.TreatmentInfoPatchDTO;
import dentall.service.UserTreatmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/missingInfo")
    public List<UserTreatmentInfo> missingInfo(){
        return treatmentInfoService.findAllByMissingArrivalAndDepartureInfo();
    }

    @PatchMapping("/{id}")
    public UserTreatmentInfo updateTreatmentInfo(@RequestBody TreatmentInfoPatchDTO dto, @PathVariable("id") Long id) {
        return treatmentInfoService.updateTreatmentInfo(id, dto);
    }
}
