package com.fatec.tg.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;


public class EmailSender {

    private static final String SMTP_HOST = "smtp-mail.outlook.com";
    private static final int SMTP_PORT = 587;
    private static final String USERNAME = "Volte.Para.Casa@outlook.com";
    private static final String PASSWORD = "Cisco1234*";   


   
    public EmailSender() {

    }

    public int sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
          
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
        return 0;
    }
}
