package com.infoeko.ai_knowledge_assistant.serviceImpl;

import com.infoeko.ai_knowledge_assistant.dto.AuthResponse;
import com.infoeko.ai_knowledge_assistant.dto.LoginRequest;
import com.infoeko.ai_knowledge_assistant.dto.RegisterRequest;
import com.infoeko.ai_knowledge_assistant.entity.User;
import com.infoeko.ai_knowledge_assistant.exception.EmailAlreadyExistsException;
import com.infoeko.ai_knowledge_assistant.exception.InvalidCredentialsException;
import com.infoeko.ai_knowledge_assistant.repository.UserRepository;
import com.infoeko.ai_knowledge_assistant.security.JwtService;
import com.infoeko.ai_knowledge_assistant.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .message("User registered successfully")
                .token(token)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .message("Login successful")
                .token(token)
                .build();
    }
}