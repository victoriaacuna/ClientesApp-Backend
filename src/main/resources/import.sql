INSERT INTO region (name) VALUES('Norte América');
INSERT INTO region (name) VALUES('Sur América');
INSERT INTO region (name) VALUES('América Central');
INSERT INTO region (name) VALUES('Europa');
INSERT INTO region (name) VALUES('Oceanía');
INSERT INTO region (name) VALUES('Asia');
INSERT INTO region (name) VALUES('Antártida');

INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'vics@gmail.com', 'Acuña', 'Victoria', '1'); 
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'fabi@gmail.com', 'Acuña', 'Fabiana', '2');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'anibal@gmail.com', 'Acuña', 'Aníbal', '3');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'gra@gmail.com', 'Ramírez', 'Graciela', '4');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'pats@gmail.com', 'Borrero', 'Patricia', '5');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'veka@gmail.com', 'Silva', 'Valeska', '6');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'val@gmail.com', 'Trotta', 'Valeria', '1');
INSERT INTO client (create_at, email, last_name, name, region_id) VALUES('2020-09-08', 'elena@gmail.com', 'Martellacci', 'Elena', '1');

INSERT INTO user (username, password, enabled, name, last_name, email) VALUES('victoria', '$2a$10$ngXJLm5J63oCi2uJPddtPubqDwjG2KemMIARLAV6isKtNx3frkvMG', 1, 'Victoria','Acuña','vics@gmail.com'); 
INSERT INTO user (username, password, enabled, name, last_name, email) VALUES('admin', '$2a$10$NwPLnfMkR48ayUn8Od8vZ.7aCDcfzSv0MNDFZqGlOOoUT1aG32WIK', 1, 'Fabiana','Acuña','fab@gmail.com');

INSERT INTO rol (name) VALUES('ROLE_USER'); 
INSERT INTO rol (name) VALUES('ROLE_ADMIN');

INSERT INTO user_rol (user_id, rol_id) VALUES(1,1); 
INSERT INTO user_rol (user_id, rol_id) VALUES(2,2);
INSERT INTO user_rol (user_id, rol_id) VALUES(2,1);

INSERT INTO product (name, price, create_at) VALUES('iPad Pro 8', 900, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPod 6', 550, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPhone 11 Max', 1250, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPhone 11', 699, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPhone X', 499, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPhone 8', 399, NOW());
INSERT INTO product (name, price, create_at) VALUES('iPhone X MAX', 999, NOW());
INSERT INTO product (name, price, create_at) VALUES('Apple Watch Series 6', 399, NOW());
INSERT INTO product (name, price, create_at) VALUES('MacBook Air', 1299, NOW());
INSERT INTO product (name, price, create_at) VALUES('MacBook Pro Touch Bar', 2499, NOW());

INSERT INTO bill (description, observation, client_id, create_at) VALUES('Factura 2 iPhones (8 y X)', null, 1, NOW());
INSERT INTO bill_item (quantity, bill_id, product_id) VALUES(1,1,6);
INSERT INTO bill_item (quantity, bill_id, product_id) VALUES(1,1,5);

INSERT INTO bill (description, observation, client_id, create_at) VALUES('Compra Regalo cumpleaños', 'Importante entregar antes del 20 de septiembre', 2, NOW());
INSERT INTO bill_item (quantity, bill_id, product_id) VALUES(2,2,7);
INSERT INTO bill_item (quantity, bill_id, product_id) VALUES(2,2,1);

INSERT INTO bill (description, observation, client_id, create_at) VALUES('Factura 5 Apple Watch', null, 1, NOW());
INSERT INTO bill_item (quantity, bill_id, product_id) VALUES(5,3,8)




