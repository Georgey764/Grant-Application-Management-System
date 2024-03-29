-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: localhost    Database: emerging_data
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `application_seq`
--

DROP TABLE IF EXISTS `application_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_seq`
--

LOCK TABLES `application_seq` WRITE;
/*!40000 ALTER TABLE `application_seq` DISABLE KEYS */;
INSERT INTO `application_seq` VALUES (301);
/*!40000 ALTER TABLE `application_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `authority_id` int NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_id`),
  UNIQUE KEY `authority_name` (`authority_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (3,'ROLE_ADMIN'),(2,'ROLE_FACULTY'),(1,'ROLE_STUDENT');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities_seq`
--

DROP TABLE IF EXISTS `authorities_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities_seq`
--

LOCK TABLES `authorities_seq` WRITE;
/*!40000 ALTER TABLE `authorities_seq` DISABLE KEYS */;
INSERT INTO `authorities_seq` VALUES (1);
/*!40000 ALTER TABLE `authorities_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `created_application`
--

DROP TABLE IF EXISTS `created_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `created_application` (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `creator` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`application_id`),
  KEY `fk_creator_capp` (`creator`),
  CONSTRAINT `fk_creator_capp` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `created_application`
--

LOCK TABLES `created_application` WRITE;
/*!40000 ALTER TABLE `created_application` DISABLE KEYS */;
INSERT INTO `created_application` VALUES (1,669,'My Man','My Woman');
/*!40000 ALTER TABLE `created_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `token_id` bigint NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token_seq`
--

DROP TABLE IF EXISTS `password_reset_token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token_seq`
--

LOCK TABLES `password_reset_token_seq` WRITE;
/*!40000 ALTER TABLE `password_reset_token_seq` DISABLE KEYS */;
INSERT INTO `password_reset_token_seq` VALUES (1);
/*!40000 ALTER TABLE `password_reset_token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `resume_id` int NOT NULL AUTO_INCREMENT,
  `resume_link` varchar(255) NOT NULL,
  PRIMARY KEY (`resume_id`)
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (152,'https://www.youtube.com'),(202,'www.youtube.com');
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume_seq`
--

DROP TABLE IF EXISTS `resume_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume_seq`
--

LOCK TABLES `resume_seq` WRITE;
/*!40000 ALTER TABLE `resume_seq` DISABLE KEYS */;
INSERT INTO `resume_seq` VALUES (401);
/*!40000 ALTER TABLE `resume_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sent_application`
--

DROP TABLE IF EXISTS `sent_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sent_application` (
  `sent_application_id` int NOT NULL AUTO_INCREMENT,
  `created_application_id` int NOT NULL,
  `sender` int NOT NULL,
  `receiver` int NOT NULL,
  `message` text,
  `resume_id` int DEFAULT NULL,
  PRIMARY KEY (`sent_application_id`),
  KEY `fk_sender_application` (`sender`),
  KEY `fk_receiver_application` (`receiver`),
  KEY `fk_resumeId_resume` (`resume_id`),
  KEY `fk_application_id_sent_application` (`created_application_id`),
  CONSTRAINT `fk_application_id_sent_application` FOREIGN KEY (`created_application_id`) REFERENCES `created_application` (`application_id`),
  CONSTRAINT `fk_receiver_application` FOREIGN KEY (`receiver`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_resumeId_resume` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`resume_id`),
  CONSTRAINT `fk_sender_application` FOREIGN KEY (`sender`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sent_application`
--

LOCK TABLES `sent_application` WRITE;
/*!40000 ALTER TABLE `sent_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `sent_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `user_id` int NOT NULL,
  `cwid` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `authority_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKaq3fakpdv385g0hcspx4y76li` (`authority_id`),
  CONSTRAINT `FKaq3fakpdv385g0hcspx4y76li` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`authority_id`),
  CONSTRAINT `FKicouhgavvmiiohc28mgk0kuj5` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (663,'30157203','Computer Science','gesad@gmail.com','George','Khawas',1),(668,'30157203','Computer Science','georgesamuel764@gmail.com','George','Khawas',1),(669,'21903','Comp Sci','hi@gmail.com','Georgey','Khawas',2);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details_seq`
--

DROP TABLE IF EXISTS `user_details_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details_seq`
--

LOCK TABLES `user_details_seq` WRITE;
/*!40000 ALTER TABLE `user_details_seq` DISABLE KEYS */;
INSERT INTO `user_details_seq` VALUES (751);
/*!40000 ALTER TABLE `user_details_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `password` varchar(72) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=670 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john',1,'$2a$10$8NilyMVaSyYo08iZvrA.1Oqz5D67PoevBRg2AzQuLReTiKy0uUIcq'),(2,'elizabeth',1,'$2a$10$8NilyMVaSyYo08iZvrA.1Oqz5D67PoevBRg2AzQuLReTiKy0uUIcq'),(3,'mary',1,'$2a$10$8NilyMVaSyYo08iZvrA.1Oqz5D67PoevBRg2AzQuLReTiKy0uUIcq'),(663,'gesad',1,'$2a$10$jUSdB7lTP4ew1naoPskn6eVzUXEnfay5o2g5J/buOxRXc3fkFI79a'),(668,'georgesamuel764',1,'$2a$10$aw3csh.NGXmg4R8WKc6VmO6awwXhyvvGGHYDD4LSHDB7/piIJ/4MK'),(669,'hi',1,'$2a$10$8AJp9PsJwYG.Moi7umQZ4OnUpkk4VaCdwsOHF9kTS2qrZX8mEspdO');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS `users_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES `users_seq` WRITE;
/*!40000 ALTER TABLE `users_seq` DISABLE KEYS */;
INSERT INTO `users_seq` VALUES (751);
/*!40000 ALTER TABLE `users_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-29 14:27:36
