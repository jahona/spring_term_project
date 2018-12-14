CREATE DATABASE  IF NOT EXISTS `wsc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wsc`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: www.google.com    Database: wsc
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_A1_idx` (`USER_ID`),
  CONSTRAINT `FK_A1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(45) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(256) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-05 16:48:22

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CODE` varchar(45) NOT NULL,
  `STATE` varchar(45) NOT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `SUB1` varchar(45) DEFAULT NULL,
  `SUB2` varchar(45) DEFAULT NULL,
  `CREATED_AT` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `trade_items`;

CREATE TABLE `trade_items` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DEAL_AMOUNT` int(10) unsigned NOT NULL,
  `BUILDING_AREA` varchar(45),
  `BUILDING_USE` varchar(45),
  `BUILD_YEAR` varchar(45),
  `CLASSIFICATION` varchar(45),
  `DEAL_YEAR` varchar(45) NOT NULL,
  `PLOTTAGE` varchar(45),
  `DONG` varchar(45),
  `SIGUNGU` varchar(45),
  `LAND_USE` varchar(45),
  `DEAL_MONTH` varchar(45) NOT NULL,
  `BUILDING_TYPE` varchar(45),
  `DEAL_DAY` varchar(45),
  `REGIONAL_CODE` varchar(45) NOT NULL,
  `FLOOR` varchar(45),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `trade_items` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `trade_ym_records`;

CREATE TABLE `trade_ym_records` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MINIMUM_DEAL` int(10) unsigned NOT NULL,
  `MAXIMUM_DEAL` int(10) unsigned NOT NULL,
  `AVERAGE_DEAL` int(10) unsigned NOT NULL,
  `MINIMUM_TRADE_ID` int(10) unsigned NOT NULL,
  `MAXIMUM_TRADE_ID` int(10) unsigned NOT NULL,
  `REGIONAL_CODE` VARCHAR(45) NOT NULL,
  `DEAL_YM` VARCHAR (6) NOT NULL,
  `TRADE_COUNT` int(10),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `REGION_YM_UNIQUE` (`REGIONAL_CODE`, `DEAL_YM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `trade_ym_records` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;