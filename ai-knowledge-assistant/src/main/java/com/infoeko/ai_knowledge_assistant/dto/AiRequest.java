package com.infoeko.ai_knowledge_assistant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AiRequest {

    @NotBlank(message = "Prompt is required")
    private String prompt;
}

