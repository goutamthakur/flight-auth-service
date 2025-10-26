package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.domain.model.Role;
import com.goutamthakur.flight.auth.domain.repository.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepositoryPort roleRepositoryPort;

    public List<Role> getAllRoles(){
        return roleRepositoryPort.findAll();
    }
}
