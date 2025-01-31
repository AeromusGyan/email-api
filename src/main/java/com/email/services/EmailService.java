package com.email.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    public  Boolean sendEEmail(String subject,String message, String to){
//        rest of the code
        boolean f =false;

        String from = "sciaku1@gmail.com";

//        variable for email
        String host = "smtp.gmail.com";
//        get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" + properties);
//        setting important information to properties object

//        host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

//      step 1 to get the session object
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("sciaku1@gmail.com","qmnbwozfzdbxffun");
            }

        });

        session.setDebug(true);

//      step 2 compose the message [text, multimedia]
        MimeMessage m = new MimeMessage(session);

        try{
//            from email
            m.setFrom(from);

//            adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//            adding subject gto message
            m.setSubject(subject);

//            adding text to message
            m.setText(message);

//            send
//            step 3 send the message using transport class
            Transport.send(m);

            System.out.println("Sent success........");

            f = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  f;
    }
}
