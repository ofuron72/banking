package com.org.converters.email;

import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailDto;
import org.mapstruct.Mapper;

@Mapper
public interface RequestEmailToDtoMapper {
    EmailDto mapToDto(EmailAddRequestDto emailAddRequestDto);
}
