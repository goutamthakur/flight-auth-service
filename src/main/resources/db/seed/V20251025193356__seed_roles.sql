-- Seeding roles table since roles are same across all profiles

INSERT INTO roles(name, description)
VALUES ('ADMIN',  'Administrator with full access'),
       ('USER', 'Customer which uses the platform for booking flight ticket')
;