INSERT INTO tb_restaurante (id, nome, endereco) VALUES (1, 'Filial', 'Rua Dois, Centro - RJ');

INSERT INTO tb_item_cardapio (id, titulo, detalhe, preco, tipo, id_restaurante) VALUES (1, 'Strogonoff Camarão', '', 29.9, 1, 1);

INSERT INTO tb_mesa (id, id_restaurante, qrcode) VALUES (1, 1, 'teste');