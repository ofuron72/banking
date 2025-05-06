package com.org.orchestrator;

import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;

public interface UserOrchestrator {
    RegisterUserResponseDto registerAndSendUser(RegisterUserRequestDto registerUserRequestDto);
}
