package com.org.orchestrator;

import com.org.converters.account.RegisterUserDtoToAccountCreateEventMapper;
import com.org.dto.AccountCreateEvent;
import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailResponseDto;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneResponseDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserOrchestratorImpl implements UserOrchestrator {
    private final UserFacade userFacade;
    private final RegisterUserDtoToAccountCreateEventMapper registerUserDtoToAccountCreateEventMapper;
    private final KafkaTemplate<String, AccountCreateEvent> kafkaTemplate;
    @Value("${kafka.topic.account.name}")
    private String topicAccountName;

    @Override
    public RegisterUserResponseDto registerAndSendUser(RegisterUserRequestDto registerUserRequestDto) {
        var registerUserDto = userFacade.registerUser(registerUserRequestDto);
        kafkaTemplate.send(topicAccountName, registerUserDtoToAccountCreateEventMapper
                .mapToEvent(registerUserDto));

        return registerUserDto;
    }

    @Override
    public List<UserResponseDto> getAlLUsers() {
        var result = userFacade.getAllUsers();
        log.debug("Users list: {}", result);
        return result;
    }

    @Override
    public void deletePhoneByNumber(String number) {
        userFacade.deletePhoneByNumber(number);
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        var result = userFacade.getUserById(userId);
        log.debug("getUserById: {}", result);
        return result;
    }

    @Override
    public PhoneResponseDto addNumber(PhoneAddRequestDto phoneAddRequestDto) {
        var result = userFacade.addNumber(phoneAddRequestDto);
        log.debug("add number: {}", result);
        return result;
    }

    @Override
    public void deleteEmailByAddress(String address) {
        userFacade.deleteEmailByAddress(address);
    }

    @Override
    public EmailResponseDto addEmail(EmailAddRequestDto emailAddRequestDto) {
        var result = userFacade.addEmail(emailAddRequestDto);
        log.debug("add email: {}", result);
        return result;
    }
}
