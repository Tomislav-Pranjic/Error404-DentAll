package dentall.rest;

import dentall.domain.Driver;
import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.CreateDriverWithVehicleDTO;
import dentall.service.DriverService;
import dentall.service.OldAPIConnectionService;
import dentall.service.UserTreatmentInfoService;
import dentall.service.connection.TreatmentsFromAPI;
import dentall.service.properties.SchedulingDataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private OldAPIConnectionService oldAPIConn;

    @Autowired
    private SchedulingDataProperties schedulingData;

    @GetMapping("/tretman/fetch")
    public String startTask(){
        oldAPIConn.getNewTreatmentsSince(schedulingData.getLastDate());
        return "Fetch completed.";
    }

    @GetMapping("/tretman/matchmaking")
    public String matchmaking(){
        oldAPIConn.treatmentMatchmaking();
        return "Matchmaking completed.";
    }
}
