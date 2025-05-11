package com.org.converters.user;

import com.org.dto.UserResponseDto;
import com.org.objects.UserProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserProjectionMapper {
    @Mapping(source = "number", target = "numbers")
    @Mapping(source = "email", target = "emails")
    UserResponseDto toResponseDto(UserProjection userProjection);
}
