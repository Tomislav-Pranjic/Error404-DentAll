package dentall.config;

import dentall.Error404BeApplication;
import dentall.dao.UserTreatmentInfoRepository;
import dentall.domain.UserTreatmentInfo;
import dentall.service.UserTreatmentInfoService;
import dentall.service.properties.SchedulingDataProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class InitialConfig {

    @Autowired
    private UserTreatmentInfoRepository userTreatmentInfoRepository;

    @Autowired
    private SchedulingDataProperties schedulingData;

    private final Logger logger = LoggerFactory.getLogger(InitialConfig.class);

    @PostConstruct
    public void initTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
    }

    @PostConstruct
    public void initDateOfLastTreatmentFetched(){
        UserTreatmentInfo uti = userTreatmentInfoRepository.findFirstByOrderByLockDateTimeDesc();
        logger.info("Loading last date of treatment fetched");

        if(uti != null){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            schedulingData.setLastDate(df.format(uti.getLockDateTime()));
        }
    }
}
