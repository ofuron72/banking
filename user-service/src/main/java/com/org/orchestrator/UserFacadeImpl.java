package com.org.orchestrator;

import com.org.converters.RegisterRequestDtoToUserDtoMapper;
import com.org.converters.RegisterUserDtoToAccountDtoMapper;
import com.org.converters.RegisterUserDtoToEmailDtoMapper;
import com.org.converters.RegisterUserDtoToPhoneDtoMapper;
import com.org.converters.RegisterUserDtoToResponseDtoMapper;
import com.org.converters.RegisterUserRequestDtoToRegisterUserDtoMapper;
import com.org.converters.UserProjectionMapper;
import com.org.dto.AccountDto;
import com.org.dto.EmailDto;
import com.org.dto.PhoneDto;
import com.org.dto.RegisterUserDto;
import com.org.dto.RegisterUserRequestDto;
import com.org.dto.RegisterUserResponseDto;
import com.org.dto.UserDto;
import com.org.dto.UserResponseDto;
import com.org.objects.UserProjection;
import com.org.repository.CustomRepository;
import com.org.service.AccountService;
import com.org.service.EmailService;
import com.org.service.PhoneService;
import com.org.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    private final UserProjectionMapper userProjectionMapper;
    private final CustomRepository customRepository;

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
        return responseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserProjection> listProjection = customRepository.getAllUserProjections();

        return listProjection.stream().map(userProjectionMapper::toResponseDto).toList();
    }
}
