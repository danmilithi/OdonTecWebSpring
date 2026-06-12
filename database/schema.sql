CREATE DATABASE IF NOT EXISTS odontecdb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE odontecdb;

CREATE TABLE IF NOT EXISTS paciente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    data_nascimento DATE,
    cep VARCHAR(15),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dentista (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    especialidade VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS fornecedor (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    produto VARCHAR(150),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS produto (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    fornecedor VARCHAR(150),
    quantidade INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tratamento (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    duracao DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS agenda (
    id INT NOT NULL AUTO_INCREMENT,
    paciente VARCHAR(150) NOT NULL,
    dentista VARCHAR(150) NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    tratamento VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario (
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('GERENTE', 'ATENDENTE', 'FINANCEIRO', 'DENTISTA') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_login (login)
);

INSERT INTO usuario (login, senha, tipo_usuario)
SELECT 'Daniel', '123', 'GERENTE'
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE login = 'Daniel');
