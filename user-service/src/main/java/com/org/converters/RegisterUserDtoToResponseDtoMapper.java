package com.org.converters;

import com.org.dto.RegisterUserDto;
import com.org.dto.RegisterUserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterUserDtoToResponseDtoMapper {
    RegisterUserResponseDto convert(RegisterUserDto registerUserDto);
}
