-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: calendrier
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
-- Table structure for table `emotion`
--

DROP TABLE IF EXISTS `emotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emotion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotion`
--

LOCK TABLES `emotion` WRITE;
/*!40000 ALTER TABLE `emotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `emotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gif`
--

DROP TABLE IF EXISTS `gif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gif` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_heure_ajout` datetime(6) DEFAULT NULL,
  `legende` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `nom_fichier_original` varchar(255) DEFAULT NULL,
  `jour_date` date DEFAULT NULL,
  `utilisateur_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc7tpureov5khi3mnp3aivan2w` (`jour_date`),
  KEY `FKvfc9dbl7nxswsaq560qgjuk4` (`utilisateur_id`),
  CONSTRAINT `FKc7tpureov5khi3mnp3aivan2w` FOREIGN KEY (`jour_date`) REFERENCES `jour` (`date`),
  CONSTRAINT `FKvfc9dbl7nxswsaq560qgjuk4` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gif`
--

LOCK TABLES `gif` WRITE;
/*!40000 ALTER TABLE `gif` DISABLE KEYS */;
/*!40000 ALTER TABLE `gif` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jour`
--

DROP TABLE IF EXISTS `jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jour` (
  `date` date NOT NULL,
  `nb_points` int NOT NULL,
  `gif_id` bigint DEFAULT NULL,
  PRIMARY KEY (`date`),
  KEY `FK6f1cqult1qodurxglwbw64tv5` (`gif_id`),
  CONSTRAINT `FK6f1cqult1qodurxglwbw64tv5` FOREIGN KEY (`gif_id`) REFERENCES `gif` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jour`
--

LOCK TABLES `jour` WRITE;
/*!40000 ALTER TABLE `jour` DISABLE KEYS */;
/*!40000 ALTER TABLE `jour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_heure` datetime(6) DEFAULT NULL,
  `emotion_id` bigint DEFAULT NULL,
  `gif_id` bigint DEFAULT NULL,
  `utilisateur_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dh6d3ymslekeyhl1t662ekik` (`emotion_id`),
  KEY `FK85e1wxmt3cisy8qw9or9m0du3` (`gif_id`),
  KEY `FKleetkhnmwd976sh66h2868g7k` (`utilisateur_id`),
  CONSTRAINT `FK7dh6d3ymslekeyhl1t662ekik` FOREIGN KEY (`emotion_id`) REFERENCES `emotion` (`id`),
  CONSTRAINT `FK85e1wxmt3cisy8qw9or9m0du3` FOREIGN KEY (`gif_id`) REFERENCES `gif` (`id`),
  CONSTRAINT `FKleetkhnmwd976sh66h2868g7k` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theme` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_heure_inscription` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nb_points` int NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `theme_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1hcph3iur5lnjlgpbg1ibens2` (`theme_id`),
  CONSTRAINT `FK1hcph3iur5lnjlgpbg1ibens2` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-12  2:54:57
