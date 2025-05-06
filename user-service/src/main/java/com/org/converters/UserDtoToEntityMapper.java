package com.org.converters;

import com.org.dto.UserDto;
import com.org.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoToEntityMapper {

    UserDto mapToDto(UserEntity entity);

    UserEntity mapToEntity(UserDto dto);
}
