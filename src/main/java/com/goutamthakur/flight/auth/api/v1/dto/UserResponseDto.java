package com.goutamthakur.flight.auth.api.v1.dto;

import com.goutamthakur.flight.auth.domain.enums.AuthType;
import com.goutamthakur.flight.auth.domain.enums.Gender;
import com.goutamthakur.flight.auth.domain.model.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
  private Long id;
  private String uuid;
  private Long roleId;
  private String name;
  private String email;
  private String phone;
  private boolean emailVerified;
  private boolean phoneVerified;
  private Gender gender;
  private LocalDate dob;
  private AuthType authType;
  private boolean active;

  public static UserResponseDto from(User user) {
    return new UserResponseDto(
        user.getId(),
        user.getUuid(),
        user.getRoleId(),
        user.getName(),
        user.getEmail(),
        user.getPhone(),
        user.isEmailVerified(),
        user.isPhoneVerified(),
        user.getGender(),
        user.getDob(),
        user.getAuthType(),
        user.isActive());
  }
}
