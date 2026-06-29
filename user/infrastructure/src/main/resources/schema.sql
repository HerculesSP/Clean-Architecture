CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    cargo ENUM('DIRETOR', 'GERENTE', 'ANALISTA', 'ESTAGIARIO') NOT NULL,
    superior_id INT NULL,

    CONSTRAINT fk_usuario_superior
        FOREIGN KEY (superior_id)
        REFERENCES usuarios(id)
);