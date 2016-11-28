CREATE TABLE `Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `Fisico` (
  `id` INT NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `renda` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `cpf`),
  CONSTRAINT `id`
  FOREIGN KEY (`id`) REFERENCES `Cliente` (`id`));

CREATE TABLE `Juridico` (
  `id` INT NOT NULL,
  `cnpj` VARCHAR(45) NOT NULL,
  `porte` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `cnpj`),
  CONSTRAINT `idjuridico`
  FOREIGN KEY (`id`)  REFERENCES `Cliente` (`id`));

CREATE TABLE `Entrega` (
  `id` INT NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `frete` FLOAT NOT NULL,
  `data_despache` DATETIME NOT NULL,
  `data_recepcao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT C1 CHECK(data_recepcao > data_despache));

CREATE TABLE `Pedido` (
  `id` INT NOT NULL,
  `data` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `Venda` (
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

CREATE TABLE `Produto` (
  `id` INT NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `Item` (
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

CREATE TABLE `Preco` (
  produto INT NOT NULL,
  `id` INT NOT NULL,
  `preco` FLOAT NOT NULL,
  `data` DATETIME NOT NULL,
  PRIMARY KEY (produto, `id`),
  CONSTRAINT `idprodutopreco`
  FOREIGN KEY (produto)
  REFERENCES `Produto` (`id`));