package com.org.converters;

import com.org.dto.RegisterUserRequestDto;
import com.org.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterRequestDtoToUserDtoMapper {
    UserDto map(RegisterUserRequestDto registerUserRequestDto);
}
