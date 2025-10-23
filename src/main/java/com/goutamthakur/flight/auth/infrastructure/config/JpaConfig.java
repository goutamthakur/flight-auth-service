package com.goutamthakur.flight.auth.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // Currently only needed @CreatedDate and @UpdatedDate
    // For @CreatedBy and @UpdatedBy setup AuditorAware
}
