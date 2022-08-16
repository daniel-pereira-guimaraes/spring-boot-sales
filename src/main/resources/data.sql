INSERT INTO tb_user(login_name, password, is_enabled) VALUES('fulano', '123', true);
INSERT INTO tb_user(login_name, password, is_enabled) VALUES('beltrano', 'abc', true);
INSERT INTO tb_user(login_name, password, is_enabled) VALUES('ciclano', 'xyz', false);

INSERT INTO person(name, tax_id) VALUES('FULANO DE TAL', '12345678901');
INSERT INTO person(name, tax_id) VALUES('BELTRANO DA SILVA', '98765432101');
INSERT INTO person(name, tax_id) VALUES('CICLANO DE SOUZA', '11122233344');

INSERT INTO product(gtin, description, quantity, cost_price, sale_price) VALUES('1234567890', 'LÁPIS LABRA PRETO', 200, 1.5, 2.5);
INSERT INTO product(gtin, description, quantity, cost_price, sale_price) VALUES('1234567891', 'CANETA BIC ESFEROGRÁFICA', 500, 2.5, 4.25);
INSERT INTO product(gtin, description, quantity, cost_price, sale_price) VALUES('1234567892', 'BORRACHA LABRA BRANCA PEQUENA', 100, 0.85, 1.65);

INSERT INTO sale(date, seller_id, customer_id) VALUES('2022-08-10', 1, 3);
INSERT INTO sale(date, seller_id, customer_id) VALUES('2022-08-10', 2, null);
INSERT INTO sale(date, seller_id, customer_id) VALUES('2022-08-10', 1, null);
INSERT INTO sale(date, seller_id, customer_id) VALUES('2022-08-14', 2, 1);
INSERT INTO sale(date, seller_id, customer_id) VALUES('2022-08-14', 1, null);

INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(1, 1, 10, 100, 150);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(1, 2, 20, 200, 150);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(1, 3, 30, 300, 350);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(2, 1, 10, 100, 150);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(2, 3, 30, 300, 350);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(3, 1, 30, 100, 160);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(3, 2, 30, 200, 260);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(4, 2, 30, 200, 270);
INSERT INTO sale_item(sale_id, product_id, quantity, cost_price, sale_price) VALUES(5, 3, 30, 300, 350);
