package com.tiy.ballad.web;

import com.tiy.ballad.model.Email;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by josh on 5/14/17.
 */
@Component
@EnableAsync
public class EmailReset {
    @Autowired
    private EmailService emailService;

    @Async
    public void setUpEmail(User user){
        Email email = emailService.getEmail();
        Properties props = new Properties();

        System.out.println(email.toString());
        System.out.println(user.getEmail());
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        Session mSession = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email.getEmail(),email.getPassword());
            }
        });
        try{
            Message message = new MimeMessage(mSession);
            message.setFrom(new InternetAddress(email.getEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.valueOf(user.getEmail())));
            message.setSubject("Recovery Password");

            message.setContent("Recovery Password"+" \n"+"Your new password is"+"\n\n"+user.getPassword(),"text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
