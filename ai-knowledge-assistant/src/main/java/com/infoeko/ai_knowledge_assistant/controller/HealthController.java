package com.infoeko.ai_knowledge_assistant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public String healthController(){
        return ("AI Knowledge Assistant Backend is working!!!");
    }
}
