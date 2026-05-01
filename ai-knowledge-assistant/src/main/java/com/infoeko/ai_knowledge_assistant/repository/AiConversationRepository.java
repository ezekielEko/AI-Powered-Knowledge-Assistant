package com.infoeko.ai_knowledge_assistant.repository;

import com.infoeko.ai_knowledge_assistant.entity.AiConversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AiConversationRepository extends MongoRepository<AiConversation, String> {

    List<AiConversation> findByUserEmailOrderByCreatedAtDesc(String userEmail);
}
