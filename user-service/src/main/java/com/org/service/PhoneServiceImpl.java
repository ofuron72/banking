package com.org.service;

import com.org.converters.PhoneDtoToEntityMapper;
import com.org.dto.PhoneDto;
import com.org.entities.PhoneEntity;
import com.org.entities.UserEntity;
import com.org.repository.PhoneRepository;
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
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneDtoToEntityMapper phoneDtoToEntityMapper;
    private final UserRepository userRepository;

    @Override
    public void create(PhoneDto phone) {
        PhoneEntity phoneEntity = phoneDtoToEntityMapper.toEntity(phone);
        phoneEntity.setId(UUID.randomUUID());
        UserEntity userEntity = userRepository.getUserById(phone.userId());
        phoneEntity.setUser(userEntity);
        phoneRepository.save(phoneEntity);
    }

    @Override
    public List<PhoneDto> getAllPhonesByUserId(UUID userId) {
        List<PhoneEntity> phoneEntities = phoneRepository.getAllPhonesByUserId(userId);

        return phoneEntities.stream()
                .map(phoneDtoToEntityMapper::toDto)
                .collect(Collectors.toList());
    }

}
