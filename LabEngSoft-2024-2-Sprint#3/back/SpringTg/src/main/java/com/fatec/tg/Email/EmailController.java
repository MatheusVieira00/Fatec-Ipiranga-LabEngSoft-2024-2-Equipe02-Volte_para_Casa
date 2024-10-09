package com.fatec.tg.Email;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;


@RestController
@RequestMapping("/Email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

	
	@PostMapping("/enviar-email")
    public int enviarEmail(@RequestBody EmailRequest emailRequest) {
        try {
        	
            EmailSender emailSender = new EmailSender();            
            return emailSender.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        } catch (MessagingException e) {
            System.out.println(e);
            return 2;
        }
    }
}
