package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.service.MedUserService;
import dentall.service.OldAPIConnectionService;
import dentall.service.UserTreatmentInfoService;
import dentall.service.connection.TreatmentsFromAPI;
import dentall.service.connection.UserInfoFromAPI;
import dentall.service.properties.SchedulingDataProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class OldAPIConnectionServiceJpa implements OldAPIConnectionService {
    private final Logger logger = LoggerFactory.getLogger(OldAPIConnectionServiceJpa.class);

    @Autowired
    private MedUserService medUserService;

    @Autowired
    private UserTreatmentInfoService userTreatmentInfoService;

    @Autowired
    private TreatmentsFromAPI treatmentsFromAPI;

    @Autowired
    private UserInfoFromAPI userInfoFromAPI;

    @Autowired
    private SchedulingDataProperties schedulingData;

    @Override
    public void getNewTreatmentsSince(String date) {
        logger.info("Getting new treatments since: " + date);

        String responseStringFromAPI;

        DateFormat dfForTimestampFromOldApi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dfForLocalTimestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        DateFormat dfWithLocalDateFormat = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

        Timestamp lastDate;
        try {
            lastDate = new Timestamp(dfForLocalTimestamp.parse(date).getTime());
        } catch (ParseException e) {
            logger.error("Error while parsing lastDate: " + e.getMessage());
            return;
        }

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

                logger.info("User: " + userObjectFromTreatment.toString());

                Long userID = userObjectFromTreatment.getLong("userId");

                logger.info("User ID: " + userID);

                Optional<MedUser> optionalMedUser = medUserService.getMedUserByRemoteId(userID);
                if(optionalMedUser.isPresent()) {
                    MedUser medUser = optionalMedUser.get();
                    userTreatmentInfo.setMedUser(medUser);
                } else {
                    logger.info("User with ID: " + userID + " not found in database, sending request to API to get user info");

                    MedUser medUser = userInfoFromAPI.getResponse(userID);
                    userTreatmentInfo.setMedUser(medUser);
                }

                String treatmentDateString = singleTreatment.getString("datum");

                Date sqlTreatmentDate = new Date(dfWithLocalDateFormat.parse(treatmentDateString).getTime());

                userTreatmentInfo.setTreatmentDate(sqlTreatmentDate);

                String lockDateTimeString = singleTreatment.getString("dtZakljucavanja");

                if(lockDateTimeString != null && !lockDateTimeString.equals("null")) {
                    Timestamp sqlLockDateTime;
                    lockDateTimeString = lockDateTimeString.replace("T", " ");

                    sqlLockDateTime = new Timestamp(dfForTimestampFromOldApi.parse(lockDateTimeString.substring(0, lockDateTimeString.indexOf("."))).getTime());

                    if(sqlLockDateTime.after(lastDate)){
                        schedulingData.setLastDate(dfForLocalTimestamp.format(sqlLockDateTime));
                        lastDate = new Timestamp(sqlLockDateTime.getTime());
                    }
                    userTreatmentInfo.setLockDateTime(sqlLockDateTime);
                }

                if(userTreatmentInfoService.countTreatmentsWithUserAndDate(userTreatmentInfo.getMedUser().getLocalUserId(), userTreatmentInfo.getTreatmentDate()) > 0) {
                    logger.info("Treatment with user ID: " + userTreatmentInfo.getMedUser().getLocalUserId() + " and date: " + userTreatmentInfo.getTreatmentDate() + " already exists in database");
                    continue;
                }

                userTreatmentInfoService.createTreatment(userTreatmentInfo);
            } catch (Exception e) {
                logger.error("Error while parsing response from API: " + e.getMessage());
                return;
            }
        }
    }

    @Override
    public void treatmentMatchmaking() {
        logger.info("Starting treatment matchmaking");

        List<UserTreatmentInfo> treatmentsToMatch = userTreatmentInfoService.findAllByMissingAccommodationAndDriverInfo();

        for(UserTreatmentInfo treatment : treatmentsToMatch){
            if(treatment.getArrivalDate() == null || treatment.getArrivalAddress() == null || treatment.getDepartureDate() == null || treatment.getDepartureAddress() == null) {
                continue;
            }


        }
    }
}
