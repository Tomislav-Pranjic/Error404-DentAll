package dentall.service.impl;

import dentall.domain.Driver;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.CreateEmailForDriverDTO;
import dentall.rest.dto.CreateEmailForMedUserDTO;

import dentall.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceJpa implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmailToMedUser(CreateEmailForMedUserDTO details) {

        try {

            // stvaranje maila
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());

            StringBuilder driverDetails = new StringBuilder();
            UserTreatmentInfo treatmentInfo = details.getTreatmentInfo();
            driverDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            driverDetails.append("Departure Date: ").append(treatmentInfo.getDepartureDate()).append("\n");
            driverDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            driverDetails.append("Departure Address: ").append(treatmentInfo.getDepartureAddress()).append("\n");
            driverDetails.append("Arrival Driver: ").append(treatmentInfo.getArrivalDriver()).append("\n");
            driverDetails.append("Departure Driver: ").append(treatmentInfo.getDepartureDriver()).append("\n");
            driverDetails.append("Accommodation: ").append(treatmentInfo.getAccommodation()).append("\n");
            driverDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            driverDetails.append("Lock Date Time: ").append(treatmentInfo.getLockDateTime()).append("\n");
            driverDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            driverDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            driverDetails.append("Departure Time: ").append(treatmentInfo.getDepartureTime()).append("\n");
            mailMessage.setText(driverDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "E-mail uspješno poslan";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Greška pri slanju e-maila" + e.getMessage();
        }
    }

    @Override
    public String sendEmailToDriver(CreateEmailForDriverDTO details) {

        try {

            // stvaranje maila
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());

            StringBuilder userDetails = new StringBuilder();
            UserTreatmentInfo treatmentInfo = details.getTreatmentInfo();
            userDetails.append("Arrival Date: ").append(treatmentInfo.getArrivalDate()).append("\n");
            userDetails.append("Departure Date: ").append(treatmentInfo.getDepartureDate()).append("\n");
            userDetails.append("Arrival Address: ").append(treatmentInfo.getArrivalAddress()).append("\n");
            userDetails.append("Departure Address: ").append(treatmentInfo.getDepartureAddress()).append("\n");
            userDetails.append("Accommodation: ").append(treatmentInfo.getAccommodation()).append("\n");
            userDetails.append("Treatment Date: ").append(treatmentInfo.getTreatmentDate()).append("\n");
            userDetails.append("Lock Date Time: ").append(treatmentInfo.getLockDateTime()).append("\n");
            userDetails.append("Med User: ").append(treatmentInfo.getMedUser()).append("\n");
            userDetails.append("Arrival Time: ").append(treatmentInfo.getArrivalTime()).append("\n");
            userDetails.append("Departure Time: ").append(treatmentInfo.getDepartureTime()).append("\n");
            mailMessage.setText(userDetails.toString());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "E-mail uspješno poslan";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Greška pri slanju e-maila" + e.getMessage();
        }
    }

}