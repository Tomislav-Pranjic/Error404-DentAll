package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.domain.Accommodation;
import dentall.domain.Driver;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.DriverWorkInfoDTO;
import dentall.service.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    @Autowired
    private DriverService driverService;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private EmailService emailService;


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

        logger.info("Finished getting new treatments");
    }

    @Override
    public void treatmentMatchmaking() {
        logger.info("Starting treatment matchmaking");

        List<UserTreatmentInfo> treatmentsToMatch = userTreatmentInfoService.findAllByMissingAccommodationAndDriverInfo();

        logger.info("Treatments to match: " + treatmentsToMatch.size());

        for(int i = 0; i < treatmentsToMatch.size(); i++){
            logger.info("i: " + i + ", size: " + treatmentsToMatch.size());
            UserTreatmentInfo treatment = treatmentsToMatch.get(i);
            logger.info("Treatment: " + treatment.toString());

            if(treatment.getArrivalDriver() == null) {
                if (treatment.getArrivalDate() != null && treatment.getArrivalAddress() != null) {

                    DriverWorkInfoDTO arrivalDriverWithWorkInfo = driverService.getFreeDriverForDate(treatment.getArrivalDate().toString());

                    if (arrivalDriverWithWorkInfo != null) {
                        Driver arrivalDriver = arrivalDriverWithWorkInfo.getDriver();
                        treatment.setArrivalDriver(arrivalDriver);

                        logger.info("Arrival driver: " + arrivalDriver.toString());

                        int numOfTrips = arrivalDriverWithWorkInfo.getNumberOfTrips();

//                        logger.info("Number of trips: " + numOfTrips);

                        java.util.Date workStartTime = new java.util.Date(arrivalDriver.getWorkStartTime().getTime());
//                        logger.info("Work start time: " + workStartTime);

                        Time tripStartTime = new Time(workStartTime.toInstant().plus(numOfTrips * 2L, ChronoUnit.HOURS).toEpochMilli());

                        treatment.setArrivalTime(tripStartTime);

                        treatmentsToMatch.set(i, treatment);
                        userTreatmentInfoService.updateTreatment(treatment);

                    } else {
                        logger.warn("No free driver for arrival date: " + treatment.getArrivalDate().toString());
                    }
                } else {
                    logger.warn("Couldn't assign arrival driver, arrival date or address is null for treatment: " + treatment);
                }
            }

            treatment = treatmentsToMatch.get(i);

            if(treatment.getDepartureDriver() == null) {
                if (treatment.getDepartureDate() != null && treatment.getDepartureAddress() != null) {
                    DriverWorkInfoDTO departureDriverWithWorkInfo = driverService.getFreeDriverForDate(treatment.getDepartureDate().toString());

                    if (departureDriverWithWorkInfo != null) {
                        Driver departureDriver = departureDriverWithWorkInfo.getDriver();
                        treatment.setDepartureDriver(departureDriver);

                        logger.info("Departure driver: " + departureDriver.toString());

                        int numOfTrips = departureDriverWithWorkInfo.getNumberOfTrips();

                        java.util.Date workStartTime = new java.util.Date(departureDriver.getWorkStartTime().getTime());
                        Time tripStartTime = new Time(workStartTime.toInstant().plus(numOfTrips * 2L, ChronoUnit.HOURS).toEpochMilli());

                        treatment.setDepartureTime(tripStartTime);

                        treatmentsToMatch.set(i, treatment);
                        userTreatmentInfoService.updateTreatment(treatment);

                    } else {
                        logger.warn("No free driver for departure date: " + treatment.getDepartureDate().toString());
                    }
                } else {
                    logger.warn("Couldn't assign departure driver, departure date or address is null for treatment: " + treatment);
                }
            }

            treatment = treatmentsToMatch.get(i);

            if(treatment.getAccommodation() == null) {
                if (treatment.getDepartureDate() != null && treatment.getArrivalDate() != null) {
//                logger.info("Arrival date: " + treatment.getArrivalDate().toString() + " Departure date: " + treatment.getDepartureDate().toString());
                    Accommodation accommodation = accommodationService.getAccommodationForUserBetweenDates(treatment.getMedUser(), treatment.getArrivalDate(), treatment.getDepartureDate());

                    if (accommodation != null) {
                        treatment.setAccommodation(accommodation);

                        logger.info("Accommodation: " + accommodation);

                        treatmentsToMatch.set(i, treatment);
                        userTreatmentInfoService.updateTreatment(treatment);
                    } else {
                        logger.warn("No free accommodation for user: " + treatment.getMedUser().getLocalUserId() + " between dates: " + treatment.getArrivalDate().toString() + " and " + treatment.getDepartureDate().toString());
                    }
                } else {
                    logger.warn("Couldn't assign accommodation, arrival date or departure date are null for treatment: " + treatment);
                }
            }

            treatment = treatmentsToMatch.get(i);
            if(treatment.getArrivalDriver() != null && treatment.getDepartureDriver() != null && treatment.getAccommodation() != null) {
                logger.info("Sending email to user and drivers for treatment: " + treatment);
                emailService.sendEmailToEveryoneInvolved(treatment);
            }
        }
    }
}
