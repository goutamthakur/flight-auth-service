package com.goutamthakur.flight.auth.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class UserLoginEvent {
    String email;
    String otp;
}

