package com.fatec.tg.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    @Autowired
    private RestTemplate restTemplate; // Injetar o RestTemplate

    @CrossOrigin(origins = "*") // Habilitar CORS para este endpoint
    @RequestMapping("/api/chamadatoken")
    public String chamadatoken(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest);
        String url = "http://localhost:8080/oauth/token"; // URL do endpoint OAuth
        
        // Configurar os cabeçalhos, se necessário
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        // Adicione qualquer outro cabeçalho necessário (como Basic Auth, se necessário)

        // Criar o corpo da requisição
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        String requestBody = "grant_type=password&username=" + authRequest.getUsername() + "&password=" + authRequest.getPassword();

        System.out.println(requestBody);

        // Criar a entidade da requisição
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Fazer a chamada ao endpoint OAuth
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        // Retornar o token (você pode precisar analisar a resposta JSON para obter o token)
        return response.getBody(); // Retorne o corpo da resposta que contém o token
    }
}

// Classe para encapsular o request com usuário e senha
class AuthRequest {
    private String username;
    private String password;

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
