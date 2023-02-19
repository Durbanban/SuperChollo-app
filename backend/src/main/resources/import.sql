-- password 1234
INSERT INTO usuario (id, username, password, avatar, full_name  , account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'user', '{bcrypt}$2a$12$7bG1Ds4qfiK.uyxjtSjtnu7Lhn2ruuk1EZPFMB7T9jBjDFO2xw.yq', 'imagen', 'Usuario González', true, true, true, true, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password 12345678
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('6f705ab2-6ff9-4068-87f8-4c4932c8d73f', 'ramoncin23', '{bcrypt}$2a$12$jIVXp55gD0dyoJzgYlltYOwVJoJ3WEy/VvvIqR97lmE4LVaUjF9ry', 'imagen', 'Ramón Villanueva', true, true, true, true, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password 5678
INSERT INTO usuario (id, username, password, avatar, full_name, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, fecha_creado, fecha_modificado, fecha_cambio_password) VALUES ('7117fd18-7be7-4c60-88c6-c4fa9897d013', 'admin', '{bcrypt}$2a$12$MgkQqpKgw6NbbCy1Sz3EPOkQ31CGEovKyvV2Voxr0apdtuEEzPmk.', 'imagen', 'Administrador López', true, true, true, true, 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categoria (id, nombre) VALUES ('24261a63-5dd3-4f81-a0a4-3dc929f74ebe', 'Carnicería');
INSERT INTO categoria (id, nombre) VALUES ('0231a8be-af6a-487e-bd21-bd98c0caad39', 'Charcutería');
INSERT INTO categoria (id, nombre) VALUES ('8c37d098-af20-4ec8-ba75-a697a48d48de', 'Consumibles');
INSERT INTO categoria (id, nombre) VALUES ('8c28d654-eb8b-4e9d-a86d-a132662b143e', 'Lácteos');
INSERT INTO categoria (id, nombre) VALUES ('a54b27e6-3208-40d0-b909-d282b4f0d58f', 'Pasta');
INSERT INTO categoria (id, nombre) VALUES ('eefcf70d-5bbe-479a-a61b-1f5df283341d', 'Otros')

INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e0a31674-c439-4c1b-83b3-77236a762cf7', 'FiambrePavo', 'imagen_fiambrepavo.jpg', 'Pavo 200 gr ALDI', 1.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('9b05f55a-7fb8-4f1f-ad5f-97f42bdd9032', 'FiambrePavo', 'imagen_fiambrepavo_456214.jpg', 'Pavo finas lonchas 200 gr Mercadona', 2.35, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('39039a90-51fd-4c81-8c0a-6b58c7e9e4ec', 'Chorizo', 'imagen_chorizo.jpg', 'Chorizo 150 gr IFA Eliges', 1.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '0231a8be-af6a-487e-bd21-bd98c0caad39');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e161158b-77c3-4079-b499-89a9fff84406', 'Espagueti', 'imagen_espagueti.jpg', 'Espaguetis Pastas Gallo 1 Kg', 2.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'a54b27e6-3208-40d0-b909-d282b4f0d58f');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('2624030c-d07d-4eff-b351-54139076fad3', 'Yogur', 'imagen_yogur.jpg', 'Yogur Clesa Sabor Fresa pack-4', 3.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('e575dd6a-90c2-49b3-a89d-609f0a8b86cb', 'Yogur', 'imagen_yogur_412412.jpg', 'Yogur Danone Sabor Natural Edulcorado pack-4', 4.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('ef5c733d-b81a-4fda-992f-4d7b39e0ef7e', 'Ternera', 'imagen_ternera.jpg', 'Lomo bajo de vaca MAS', 18.99, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('8dd9e1b0-bef3-4aa9-a132-d7a30c3d254a', 'Ternera', 'imagen_ternera_857121.jpg', 'Solomillo de ternera El Jamón', 27.99, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('dfe188cc-4027-402c-b9e2-ae0eeba16255', 'Chips', 'imagen_chips.jpg', 'Lays Campesina 190gr', 2.60, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('d27406a4-e427-4680-8da5-3fc23b28c7a3', 'Pollo', 'imagen_pollo.jpg', 'Pechuga de pollo LIDL', 6.49, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '24261a63-5dd3-4f81-a0a4-3dc929f74ebe');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('08c0fd3a-fe17-454a-be1e-af29abbcec77', 'Queso', 'imagen_queso.jpg', 'Queso Bouffard 400 gr', 15.99, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('8f271074-d8f7-4759-b7dd-072dfcec1c2e', 'Queso', 'imagen_queso_867564.jpg', 'Queso loncha cheddar 200 gr ALDI', 1.69, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c28d654-eb8b-4e9d-a86d-a132662b143e');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('995cef74-6ddb-4457-bbd7-ab735c774906', 'Chips', 'imagen_chips_451231.jpg', 'Ruffles Jamón 190 gr', 2.15, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('412264be-ddb5-4b46-8f7a-c9139d0c423d', 'Chips', 'imagen_chips_651124.jpg', 'Pringles Sour cream & onion 190 gr', 2.90, '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '8c37d098-af20-4ec8-ba75-a697a48d48de');
INSERT INTO producto (id, generico, imagen, nombre, precio, autor_id, categoria_id) VALUES ('92ccf1ad-131d-4993-ae16-50c5f52d6522', 'Macarrón', 'imagen_macarron.jpg', 'Macarrones Pasta Eliges 500 gr', 1.10, '89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'a54b27e6-3208-40d0-b909-d282b4f0d58f');

INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('e0a31674-c439-4c1b-83b3-77236a762cf7', '89a8725e-ebd8-40fa-9caa-105b742c4ea0', CURRENT_TIMESTAMP, 9);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('e0a31674-c439-4c1b-83b3-77236a762cf7', '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', CURRENT_TIMESTAMP, 10);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('9b05f55a-7fb8-4f1f-ad5f-97f42bdd9032', '89a8725e-ebd8-40fa-9caa-105b742c4ea0', CURRENT_TIMESTAMP, 7);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('dfe188cc-4027-402c-b9e2-ae0eeba16255', '89a8725e-ebd8-40fa-9caa-105b742c4ea0', CURRENT_TIMESTAMP, 7);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('08c0fd3a-fe17-454a-be1e-af29abbcec77', '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', CURRENT_TIMESTAMP, 9);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('92ccf1ad-131d-4993-ae16-50c5f52d6522', '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', CURRENT_TIMESTAMP, 6);
INSERT INTO rating (producto_id, usuario_id, fecha_rating, nota) VALUES ('2624030c-d07d-4eff-b351-54139076fad3', '6f705ab2-6ff9-4068-87f8-4c4932c8d73f', CURRENT_TIMESTAMP, 8);


INSERT INTO supermercado (id, address, nombre) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', 'Calle Virgen de Loreto 35', 'LIDL');
INSERT INTO supermercado (id, address, nombre) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', 'Calle Salado s/n', 'Mercadona');
INSERT INTO supermercado (id, address, nombre) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', 'Calle Virgen de Montserrat 9', 'MAS');
INSERT INTO supermercado (id, address, nombre) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', 'Calle Santa Fe 3-5-7', 'MAS');
INSERT INTO supermercado (id, address, nombre) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', 'Avenida de la República Argentina 27', 'DIA');
INSERT INTO supermercado (id, address, nombre) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', 'Calle Evangelista 50', 'El Jamón');
INSERT INTO supermercado (id, address, nombre) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', 'Calle Virgen de Setefilla 3', 'ALDI');

INSERT INTO preferido (usuario_id, supermercado_id) VALUES ('89a8725e-ebd8-40fa-9caa-105b742c4ea0', 'b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a');
INSERT INTO preferido (usuario_id, supermercado_id) VALUES ('89a8725e-ebd8-40fa-9caa-105b742c4ea0', '3ff87f82-177f-40fa-978c-8df7b41a757f');
INSERT INTO preferido (usuario_id, supermercado_id) VALUES ('6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '3ff87f82-177f-40fa-978c-8df7b41a757f');
INSERT INTO preferido (usuario_id, supermercado_id) VALUES ('6f705ab2-6ff9-4068-87f8-4c4932c8d73f', '33eed5ae-ed92-484e-9578-b50407af4b82');
INSERT INTO preferido (usuario_id, supermercado_id) VALUES ('6f705ab2-6ff9-4068-87f8-4c4932c8d73f', 'd8e99345-5b31-43a0-96ca-1f059c5ffbd9');

INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', 'd27406a4-e427-4680-8da5-3fc23b28c7a3');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('b7b86f11-ccec-46fd-b3d4-dd1cb3268e6a', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', '9b05f55a-7fb8-4f1f-ad5f-97f42bdd9032');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', '2624030c-d07d-4eff-b351-54139076fad3');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', '08c0fd3a-fe17-454a-be1e-af29abbcec77');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('d8e99345-5b31-43a0-96ca-1f059c5ffbd9', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', '39039a90-51fd-4c81-8c0a-6b58c7e9e4ec');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', 'ef5c733d-b81a-4fda-992f-4d7b39e0ef7e');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', '08c0fd3a-fe17-454a-be1e-af29abbcec77');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('f223e31f-3f6c-497d-ae30-bd59f7d39928', '92ccf1ad-131d-4993-ae16-50c5f52d6522');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', '39039a90-51fd-4c81-8c0a-6b58c7e9e4ec');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', '2624030c-d07d-4eff-b351-54139076fad3');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', 'ef5c733d-b81a-4fda-992f-4d7b39e0ef7e');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('3ff87f82-177f-40fa-978c-8df7b41a757f', '92ccf1ad-131d-4993-ae16-50c5f52d6522');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', '2624030c-d07d-4eff-b351-54139076fad3');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('2b4072df-a6ca-440d-ad44-74c6c49570eb', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', '39039a90-51fd-4c81-8c0a-6b58c7e9e4ec');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', '8dd9e1b0-bef3-4aa9-a132-d7a30c3d254a');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', '412264be-ddb5-4b46-8f7a-c9139d0c423d');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('caec4dc0-761a-4ae1-9904-a3560a40faa9', '92ccf1ad-131d-4993-ae16-50c5f52d6522');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', 'e0a31674-c439-4c1b-83b3-77236a762cf7');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', 'e161158b-77c3-4079-b499-89a9fff84406');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', 'e575dd6a-90c2-49b3-a89d-609f0a8b86cb');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', 'dfe188cc-4027-402c-b9e2-ae0eeba16255');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', '8f271074-d8f7-4759-b7dd-072dfcec1c2e');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', '995cef74-6ddb-4457-bbd7-ab735c774906');
INSERT INTO catalogo (supermercado_id, producto_id) VALUES ('33eed5ae-ed92-484e-9578-b50407af4b82', '412264be-ddb5-4b46-8f7a-c9139d0c423d');





















