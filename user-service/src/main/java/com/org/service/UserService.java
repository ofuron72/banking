package com.org.service;

import com.org.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UUID createUser(UserDto userDto);

    UserDto getUserById(UUID id);

}
