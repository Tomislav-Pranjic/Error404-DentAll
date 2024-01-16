package dentall.service.connection;

import dentall.Error404BeApplication;
import dentall.domain.MedUser;
import dentall.service.MedUserService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoFromAPI {
    private final Logger logger = LoggerFactory.getLogger(UserInfoFromAPI.class);

    @Autowired
    private MedUserService medUserService;

    URL url;

    {
        try {
            url = new URL("https://dentall-test-api.onrender.com/api/user");
        } catch (MalformedURLException e) {
            logger.error("Error while creating URL object: " + e.getMessage());
        }
    }

    public MedUser getResponse(Long id) throws IllegalArgumentException{
        HttpURLConnection con;

        try {
            URL connectionUrl = new URL(url + "/" + id);

            con = (HttpURLConnection) connectionUrl.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                logger.error("Error while getting response from API: " + responseCode);
                throw new IllegalArgumentException("Error while getting response from API.");
            }

            String response = responseToString(con, logger);

            MedUser medUser = getMedUser(id, response);

            return medUserService.createMedUser(medUser);

        } catch (Exception e) {
            logger.error("Error while opening connection: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    private static MedUser getMedUser(Long id, String response) throws JSONException {
        MedUser medUser = new MedUser();

        JSONObject jsonObject = new JSONObject(response);

        medUser.setRemoteUserId(id);
        medUser.setName(jsonObject.getString("name"));
        medUser.setSurname(jsonObject.getString("surname"));
        medUser.setEmail(jsonObject.getString("email"));
        medUser.setPhoneNumber(jsonObject.getString("phoneNumber"));
        String dob = jsonObject.getString("birthDate");

        DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

        Date sqlDateOfBirth;

        try {
            sqlDateOfBirth = new Date(df.parse(dob).getTime());

        } catch (Exception e) {
            throw new IllegalArgumentException("Error while getting response from API." + e.getMessage());
        }

        medUser.setDateOfBirth(sqlDateOfBirth);
        return medUser;
    }

    static String responseToString(HttpURLConnection con, Logger logger) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuilder response = new StringBuilder();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        if(response.toString().equals("[]")){
            logger.info("No users");
            return "[]";
        }

        logger.info("Response: " + response);
        return response.toString();
    }
}