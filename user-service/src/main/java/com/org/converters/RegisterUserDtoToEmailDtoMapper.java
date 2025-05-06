package com.org.converters;

import com.org.dto.EmailDto;
import com.org.dto.RegisterUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterUserDtoToEmailDtoMapper {

    @Mapping(target = "userId", source = "id")
    EmailDto map(RegisterUserDto registerUserDto);
}
