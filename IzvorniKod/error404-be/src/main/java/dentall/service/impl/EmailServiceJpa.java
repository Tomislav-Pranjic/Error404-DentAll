package dentall.service.impl;

import dentall.domain.UserTreatmentInfo;

import dentall.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceJpa implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final Logger logger = LoggerFactory.getLogger(EmailServiceJpa.class);

    @Override
    public String sendEmailToMedUser(UserTreatmentInfo treatmentInfo) {

        try {

            // stvaranje maila
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(sender); // postavljeno ovako da mozemo slati na mailove koji zapravo postoje
            //mailMessage.setTo(details.getTreatmentInfo().getMedUser().getEmail()); // nepostojeci e-mail
            mailMessage.setSubject("Details about the ride");

            StringBuilder driverDetails = new StringBuilder();
            driverDetails.append("Med User (person to whom this e-mail should be sent to): ").append(treatmentInfo.getMedUser()).append("\n");
            driverDetails.append("Arrival Driver: ").append(treatmentInfo.getArrivalDriver().toString()).append("\n");
            driverDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            driverDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            driverDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            driverDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");

            mailMessage.setText(driverDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessage);
            logger.info("E-mail to MedUser successfully sent");
            return "E-mail to MedUser successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            logger.error("Error while sending e-mail to MedUser: " + e.getMessage());
            return "Error while sending e-mail to MedUser" + e.getMessage();
        }
    }

    @Override
    public String sendEmailToArrivalDriver(UserTreatmentInfo treatmentInfo) {
        try {

            // stvaranje maila
            SimpleMailMessage mailMessageArrival
                    = new SimpleMailMessage();

            mailMessageArrival.setFrom(sender);
            mailMessageArrival.setTo(sender);
            //mailMessage.setTo(details.getTreatmentInfo().getArrivalDriver().getEmail());  //nepostojeci e-mailovi
            mailMessageArrival.setSubject("Details about ride");

            StringBuilder userDetails = new StringBuilder();
            userDetails.append("Arrival Driver(person to whom this e-mail should be sent to): ").append(treatmentInfo.getArrivalDriver().toString()).append("\n");
            userDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            userDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            userDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            userDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            userDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            mailMessageArrival.setText(userDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessageArrival);
            logger.info("E-mail to arrival driver successfully sent");
            return "E-mail to arrival driver successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            logger.error("Error while sending e-mail to arrival driver: " + e.getMessage());
            return "Error while sending e-mail to arrival driver" + e.getMessage();
        }
    }

    @Override
    public String sendEmailToDepartureDriver(UserTreatmentInfo treatmentInfo) {
        try {

            // stvaranje maila
            SimpleMailMessage mailMessageDeparture
                    = new SimpleMailMessage();

            mailMessageDeparture.setFrom(sender);
            mailMessageDeparture.setTo(sender);
            //mailMessage.setTo(details.getTreatmentInfo().getArrivalDriver().getEmail());  //nepostojeci e-mail
            mailMessageDeparture.setSubject("Details about ride");

            StringBuilder userDetails = new StringBuilder();
            userDetails.append("Departure Driver(person to whom this e-mail should be sent to): ").append(treatmentInfo.getDepartureDriver()).append("\n");
            userDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            userDetails.append("Departure Date: ").append(treatmentInfo.getDepartureDate()).append("\n");
            userDetails.append("Departure Address: ").append(treatmentInfo.getDepartureAddress()).append("\n");
            userDetails.append("Departure Time: ").append(treatmentInfo.getDepartureTime()).append("\n");
            userDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            mailMessageDeparture.setText(userDetails.toString());

            javaMailSender.send(mailMessageDeparture);
            logger.info("E-mail to departure driver successfully sent");
            return "E-mail to departure driver successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            logger.error("Error while sending e-mail to departure driver: " + e.getMessage());
            return "Error while sending e-mail to departure driver" + e.getMessage();
        }
    }

    @Override
    public void sendEmailToEveryoneInvolved(UserTreatmentInfo details) {
        try {
            sendEmailToMedUser(details);
            sendEmailToArrivalDriver(details);
            sendEmailToDepartureDriver(details);
        }catch (Exception e) {
            logger.error("Error while sending e-mail to everyone involved: " + e.getMessage());
        }
    }
}