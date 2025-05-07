package com.org.controllers;

import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailResponseDto;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneResponseDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;
import com.org.orchestrator.UserFacade;
import com.org.orchestrator.UserOrchestrator;
import com.org.service.EmailService;
import com.org.service.PhoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserOrchestrator userOrchestrator;
    private final UserFacade userFacade;
    private final PhoneService phoneService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        var response = userOrchestrator.registerAndSendUser(registerUserRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") UUID id) {
        var response = userFacade.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var response = userFacade.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addNumber")
    public ResponseEntity<PhoneResponseDto> addNumber(@Valid @RequestBody PhoneAddRequestDto phoneAddRequestDto) {
        var response = phoneService.addNumber(phoneAddRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNumber/{number}")
    public ResponseEntity<Void> deleteNumber(@PathVariable("number") String number) {
        phoneService.deletePhoneByNumber(number);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addEmail")
    public ResponseEntity<EmailResponseDto> addEmail(@Valid @RequestBody EmailAddRequestDto emailAddRequestDto) {
        var response = emailService.addEmail(emailAddRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteEmail/{email}")
    public ResponseEntity<Void> deleteEmail(@PathVariable("email") String email) {
        emailService.deleteEmailByAddress(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
