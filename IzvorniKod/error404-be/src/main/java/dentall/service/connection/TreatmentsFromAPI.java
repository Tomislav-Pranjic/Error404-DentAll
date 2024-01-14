package dentall.service.connection;

import org.apache.commons.lang3.time.DateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

public class TreatmentsFromAPI {

    private final Logger logger = LoggerFactory.getLogger(TreatmentsFromAPI.class);

    URL url;

    {
        try {
            url = new URL("https://dentall-test-api.onrender.com/api/tretman/zakljucan");
        } catch (MalformedURLException e) {
            logger.error("Error while creating URL object: " + e.getMessage());
        }
    }
    // params: bool:zakljucan i String:after <- date(yyyy-MM-dd-HH-mm-ss)

    public void getResponse(String after){
        HttpURLConnection con = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp sqlAfter = null;
        try {
            sqlAfter = new Timestamp(df.parse(after).getTime());
        } catch (Exception e) {
            logger.error("Error while parsing date: " + e.getMessage());
            throw new IllegalArgumentException("Date must be in format 'yyyy-MM-dd-HH-mm-ss'.");
        }

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (Exception e) {
            logger.error("Error while opening connection: " + e.getMessage());
            throw new IllegalArgumentException("Error while opening connection.");
        }


        con.setDoOutput(true);
        try {
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeChars("zakljucan=true&after=" + after);
        } catch (IOException e) {
            logger.error("Error while getting output stream: " + e.getMessage());
            throw new IllegalArgumentException("Error while getting output stream.");
        }

    }
}
