package com.org.service;

import com.org.converters.email.EmailDtoToEntityMapper;
import com.org.converters.email.EmailDtoToResponseMapper;
import com.org.converters.email.RequestEmailToDtoMapper;
import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailDto;
import com.org.dto.EmailResponseDto;
import com.org.entities.EmailEntity;
import com.org.entities.UserEntity;
import com.org.exceptions.EmailNotFoundException;
import com.org.exceptions.InvalidEmailFormatException;
import com.org.exceptions.LastEmailException;
import com.org.repository.EmailRepository;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailDtoToEntityMapper emailDtoToEntityMapper;
    private final UserRepository userRepository;
    private final EmailDtoToResponseMapper emailDtoToResponseMapper;
    private final RequestEmailToDtoMapper requestEmailToDtoMapper;

    @Override
    public void create(EmailDto emailDto) {
        EmailEntity emailEntity = emailDtoToEntityMapper
                .toEntity(emailDto);

        emailEntity.setId(UUID.randomUUID());

        UserEntity userEntity = userRepository.getUserById(emailDto.userId());

        emailEntity.setUser(userEntity);

        emailRepository.save(emailEntity);
    }

    @Override
    public List<EmailDto> getAllEmailsByUserId(UUID userId) {
        List<EmailEntity> emailEntities = emailRepository.findAllByUserId(userId);

        return emailEntities.stream()
                .map(emailDtoToEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmailResponseDto addEmail(EmailAddRequestDto email) {
        var dto = requestEmailToDtoMapper.mapToDto(email);

        var entity = emailDtoToEntityMapper.toEntity(dto);

        entity.setId(UUID.randomUUID());

        UserEntity userEntity = userRepository.getUserById(email.userId());

        entity.setUser(userEntity);

        emailRepository.addEmail(entity);

        return emailDtoToResponseMapper.toResponseDto(dto);
    }

    @Override
    public void deleteEmailByAddress(String address) {
        validateEmailFormat(address);

        var emailDto = getEmailByAddress(address);

        var listEmails = getAllEmailsByUserId(emailDto.userId());

        if (listEmails.size()<=1){
            throw new LastEmailException("you trying to delete the last one");
        }
        emailRepository.deleteEmailByAddress(emailDto.email());
    }

    @Override
    public EmailDto getEmailByAddress(String address) {
        return Optional.ofNullable(emailDtoToEntityMapper
                                .toDto(emailRepository.getEmailByAddress(address)))
                .orElseThrow(()-> new EmailNotFoundException(String
                        .format("Email with address %s not found", address)));
    }

    private void validateEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!email.matches(regex)) {
            throw new InvalidEmailFormatException("Invalid email format: " + email);
        }
    }
}
