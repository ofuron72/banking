package com.org.controllers;

import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailResponseDto;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneResponseDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;
import com.org.orchestrator.UserOrchestrator;
import com.org.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserOrchestrator userOrchestrator;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        var response = userOrchestrator.registerAndSendUser(registerUserRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") UUID id) {
        var response = userOrchestrator.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var response = userOrchestrator.getAlLUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addNumber")
    public ResponseEntity<PhoneResponseDto> addNumber(@Valid @RequestBody PhoneAddRequestDto phoneAddRequestDto) {
        var response = userOrchestrator.addNumber(phoneAddRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNumber/{number}")
    public ResponseEntity<Void> deleteNumber(@PathVariable("number") String number) {
        userOrchestrator.deletePhoneByNumber(number);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addEmail")
    public ResponseEntity<EmailResponseDto> addEmail(@Valid @RequestBody EmailAddRequestDto emailAddRequestDto) {
        var response = userOrchestrator.addEmail(emailAddRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteEmail/{email}")
    public ResponseEntity<Void> deleteEmail(@PathVariable("email") String email) {
        userOrchestrator.deleteEmailByAddress(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDto>> getAllUsersWithFilter(
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String email,
            Pageable pageable
            ){
        var page =  userService.getUsersWithFilter(birthDate, fullName, number, email,
                pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }



}
