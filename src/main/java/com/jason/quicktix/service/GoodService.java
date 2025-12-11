package com.jason.quicktix.service;

import com.jason.quicktix.model.Good;
import com.jason.quicktix.repository.GoodRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GoodService {
  private final GoodRepository goodRepository;

  public GoodService(GoodRepository goodRepository) {
    this.goodRepository = goodRepository;
  }

  public List<Good> getAllGoods() {
    return goodRepository.findAll();
  }

  public Good getGoodById(Long id) {
    return goodRepository.findById(id).orElse(null);
  }
}
