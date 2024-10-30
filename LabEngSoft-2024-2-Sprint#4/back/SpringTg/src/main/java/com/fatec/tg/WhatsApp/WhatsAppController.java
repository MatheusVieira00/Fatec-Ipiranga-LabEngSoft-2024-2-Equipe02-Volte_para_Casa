package com.fatec.tg.WhatsApp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody WhatsAppMessageRequest request) {
        try {
            return whatsAppService.sendMessage(request);
        } catch (IOException e) {
            return "Erro ao enviar a mensagem: " + e.getMessage();
        }
    }
}
