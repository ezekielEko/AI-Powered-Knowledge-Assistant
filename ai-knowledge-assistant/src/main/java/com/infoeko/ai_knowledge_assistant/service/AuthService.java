package com.infoeko.ai_knowledge_assistant.service;

import com.infoeko.ai_knowledge_assistant.dto.AuthResponse;
import com.infoeko.ai_knowledge_assistant.dto.LoginRequest;
import com.infoeko.ai_knowledge_assistant.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}