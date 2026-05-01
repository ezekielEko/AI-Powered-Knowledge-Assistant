package com.infoeko.ai_knowledge_assistant.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/user/profile")
    public String getProfile(Authentication authentication){
        return "Welcome "+ authentication.getName()+ " This is a protected endpoint. ";
    }
}
