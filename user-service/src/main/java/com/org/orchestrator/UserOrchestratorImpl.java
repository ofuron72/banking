package com.org.orchestrator;

import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserOrchestratorImpl implements UserOrchestrator {
    private final UserFacade userFacade;

    @Override
    public RegisterUserResponseDto registerAndSendUser(RegisterUserRequestDto registerUserRequestDto) {
        var result = userFacade.registerUser(registerUserRequestDto);
        return result;
    }
}
