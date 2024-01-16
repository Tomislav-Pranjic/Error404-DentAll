package dentall.service.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dentall.oldapi.treatment")
public class SchedulingDataProperties {

    private final Logger logger = LoggerFactory.getLogger(SchedulingDataProperties.class);

    private String lastDate;

    public String getLastDate() {
        logger.info("Getting lastDate: " + lastDate);
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        logger.info("Setting lastDate to: " + lastDate);
        this.lastDate = lastDate;
    }
}
