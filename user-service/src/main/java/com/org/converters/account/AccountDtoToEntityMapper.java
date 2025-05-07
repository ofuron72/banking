package com.org.converters.account;
import com.org.dto.AccountDto;
import com.org.entities.AccountEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AccountDtoToEntityMapper {

    AccountDto mapToDto(AccountEntity entity);

    AccountEntity mapToEntity(AccountDto dto);

}
