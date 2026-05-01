package com.infoeko.ai_knowledge_assistant.serviceImpl;

import com.infoeko.ai_knowledge_assistant.service.OpenAiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAiClientServiceImpl implements OpenAiClientService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    private final WebClient.Builder webClientBuilder;

    @Override
    public String ask(String prompt) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "model", model,
                    "input", prompt
            );

            Map response = webClientBuilder.build()
                    .post()
                    .uri(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return extractText(response);

        } catch (Exception ex) {
            return "AI provider error: " + ex.getMessage();
        }
    }

    private String extractText(Map response) {
        if (response == null || !response.containsKey("output")) {
            return "No response received from AI provider.";
        }

        List output = (List) response.get("output");

        if (output.isEmpty()) {
            return "AI provider returned empty output.";
        }

        Map firstOutput = (Map) output.get(0);
        List content = (List) firstOutput.get("content");

        if (content == null || content.isEmpty()) {
            return "AI provider returned no text content.";
        }

        Map firstContent = (Map) content.get(0);

        Object text = firstContent.get("text");

        return text != null ? text.toString() : "AI provider returned no text.";
    }
}