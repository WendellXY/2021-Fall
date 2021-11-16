-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wku
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `P_CODE` varchar(10) NOT NULL,
  `P_DESCRIPT` varchar(35) NOT NULL,
  `P_INDATE` date NOT NULL,
  `P_QOH` smallint(6) NOT NULL,
  `P_MIN` smallint(10) DEFAULT NULL,
  `P_PRICE` decimal(9,2) DEFAULT NULL,
  `P_DISCOUNT` decimal(5,2) NOT NULL,
  `V_CODE` int(11) DEFAULT NULL,
  `P_SALECODE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`P_CODE`),
  UNIQUE KEY `P_CODE` (`P_CODE`),
  KEY `V_CODE` (`V_CODE`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`V_CODE`) REFERENCES `vendor` (`V_CODE`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('0101','Cake','2021-10-21',3,2,4.00,0.00,2126,'7'),('0102','colla','2021-10-21',4,5,5.50,0.00,2126,'5'),('0104','Cake','2021-10-21',8,2,3.40,0.00,2126,'7'),('10000','TestTriger2','2021-10-27',9,9,30.00,0.00,2126,NULL),('101010','banana','2021-10-20',2,3,3.40,0.00,6367,'5'),('1299','TestTrigger','2021-11-07',4,NULL,50.00,0.00,2126,NULL),('5555','ORANGE','2021-12-26',7,7,7.00,0.00,6367,'5'),('77799','TestTrigger3','2021-11-07',4,NULL,50.00,0.00,NULL,NULL),('8888','LIGHT','2021-10-21',9,9,9.00,0.00,6367,'5'),('99999','MOUSE','2021-10-27',8,8,8.00,0.00,2126,'5');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-16 10:07:03
