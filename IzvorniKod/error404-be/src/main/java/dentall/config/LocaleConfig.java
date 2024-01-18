package dentall.config;

import dentall.service.UserTreatmentInfoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class LocaleConfig {
    @Autowired
    private UserTreatmentInfoService userTreatmentInfoService;

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
    }
}
