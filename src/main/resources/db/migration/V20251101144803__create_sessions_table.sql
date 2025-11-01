-- Write your SQL migration here

CREATE TABLE sessions(
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    device_id VARCHAR(255),
    device_info VARCHAR(1024) NULL,
    ip_address VARCHAR(100),
    location VARCHAR(255) NULL,
    access_token_jti VARCHAR(255) NOT NULL,
    refresh_token_hash VARCHAR(255) NOT NULL,
    refresh_token_expiry TIMESTAMP NOT NULL,
    last_active_at TIMESTAMP NOT NULL,
    revoked_at TIMESTAMP NULL,
    revoked_reason VARCHAR(255) NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_sessions_active (user_id, is_active),
    INDEX idx_sessions_refresh_token (refresh_token_hash),
    INDEX idx_sessions_access_jti (access_token_jti),

    CONSTRAINT fk_sessions_user FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE

);