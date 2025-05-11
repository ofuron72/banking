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

public interface UserOrchestrator {
    RegisterUserResponseDto registerAndSendUser(RegisterUserRequestDto registerUserRequestDto);

    List<UserResponseDto> getAlLUsers();

    PhoneResponseDto addNumber(PhoneAddRequestDto phoneAddRequestDto);

    UserResponseDto getUserById(UUID userId);

    void deletePhoneByNumber(String number);

    void deleteEmailByAddress(String address);

    EmailResponseDto addEmail(EmailAddRequestDto emailAddRequestDto);
}
