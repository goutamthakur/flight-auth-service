package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.event.UserLoginEvent;
import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;

public interface UserEventPublisherPort {
    void publishUserRegisteredEvent(UserRegisteredEvent event);
    void publishUserLoginEvent(UserLoginEvent event);
}
