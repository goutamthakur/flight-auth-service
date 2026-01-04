package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.model.Session;

public interface SessionRepositoryPort {
    Session createSession(Session session);
}

