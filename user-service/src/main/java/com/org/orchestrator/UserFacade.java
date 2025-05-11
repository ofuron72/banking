package com.org.orchestrator;

import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailResponseDto;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneResponseDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserFacade {
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUsers();

    PhoneResponseDto addNumber(PhoneAddRequestDto phoneAddRequestDto);

    void deletePhoneByNumber(String number);

    void deleteEmailByAddress(String number);

    EmailResponseDto addEmail(EmailAddRequestDto emailAddRequestDto);

}
