package com.jason.quicktix.controller;

import com.jason.quicktix.dto.UserDto;
import com.jason.quicktix.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<UserDto> findAll() {
    return userService.getAllUsers();
  }

  @PostMapping
  public UserDto createUser(@Valid @RequestBody UserDto userDto) {
    return userService.createUser(userDto);
  }

  @GetMapping("/{id}")
  public UserDto findUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }
}
