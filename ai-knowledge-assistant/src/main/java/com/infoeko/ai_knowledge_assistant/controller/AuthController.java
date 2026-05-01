package com.infoeko.ai_knowledge_assistant.controller;

import com.infoeko.ai_knowledge_assistant.dto.AuthResponse;
import com.infoeko.ai_knowledge_assistant.dto.LoginRequest;
import com.infoeko.ai_knowledge_assistant.dto.RegisterRequest;
import com.infoeko.ai_knowledge_assistant.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}