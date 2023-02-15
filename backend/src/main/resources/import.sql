-- password 1234
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'user', '{bcrypt}$2a$12$7bG1Ds4qfiK.uyxjtSjtnu7Lhn2ruuk1EZPFMB7T9jBjDFO2xw.yq', 'imagen', 'Usuario González', true, true, true, true, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password 12345678
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('6f705ab2-6ff9-4068-87f8-4c4932c8d73f', 'ramoncin', '{bcrypt}$2a$12$jIVXp55gD0dyoJzgYlltYOwVJoJ3WEy/VvvIqR97lmE4LVaUjF9ry', 'imagen', 'Ramón Villanueva', true, true, true, true, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password 5678
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('7117fd18-7be7-4c60-88c6-c4fa9897d013', 'admin', '{bcrypt}$2a$12$MgkQqpKgw6NbbCy1Sz3EPOkQ31CGEovKyvV2Voxr0apdtuEEzPmk.', 'imagen', 'Administrador López', true, true, true, true, 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categoria (id, nombre) VALUES ('24261a63-5dd3-4f81-a0a4-3dc929f74ebe', 'CARNICERIA');
INSERT INTO categoria (id, nombre) VALUES ('0231a8be-af6a-487e-bd21-bd98c0caad39', 'CHARCUTERIA');
INSERT INTO categoria (id, nombre) VALUES ('8c37d098-af20-4ec8-ba75-a697a48d48de', 'CONSUMIBLES');
INSERT INTO categoria (id, nombre) VALUES ('8c28d654-eb8b-4e9d-a86d-a132662b143e', 'LACTEOS');
INSERT INTO categoria (id, nombre) VALUES ('a54b27e6-3208-40d0-b909-d282b4f0d58f', 'PASTA');

INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e0a31674-c439-4c1b-83b3-77236a762cf7', 'FIAMBREPAVO', 'imagen_fiambrepavo.jpg', 'Pavo 200 gr ALDI', 1.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('9b05f55a-7fb8-4f1f-ad5f-97f42bdd9032', 'FIAMBREPAVO', 'imagen_fiambrepavo_456214.jpg', 'Pavo finas lonchas 200 gr Mercadona', 2.35, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('39039a90-51fd-4c81-8c0a-6b58c7e9e4ec', 'CHORIZO', 'imagen_chorizo.jpg', 'Chorizo 150 gr IFA Eliges', 1.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e161158b-77c3-4079-b499-89a9fff84406', 'ESPAGUETI', 'imagen_espagueti.jpg', 'Espaguetis Pastas Gallo 1 Kg', 2.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'a54b27e6-3208-40d0-b909-d282b4f0d58f');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('2624030c-d07d-4eff-b351-54139076fad3', 'YOGUR', 'imagen_yogur.jpg', 'Yogur Clesa Sabor Fresa pack-4', 3.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e575dd6a-90c2-49b3-a89d-609f0a8b86cb', 'YOGUR', 'imagen_yogur_412412.jpg', 'Yogur Danone Sabor Natural Edulcorado pack-4', 4.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('ef5c733d-b81a-4fda-992f-4d7b39e0ef7e', 'TERNERA', 'imagen_ternera.jpg', 'Lomo bajo de vaca MAS', 18.99, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('8dd9e1b0-bef3-4aa9-a132-d7a30c3d254a', 'TERNERA', 'imagen_ternera_857121.jpg', 'Solomillo de ternera', 27.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('dfe188cc-4027-402c-b9e2-ae0eeba16255', 'CHIPS', 'imagen_chips.jpg', 'Lays Campesina 190gr', 2.60, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('d27406a4-e427-4680-8da5-3fc23b28c7a3', 'POLLO', 'imagen_pollo.jpg', 'Pechuga de pollo LIDL', 6.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('08c0fd3a-fe17-454a-be1e-af29abbcec77', 'QUESO', 'imagen_queso.jpg', 'Queso Bouffard 400 gr', 15.99, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('8f271074-d8f7-4759-b7dd-072dfcec1c2e', 'QUESO', 'imagen_queso_867564.jpg', 'Queso loncha cheddar 200 gr ALDI', 1.69, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('995cef74-6ddb-4457-bbd7-ab735c774906', 'CHIPS', 'imagen_chips_451231.jpg', 'Ruffles Jamón 190 gr', 2.15, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('412264be-ddb5-4b46-8f7a-c9139d0c423d', 'CHIPS', 'imagen_chips_651124.jpg', 'Pringles Sour cream & onion 190 gr', 2.90, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('92ccf1ad-131d-4993-ae16-50c5f52d6522', 'MACARRON', 'imagen_macarron.jpg', 'Macarrones Pasta Eliges 500 gr', 1.10, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'a54b27e6-3208-40d0-b909-d282b4f0d58f');


