package com.example.demo.Pojo;

import java.util.List;

public class OpenRouterResponse {
     private List<Choice> choices;

    public static class Choice {
        private ChatMessage message;

        public ChatMessage getMessage() {
            return message;
        }

        public void setMessage(ChatMessage message) {
            this.message = message;
        }
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}