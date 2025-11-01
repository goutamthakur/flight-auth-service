-- Write your SQL migration here

CREATE TABLE addresses (
  id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT UNSIGNED NOT NULL,
  label VARCHAR(100) NULL,
  street_1 VARCHAR(255) NULL,
  street_2 VARCHAR(255) NULL,
  district VARCHAR(255) NULL,
  city VARCHAR(255) NULL,
  state VARCHAR(255) NULL,
  country_code CHAR(2) NULL,
  postal_code VARCHAR(20) NULL,
  phone VARCHAR(20) NULL,
  latitude DECIMAL(10,7) NULL,
  longitude DECIMAL(10,7) NULL,
  is_primary BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  
  INDEX idx_addresses_primary (user_id, is_primary),
  INDEX idx_addresses_user_city (user_id, city),
  INDEX idx_addresses_postal_code (postal_code),

  CONSTRAINT fk_addresses_user FOREIGN KEY (user_id) REFERENCES users(id)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);
