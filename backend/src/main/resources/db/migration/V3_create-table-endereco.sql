CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    numero BIGINT,
    complemento VARCHAR(10),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(9),
    usuario_id BIGINT,
    atendente_id BIGINT,
    CONSTRAINT fk_telefone_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_telefone_atendente FOREIGN KEY (atendente_id) REFERENCES atendente(id) ON DELETE CASCADE
);