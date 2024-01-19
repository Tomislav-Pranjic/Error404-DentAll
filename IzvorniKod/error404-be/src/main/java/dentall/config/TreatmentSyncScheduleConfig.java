package dentall.config;

import dentall.service.OldAPIConnectionService;
import dentall.service.properties.SchedulingDataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TreatmentSyncScheduleConfig {

    @Autowired
    private OldAPIConnectionService connService;

    @Autowired
    private SchedulingDataProperties schedulingData;

    @Scheduled(fixedDelay = 1000 * 60)
    public void scheduledFixedDelayTreatmentSync(){
        connService.getNewTreatmentsSince(schedulingData.getLastDate());

        connService.treatmentMatchmaking();
    }
}
