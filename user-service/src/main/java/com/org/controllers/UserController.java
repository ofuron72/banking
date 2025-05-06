package com.org.controllers;

import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.orchestrator.UserFacade;
import com.org.orchestrator.UserOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserOrchestrator userOrchestrator;
    private final UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        var response = userOrchestrator.registerAndSendUser(registerUserRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) {
        var response = userFacade.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        var response = userFacade.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
