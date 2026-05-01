package com.infoeko.ai_knowledge_assistant.serviceImpl;

import com.infoeko.ai_knowledge_assistant.dto.AiRequest;
import com.infoeko.ai_knowledge_assistant.dto.AiResponse;
import com.infoeko.ai_knowledge_assistant.entity.AiConversation;
import com.infoeko.ai_knowledge_assistant.repository.AiConversationRepository;
import com.infoeko.ai_knowledge_assistant.service.AiService;
import com.infoeko.ai_knowledge_assistant.service.OpenAiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final OpenAiClientService openAiClientService;
    private final AiConversationRepository aiConversationRepository;

    @Override
    public AiResponse ask(AiRequest request, String userEmail) {
        String answer = openAiClientService.ask(request.getPrompt());

        AiConversation conversation = AiConversation.builder()
                .userEmail(userEmail)
                .prompt(request.getPrompt())
                .answer(answer)
                .createdAt(LocalDateTime.now())
                .build();

        aiConversationRepository.save(conversation);

        return AiResponse.builder()
                .prompt(request.getPrompt())
                .answer(answer)
                .build();
    }

    @Override
    public List<AiConversation> history(String userEmail) {
        return aiConversationRepository.findByUserEmailOrderByCreatedAtDesc(userEmail);
    }
}
