package com.example.demo.Pojo;

import java.util.List;

public class OpenRouterRequest {
    private String model;
    private List<ChatMessage> messages;

    public OpenRouterRequest() {}
    public OpenRouterRequest(String model, List<ChatMessage> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}

