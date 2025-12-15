package com.jason.quicktix.dto;

import com.jason.quicktix.comm.base.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
  @NotBlank(message = "姓名不能為空")
  private String username;

  @Email(message = "信箱格式不正確")
  @NotBlank(message = "信箱不能為空")
  private String email;
}
