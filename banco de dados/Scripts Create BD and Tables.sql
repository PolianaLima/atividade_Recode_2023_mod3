CREATE DATABASE  IF NOT EXISTS `agencia_viagem_lazer_ferias`;
USE `agencia_viagem_lazer_ferias`;

DROP TABLE IF EXISTS `aeroportos`;
CREATE TABLE `aeroportos` (
  `id_aeroporto` int NOT NULL AUTO_INCREMENT,
  `nome_aeroporto` varchar(255) NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_aeroporto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `Sobrenome` varchar(50) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `genero` varchar(1) DEFAULT NULL,
  `cep` varchar(8) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `cidade` varchar(70) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `voos`;
CREATE TABLE `voos` (
  `id_voo` int NOT NULL AUTO_INCREMENT,
  `numero_voo` int NOT NULL,
  `companhia_aerea` varchar(255) NOT NULL,
  `aeroporto_partida` int DEFAULT NULL,
  `aeroporto_chegada` int DEFAULT NULL,
  `data_partida` date NOT NULL,
  `data_chegada` date NOT NULL,
  `duracao_voo` time NOT NULL,
  `numero_assentos` int NOT NULL,
  `preco_voo` decimal(10,2) NOT NULL,
  `origem` varchar(45) NOT NULL,
  `destino` varchar(45) NOT NULL,
  PRIMARY KEY (`id_voo`),
  UNIQUE KEY `numero_voo_UNIQUE` (`numero_voo`),
  KEY `voos_ibfk_1` (`aeroporto_partida`),
  KEY `voos_ibfk_2` (`aeroporto_chegada`),
  CONSTRAINT `voos_ibfk_1` FOREIGN KEY (`aeroporto_partida`) REFERENCES `aeroportos` (`id_aeroporto`),
  CONSTRAINT `voos_ibfk_2` FOREIGN KEY (`aeroporto_chegada`) REFERENCES `aeroportos` (`id_aeroporto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas` (
  `id_reserva` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `id_voo` int DEFAULT NULL,
  `numero_passageiro` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_voo` (`id_voo`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_voo`) REFERENCES `voos` (`id_voo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `passageiros`;
CREATE TABLE `passageiros` (
  `id_passageiro` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `sobrenome` varchar(255) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `nacionalidade` varchar(50) DEFAULT NULL,
  `numero_bilhete` int GENERATED ALWAYS AS ((`id_cliente` * 5)) STORED,
  PRIMARY KEY (`id_passageiro`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `passageiros_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
