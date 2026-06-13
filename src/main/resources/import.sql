insert into usuario(nome, email, senha, perfil) values ('Gabriella Mittelstad', 'gaby@gmail.com', '$2a$10$YGGN3roE/W7MgJUJC2QFWOv5ikaVNbVXA9XvQTLhHM/3tx0LzKsX.', 1);
insert into usuario(nome, email, senha, perfil) values ('Cliente', 'cliente@gmail.com', '$2a$10$YGGN3roE/W7MgJUJC2QFWOv5ikaVNbVXA9XvQTLhHM/3tx0LzKsX.', 2);

insert into formato(nome) values ('Redondo');
insert into formato(nome) values ('Quadrado');
insert into formato(nome) values ('Triangular');
insert into formato(nome) values ('Oval');
insert into formato(nome) values ('Coração');

insert into cor(nome) values ('Dourado');
insert into cor(nome) values ('Prateado');
insert into cor(nome) values ('Cobre');
insert into cor(nome) values ('Azul');
insert into cor(nome) values ('Vermelho');

insert into colecao(nome) values ('Coleção Verão');
insert into colecao(nome) values ('Coleção Inverno');
insert into colecao(nome) values ('Coleção Primavera');

insert into material(nome) values ('Ouro');
insert into material(nome) values ('Prata');
insert into material(nome) values ('Cobre');
insert into material(nome) values ('Aço Inoxidável');
insert into material(nome) values ('Plástico');

insert into produto(DTYPE, tamanho, id_colecao, id_formato, nome, preco, estoque) values ('Brinco', 0.5, 1, 1, 'Brinco Redondo Dourado', 50.00, 100);
insert into produto(DTYPE, tamanho, id_colecao, id_formato, nome, preco, estoque) values ('Brinco', 0.7, 2, 2, 'Brinco Quadrado Prateado', 75.00, 80);
insert into produto(DTYPE, tamanho, id_colecao, id_formato, nome, preco, estoque) values ('Brinco', 0.3, 2, 3, 'Brinco Triangular Cobre', 45.00, 120);
insert into produto(DTYPE, tamanho, id_colecao, id_formato, nome, preco, estoque) values ('Brinco', 0.4, 3, 4, 'Brinco Oval Azul', 60.00, 90);
insert into produto(DTYPE, tamanho, id_colecao, id_formato, nome, preco, estoque) values ('Brinco', 0.6, 1, 5, 'Brinco Coração Vermelho', 75.50, 70);

-- Relacionamentos Many-to-Many: Produto -> Material
insert into produtos_materiais(id, id_material) values (1, 1);
insert into produtos_materiais(id, id_material) values (2, 2);
insert into produtos_materiais(id, id_material) values (3, 3);
insert into produtos_materiais(id, id_material) values (4, 4);
insert into produtos_materiais(id, id_material) values (5, 5);

-- Relacionamentos Many-to-Many: Produto -> Cor
insert into produtos_cores(id, id_cor) values (1, 1);
insert into produtos_cores(id, id_cor) values (2, 2);
insert into produtos_cores(id, id_cor) values (3, 3);
insert into produtos_cores(id, id_cor) values (4, 4);
insert into produtos_cores(id, id_cor) values (5, 5);

insert into enderecousuario (pais, estado, cidade, bairro, cep, complemento, numero, usuario_id) values ('Brasil', 'SP', 'São Paulo', 'Centro', '01234-567', 'Apto 101', '123', 1);
