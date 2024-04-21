package org.gieback.Service;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class MailService {

    public void sendSimpleEmail(String to, String subject, String message) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp-mail.outlook.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("pds.gie33@outlook.com", "Anir@2003"));
            email.setStartTLSEnabled(true);
            email.setFrom("pds.gie33@outlook.com");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            email.send();
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}





