CREATE TABLE IF NOT EXISTS `Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE IF NOT EXISTS `Fisico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `renda` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `cpf`),
  CONSTRAINT `id`
  FOREIGN KEY (`id`) REFERENCES `Cliente` (`id`));

CREATE TABLE IF NOT EXISTS `Juridico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cnpj` VARCHAR(45) NOT NULL,
  `porte` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `cnpj`),
  CONSTRAINT `idjuridico`
  FOREIGN KEY (`id`)  REFERENCES `Cliente` (`id`));

CREATE TABLE IF NOT EXISTS `Entrega` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(45) NOT NULL,
  `frete` FLOAT NOT NULL,
  `data_despache` DATETIME NOT NULL,
  `data_recepcao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT C1 CHECK(data_recepcao > data_despache));

CREATE TABLE IF NOT EXISTS `Pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `Venda` (
  cliente INT NOT NULL,
  entrega INT NOT NULL,
  pedido INT NOT NULL,
  `data_pagamento` DATETIME NOT NULL,
  `desconto` FLOAT NOT NULL,
  `nota_fiscal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (cliente, entrega, pedido),
  CONSTRAINT `idcliente`
  FOREIGN KEY (cliente)
  REFERENCES `Cliente` (`id`),
  CONSTRAINT `identrega`
  FOREIGN KEY (entrega)
  REFERENCES `Entrega` (`id`),
  CONSTRAINT `idpedido`
  FOREIGN KEY (entrega)
  REFERENCES `Pedido` (`id`));

CREATE TABLE IF NOT EXISTS `Produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `Item` (
  produto INT NOT NULL,
  `pedido` INT NOT NULL,
  `id` INT NOT NULL,
  `quantidade` FLOAT NOT NULL,
  `lote` VARCHAR(45) NOT NULL,
  PRIMARY KEY (produto, pedido, `id`),
  CONSTRAINT `idprodutoitem`
  FOREIGN KEY (produto)
  REFERENCES `Produto` (`id`),
  CONSTRAINT `idpedidoitem`
  FOREIGN KEY (pedido)
  REFERENCES `Pedido` (`id`));

CREATE TABLE IF NOT EXISTS `Preco` (
  produto INT NOT NULL AUTO_INCREMENT,
  `preco` FLOAT NOT NULL,
  `data` DATETIME NOT NULL,
  PRIMARY KEY (produto, `data`),
  CONSTRAINT `idprodutopreco`
  FOREIGN KEY (produto)
  REFERENCES `Produto` (`id`));

CREATE TABLE IF NOT EXISTS Usuario
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE ,
  password BINARY(32) NOT NULL,
  salt BINARY(32) NOT NULL,
  root BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE UNIQUE INDEX Usuario_usuario_uindex ON Usuario (usuario);