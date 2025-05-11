package com.org.converters.user;

import com.org.dto.RegisterUserDto;
import com.org.dto.RegisterUserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterUserDtoToResponseDtoMapper {
    RegisterUserResponseDto convert(RegisterUserDto registerUserDto);
}
