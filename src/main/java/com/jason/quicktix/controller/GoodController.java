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

  /**
   * Get all goods.
   *
   * @return a list of all goods
   */
  @GetMapping
  public List<GoodDto> getAllGoods() {
    return goodService.getAllGoods();
  }

  /**
   * Get a good by its ID.
   *
   * @param id the ID of the good
   * @return the good with the specified ID
   */
  @GetMapping("/{id}")
  public GoodDto getGoodById(@PathVariable("id") Long id) {
    return goodService.getGoodById(id);
  }

  /**
   * Create a new good.
   *
   * @param goodDto the good to create
   * @return the created good
   */
  @PostMapping
  public GoodDto createGood(@Valid @RequestBody GoodDto goodDto) {
    // Implementation for creating a new good
    return goodService.createGood(goodDto);
  }

  /**
   * Delete a good by its ID.
   *
   * @param id the ID of the good to delete
   */
  @DeleteMapping("/{id}")
  public void deleteGood(@PathVariable("id") Long id) {
    goodService.deleteGood(id);
  }
}
