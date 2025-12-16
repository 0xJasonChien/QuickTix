package com.jason.quicktix.controller;

import com.jason.quicktix.dto.OrderDto;
import com.jason.quicktix.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public List<OrderDto> getAllOrders() {
    return orderService.getAllOrders();
  }

  @PostMapping
  public void createOrder(@Valid @RequestBody OrderDto orderDto) {
    orderService.createOrder(orderDto);
  }
}
