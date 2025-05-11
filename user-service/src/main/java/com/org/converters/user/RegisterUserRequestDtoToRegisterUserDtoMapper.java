package com.org.converters.user;

import com.org.dto.RegisterUserDto;
import com.org.dto.RegisterUserRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterUserRequestDtoToRegisterUserDtoMapper {
    RegisterUserDto convert(RegisterUserRequestDto registerUserRequestDto);
}
