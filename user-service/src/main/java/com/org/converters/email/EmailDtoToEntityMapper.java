package com.org.converters.email;

import com.org.dto.EmailDto;
import com.org.entities.EmailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailDtoToEntityMapper {
    EmailEntity toEntity(EmailDto emailDto);

    @Mapping(source = "user.id", target = "userId")
    EmailDto toDto(EmailEntity emailEntity);
}
