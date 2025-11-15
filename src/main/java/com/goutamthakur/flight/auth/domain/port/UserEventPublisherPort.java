package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;

public interface UserEventPublisherPort {
    void publishUserRegisteredEvent(UserRegisteredEvent event);
}
