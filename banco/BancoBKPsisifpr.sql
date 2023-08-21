CREATE DATABASE ifsis;
USE ifsis;

CREATE TABLE usuario (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
email VARCHAR(100),
senha VARCHAR(100)
);
ALTER TABLE usuario
ADD COLUMN status INT(1) DEFAULT 1;

CREATE TABLE grupo (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) not null
);

CREATE TABLE categoria (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) not null
);

CREATE TABLE cliente(
	id INT PRIMARY KEY auto_increment,
    nome varchar(100) NOT NULL,
    tipoCliente int(1) CHECK (tipoCliente IN (0, 1)) NOT NULL,
    cpfCnpj varchar(15) not null,
    telefone varchar(20),
    email varchar(100),
    observacao varchar(250),
    datacadastro datetime default current_timestamp
);

INSERT INTO cliente (nome, tipoCliente, cpfCnpj, telefone, email, observacao)
VALUES
    ('João Silva', 1, '123.456.789-01', '(11) 9876-5432', 'joao@email.com', 'Cliente VIP'),
    ('Maria Santos', 0, '987.654.321-09', '(21) 5555-1234', 'maria@email.com', 'Cliente novo'),
    ('Carlos Almeida', 1, '555.555.555-55', '(31) 3333-7777', 'carlos@email.com', 'Cliente antigo'),
    ('Ana Oliveira', 0, '111.222.333-44', '(41) 2222-1111', 'ana@email.com', 'Cliente preferencial');

INSERT INTO grupo (nome) VALUES 
('Funcionario'), 
('Gerente'),
('Cletin');

INSERT INTO categoria (nome) VALUES 
('Nestlé S.A.'), 
('Nestlé Purina'),
('Nestlé Nespresso');
