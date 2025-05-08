package com.org.service;

import com.org.converters.user.UserDtoToEntityMapper;
import com.org.converters.user.UserProjectionMapper;
import com.org.dto.UserDto;
import com.org.dto.UserResponseDto;
import com.org.entities.UserEntity;
import com.org.objects.UserProjection;
import com.org.repository.CustomRepository;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoToEntityMapper userDtoToEntityMapper;
    private final UserProjectionMapper userProjectionMapper;
    private final CustomRepository customRepository;


    @Override
    public UUID createUser(UserDto userDto) {
        UserEntity userEntity = userDtoToEntityMapper.mapToEntity(userDto);
        userEntity.setId(UUID.randomUUID());
        return userRepository.saveUser(userEntity);
    }

    @Override
    public UserDto getUserById(UUID id) {
        return userDtoToEntityMapper
                .mapToDto(userRepository.getUserById(id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserProjection> listProjection = customRepository.getAllUserProjections();

        return listProjection.stream().map(userProjectionMapper::toResponseDto).toList();
    }

    @Override
    public Page<UserResponseDto> getUsersWithFilter(LocalDate birthDate, String fullName, String number, String email,
                                                    Pageable pageable) {
        var result = customRepository.findAllUsersWithFilter(birthDate, fullName, number, email , pageable)
                .map(userProjectionMapper::toResponseDto);
        return result;
    }
}
