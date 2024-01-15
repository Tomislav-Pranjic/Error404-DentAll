package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.service.MedUserService;
import dentall.service.OldAPIConnectionService;
import dentall.service.connection.TreatmentsFromAPI;
import dentall.service.connection.UserInfoFromAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class OldAPIConnectionServiceJpa implements OldAPIConnectionService {
    private final Logger logger = LoggerFactory.getLogger(OldAPIConnectionServiceJpa.class);

    @Autowired
    private MedUserService medUserService;

    @Override
    public void getNewTreatmentsSince(String date) {
        String responseStringFromAPI;

        TreatmentsFromAPI treatmentsFromAPI = new TreatmentsFromAPI();

        try {
            responseStringFromAPI = treatmentsFromAPI.getResponse(date);
        }catch (Exception e) {
            logger.error("Error while getting response from API: " + e.getMessage());
            return;
        }

        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(responseStringFromAPI);
        } catch (JSONException e) {
            logger.error("Error while parsing response from API: " + e.getMessage());
            return;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject singleTreatment;
            UserTreatmentInfo userTreatmentInfo = new UserTreatmentInfo();

            try {
                singleTreatment = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                logger.error("Error while parsing response from API: " + e.getMessage());
                return;
            }

            logger.info("Treatment: " + singleTreatment.toString());

            try {
                JSONObject userObjectFromTreatment = singleTreatment.getJSONObject("user");
                Long userID = userObjectFromTreatment.getLong("userId");

                Optional<MedUser> optionalMedUser = medUserService.getMedUserByRemoteId(userID);
                if(optionalMedUser.isPresent()) {
                    MedUser medUser = optionalMedUser.get();
                    userTreatmentInfo.setMedUser(medUser);
                } else {
                    logger.info("User with ID: " + userID + " not found in database, sending request to API to get user info");
                    UserInfoFromAPI userInfoFromAPI = new UserInfoFromAPI();

                    MedUser medUser = userInfoFromAPI.getResponse(userID);
                    userTreatmentInfo.setMedUser(medUser);
                }

                String treatmentDateString = singleTreatment.getString("datum");

                DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

                Date sqlTreatmentDate = new Date(df.parse(treatmentDateString).getTime());

                userTreatmentInfo.setTreatmentDate(sqlTreatmentDate);

                // TODO: set other fields

            } catch (Exception e) {
                logger.error("Error while parsing response from API: " + e.getMessage());
                return;
            }
        }
    }
}
