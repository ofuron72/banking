package com.org.services;

import com.org.converters.phone.PhoneDtoToEntityMapper;

import com.org.converters.phone.PhoneDtoToResponseMapper;
import com.org.converters.phone.RequestPhoneToDtoMapper;
import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneDto;
import com.org.dto.PhoneResponseDto;
import com.org.entities.PhoneEntity;
import com.org.entities.UserEntity;
import com.org.exceptions.InvalidNumberFormatException;
import com.org.exceptions.LastPhoneException;
import com.org.repository.PhoneRepository;
import com.org.repository.UserRepository;
import com.org.exceptions.PhoneNotFoundException;
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
    public PhoneDto getPhoneByNumber(String number) {
        return Optional.ofNullable(phoneDtoToEntityMapper
                        .toDto(phoneRepository.getPhoneByNumber(number)))
                .orElseThrow(()-> new PhoneNotFoundException(String
                        .format("Phone with number %s not found", number)));
    }

    @Override
    public List<PhoneDto> getAllPhonesByUserId(UUID userId) {
        List<PhoneEntity> phoneEntities = phoneRepository.getAllPhonesByUserId(userId);

        return phoneEntities.stream()
                .map(phoneDtoToEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PhoneResponseDto addNumber(PhoneAddRequestDto phone) {
        var dto = requestPhoneToDtoMapper.mapToDto(phone);

        var entity = phoneDtoToEntityMapper.toEntity(dto);

        entity.setId(UUID.randomUUID());

        UserEntity userEntity = userRepository.getUserById(phone.userId());

        entity.setUser(userEntity);

        phoneRepository.addPhone(entity);

        return phoneDtoToResponseMapper.mapToResponseDto(dto);
    }

    @Override
    public void deletePhoneByNumber(String number) {
        validateNumberFormat(number);

        var phone = getPhoneByNumber(number);

        var listPhones = getAllPhonesByUserId(phone.userId());

        if (listPhones.size()<=1){
            throw new LastPhoneException("you trying to delete the last one");
        }
        phoneRepository.deletePhoneByNumber(phone.number());
    }

    private void validateNumberFormat(String number) {
        if (!number.matches("^\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}$")) {
            throw new InvalidNumberFormatException("Invalid phone number format, must be" +
                    " x-xxx-xxx-xx-xx");
        }
    }
}
