-- password 1234
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES('8d7b892a-ab7b-11ed-afa1-0242ac120002', 'user', '{bcrypt}$2a$12$7bG1Ds4qfiK.uyxjtSjtnu7Lhn2ruuk1EZPFMB7T9jBjDFO2xw.yq', 'imagen', 'Usuario González', true, true, true, true, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- password 5678
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES('b3f3bbca-ab7d-11ed-afa1-0242ac120002', 'admin', '{bcrypt}$2a$12$MgkQqpKgw6NbbCy1Sz3EPOkQ31CGEovKyvV2Voxr0apdtuEEzPmk.', 'imagen', 'Administrador López', true, true, true, true, 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);





