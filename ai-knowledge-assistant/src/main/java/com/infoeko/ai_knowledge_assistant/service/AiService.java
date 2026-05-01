package com.infoeko.ai_knowledge_assistant.service;

import com.infoeko.ai_knowledge_assistant.dto.AiRequest;
import com.infoeko.ai_knowledge_assistant.dto.AiResponse;
import com.infoeko.ai_knowledge_assistant.entity.AiConversation;

import java.util.List;

public interface AiService {

    AiResponse ask(AiRequest request, String userEmail);

    List<AiConversation> history(String userEmail);
}
