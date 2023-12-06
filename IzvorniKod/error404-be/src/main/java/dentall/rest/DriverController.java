package dentall.rest;

import dentall.domain.Driver;
import dentall.rest.dto.CreateDriverDTO;
import dentall.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@PreAuthorize("hasRole('ROLE_PRIJEVOZNI') or hasRole('ROLE_SMJESTAJNI')")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("")
    public List<Driver> listDriver(){
        return driverService.listAll();
    }

    @PostMapping("")
    public Driver createDriver(@RequestBody CreateDriverDTO dto){
        return driverService.createDriver(
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getEmail(),
                        dto.getPhoneNumber(),
                        dto.getVehicleReg(),
                        dto.getWorkStartTime(),
                        dto.getWorkingDays());
    }
}
