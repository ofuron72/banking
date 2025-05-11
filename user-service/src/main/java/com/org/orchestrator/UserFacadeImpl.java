package com.org.orchestrator;

import com.org.converters.user.RegisterRequestDtoToUserDtoMapper;
import com.org.converters.account.RegisterUserDtoToAccountDtoMapper;
import com.org.converters.email.RegisterUserDtoToEmailDtoMapper;
import com.org.converters.phone.RegisterUserDtoToPhoneDtoMapper;
import com.org.converters.user.RegisterUserDtoToResponseDtoMapper;
import com.org.converters.user.RegisterUserRequestDtoToRegisterUserDtoMapper;
import com.org.dto.AccountDto;
import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailDto;
import com.org.dto.EmailResponseDto;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneDto;
import com.org.dto.PhoneResponseDto;
import com.org.dto.RegisterUserDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserDto;
import com.org.dto.UserResponseDto;
import com.org.services.AccountService;
import com.org.services.EmailService;
import com.org.services.PhoneService;
import com.org.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final AccountService accountService;
    private final EmailService emailService;
    private final PhoneService phoneService;
    private final RegisterRequestDtoToUserDtoMapper registerRequestDtoToUserDtoMapper;
    private final RegisterUserRequestDtoToRegisterUserDtoMapper registerUserRequestDtoToRegisterUserDtoMapper;
    private final RegisterUserDtoToResponseDtoMapper registerUserDtoToResponseDtoMapper;
    private final RegisterUserDtoToAccountDtoMapper registerUserDtoToAccountDtoMapper;
    private final RegisterUserDtoToEmailDtoMapper registerUserDtoToEmailDtoMapper;
    private final RegisterUserDtoToPhoneDtoMapper registerUserDtoToPhoneDtoMapper;

    /**
     * @param registerUserRequestDto
     * разбивается на AccountDto, UserDto, EmailDto, PhoneDto
     * и сохраняется в таблицы
     */
    @Override
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {
        var userId = userService.createUser(registerRequestDtoToUserDtoMapper
                .map(registerUserRequestDto));

        RegisterUserDto registerUserDto = registerUserRequestDtoToRegisterUserDtoMapper.convert(registerUserRequestDto);

        registerUserDto.setId(userId);

        accountService.create(registerUserDtoToAccountDtoMapper
                .map(registerUserDto));

        emailService.create(registerUserDtoToEmailDtoMapper
                .map(registerUserDto));

        phoneService.create(registerUserDtoToPhoneDtoMapper
                .map(registerUserDto));

        return registerUserDtoToResponseDtoMapper.convert(registerUserDto);
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserDto userDto = userService.getUserById(userId);

        AccountDto accountDto = accountService.getAccountByUserId(userId);

        List<String> phones = phoneService.getAllPhonesByUserId(userId)
                .stream()
                .map(PhoneDto::number)
                .toList();

        List<String> emails = emailService.getAllEmailsByUserId(userId)
                .stream()
                .map(EmailDto::email)
                .toList();

        var responseDto = UserResponseDto.builder()
                .id(userId)
                .fullName(userDto.fullName())
                .birthDate(userDto.birthDate())
                .emails(emails)
                .numbers(phones)
                .balance(accountDto.balance())
                .initialDeposit(accountDto.initialDeposit())
                .build();
        log.debug("getUserById: {}", responseDto);
        return responseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        var result = userService.getAllUsers();
        log.debug("getAllUsers: {}", result);
        return result;
    }

    @Override
    public PhoneResponseDto addNumber(PhoneAddRequestDto phoneAddRequestDto) {
        var result = phoneService.addNumber(phoneAddRequestDto);
        log.debug("addNumber: {}", result);
        return result;
    }

    @Override
    public void deletePhoneByNumber(String number) {
        phoneService.deletePhoneByNumber(number);
    }

    @Override
    public void deleteEmailByAddress(String number) {
        emailService.deleteEmailByAddress(number);
    }

    @Override
    public EmailResponseDto addEmail(EmailAddRequestDto emailAddRequestDto) {
        var result = emailService.addEmail(emailAddRequestDto);
        log.debug("addEmail: {}", result);
        return result;
    }
}
