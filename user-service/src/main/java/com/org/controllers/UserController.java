package com.org.controllers;

import com.org.dto.RegisterUserRequestDto;
import com.org.orchestrator.UserOrchestratorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserOrchestratorImpl userOrchestrator;

    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {

    }

}
