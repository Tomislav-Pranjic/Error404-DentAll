package dentall.rest;

import dentall.service.OldAPIConnectionService;
import dentall.service.connection.TreatmentsFromAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private OldAPIConnectionService oldAPIConn;

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
        oldAPIConn.getNewTreatmentsSince("2020-01-01-00-00-00");
        return "Task started!";
    }
}
