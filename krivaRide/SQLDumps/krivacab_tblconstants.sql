-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: krivacab
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `tblconstants`
--

DROP TABLE IF EXISTS `tblconstants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblconstants` (
  `constId` int(11) NOT NULL AUTO_INCREMENT,
  `constCode` varchar(50) NOT NULL,
  `constCodeDesc` varchar(50) NOT NULL,
  `constType` varchar(50) NOT NULL,
  `constSubType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`constId`),
  UNIQUE KEY `constCode` (`constCode`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblconstants`
--

LOCK TABLES `tblconstants` WRITE;
/*!40000 ALTER TABLE `tblconstants` DISABLE KEYS */;
INSERT INTO `tblconstants` VALUES (1,'micro','Micro','CabType',NULL),(2,'mini','Mini','CabType',NULL),(3,'sedan','Sedan','CabType',NULL),(4,'prime','Prime Sedan','CabType',NULL),(5,'LNG','Mahindra Logan','Cab','sedan'),(6,'HND','Honda City','Cab','sedan'),(7,'WAG','Maruti Wagon R','Cab','mini'),(8,'55','55','Rate','sedan'),(9,'80','80','Rate','prime'),(10,'45','45','Rate','mini');
/*!40000 ALTER TABLE `tblconstants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 22:25:23
