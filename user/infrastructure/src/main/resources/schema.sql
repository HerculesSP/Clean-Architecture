CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    cargo ENUM('Diretor', 'Gerente', 'Analista', 'Estagiário') NOT NULL,
    supervisor_id INT NULL,

    CONSTRAINT fk_usuario_supervisor
        FOREIGN KEY (supervisor_id)
        REFERENCES usuarios(id)
);