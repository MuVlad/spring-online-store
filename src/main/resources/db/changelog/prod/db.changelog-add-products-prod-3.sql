--liquibase formatted sql

--changeset muslimov_vlad:8
INSERT INTO products (id, title, price)
VALUES (1, 'Cheese', 450.0),
       (2, 'Beer', 45.0),
       (3, 'Milk', 65.0),
       (4, 'Tomato', 115.0),
       (5, 'Bread', 58.0);

ALTER SEQUENCE product_seq RESTART WITH 6;
