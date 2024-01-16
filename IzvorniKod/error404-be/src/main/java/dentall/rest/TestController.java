package dentall.rest;

import dentall.domain.UserTreatmentInfo;
import dentall.service.OldAPIConnectionService;
import dentall.service.UserTreatmentInfoService;
import dentall.service.connection.TreatmentsFromAPI;
import dentall.service.properties.SchedulingDataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private OldAPIConnectionService oldAPIConn;

    @Autowired
    private SchedulingDataProperties schedulingData;

    @Autowired
    private UserTreatmentInfoService userTreatmentInfoService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("/tretman/{date}")
    public String tretman(@PathVariable("date") String date){
        TreatmentsFromAPI t = new TreatmentsFromAPI();

        return t.getResponse(date);
    }

    @GetMapping("/tretman/startTask")
    public String startTask(){
        oldAPIConn.getNewTreatmentsSince(schedulingData.getLastDate());
        return "Task started!";
    }

    @GetMapping("/tretman/missingInfo")
    public List<UserTreatmentInfo> missingInfo(){
        return userTreatmentInfoService.findAllByMissingArrivalAndDepartureInfo();
    }
}
