package com.org.converters;

import com.org.dto.PhoneDto;
import com.org.entities.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneDtoToEntityMapper {
    PhoneEntity toEntity(PhoneDto phoneDto);

    PhoneDto toDto(PhoneEntity phoneEntity);
}
