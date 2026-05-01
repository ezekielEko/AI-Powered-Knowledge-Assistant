package com.infoeko.ai_knowledge_assistant.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ai_conversations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiConversation {

    @Id
    private String id;

    private String userEmail;

    private String prompt;

    private String answer;

    private LocalDateTime createdAt;
}
