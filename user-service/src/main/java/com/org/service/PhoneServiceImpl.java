package com.org.service;

import com.org.converters.phone.PhoneDtoToEntityMapper;

import com.org.converters.phone.PhoneDtoToResponseMapper;
import com.org.converters.phone.RequestPhoneToDtoMapper;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneAddResponseDto;
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
    private final PhoneDtoToResponseMapper phoneDtoToResponseMapper;
    private final RequestPhoneToDtoMapper requestPhoneToDtoMapper;

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

    @Override
    public PhoneAddResponseDto addNumber(PhoneAddRequestDto phone) {
        var dto = requestPhoneToDtoMapper.mapToDto(phone);
        var entity = phoneDtoToEntityMapper.toEntity(dto);
        entity.setId(UUID.randomUUID());
        UserEntity userEntity = userRepository.getUserById(phone.userId());
        entity.setUser(userEntity);
        phoneRepository.addPhone(entity);
        return phoneDtoToResponseMapper.mapToRequestDto(dto);
    }

    @Override
    public void deletePhoneById(UUID phoneId) {

    }
}
