package com.org.converters.transaction;

import com.org.dto.TransactionDto;
import com.org.dto.TransactionRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionRequestToDtoMapper {
    TransactionDto mapToDto(TransactionRequestDto transactionRequestDto);
}
