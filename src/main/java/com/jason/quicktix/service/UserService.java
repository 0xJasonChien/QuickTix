package com.jason.quicktix.service;

import com.jason.quicktix.dto.UserDto;
import com.jason.quicktix.entity.UserEntity;
import com.jason.quicktix.mapper.UserMapper;
import com.jason.quicktix.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserDto createUser(UserDto userDto) {
    UserEntity userEntity = userMapper.toEntity(userDto);
    userEntity = userRepository.save(userEntity);
    return userMapper.toDto(userEntity);
  }

  public UserDto getUserById(Long id) {
    UserEntity userEntity =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    ;
    return userMapper.toDto(userEntity);
  }

  public List<UserDto> getAllUsers() {
    List<UserEntity> userEntities = userRepository.findAll();
    return userMapper.toDtoList(userEntities);
  }
}
