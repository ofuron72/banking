package com.org.converters.phone;

import com.org.dto.PhoneAddResponseDto;
import com.org.dto.PhoneDto;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneDtoToResponseMapper {
    PhoneAddResponseDto mapToRequestDto(PhoneDto phoneDto);
}
