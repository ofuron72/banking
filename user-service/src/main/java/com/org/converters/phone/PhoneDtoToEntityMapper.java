package com.org.converters.phone;

import com.org.dto.PhoneDto;
import com.org.entities.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneDtoToEntityMapper {
    PhoneEntity toEntity(PhoneDto phoneDto);

    PhoneDto toDto(PhoneEntity phoneEntity);
}
