package com.org.converters.email;

import com.org.dto.EmailDto;
import com.org.entities.EmailEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EmailDtoToEntityMapper {
    EmailEntity toEntity(EmailDto emailDto);

    EmailDto toDto(EmailEntity emailEntity);
}
