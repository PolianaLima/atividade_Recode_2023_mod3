CREATE DATABASE  IF NOT EXISTS `agencia_viagem_lazer_ferias` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agencia_viagem_lazer_ferias`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: agencia_viagem_lazer_ferias
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aeroportos`
--

DROP TABLE IF EXISTS `aeroportos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aeroportos` (
  `id_aeroporto` int NOT NULL AUTO_INCREMENT,
  `nome_aeroporto` varchar(255) NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_aeroporto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aeroportos`
--

LOCK TABLES `aeroportos` WRITE;
/*!40000 ALTER TABLE `aeroportos` DISABLE KEYS */;
INSERT INTO `aeroportos` VALUES (1,'AI BRASILIA','BRASILIA','DF','BRASIL'),(2,'GALEAO','RIO DE JANEIRO','RJ','BRASIL'),(3,'SENADOR PETRONIO PORTELA','TERESINA','PI','BRASIL'),(4,'AEROPORTO INTERNACIONAL DE SAO LUIS','SAO LUIS','MA','BRASIL'),(5,'AI RIO','RIO','RJ','CHINA');
/*!40000 ALTER TABLE `aeroportos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (2,'15614823743','ANDRE','BARBOZA','1994-02-26','M','21510640','RJ','RIO DE JANEIRO','RUA SERGIO DE SIQUEIRA DE MACEDO',31,'','ANDRE@HOTMAIL.COM','8649');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passageiros`
--

DROP TABLE IF EXISTS `passageiros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passageiros`
--

LOCK TABLES `passageiros` WRITE;
/*!40000 ALTER TABLE `passageiros` DISABLE KEYS */;
INSERT INTO `passageiros` (`id_passageiro`, `id_cliente`, `nome`, `sobrenome`, `data_nascimento`, `cpf`, `nacionalidade`) VALUES (2,2,'ALICE ','LIMA','2023-08-23','14625542399','BRASIL'),(3,2,'HELENA','LIMA','2021-10-13','14825546399','BRASIL'),(4,2,'ANDRE','BARBOZA','1994-02-26','15614823743','BRASIL');
/*!40000 ALTER TABLE `passageiros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (3,2,2,1,'RESERVADO');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voos`
--

DROP TABLE IF EXISTS `voos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voos`
--

LOCK TABLES `voos` WRITE;
/*!40000 ALTER TABLE `voos` DISABLE KEYS */;
INSERT INTO `voos` VALUES (1,437,'LATAM',2,1,'2023-09-24','2023-09-24','05:00:00',150,750.00,'RIO DE JANEIRO','BRASILIA'),(2,55,'AZUL',2,3,'2023-12-25','2023-12-25','05:00:00',200,600.00,'RIO DE JANEIRO','TERESINA'),(3,1130,'ALIANCA',4,1,'2024-01-01','2024-01-01','04:05:00',200,700.00,'SAO LUIS','BRASILIA');
/*!40000 ALTER TABLE `voos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-03 15:12:51
