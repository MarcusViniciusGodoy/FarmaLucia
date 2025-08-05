INSERT INTO tb_user (nome, email, senha) VALUES ('Maria Brown', 'maria@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');
INSERT INTO tb_user (nome, email, senha) VALUES ('Alex Green', 'alex@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_especialidade(name) VALUES ('Ortopedista');
INSERT INTO tb_especialidade(name) VALUES ('Cardiologista');

INSERT INTO tb_medico (nome, email, senha, crm) VALUES ('João', 'joao@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO', '222');
INSERT INTO tb_medico (nome, email, senha, crm) VALUES ('Milena', 'milena@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO', '333');

INSERT INTO tb_medico_especialidade (medico_id, especialidade_id) VALUES (1, 1);
INSERT INTO tb_medico_especialidade (medico_id, especialidade_id) VALUES (2, 2);
