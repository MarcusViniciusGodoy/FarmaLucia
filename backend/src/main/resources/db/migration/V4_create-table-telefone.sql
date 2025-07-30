CREATE TABLE telefone (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(10),
    ddd VARCHAR(3),
    usuario_id BIGINT,
    atendente_id BIGINT,
    CONSTRAINT fk_telefone_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_telefone_atendente FOREIGN KEY (atendente_id) REFERENCES atendente(id) ON DELETE CASCADE
);