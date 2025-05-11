package com.org.converters.phone;

import com.org.dto.PhoneResponseDto;
import com.org.dto.PhoneDto;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneDtoToResponseMapper {
    PhoneResponseDto mapToResponseDto(PhoneDto phoneDto);
}
