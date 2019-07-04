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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinatario`
--

LOCK TABLES `destinatario` WRITE;
/*!40000 ALTER TABLE `destinatario` DISABLE KEYS */;
INSERT INTO `destinatario` VALUES (26,'Pietra e Aparecida Lavanderia Ltda','17.300.267/0001-05',57),(27,'Anthony e José Informática ME','44.231.984/0001-97',58),(28,'Marcelo e Luzia Casa Noturna ME','20.516.953/0001-14',59),(29,'Iago e Iago Entulhos ME','80.474.411/0001-58',60),(30,'Francisco e Roberto Vidros Ltda','20.409.238/0001-82',61),(31,'Marcos Vinicius Hugo Jorge Rezende','705.014.487-80',62),(32,'Márcia Nicole Nunes','664.091.336-42',63),(33,'Renata Vitória Assunção','314.178.481-70',64);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dimensao`
--

LOCK TABLES `dimensao` WRITE;
/*!40000 ALTER TABLE `dimensao` DISABLE KEYS */;
INSERT INTO `dimensao` VALUES (7,5,2,4),(8,3,4,5),(9,5,25,10),(10,50,25,25),(11,20,25,40),(12,6,26,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encomenda`
--

LOCK TABLES `encomenda` WRITE;
/*!40000 ALTER TABLE `encomenda` DISABLE KEYS */;
INSERT INTO `encomenda` VALUES (7,'VD-A0BA951E',10.20,7,25.90),(8,'VD-8C8203EE',2.30,8,25.00),(9,'VD-FCC475E',9.30,9,45.00),(10,'VD-4BE3FB5C',4.30,10,10.99),(11,'VD-2CA95AF4',10.20,11,25.20),(12,'VD-355AC60C',25.20,12,250.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'88133810','SC','Palhoça','Jardim das Palmeiras','Dália','Casa Marrom','538'),(2,'88130800','SC','Palhoça','Jardim Eldorado','Não sei o nome','Não sei','500'),(44,'89292870','SC','São Bento do Sul','Rio Vermelho Povoado','Rua Carlos Muhlmann','de 1042/1043 a 1640/1641','502'),(45,'89265744','SC','Jaraguá do Sul','Nereu Ramos','Rua Júlio Gadotti','','469'),(46,'89265744','SC','Jaraguá do Sul','Nereu Ramos','Rua Júlio Gadotti','','469'),(47,'89265744','SC','Jaraguá do Sul','Nereu Ramos','Rua Júlio Gadotti','','469'),(48,'89265744','SC','Jaraguá do Sul','Nereu Ramos','Rua Júlio Gadotti','','469'),(49,'88353402','SC','Brusque','Azambuja','Rua Emílio Jeske','','71'),(50,'88353402','SC','Brusque','Azambuja','Rua Emílio Jeske','','71'),(51,'88706091','SC','Tubarão','São Clemente','Rua José João Constantino Fernandes','','51'),(52,'89237780','SC','Joinville','Vila Nova','Rua dos Portugueses','','748'),(53,'89802265','SC','Chapecó','Jardim Itália','Avenida General Osório - D','até 481 - lado ímpar','255'),(54,'88020429','SC','Florianópolis','Centro','Travessa Magalhães','asdadsa','628'),(55,'88020429','SC','Florianópolis','Centro','Travessa Magalhães','asdadsa','628'),(56,'88816745','SC','Criciúma','Morro Estevão','Rua José Comin','','784'),(57,'88813705','SC','Criciúma','dos Imigrantes','Rua Manoel Ezequiel','','544'),(58,'89215188','SC','Joinville','Morro do Meio','Rua Laudelino Schaeffer','','409'),(59,'89206730','SC','Joinville','Boa Vista','Rua Cooperativa Tupy','asda','252'),(60,'89803442','SC','Chapecó','Jardim América','Rua Princesa Isabel - E','de 1801/1802 ao fim','860'),(61,'89260510','SC','Jaraguá do Sul','Barra do Rio Cerro','Rua Victor Satler','asda','907'),(62,'69006661','AM','Manaus','Gilberto Mestrinho','Rua Jatubu','','382'),(63,'59146330','RN','Parnamirim','Monte Castelo','Rua das Vitórias','','960'),(64,'77019526','TO','Palmas','Plano Diretor Sul','1204 Sul Avenida NS 2','','493'),(65,'45990732','BA','Teixeira de Freitas','Jardim Caraípe','Rua Dorival Santos de Jesus','','597'),(66,'45990732','BA','Teixeira de Freitas','Jardim Caraípe','Rua Dorival Santos de Jesus','','597'),(67,'49008256','SE','Aracaju','Zona de Expansão (Mosqueiro)','Travessa Esperança I','','207'),(68,'58428198','PB','Campina Grande','Centenário','Rua São Miguel','','351');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
INSERT INTO `entrega` VALUES (15,15.56,'2019-07-03 21:19:41',NULL,0,14,28,12),(16,25.00,'2019-07-03 21:21:24',NULL,0,11,28,10),(17,25.02,'2019-07-04 10:24:05',NULL,0,16,30,7),(18,14.52,'2019-07-04 10:24:24',NULL,0,17,26,11),(19,10.50,'2019-07-04 16:00:28',NULL,0,15,29,7),(20,25.02,'2019-07-04 16:00:49',NULL,0,14,29,7),(21,17.78,'2019-07-04 16:01:04',NULL,0,15,26,7),(22,0.20,'2019-07-04 16:04:41',NULL,0,13,32,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita`
--

LOCK TABLES `receita` WRITE;
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
INSERT INTO `receita` VALUES (15,'2019-07-04 10:24:12',NULL,'2019-07-18',25.02,NULL,17,NULL),(16,'2019-07-04 10:24:28',NULL,'2019-07-19',14.52,NULL,18,NULL),(17,'2019-07-04 16:00:35',NULL,'2019-07-17',10.50,NULL,19,NULL),(18,'2019-07-04 16:00:56',NULL,'2019-07-25',25.02,NULL,20,NULL),(19,'2019-07-04 16:01:07',NULL,'2019-07-31',17.78,NULL,21,NULL),(20,'2019-07-04 16:04:45',NULL,'2019-07-24',0.20,NULL,22,NULL);
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
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remetente`
--

LOCK TABLES `remetente` WRITE;
/*!40000 ALTER TABLE `remetente` DISABLE KEYS */;
INSERT INTO `remetente` VALUES (11,'Silvana Giovana de Paula','936.325.869-69',44,'(47)9845-1881','silvanagiovanadepaula_@sdrifs.com.br'),(12,'Elisa Carolina Almeida','982.433.219-77',48,'(47)2972-9681','elisacarolinaalmeida@cuppari.com.br'),(13,'Letícia Marcela Fabiana Pereira','721.033.189-19',50,'(47)3950-6737','leticiamarcela-91@mcexecutiva.com.br'),(14,'César Jorge Carlos Eduardo Lima','851.118.869-07',51,'(48)3622-0786','cesarjorgecarloseduardolima_@iedi.com.br'),(15,'Olivia e Kamilly Esportes Ltda','79.800.134/0001-47',52,'(47)3844-7789','fiscal@oliviaekamillyesportesltda.com.br'),(16,'Elza e Melissa Construções Ltda','68.925.126/0001-00',53,'(49)2593-3398','producao@elzaemelissaconstrucoesltda.com.br'),(17,'Daniel e Rodrigo Pizzaria Delivery ME','48.904.749/0001-90',55,'(48)3605-0235','contabilidade@danzzariadeliveryme.com.br'),(18,'Rodrigo e Daniel Mudanças ME','43.931.450/0001-00',56,'(48)2808-7912','juridico@rodrigoedanielmudancasme.com.br');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Administrador',1,1),(2,'Funcionário',2,1),(3,'Diretoria',1,1),(4,'Recebimento',2,1),(5,'Depósito',2,0),(6,'Conferência',2,0),(7,'Separação',2,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramite`
--

LOCK TABLES `tramite` WRITE;
/*!40000 ALTER TABLE `tramite` DISABLE KEYS */;
INSERT INTO `tramite` VALUES (25,'2019-07-03 21:19:44','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',15,3),(26,'2019-07-03 21:21:31','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',16,3),(27,'2019-07-04 10:24:12','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',17,3),(28,'2019-07-04 10:24:28','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',18,3),(29,'2019-07-04 16:00:35','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',19,3),(30,'2019-07-04 16:00:56','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',20,3),(31,'2019-07-04 16:01:07','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',21,3),(32,'2019-07-04 16:04:45','CHEGOU EM: VIN DIESEL','Encomenda foi registrada para entrega na transportadora',22,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'William Bigas Mauro','1997-11-21','(48)3033-7442','admin@vindiesel.com.br','100.521.859-58','1234',1550.00,123456,1,1,1),(2,'Agostinho Detófano Junior','1996-02-12','(48)9682-4700','funcionario@vindiesel.com.br','106.827.829-39','1234',2000.00,123987,1,2,2),(4,'Rita Aurora Carolina da Silva','1970-02-16','(73)2627-1166','ritaaurora@escritoriogold.com.br','852.238.526-21','BP4SgRGDkw',1250.00,123456789,0,66,1),(5,'Isis Raquel Lima','1973-10-18','(79)8334-2552','iisisraquellima@localiza.com','768.693.336-96','aM5Q00oNLU',2000.00,987654321,1,67,2),(6,'Oliver Mateus dos Santos','1996-12-06','(83)2556-4143','oolivermateus@harmonia.com.br','732.174.726-32','eOksWTJEh0',2500.00,189643267,0,68,1);
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

-- Dump completed on 2019-07-04 16:46:11
