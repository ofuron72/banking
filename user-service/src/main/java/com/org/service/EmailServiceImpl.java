package com.org.service;

import com.org.converters.email.EmailDtoToEntityMapper;
import com.org.dto.EmailDto;
import com.org.entities.EmailEntity;
import com.org.entities.UserEntity;
import com.org.repository.EmailRepository;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailDtoToEntityMapper emailDtoToEntityMapper;
    private final UserRepository userRepository;

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

}
