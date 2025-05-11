package com.org.converters.transaction;

import com.org.dto.TransactionDto;
import com.org.dto.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransactionDtoToResponseMapper {
    @Mapping(target = "responseMessage", constant = "transaction completed")
    TransactionResponseDto mapToResponse(TransactionDto dto);
}
