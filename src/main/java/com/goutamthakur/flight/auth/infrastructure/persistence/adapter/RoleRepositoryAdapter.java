package com.goutamthakur.flight.auth.infrastructure.persistence.adapter;

import com.goutamthakur.flight.auth.domain.model.Role;
import com.goutamthakur.flight.auth.domain.port.RoleRepositoryPort;
import com.goutamthakur.flight.auth.infrastructure.persistence.jpa.RoleJpaRepository;
import com.goutamthakur.flight.auth.infrastructure.persistence.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<Role> findAll(){
       return roleJpaRepository.findAll()
               .stream()
               .map(roleMapper::toDomain)
               .toList();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id)
                .map(roleMapper::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository.findByName(name)
                .map(roleMapper::toDomain);
    }
}
