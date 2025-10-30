package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.model.Role;
import com.goutamthakur.flight.auth.domain.repository.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepositoryPort roleRepositoryPort;

    public List<Role> getAllRoles(){
        return roleRepositoryPort.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepositoryPort.findById(id)
                .orElseThrow(() -> new AppException("Role not found", HttpStatus.NOT_FOUND));
    }

    public Role getRoleByName(String name){
        return roleRepositoryPort.findByName(name)
                .orElseThrow(() -> new AppException("Role with " + name + " does not exist", HttpStatus.NOT_FOUND));
    }

}
