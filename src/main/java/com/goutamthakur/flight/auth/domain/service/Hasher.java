package com.goutamthakur.flight.auth.domain.service;

public interface Hasher {

    String hash(String password);

    Boolean compare(String rawPassword, String hashPassword);

    String hashToken(String token);

}

