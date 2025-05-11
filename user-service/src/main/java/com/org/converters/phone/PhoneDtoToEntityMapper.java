package com.org.converters.phone;

import com.org.dto.PhoneDto;
import com.org.entities.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PhoneDtoToEntityMapper {
    PhoneEntity toEntity(PhoneDto phoneDto);

    @Mapping(source = "user.id", target = "userId")
    PhoneDto toDto(PhoneEntity phoneEntity);
}
