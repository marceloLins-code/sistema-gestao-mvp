insert into setor (id, nome) values (1, 'Tecnologia');
insert into setor (id, nome) values (2, 'Administração');

insert into cargo (id, nome, setor_id) values (1, 'Desenvolvedor', 1);
insert into cargo (id, nome, setor_id) values (2, 'Gerente', 2);

insert into trabalhador (id, nome, cpf, setor_id, cargo_id) values (1, 'Marcelo', 773, 1, 1);
insert into trabalhador (id, nome, cpf, setor_id, cargo_id) values (2, 'Ingrid', 521, 2, 2);