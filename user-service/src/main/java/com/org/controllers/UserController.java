package com.org.controllers;

import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneAddResponseDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;
import com.org.orchestrator.UserFacade;
import com.org.orchestrator.UserOrchestrator;
import com.org.service.PhoneService;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
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
    public ResponseEntity<PhoneAddResponseDto> addNumber(@RequestBody PhoneAddRequestDto phoneAddRequestDto) {
        var response = phoneService.addNumber(phoneAddRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNumber/{id}")
    public ResponseEntity<Void> deleteNumber(@PathVariable("id") UUID id) {
      //  phoneService.deleteNumber(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
