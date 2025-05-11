package com.org.converters.transaction;

import com.org.dto.TransactionDto;
import com.org.entities.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionDtoToEntityMapper {

    TransactionEntity mapDtoToEntity(TransactionDto dto);

    TransactionDto mapEntityToDto(TransactionEntity entity);
}
