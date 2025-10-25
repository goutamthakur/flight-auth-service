/**
 * Role is POJO free from any infrastructure logic
 */

package com.goutamthakur.flight.auth.domain.model;

import java.time.Instant;

public class Role {
    private Long id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
