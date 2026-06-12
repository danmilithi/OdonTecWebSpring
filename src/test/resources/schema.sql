DROP TABLE IF EXISTS agenda;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS tratamento;
DROP TABLE IF EXISTS fornecedor;
DROP TABLE IF EXISTS dentista;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS usuario;

CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    data_nascimento DATE,
    cep VARCHAR(15)
);

CREATE TABLE dentista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    especialidade VARCHAR(100)
);

CREATE TABLE fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    produto VARCHAR(150)
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    fornecedor VARCHAR(150),
    quantidade INT NOT NULL
);

CREATE TABLE tratamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    duracao DOUBLE
);

CREATE TABLE agenda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente VARCHAR(150) NOT NULL,
    dentista VARCHAR(150) NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    tratamento VARCHAR(150) NOT NULL
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL
);

INSERT INTO usuario (login, senha, tipo_usuario) VALUES ('Daniel', '123', 'GERENTE');
