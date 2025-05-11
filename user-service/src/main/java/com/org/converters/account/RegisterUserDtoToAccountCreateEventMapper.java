package com.org.converters.account;

import com.org.dto.AccountCreateEvent;
import com.org.dto.RegisterUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterUserDtoToAccountCreateEventMapper {
    @Mapping(target = "userId", source = "id")
    AccountCreateEvent mapToEvent(RegisterUserResponseDto registerUserDto);
}
