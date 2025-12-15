package com.jason.quicktix.mapper;

import com.jason.quicktix.comm.base.BaseMapper;
import com.jason.quicktix.dto.UserDto;
import com.jason.quicktix.entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserEntity, UserDto> {

  @Override
  public UserEntity toEntity(UserDto dto) {
    return UserEntity.builder().username(dto.getUsername()).email(dto.getEmail()).build();
  }

  @Override
  public UserDto toDto(UserEntity entity) {
    return UserDto.builder()
        .username(entity.getUsername())
        .email(entity.getEmail())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  @Override
  public List<UserDto> toDtoList(List<UserEntity> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public List<UserEntity> toEntityList(List<UserDto> dtos) {
    return dtos.stream().map(this::toEntity).toList();
  }
}
