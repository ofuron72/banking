package com.org.converters;

import com.org.dto.AccountDto;
import com.org.dto.RegisterUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterUserDtoToAccountDtoMapper {

    @Mapping(target = "userId", source = "id")
    AccountDto map(RegisterUserDto registerUserDto);
}
