-- Write your SQL migration here

CREATE TABLE users (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    uuid CHAR(36) NOT NULL,
    role_id BIGINT,
    name VARCHAR(255),
    email VARCHAR(320),
    phone VARCHAR(20) NULL,
    password_hash VARCHAR(255) NULL,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    phone_verified BOOLEAN NOT NULL DEFAULT FALSE,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NULL,
    dob DATE NULL,
    auth_type ENUM('PASSWORD','OAUTH','BOTH') NOT NULL DEFAULT 'PASSWORD',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_users_uuid (uuid),
    INDEX idx_users_email (email),
    INDEX idx_users_phone (phone),
    INDEX idx_users_active (is_active),

    CONSTRAINT fk_users_role
      FOREIGN KEY (role_id)
      REFERENCES roles(id)
      ON UPDATE CASCADE
      ON DELETE RESTRICT
);




