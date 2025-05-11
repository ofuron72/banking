package com.org.converters.account;

import com.org.dto.AccountDto;
import com.org.dto.AccountCreateEvent;
import org.mapstruct.Mapper;

@Mapper
public interface AccountCreateEventToDtoMapper {
    AccountDto mapSendDtoTOAccountDto(AccountCreateEvent accountCreateEvent);
}
