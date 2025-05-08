package com.org.orchestrator;

import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;

import java.util.UUID;

public interface UserFacade {
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);

    UserResponseDto getUserById(UUID userId);

}
