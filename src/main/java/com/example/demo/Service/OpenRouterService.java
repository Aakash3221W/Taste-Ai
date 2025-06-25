package com.example.demo.Service;

import com.example.demo.model.CompareRequest;
import okhttp3.*;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenRouterService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.api.url}")
    private String apiUrl;

    @Value("${openrouter.referer}")
    private String referer;

    public String getComparison(CompareRequest request) {
        String prompt = String.format("I'm from %s, %s. I'm eating %s, a dish from %s. Can you describe what dish from my region this tastes like, and why?",
                request.getUserState(), request.getUserCountry(), request.getDishName(), request.getDishCountry());

        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "mistralai/mistral-7b-instruct"); // Free model supported by OpenRouter
            payload.put("messages", new Object[] {
                    Map.of("role", "system", "content", "You are a food expert."),
                    Map.of("role", "user", "content", prompt)
            });

            String jsonPayload = mapper.writeValueAsString(payload);

            RequestBody body = RequestBody.create(
                    jsonPayload,
                    MediaType.parse("application/json")
            );

            Request httpRequest = new Request.Builder()
                    .url(apiUrl)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("HTTP-Referer", referer)
                    .addHeader("X-Title", "TasteAI")
                    .post(body)
                    .build();

            Response response = client.newCall(httpRequest).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Map<?, ?> result = mapper.readValue(responseBody, Map.class);
                Object content = ((Map<?, ?>)((Map<?, ?>)((java.util.List<?>) result.get("choices")).get(0)).get("message")).get("content");
                return content.toString();
            } else {
                return "Error: " + response.code() + " - " + response.message();
            }
        } catch (IOException e) {
            return "Exception: " + e.getMessage();
        }
    }
}
