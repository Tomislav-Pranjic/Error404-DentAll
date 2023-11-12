package dentall.rest;

import dentall.domain.Accomodation;
import dentall.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accomodation")
public class AccomodationController {

    @Autowired
    private AccomodationService accomodationService;

    @GetMapping("")
    public List<Accomodation> listAccomodation(){
        return accomodationService.listAll();
    }
}
