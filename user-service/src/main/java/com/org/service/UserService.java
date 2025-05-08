package com.org.service;

import com.org.dto.UserDto;
import com.org.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID createUser(UserDto userDto);

    UserDto getUserById(UUID id);

    List<UserResponseDto> getAllUsers();

    Page<UserResponseDto> getUsersWithFilter(LocalDate birthDate,
                                            String fullName,
                                            String number,
                                            String email,
                                            Pageable pageable
                                            );

}
