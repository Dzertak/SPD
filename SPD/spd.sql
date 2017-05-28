-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sdp
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id_authors` int(11) NOT NULL AUTO_INCREMENT,
  `id_identifiers` int(11) NOT NULL,
  `full_name_en` varchar(255) DEFAULT NULL,
  `full_name_ru` varchar(255) DEFAULT NULL,
  `full_name_ua` varchar(255) DEFAULT NULL,
  `scientific_degree` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_authors`),
  UNIQUE KEY `id_identifiers_UNIQUE` (`id_identifiers`),
  KEY `fk_authors_Identifiers1_idx` (`id_identifiers`),
  CONSTRAINT `fk_authors_Identifiers1` FOREIGN KEY (`id_identifiers`) REFERENCES `identifiers` (`id_identifiers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (8,1,'вфывпа','фывафоы','фываф','фыва','1999-11-11','ываф','фпа'),(9,2,'пап','ыва','13','авмя',NULL,'ячсм','ячсм');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliography`
--

DROP TABLE IF EXISTS `bibliography`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bibliography` (
  `id_bibliography` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_publication` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_bibliography`),
  KEY `fk_bibliography_publication1_idx` (`id_publication`),
  CONSTRAINT `fk_bibliography_publication1` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id_publication`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliography`
--

LOCK TABLES `bibliography` WRITE;
/*!40000 ALTER TABLE `bibliography` DISABLE KEYS */;
/*!40000 ALTER TABLE `bibliography` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cathedra`
--

DROP TABLE IF EXISTS `cathedra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cathedra` (
  `id_cathedra` int(11) NOT NULL AUTO_INCREMENT,
  `id_faculties` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cathedra`),
  KEY `fk_cathedra_faculties1_idx` (`id_faculties`),
  CONSTRAINT `fk_cathedra_faculties1` FOREIGN KEY (`id_faculties`) REFERENCES `faculties` (`id_faculties`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cathedra`
--

LOCK TABLES `cathedra` WRITE;
/*!40000 ALTER TABLE `cathedra` DISABLE KEYS */;
INSERT INTO `cathedra` VALUES (1,1,'Cathedra of Information Technology and CyberSecurity','720-91-90','http://fitkb.onaft.edu.ua/');
/*!40000 ALTER TABLE `cathedra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment_history`
--

DROP TABLE IF EXISTS `employment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employment_history` (
  `id_employment_history` int(11) NOT NULL AUTO_INCREMENT,
  `id_scientific_institutions` int(11) NOT NULL,
  `id_cathedra` int(11) DEFAULT NULL,
  `id_authors` int(11) NOT NULL,
  `beginning_of_period` varchar(255) DEFAULT NULL,
  `ending_of_period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_employment_history`),
  KEY `fk_employment_history_scientific_institutions1_idx` (`id_scientific_institutions`),
  KEY `fk_employment_history_cathedra1_idx` (`id_cathedra`),
  KEY `fk_employment_history_authors1_idx` (`id_authors`),
  CONSTRAINT `fk_employment_history_authors1` FOREIGN KEY (`id_authors`) REFERENCES `authors` (`id_authors`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employment_history_cathedra1` FOREIGN KEY (`id_cathedra`) REFERENCES `cathedra` (`id_cathedra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employment_history_scientific_institutions1` FOREIGN KEY (`id_scientific_institutions`) REFERENCES `scientific_institutions` (`id_scientific_institutions`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment_history`
--

LOCK TABLES `employment_history` WRITE;
/*!40000 ALTER TABLE `employment_history` DISABLE KEYS */;
INSERT INTO `employment_history` VALUES (1,1,1,9,'2000-12-12','2002-01-01'),(4,1,1,9,'2004-08-23','2012-07-1'),(5,1,1,8,'2008-05-03','2009-12-03');
/*!40000 ALTER TABLE `employment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculties`
--

DROP TABLE IF EXISTS `faculties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculties` (
  `id_faculties` int(11) NOT NULL AUTO_INCREMENT,
  `id_institutions` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_faculties`),
  KEY `fk_faculties_institutions1_idx` (`id_institutions`),
  CONSTRAINT `fk_faculties_institutions1` FOREIGN KEY (`id_institutions`) REFERENCES `institutions` (`id_institutions`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculties`
--

LOCK TABLES `faculties` WRITE;
/*!40000 ALTER TABLE `faculties` DISABLE KEYS */;
INSERT INTO `faculties` VALUES (1,1,'Faculty of Information Technology and CyberSecurity','720-91-90','http://fitkb.onaft.edu.ua/');
/*!40000 ALTER TABLE `faculties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identifiers`
--

DROP TABLE IF EXISTS `identifiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identifiers` (
  `id_identifiers` int(11) NOT NULL,
  `scopusid` varchar(255) DEFAULT NULL,
  `orcid` varchar(255) DEFAULT NULL,
  `researcherid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_identifiers`),
  UNIQUE KEY `scopusid_UNIQUE` (`scopusid`),
  UNIQUE KEY `orcid_UNIQUE` (`orcid`),
  UNIQUE KEY `researcherid_UNIQUE` (`researcherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identifiers`
--

LOCK TABLES `identifiers` WRITE;
/*!40000 ALTER TABLE `identifiers` DISABLE KEYS */;
INSERT INTO `identifiers` VALUES (1,'вапы','12dfg34','12344532'),(2,'sfg345','фыв','sfg23');
/*!40000 ALTER TABLE `identifiers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutions`
--

DROP TABLE IF EXISTS `institutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutions` (
  `id_institutions` int(11) NOT NULL AUTO_INCREMENT,
  `id_scientific_institutions` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_institutions`),
  KEY `fk_institutions_scientific_institutions_idx` (`id_scientific_institutions`),
  CONSTRAINT `fk_institutions_scientific_institutions` FOREIGN KEY (`id_scientific_institutions`) REFERENCES `scientific_institutions` (`id_scientific_institutions`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutions`
--

LOCK TABLES `institutions` WRITE;
/*!40000 ALTER TABLE `institutions` DISABLE KEYS */;
INSERT INTO `institutions` VALUES (1,1,'Institute of refrigeration, cryotechnology and ecoenergetics named after V. S. Martynovsky','65082, ul.Dvoryanskaya 1/3','','http://irce.od.ua/');
/*!40000 ALTER TABLE `institutions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id_language` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magazine_sections`
--

DROP TABLE IF EXISTS `magazine_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `magazine_sections` (
  `id_magazine_sections` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_magazine_sections`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazine_sections`
--

LOCK TABLES `magazine_sections` WRITE;
/*!40000 ALTER TABLE `magazine_sections` DISABLE KEYS */;
/*!40000 ALTER TABLE `magazine_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publication` (
  `id_publication` bigint(20) NOT NULL,
  `id_magazine_sections` int(11) NOT NULL,
  `id_language` int(11) NOT NULL,
  `identifier_value` bigint(20) DEFAULT NULL,
  `journal_title` varchar(255) DEFAULT NULL,
  `issue` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `DOI` varchar(255) DEFAULT NULL,
  `page_beginning` int(11) DEFAULT NULL,
  `page_ending` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_publication`),
  KEY `fk_publication_magazine_sections1_idx` (`id_magazine_sections`),
  KEY `fk_publication_language1_idx` (`id_language`),
  CONSTRAINT `fk_publication_language1` FOREIGN KEY (`id_language`) REFERENCES `language` (`id_language`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_publication_magazine_sections1` FOREIGN KEY (`id_magazine_sections`) REFERENCES `magazine_sections` (`id_magazine_sections`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication`
--

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scientific_institutions`
--

DROP TABLE IF EXISTS `scientific_institutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scientific_institutions` (
  `id_scientific_institutions` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_scientific_institutions`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scientific_institutions`
--

LOCK TABLES `scientific_institutions` WRITE;
/*!40000 ALTER TABLE `scientific_institutions` DISABLE KEYS */;
INSERT INTO `scientific_institutions` VALUES (1,'Odessa National Academy of Food Technologies','Оdessa, Kanatnaya Str., Ukraine, 65039','725-32-84','http://www.onaft.edu.ua/');
/*!40000 ALTER TABLE `scientific_institutions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `working_group`
--

DROP TABLE IF EXISTS `working_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `working_group` (
  `id_working_group` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_authors` int(11) NOT NULL,
  `id_publication` bigint(20) NOT NULL,
  PRIMARY KEY (`id_working_group`),
  KEY `fk_working_group_authors1_idx` (`id_authors`),
  KEY `fk_working_group_publication1_idx` (`id_publication`),
  CONSTRAINT `fk_working_group_authors1` FOREIGN KEY (`id_authors`) REFERENCES `authors` (`id_authors`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_working_group_publication1` FOREIGN KEY (`id_publication`) REFERENCES `publication` (`id_publication`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `working_group`
--

LOCK TABLES `working_group` WRITE;
/*!40000 ALTER TABLE `working_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `working_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sdp'
--

--
-- Dumping routines for database 'sdp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-13 18:27:32
