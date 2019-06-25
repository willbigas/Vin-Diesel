-- MySQL dump 10.16  Distrib 10.1.36-MariaDB, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: vindiesel
-- ------------------------------------------------------
-- Server version	10.1.36-MariaDB

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinatario`
--

LOCK TABLES `destinatario` WRITE;
/*!40000 ALTER TABLE `destinatario` DISABLE KEYS */;
INSERT INTO `destinatario` VALUES (1,'Daniela Joana Santos','710.889.659-11',15),(2,'Cecília Bruna da Mota','102.854.359-06',16),(3,'Marina Bianca Campos','174.505.089-23',17),(4,'Renan Samuel Jorge Novaes','313.106.819-10',18),(5,'Mirella Manuela Pinto','226.726.569-91',19),(6,'Sandra e Olivia Fotografias ME','12.284.265/0001-48',20),(7,'Aparecida e Henrique Mudanças ME','82.127.493/0001-90',21),(8,'Vitória e Joaquim Marketing Ltda','96.293.710/0001-26',22),(9,'Raquel e Pedro Henrique Restaurante ME','16.715.915/0001-13',23),(10,'Silvana e Kauê Comercio de Bebidas ME','82.757.110/0001-67',24);
/*!40000 ALTER TABLE `destinatario` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dimensao`
--

LOCK TABLES `dimensao` WRITE;
/*!40000 ALTER TABLE `dimensao` DISABLE KEYS */;
INSERT INTO `dimensao` VALUES (1,4,8,22),(2,2,6,14),(3,5,4,13),(4,7,5,16),(5,3,5,14);
/*!40000 ALTER TABLE `dimensao` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encomenda`
--

LOCK TABLES `encomenda` WRITE;
/*!40000 ALTER TABLE `encomenda` DISABLE KEYS */;
INSERT INTO `encomenda` VALUES (1,'VD-4EEA25CF',0.44,1,15.38),(2,'VD-9441980E',0.09,2,4.78),(3,'VD-6F14A79F',0.07,3,32.22),(4,'VD-A0F481AB',0.12,4,12.03),(5,'VD-D5ED66DE',0.43,5,29.77);
/*!40000 ALTER TABLE `encomenda` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'88133810','SC','Palhoça','Jardim das Palmeiras','Dália','Casa Marrom','538'),(2,'88130800','SC','Palhoça','Jardim Eldorado','Não sei o nome','Não sei','500'),(3,'13105838','SP','Campinas','Ville Sainte Hélène','Rua Monte Carlo','','183'),(4,'13105838','SP','Campinas','Ville Sainte Hélène','Rua Monte Carlo','','183'),(5,'13105838','SP','Campinas','Ville Sainte Hélène','Rua Monte Carlo','','183'),(6,'88708812','SC','Tubarão','São Martinho','Rua Euclides Cascaes','','227'),(7,'88310455','SC','Itajaí','Cordeiros','Rua Theodoro Benassi','','774'),(8,'88134040','SC','Palhoça','Barra do Aririú','Rua Paulo José Ávila','','602'),(9,'88526130','SC','Lages','Popular','Rua Cândido Mendes Ouriques','','222'),(10,'88704290','SC','Tubarão','Dehon','Rua Luís Pedro Oliveira','de 303/304 a 1552/1553','580'),(11,'89256025','SC','Jaraguá do Sul','Vila Baependi','Rua Bertha L. Kassner','','573'),(12,'88345515','SC','Camboriú','Santa Regina','Rua Berna','','834'),(13,'88061350','SC','Florianópolis','Barra da Lagoa','Rua Estrela do Mar','','102'),(14,'89256550','SC','Jaraguá do Sul','Vila Lalau','Rua Germano Hansch','','436'),(15,'88161528','SC','Biguaçu','Fundos','Rua Kiliano Kons','','478'),(16,'88336050','SC','Balneário Camboriú','Nova Esperança','Rua Pedras Brancas','','770'),(17,'88501192','SC','Lages','Centro','Avenida Belisário Ramos','de 2402 a 2862 - lado par','745'),(18,'89040020','SC','Blumenau','Velha','Rua Araucária','','359'),(19,'89066780','SC','Blumenau','Itoupavazinha','Rua Dallas','','909'),(20,'88058481','SC','Florianópolis','Ingleses do Rio Vermelho','Servidão Caminho do Sítio','','843'),(21,'88330107','SC','Balneário Camboriú','Centro','4ª Avenida','de 283 a 551 - lado ímpar','681'),(22,'88117115','SC','São José','Campinas','Rua Pedro Paulo Kretzer','','819'),(23,'88035330','SC','Florianópolis','Santa Mônica','Rua Félix Kleis','','217'),(24,'89280439','SC','São Bento do Sul','Centro','Rua Antônio Van Den Boom','','468');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `formapagamento`
--

LOCK TABLES `formapagamento` WRITE;
/*!40000 ALTER TABLE `formapagamento` DISABLE KEYS */;
INSERT INTO `formapagamento` VALUES (1,'DINHEIRO'),(2,'DEPOSITO EM CONTA'),(3,'BOLETO');
/*!40000 ALTER TABLE `formapagamento` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita`
--

LOCK TABLES `receita` WRITE;
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
/*!40000 ALTER TABLE `receita` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remetente`
--

LOCK TABLES `remetente` WRITE;
/*!40000 ALTER TABLE `remetente` DISABLE KEYS */;
INSERT INTO `remetente` VALUES (1,'Martin e Ana Lavanderia Ltda','97.546.846/0001-63',5,'(19)3924-7612'),(2,'Milena e Hugo Filmagens Ltda','68.816.145/0001-07',6,'(48)2566-9150'),(3,'Marina e Emilly Buffet Ltda','79.383.898/0001-84',7,'(47)3517-9349'),(4,'Sophie e Bernardo Pizzaria Delivery Ltda','15.663.700/0001-33',8,'(48)3707-6049'),(5,'Kevin e Severino Informática Ltda','26.846.366/0001-70',9,'(49)3786-1655'),(6,'Geraldo Arthur Anthony Aparício','136.462.269-60',10,'(48)3584-1513'),(7,'Laura Giovana Fernanda Rocha','444.672.089-92',11,'(47)2780-2436'),(8,'Ryan Giovanni da Conceição','767.692.899-03',12,'(47)3742-0145'),(9,'Juan Benedito Alves','017.583.979-42',13,'(48)2721-6706'),(10,'Edson Raimundo Porto','503.221.309-80',14,'(47)3758-0905');
/*!40000 ALTER TABLE `remetente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tipotramite`
--

LOCK TABLES `tipotramite` WRITE;
/*!40000 ALTER TABLE `tipotramite` DISABLE KEYS */;
INSERT INTO `tipotramite` VALUES (1,'SAIU DE:'),(2,'FOI PARA:'),(3,'CHEGOU EM:');
/*!40000 ALTER TABLE `tipotramite` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Administrador',1,1),(2,'Caixa',2,1);
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramite`
--

LOCK TABLES `tramite` WRITE;
/*!40000 ALTER TABLE `tramite` DISABLE KEYS */;
/*!40000 ALTER TABLE `tramite` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'William Bigas Mauro','1997-11-21','(48)3033-7442','admin','100.521.859-58','1234',1550.00,123456,1,1,1),(2,'Agostinho Detófano Junior','1996-02-12','(48)9682-4700','funcionario','100.521.859-64','1234',2000.00,123987,1,2,2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'vindiesel'
--

--
-- Dumping routines for database 'vindiesel'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-25 12:02:55
