-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: touloulou
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entreprise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entreprise`
--

LOCK TABLES `entreprise` WRITE;
/*!40000 ALTER TABLE `entreprise` DISABLE KEYS */;
INSERT INTO `entreprise` VALUES (5,'SEMIFIR'),(6,'SEMIFIR'),(7,'SEMIFIR'),(8,'SEMIFIR'),(9,'SEMIFIR'),(10,'SEMIFIR'),(11,'SEMIFIR'),(12,'SEMIFIR'),(13,'SEMIFIR'),(14,'SEMIFIR');
/*!40000 ALTER TABLE `entreprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filiale`
--

DROP TABLE IF EXISTS `filiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filiale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `nb_employee` int NOT NULL,
  `entreprise_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_filiale_entreprise1_idx` (`entreprise_id`),
  CONSTRAINT `fk_filiale_entreprise1` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filiale`
--

LOCK TABLES `filiale` WRITE;
/*!40000 ALTER TABLE `filiale` DISABLE KEYS */;
INSERT INTO `filiale` VALUES (4,'TOTOTATA',2,7),(5,'TOTOTATA',2,8),(6,'TOTOTATA',2,9),(7,'TOTOTATA',2,10),(8,'TOTOTATA',2,11),(9,'TOTOTATA',2,12),(10,'TOTOTATA',2,13),(11,'TOTOTATA',2,14);
/*!40000 ALTER TABLE `filiale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secteur`
--

DROP TABLE IF EXISTS `secteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secteur` (
  `secteur_id` int NOT NULL AUTO_INCREMENT,
  `localisation` varchar(255) NOT NULL,
  PRIMARY KEY (`secteur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secteur`
--

LOCK TABLES `secteur` WRITE;
/*!40000 ALTER TABLE `secteur` DISABLE KEYS */;
INSERT INTO `secteur` VALUES (35,'Lille'),(36,'Lille'),(37,'Lille'),(38,'Lille'),(39,'Lille'),(40,'Lille');
/*!40000 ALTER TABLE `secteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secteur_has_filiale`
--

DROP TABLE IF EXISTS `secteur_has_filiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secteur_has_filiale` (
  `secteur_id` int NOT NULL,
  `filiale_id` int NOT NULL,
  PRIMARY KEY (`secteur_id`,`filiale_id`),
  KEY `fk_secteur_has_filiale_filiale1_idx` (`filiale_id`),
  KEY `fk_secteur_has_filiale_secteur1_idx` (`secteur_id`),
  CONSTRAINT `fk_secteur_has_filiale_filiale1` FOREIGN KEY (`filiale_id`) REFERENCES `filiale` (`id`),
  CONSTRAINT `fk_secteur_has_filiale_secteur1` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`secteur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secteur_has_filiale`
--

LOCK TABLES `secteur_has_filiale` WRITE;
/*!40000 ALTER TABLE `secteur_has_filiale` DISABLE KEYS */;
INSERT INTO `secteur_has_filiale` VALUES (35,6),(36,7),(37,8),(38,9),(39,10),(40,11);
/*!40000 ALTER TABLE `secteur_has_filiale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `age` int NOT NULL,
  `fonction` varchar(45) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `adresse` varchar(45) NOT NULL,
  `secteur_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `secteur_fk_idx` (`secteur_id`),
  CONSTRAINT `secteur_fk` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`secteur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (13,'Pierre','Dupont','pierre@dupont.fr',70,'Dev','0647382911','25 rue autrepart',38),(14,'Jean','Valjean','jean@jean.fr',45,'CEO','0607080910','10 rue qqpart',38),(15,'Pierre','Dupont','pierre@dupont.fr',70,'Dev','0647382911','25 rue autrepart',39),(16,'Jean','Valjean','jean@jean.fr',45,'CEO','0607080910','10 rue qqpart',39),(17,'Pierre','Dupont','pierre@dupont.fr',70,'Dev','0647382911','25 rue autrepart',40),(18,'Jean','Valjean','jean@jean.fr',45,'CEO','0607080910','10 rue qqpart',40);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04 22:27:10
