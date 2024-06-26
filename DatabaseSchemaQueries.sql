-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: emerging_data
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
  `creator` int DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`application_id`),
  UNIQUE KEY `creator` (`creator`),
  CONSTRAINT `fk_creator_capp` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `created_application`
--

LOCK TABLES `created_application` WRITE;
/*!40000 ALTER TABLE `created_application` DISABLE KEYS */;
INSERT INTO `created_application` VALUES (7,674,'Impact of Remote Work on Employee Productivity','Join our research team to investigate how remote work influences productivity and job satisfaction within tech startups. This study requires students with a keen interest in organizational behavior, data analysis skills, and a commitment to rigorous research methodologies. Ideal candidates will demonstrate strong analytical abilities and a proactive approach to research.'),(8,675,'Quantum Computing: Current Applications','Explore the current applications, challenges, and future potential of quantum computing in solving complex computational problems. This research investigates practical implementations in various fields and anticipates advancements that could revolutionize computing capabilities.'),(9,676,'Impact of Social Media on Mental Health','Examine how social media usage affects mental health differently between adolescents and adults. This research investigates psychological outcomes such as anxiety, depression, and self-esteem, aiming to understand the unique challenges posed by social media in different age groups.'),(10,677,'Gender Roles in Contemporary Literature','Analyze how gender roles are portrayed and challenged in recent literary works. This research explores themes, characterizations, and narrative strategies employed by authors to depict evolving societal norms and expectations regarding gender identity and relationships.');
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
) ENGINE=InnoDB AUTO_INCREMENT=466 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (452,'https://googledocs.com'),(453,'https://googledocs.com'),(454,'https://googledocs.com'),(455,'https://googledocs.com'),(456,'https://googledocs.com'),(457,'https://googledocs.com'),(458,'https://googledocs.com'),(459,'https://pdf.com'),(460,'https://pdf.com'),(461,'https://pdf.com'),(462,'https://pdf.com'),(463,'https://docx.com'),(464,'https://docx.com'),(465,'https://docx.com');
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
INSERT INTO `resume_seq` VALUES (551);
/*!40000 ALTER TABLE `resume_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `semester_id` int NOT NULL,
  `semester_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
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
  `decision` varchar(50) NOT NULL DEFAULT (_utf8mb4'IN-PROGRESS'),
  `classification` varchar(255) DEFAULT NULL,
  `gpa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sent_application_id`),
  KEY `fk_sender_application` (`sender`),
  KEY `fk_receiver_application` (`receiver`),
  KEY `fk_resumeId_resume` (`resume_id`),
  KEY `fk_application_id_sent_application` (`created_application_id`),
  CONSTRAINT `fk_application_id_sent_application` FOREIGN KEY (`created_application_id`) REFERENCES `created_application` (`application_id`),
  CONSTRAINT `fk_receiver_application` FOREIGN KEY (`receiver`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_resumeId_resume` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`resume_id`),
  CONSTRAINT `fk_sender_application` FOREIGN KEY (`sender`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sent_application`
--

LOCK TABLES `sent_application` WRITE;
/*!40000 ALTER TABLE `sent_application` DISABLE KEYS */;
INSERT INTO `sent_application` VALUES (3,8,673,675,'Hi Professor,\n\nI’m eager to join your research team exploring how remote work affects productivity and job satisfaction in tech startups. I have a strong interest in organizational behavior, proficient data analysis skills, and a commitment to rigorous research methodologies. Looking forward to contributing actively to your study.',452,'IN - PROGRESS','Junior','4.0'),(4,7,673,674,'Hi Professor,\n\nI am interested!',453,'ACCEPTED','Junior','4.0'),(5,9,673,676,'Hi Prof,\n\nI want in!',454,'IN - PROGRESS','Junior','4.0'),(6,7,680,674,'Hi Prof,\n\nI want to participate!',455,'ACCEPTED','Junior','3.9'),(7,8,680,675,'Hi,\n\nI like this idea!',456,'IN - PROGRESS','Junior','3.9'),(8,9,680,676,'Hi,\n\nI like this idea!',457,'ACCEPTED','Junior','3.9'),(9,10,680,677,'Hi,\n\nI like this idea!',458,'IN - PROGRESS','Junior','3.9'),(10,7,679,674,'Hi,\n\nThis looks amazing!',459,'DECLINED','Sophomore','3.8'),(11,8,679,675,'Hi,\n\nThis looks amazing!',460,'DECLINED','Sophomore','3.8'),(12,9,679,676,'Hi,\n\nThis looks amazing!',461,'DECLINED','Sophomore','3.8'),(13,10,679,677,'Hi,\n\nThis looks amazing!',462,'IN - PROGRESS','Sophomore','3.8'),(14,7,678,674,'Hi,\n\nThis looks SO interesting I want to take part in it!',463,'IN - PROGRESS','Freshmen','3.7'),(15,8,678,675,'Hi,\n\nThis looks SO interesting I want to take part in it!',464,'ACCEPTED','Freshmen','3.7'),(16,9,678,676,'Hi,\n\nThis looks SO interesting I want to take part in it!',465,'IN - PROGRESS','Freshmen','3.7');
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
  `gpa` varchar(50) DEFAULT NULL,
  `classification` varchar(50) DEFAULT NULL,
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
INSERT INTO `user_details` VALUES (673,'30010101','Computer Science','johndoe@warhawks.ulm.edu','John','Doe',1,NULL,NULL),(674,'20010010','Business Administrator','janedoe@ulm.edu','Jane','Doe',2,NULL,NULL),(675,'20001101','Computer Science','welsh@ulm.edu','Welsh','Abubaker',2,NULL,NULL),(676,'30112123','Psychology','michaels@ulm.edu','William','Michaels',2,NULL,NULL),(677,'10010009','English','margaret@ulm.edu','Margaret','Red',2,NULL,NULL),(678,'10010100','Business Administration','nelson@warhawks.ulm.edu','Will','Nelson',1,NULL,NULL),(679,'10010102','English','blue@warhawks.ulm.edu','Brian','Blue',1,NULL,NULL),(680,'10010104','Psychology','green@warhawks.ulm.edu','Faith','Green',1,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=681 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (673,'johndoe',1,'$2a$10$s3Y2m45vVwp/IviNbACl6uzwoo/jpBLyvItHjvGeu7/a0QEmrr06u'),(674,'janedoe',1,'$2a$10$IxC6skuzOwzE8D.oEieEmOAm2edLylXBqh7gMDDY3wnrwflmqYAwq'),(675,'welsh',1,'$2a$10$JMI0C93pB.Tv2AfylsvQzeK2rDrRgtDtZ5QQRcDTDo4PGkexsc5tO'),(676,'michaels',1,'$2a$10$UWUgOTkoEJh4aJqPET8hAOSoL3g5S4Ah7X779CauFU3SF1IcvSf6y'),(677,'margaret',1,'$2a$10$r4bO8fX6SU06RwMquH9s3uKe1WXcuwMQDaVX9puPsCZx3O5oex1D6'),(678,'nelson',1,'$2a$10$H53bcAW1DBP.mE6x3BwUgOHRE7BkUaHfnLXVsXPXOMNm1NESNflKK'),(679,'blue',1,'$2a$10$bcoQv1f1X/pXxZ.c9MeVSOJ4aJbx.Eo0Ru3K0qkQmMkOC77N2P.9y'),(680,'green',1,'$2a$10$mtJZtuwoayE0VOancZVjiuGXBq7N6QGgId2rWpPdzxwlgRWCgmJrq');
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

-- Dump completed on 2024-06-26  0:06:41
