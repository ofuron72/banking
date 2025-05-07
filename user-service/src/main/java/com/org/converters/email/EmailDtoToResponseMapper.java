package com.org.converters.email;

import com.org.dto.EmailDto;
import com.org.dto.EmailResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface EmailDtoToResponseMapper {
    EmailResponseDto toResponseDto(EmailDto emailDto);
}
