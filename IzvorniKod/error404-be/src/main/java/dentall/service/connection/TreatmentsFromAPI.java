package dentall.service.connection;

import org.apache.commons.lang3.time.DateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

@Service
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

    public String getResponse(String after){
        HttpURLConnection con;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        try {
            new Timestamp(df.parse(after).getTime());
        } catch (Exception e) {
            logger.error("Error while parsing date: " + e.getMessage());
            throw new IllegalArgumentException("Date must be in format 'yyyy-MM-dd-HH-mm-ss'.");
        }

        try {
            URL connectionUrl = new URL(url + "?zakljucan=true&after=" + after);

            con = (HttpURLConnection) connectionUrl.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestMethod("GET");
        } catch (Exception e) {
            logger.error("Error while opening connection: " + e.getMessage());
            throw new IllegalArgumentException("Error while opening connection.");
        }


//        con.setDoOutput(true);
//        try {
//            DataOutputStream os = new DataOutputStream(con.getOutputStream());
//            os.writeChars("zakljucan=true&after=" + after);
//            os.flush();
//        } catch (IOException e) {
//            logger.error("Error while getting output stream: " + e.getMessage());
//            throw new IllegalArgumentException("Error while getting output stream.");
//        }

        try {
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
                logger.info("Response code: " + responseCode);

                String response = UserInfoFromAPI.responseToString(con, logger);
                return response.toString();

            } else {
                logger.error("Response code: " + responseCode);
                return "[]";
            }
        } catch (IOException e) {
            logger.error("Error while getting response code: " + e.getMessage());
            return "[]";
        }

    }
}
