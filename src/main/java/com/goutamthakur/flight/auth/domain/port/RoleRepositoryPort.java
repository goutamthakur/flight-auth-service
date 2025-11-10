/**
 * RoleRepositoryPort is used as contract between the domain and infrastructure
 * Methods defined here is implemented by adapters in infrastructure
 */

package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
}
