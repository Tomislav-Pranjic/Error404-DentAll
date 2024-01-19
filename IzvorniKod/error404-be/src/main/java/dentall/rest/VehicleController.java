package dentall.rest;

import dentall.domain.Vehicle;
import dentall.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@PreAuthorize("hasRole('ROLE_PRIJEVOZNI')")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("")
    public List<Vehicle> listVehicles(){
        return vehicleService.listAll();
    }

    @PostMapping("")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(
                vehicle.getRegistration(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getCapacity());
    }

    @PatchMapping("")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }
}
