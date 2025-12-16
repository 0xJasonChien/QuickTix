package com.jason.quicktix.service;

import com.jason.quicktix.dto.OrderDto;
import com.jason.quicktix.mapper.OrderMapper;
import com.jason.quicktix.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  @Transactional
  public void createOrder(OrderDto orderDto) {
    orderRepository.save(orderMapper.toEntity(orderDto));
  }

  public List<OrderDto> getAllOrders() {
    return orderMapper.toDtoList(orderRepository.findAll());
  }
}
