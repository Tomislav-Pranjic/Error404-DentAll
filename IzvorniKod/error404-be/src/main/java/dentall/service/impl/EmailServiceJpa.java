package dentall.service.impl;

import dentall.rest.dto.CreateEmailDTO;

import java.io.File;

import dentall.service.EmailService;
//import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Profile("email")
public class EmailServiceJpa implements EmailService {

//    @Autowired
//    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(CreateEmailDTO details) {

//        try {
//
//            // stvaranje maila
//            SimpleMailMessage mailMessage
//                    = new SimpleMailMessage();
//
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(details.getRecipient());
//            mailMessage.setText(details.getMsgBody());
//            mailMessage.setSubject(details.getSubject());
//
//            // Sending the mail
//            javaMailSender.send(mailMessage);
//            return "E-mail uspješno poslan";
//        }
//
//        // Catch block to handle the exceptions
//        catch (Exception e) {
//            return "Greška pri slanju e-maila";
//        }
        return "E-mail nije poslan";
    }

    @Override
    public String sendMailWithAttachment(CreateEmailDTO details) {

//        MimeMessage mimeMessage
//                = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "E-mail uspješno poslan";
//        }
//
//        // Catch block to handle MessagingException
//        catch (jakarta.mail.MessagingException e) {
//
//            // Display message when exception occurred
//            return "Greška pri slanju e-maila";
//        }
        return "E-mail nije poslan";
    }
}