package dentall.rest;

import dentall.domain.Driver;
import dentall.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    private DriverService driverService;

    @GetMapping("")
    public List<Driver> listTransport(){
        return driverService.listAll();
    }

}
