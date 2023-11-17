package dentall.rest;

import dentall.domain.Accommodation;
import dentall.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("")
    public List<Accommodation> listAccomodation(){
        return accommodationService.listAll();
    }
}
