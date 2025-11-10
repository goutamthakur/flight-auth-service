package com.goutamthakur.flight.auth.domain.model;

import com.goutamthakur.flight.auth.domain.enums.AuthType;
import com.goutamthakur.flight.auth.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class User {
    private Long id;
    private String uuid;
    private Long roleId;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;
    private boolean emailVerified;
    private boolean phoneVerified;
    private Gender gender;
    private LocalDate dob;
    private AuthType authType;
    private boolean active = true;
    private boolean deleted = false;
    private Instant deletedAt;
    private Instant createdAt;
    private Instant updatedAt;
}
