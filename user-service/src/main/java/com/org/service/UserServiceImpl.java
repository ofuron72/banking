package com.org.service;

import com.org.converters.user.UserDtoToEntityMapper;
import com.org.dto.UserDto;
import com.org.entities.UserEntity;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoToEntityMapper userDtoToEntityMapper;


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



}
