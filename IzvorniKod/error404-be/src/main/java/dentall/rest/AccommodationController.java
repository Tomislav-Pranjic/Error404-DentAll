package dentall.rest;

import dentall.domain.Accommodation;
import dentall.domain.AccommodationType;
import dentall.rest.dto.CreateAccommodationDTO;
import dentall.service.AccommodationService;
import dentall.service.AccommodationTypeService;
import dentall.service.exceptions.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private AccommodationTypeService accommodationTypeService;

    private Logger logger = LoggerFactory.getLogger(AccommodationController.class);

    @GetMapping("")
    public List<Accommodation> listAccomodation(){
        return accommodationService.listAll();
    }

    @GetMapping("/{id}")
    public Accommodation getAccommodation(@PathVariable Long id){
        return accommodationService.getAccommodation(id);
    }

    @PostMapping("")
    public Accommodation createAccommodation(@RequestBody CreateAccommodationDTO dto){
        logger.debug(dto.toString());
        return accommodationService.createAccommodation(
                dto.getTypeId(),
                dto.getStars(),
                dto.getAddressId(),
                dto.getOwnership(),
                dto.getAvailableUntil(),
                dto.getNoOfBeds()
        );
    }

    @PatchMapping("/{id}")
    public Accommodation updateAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDTO dto){

        return accommodationService.updateAccommodation(
                id,
                dto.getTypeId(),
                dto.getStars(),
                dto.getAddressId(),
                dto.getOwnership(),
                dto.getAvailableUntil(),
                dto.getNoOfBeds()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAccommodation(@PathVariable Long id){
        accommodationService.deleteAccommodation(id);
    }

    @GetMapping("/type")
    public List<AccommodationType> listAccommodationTypes() {
        return accommodationTypeService.listAll();
    }

    @GetMapping("/type/{id}")
    public AccommodationType getAccommodationType(@PathVariable Long id) {
        return accommodationTypeService.findById(id).orElseThrow(() -> new ItemNotFoundException("Accommodation type with id '" + id + "' not found"));
    }

    @PostMapping("/type")
    public AccommodationType createAccommodationType(@RequestBody AccommodationType accommodationType) {
        return accommodationTypeService.createAccommodationType(accommodationType.getTypeName(), accommodationType.getTypeSize());
    }

    @PatchMapping("/type/{id}")
    public AccommodationType updateAccommodationType(@PathVariable Long id,
                                                     @RequestBody AccommodationType accommodationType) {
        return accommodationTypeService.updateAccommodationType(id, accommodationType.getTypeName(), accommodationType.getTypeSize());
    }
}
