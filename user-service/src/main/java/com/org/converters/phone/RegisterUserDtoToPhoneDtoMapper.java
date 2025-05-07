package com.org.converters.phone;

import com.org.dto.PhoneDto;
import com.org.dto.RegisterUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterUserDtoToPhoneDtoMapper {

    @Mapping(target = "userId", source = "id")
    PhoneDto map(RegisterUserDto registerUserDto);
}
