package dentall.rest;

import dentall.domain.Accommodation;
import dentall.rest.dto.CreateAccommodationDTO;
import dentall.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodation")
@PreAuthorize("hasRole('ROLE_SMJESTAJNI')")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("")
    public List<Accommodation> listAccomodation(){
        return accommodationService.listAll();
    }

    @PostMapping("")
    public Accommodation createAccommodation(@RequestBody CreateAccommodationDTO dto){
        return accommodationService.createAccommodation(
                dto.getTypeId(),
                dto.getStars(),
                dto.getAddressId(),
                dto.getOwner(),
                dto.getAvailableUntil()
        );
    }
}
