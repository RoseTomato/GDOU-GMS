-- MySQL dump 10.13  Distrib 5.6.20, for Win64 (x86_64)
--
-- Host: localhost    Database: db_gdou_gms
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(4) DEFAULT '男',
  `age` int(11) DEFAULT '18',
  `birthday` date DEFAULT NULL,
  `state` varchar(10) DEFAULT '未冻结',
  `head_image` varchar(40) DEFAULT 'defaultHeadImage',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','Rose','18344067111','广东省湛江市广东海洋大学','男',18,NULL,'未冻结','defaultHeadImage'),(3,'lovedrose','e10adc3949ba59abbe56e057f20f883e','Rose','18344067421','广东海洋大学','男',18,NULL,'未冻结','defaultHeadImage'),(4,'753653287@qq.com','123456','Rose','18320303378','伊利诺顿','女',25,'1994-01-01','未冻结','defaultHeadImage'),(5,'lovedrose@outlook.com','123456','艾洛斯','18344067421','美国芝加哥','男',20,'2015-03-20','未冻结','5'),(6,'test@qq.com','e10adc3949ba59abbe56e057f20f883e','Rose','18320303378','旧城以西','女',20,'2015-03-20','未冻结','6'),(7,'fieldManager@gdou.cn','e10adc3949ba59abbe56e057f20f883e','Rose','18344067421','上海南站','女',20,'2015-03-29','未冻结','7'),(8,'Rose','e10adc3949ba59abbe56e057f20f883e','Rose','18320303378','','男',20,'2015-03-19','冻结','8'),(9,'test','e10adc3949ba59abbe56e057f20f883e','Rose','18320303378','','女',20,'2015-03-25','冻结','defaultHeadImage'),(10,'test01','e10adc3949ba59abbe56e057f20f883e','Rose','18320303378','','男',20,'2015-03-19','冻结','defaultHeadImage'),(11,'test02','e10adc3949ba59abbe56e057f20f883e','哈哈哈哈','18320303111','孤傲的广东大学','男',20,'2015-11-11','未冻结','defaultHeadImage');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (7,'新增管理员',2,NULL),(8,'删除管理员',2,NULL),(9,'更新管理员',2,NULL),(10,'查询管理员',2,NULL),(11,'新增管理员',1,NULL),(14,'测试02',NULL,'测是'),(15,'测试03',NULL,'测试'),(16,'测试04',NULL,'测是'),(17,'测试05',NULL,'测是'),(18,'测试06',NULL,'测是'),(19,'测试07',NULL,'测是'),(21,'新增管理员',NULL,'为系统添加一个管理员'),(22,'删除管理员',NULL,'删除管理员数据'),(23,'查看管理员',NULL,'查看管理员'),(24,'更新管理员',NULL,'更新管理员信息'),(32,'测试02',32,NULL),(33,'测试03',32,'测试');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`manager_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'超级管理员',NULL,NULL),(2,'组织结构管理员',NULL,NULL),(3,'器材管理员',NULL,NULL),(4,'赛事管理员',NULL,'负责赛事审核、安排等工作'),(5,'用户管理员',NULL,NULL),(7,'超级管理员',1,NULL),(32,'测试管理者',NULL,'测试的'),(33,'测试管理者',9,'测试的');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-24 23:28:41
