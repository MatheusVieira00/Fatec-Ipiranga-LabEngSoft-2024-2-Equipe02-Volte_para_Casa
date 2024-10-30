package com.fatec.tg.WhatsApp;

import java.util.List;

public class WhatsAppMessageRequest {
    private String instance_id;
    private List<String> message;
    private List<String> phone;

    // Getters e Setters
    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
