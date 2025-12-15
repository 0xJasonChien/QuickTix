package com.jason.quicktix.controller;

import com.jason.quicktix.dto.GoodDto;
import com.jason.quicktix.service.GoodService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {
  private final GoodService goodService;

  @GetMapping
  public List<GoodDto> getAllGoods() {
    return goodService.getAllGoods();
  }

  @GetMapping("/{id}")
  public GoodDto getGoodById(@PathVariable("id") Long id) {
    return goodService.getGoodById(id);
  }

  @PostMapping
  public GoodDto createGood(@Valid @RequestBody GoodDto goodDto) {
    // Implementation for creating a new good
    return goodService.createGood(goodDto);
  }

  @DeleteMapping("/{id}")
  public void deleteGood(@PathVariable("id") Long id) {
    goodService.deleteGood(id);
  }
}
