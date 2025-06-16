CREATE TABLE users (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco_rua VARCHAR(255),
    endereco_cidade VARCHAR(255),
    endereco_estado VARCHAR(50),
    endereco_cep VARCHAR(20)
);