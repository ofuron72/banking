package com.org.converters.transaction;

import com.org.dto.TransactionCompletedEvent;
import com.org.dto.TransactionRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionRequestDtoToEventMapper {
    TransactionCompletedEvent map(TransactionRequestDto transactionRequestDto);
}
