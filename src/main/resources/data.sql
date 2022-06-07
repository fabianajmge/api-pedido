INSERT INTO tb_restaurante (id, nome, endereco) VALUES (1, 'Filial', 'Rua Dois, Centro - RJ');
INSERT INTO tb_restaurante (id, nome, endereco) VALUES (2, 'Unidade 2', 'Rua Tres, Centro - RJ');


INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (1, 'Filé de Merluza com purê, arroz e feijão', '', 29.9, 0, 1);
INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (2, 'Costela no bafo, arroz, feijão e farofa', '', 25.0, 0, 1);
INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (4, 'Porção de isca de peixe a milanesa', '', 59.8, 0, 1);
INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (5, 'Filé de frango grelhado, arroz e fritas', '', 19.8, 0, 1);

INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (6, 'H2O Limão', '', 6.9, 1, 1);
INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (7, 'Coca Cola', '', 6.9, 1, 1);

INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (8, 'Petit Gateau', '', 19.9, 2, 1);
INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (9, 'Pudim', '', 6.9, 2, 1);

INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (3, 'Strogonoff Carne', '', 29.9, 0, 2);


INSERT INTO tb_mesa (id, id_restaurante, qrcode) VALUES (1, 1, 'teste');
INSERT INTO tb_mesa (id, id_restaurante, qrcode) VALUES (2, 1, 'teste');
INSERT INTO tb_mesa (id, id_restaurante, qrcode) VALUES (3, 2, 'teste');