package com.jason.quicktix.mapper;

import com.jason.quicktix.comm.base.BaseMapper;
import com.jason.quicktix.dto.GoodDto;
import com.jason.quicktix.entity.GoodEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GoodMapper implements BaseMapper<GoodEntity, GoodDto> {
  @Override
  public GoodDto toDto(GoodEntity entity) {
    return GoodDto.builder()
        .name(entity.getName())
        .price(entity.getPrice())
        .stock(entity.getStock())
        .build();
  }

  @Override
  public GoodEntity toEntity(GoodDto dto) {
    return GoodEntity.builder()
        .name(dto.getName())
        .price(dto.getPrice())
        .stock(dto.getStock())
        .build();
  }

  @Override
  public List<GoodDto> toDtoList(List<GoodEntity> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public List<GoodEntity> toEntityList(List<GoodDto> dtos) {
    return dtos.stream().map(this::toEntity).toList();
  }
}
