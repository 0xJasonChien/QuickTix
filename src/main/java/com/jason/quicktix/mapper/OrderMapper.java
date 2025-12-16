package com.jason.quicktix.mapper;

import com.jason.quicktix.comm.base.BaseMapper;
import com.jason.quicktix.dto.OrderDto;
import com.jason.quicktix.entity.GoodEntity;
import com.jason.quicktix.entity.OrderEntity;
import com.jason.quicktix.entity.UserEntity;
import com.jason.quicktix.repository.GoodRepository;
import com.jason.quicktix.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper implements BaseMapper<OrderEntity, OrderDto> {
  private final GoodRepository goodRepository;
  private final UserRepository userRepository;

  @Override
  public OrderEntity toEntity(OrderDto dto) {
    GoodEntity goodProxy = goodRepository.getReferenceById(dto.getGoodId());
    UserEntity userProxy = userRepository.getReferenceById(dto.getUserId());

    return OrderEntity.builder()
        .good(goodProxy)
        .user(userProxy)
        .amount(dto.getAmount())
        .address(dto.getAddress())
        .build();
  }

  @Override
  public OrderDto toDto(OrderEntity entity) {
    return OrderDto.builder()
        .id(entity.getId())
        .goodId(entity.getGood().getId())
        .userId(entity.getUser().getId())
        .amount(entity.getAmount())
        .address(entity.getAddress())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  @Override
  public List<OrderDto> toDtoList(List<OrderEntity> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public List<OrderEntity> toEntityList(List<OrderDto> dtos) {
    return dtos.stream().map(this::toEntity).toList();
  }
}
