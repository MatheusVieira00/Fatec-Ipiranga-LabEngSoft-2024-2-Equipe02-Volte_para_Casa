 package com.fatec.tg.WhatsApp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class WhatsAppService {

    @Value("${gzappy.api.url}")
    private String apiUrl;

    @Value("${gzappy.token}")
    private String token;

    public String sendMessage(WhatsAppMessageRequest request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        // Criando o JSON
        String json = objectMapper.writeValueAsString(request);

        // Criação do RequestBody para versões antigas
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        // Mudança de nome para evitar conflito
        Request httpRequest = new Request.Builder()
                .url(apiUrl + "/message/send-message")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        Response response = client.newCall(httpRequest).execute();
        return response.body().string();
    }
}
