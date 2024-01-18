package dentall.service.impl;

import dentall.domain.UserTreatmentInfo;

import dentall.service.EmailService;
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


    @Override
    public String sendEmailToMedUser(UserTreatmentInfo treatmentInfo) {

        try {

            // stvaranje maila
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(sender); // postavljeno ovako da mozemo slati na mailove koji zapravo postoje
            //mailMessage.setTo(details.getTreatmentInfo().getMedUser().getEmail()); // nepostojeci e-mail
            mailMessage.setSubject("Details about the transport");

            StringBuilder driverDetails = new StringBuilder();
            driverDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            driverDetails.append("Arrival Driver: ").append(treatmentInfo.getArrivalDriver().toString()).append("\n");
            driverDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            driverDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            driverDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            driverDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");

            mailMessage.setText(driverDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "E-mail successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while sending e-mail" + e.getMessage();
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
            userDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            userDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            userDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            userDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            userDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            mailMessageArrival.setText(userDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessageArrival);

            return "E-mail successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while sending e-mail" + e.getMessage();
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
            userDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            userDetails.append("Departure Date: ").append(treatmentInfo.getDepartureDate()).append("\n");
            userDetails.append("Departure Address: ").append(treatmentInfo.getDepartureAddress()).append("\n");
            userDetails.append("Departure Time: ").append(treatmentInfo.getDepartureTime()).append("\n");
            userDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            mailMessageDeparture.setText(userDetails.toString());

            return "E-mail successfully sent";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while sending e-mail" + e.getMessage();
        }
    }
}