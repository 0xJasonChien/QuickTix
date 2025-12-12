package com.jason.quicktix.service;

import com.jason.quicktix.dto.GoodDto;
import com.jason.quicktix.entity.GoodEntity;
import com.jason.quicktix.mapper.GoodMapper;
import com.jason.quicktix.repository.GoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodService {
  private final GoodRepository goodRepository;
  private final GoodMapper goodMapper;

  public List<GoodDto> getAllGoods() {
    List<GoodEntity> goods = goodRepository.findAll();
    return goodMapper.toDtoList(goods);
  }

  public GoodDto getGoodById(Long id) {
    GoodEntity goodEntity =
        goodRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Good not found with id: " + id));
    return goodMapper.toDto(goodEntity);
  }

  public GoodDto createGood(GoodDto goodDto) {
    GoodEntity goodEntity = goodMapper.toEntity(goodDto);
    GoodEntity savedEntity = goodRepository.save(goodEntity);
    return goodMapper.toDto(savedEntity);
  }

  public void deleteGood(Long id) {
    goodRepository.deleteById(id);
  }
}
