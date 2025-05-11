package com.org.converters.phone;

import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneDto;
import org.mapstruct.Mapper;

@Mapper
public interface RequestPhoneToDtoMapper {
    PhoneDto mapToDto(PhoneAddRequestDto requestDto);
}
