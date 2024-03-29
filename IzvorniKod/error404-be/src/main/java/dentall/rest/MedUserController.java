package dentall.rest;

import dentall.domain.MedUser;
import dentall.rest.dto.CreateMedUserDTO;
import dentall.service.MedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_KORISNICKI') or hasRole('ROLE_SMJESTAJNI')")
public class MedUserController {

    @Autowired
    private MedUserService medUserService;

    @GetMapping("")
    public List<MedUser> listUsers(){
        return medUserService.listAll();
    }

    @PostMapping("")
    public MedUser createMedUser(@RequestBody CreateMedUserDTO dto){
        return medUserService.createMedUser(
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getEmail(),
                        dto.getPhoneNumber(),
                        dto.getAccTypePrefId(),
                        dto.getDateOfBirth());
    }

    @PatchMapping("/{id}")
    public MedUser updateMedUser(@PathVariable("id") Long id, @RequestBody CreateMedUserDTO dto){
        return medUserService.updateMedUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedUser(@PathVariable("id") Long id){
        medUserService.deleteMedUser(id);
    }

}
