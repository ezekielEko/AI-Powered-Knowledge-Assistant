package com.infoeko.ai_knowledge_assistant.controller;

import com.infoeko.ai_knowledge_assistant.dto.AiRequest;
import com.infoeko.ai_knowledge_assistant.dto.AiResponse;
import com.infoeko.ai_knowledge_assistant.entity.AiConversation;
import com.infoeko.ai_knowledge_assistant.service.AiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ask")
    public AiResponse ask(
            @Valid @RequestBody AiRequest request,
            Authentication authentication
    ) {
        return aiService.ask(request, authentication.getName());
    }

    @GetMapping("/history")
    public List<AiConversation> history(Authentication authentication) {
        return aiService.history(authentication.getName());
    }
}
