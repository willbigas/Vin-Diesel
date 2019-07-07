-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: vindiesel
-- ------------------------------------------------------
-- Server version	10.1.38-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destinatario`
--

DROP TABLE IF EXISTS `destinatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destinatario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `codigoPessoa` varchar(18) NOT NULL,
  `endereco_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqvbnd31qb9nkbir4nbkk9oi08` (`endereco_id`),
  CONSTRAINT `FKqvbnd31qb9nkbir4nbkk9oi08` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dimensao`
--

DROP TABLE IF EXISTS `dimensao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dimensao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comprimento` decimal(10,0) DEFAULT NULL,
  `largura` decimal(10,0) DEFAULT NULL,
  `altura` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `encomenda`
--

DROP TABLE IF EXISTS `encomenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encomenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigoRastreio` varchar(255) DEFAULT NULL,
  `peso` decimal(10,2) NOT NULL,
  `dimensao_id` int(11) NOT NULL,
  `valorNotaFiscal` double(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7svlcxvyf6o638h48wppior7y` (`dimensao_id`),
  CONSTRAINT `FK7svlcxvyf6o638h48wppior7y` FOREIGN KEY (`dimensao_id`) REFERENCES `dimensao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cep` varchar(8) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `rua` varchar(45) NOT NULL,
  `complemento` varchar(45) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrega` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valorTotal` decimal(10,2) NOT NULL,
  `dataCadastro` datetime NOT NULL,
  `dataEntrega` datetime DEFAULT NULL,
  `entregue` tinyint(4) NOT NULL,
  `remetente_id` int(11) NOT NULL,
  `destinatario_id` int(11) NOT NULL,
  `encomenda_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa9h2x8ket7hhfolda74si3pvt` (`destinatario_id`),
  KEY `FK585q4q4k5nl0uxsbarh7o5sy7` (`encomenda_id`),
  KEY `FKbfjakt56i8ee3aa15njw8s54x` (`remetente_id`),
  CONSTRAINT `FK585q4q4k5nl0uxsbarh7o5sy7` FOREIGN KEY (`encomenda_id`) REFERENCES `encomenda` (`id`),
  CONSTRAINT `FKa9h2x8ket7hhfolda74si3pvt` FOREIGN KEY (`destinatario_id`) REFERENCES `destinatario` (`id`),
  CONSTRAINT `FKbfjakt56i8ee3aa15njw8s54x` FOREIGN KEY (`remetente_id`) REFERENCES `remetente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formapagamento`
--

DROP TABLE IF EXISTS `formapagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formapagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `receita`
--

DROP TABLE IF EXISTS `receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataCadastro` datetime NOT NULL,
  `dataPagamento` datetime DEFAULT NULL,
  `dataVencimento` date NOT NULL,
  `valorTotal` decimal(9,2) NOT NULL,
  `valorRecebido` decimal(9,2) DEFAULT NULL,
  `entrega_id` int(11) NOT NULL,
  `formaPagamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj0qusp912bc7m56dfihogjjli` (`entrega_id`),
  KEY `FKfwdsr5bmj4tfkbxmsg2306scl` (`formaPagamento_id`),
  CONSTRAINT `FKfwdsr5bmj4tfkbxmsg2306scl` FOREIGN KEY (`formaPagamento_id`) REFERENCES `formapagamento` (`id`),
  CONSTRAINT `FKj0qusp912bc7m56dfihogjjli` FOREIGN KEY (`entrega_id`) REFERENCES `entrega` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `remetente`
--

DROP TABLE IF EXISTS `remetente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remetente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `codigoPessoa` varchar(18) NOT NULL,
  `endereco_id` int(11) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiq8k1c56e7qsmn2eiwo7mnwvm` (`endereco_id`),
  CONSTRAINT `FKiq8k1c56e7qsmn2eiwo7mnwvm` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipotramite`
--

DROP TABLE IF EXISTS `tipotramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipotramite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `tipoPermissao` int(2) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tramite`
--

DROP TABLE IF EXISTS `tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tramite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datahora` datetime NOT NULL,
  `nome` varchar(45) NOT NULL,
  `observacao` text,
  `entrega_id` int(11) NOT NULL,
  `tipoTramite_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3m79gmua8okbv6a9k25cjxxdy` (`entrega_id`),
  KEY `FKtrpk4brf3alv0yy8bk3j008x0` (`tipoTramite_id`),
  CONSTRAINT `FK3m79gmua8okbv6a9k25cjxxdy` FOREIGN KEY (`entrega_id`) REFERENCES `entrega` (`id`),
  CONSTRAINT `FKtrpk4brf3alv0yy8bk3j008x0` FOREIGN KEY (`tipoTramite_id`) REFERENCES `tipotramite` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `dataNascimento` date NOT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  `numeroPis` int(20) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  `endereco_id` int(11) NOT NULL,
  `tipoUsuario_id` int(11) NOT NULL,
  `pis` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpjxurfuxsxph7n9qv4q8ougq` (`endereco_id`),
  KEY `FKl70vdy3uwxsyhb9h3vnp29q74` (`tipoUsuario_id`),
  CONSTRAINT `FKjpjxurfuxsxph7n9qv4q8ougq` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  CONSTRAINT `FKl70vdy3uwxsyhb9h3vnp29q74` FOREIGN KEY (`tipoUsuario_id`) REFERENCES `tipousuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-07 20:18:31
