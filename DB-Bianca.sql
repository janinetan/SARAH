-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: sarah_kb
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `eventId` int(11) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `objectId` int(11) NOT NULL,
  `duration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (0,'sleep',1,120),(0,'drinkWater',2,5),(0,'takeMedicine',3,5),(0,'play',4,60);
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `body_part`
--

DROP TABLE IF EXISTS `body_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `body_part` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_part`
--

LOCK TABLES `body_part` WRITE;
/*!40000 ALTER TABLE `body_part` DISABLE KEYS */;
/*!40000 ALTER TABLE `body_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dialogue`
--

DROP TABLE IF EXISTS `dialogue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dialogue` (
  `dump` int(11) NOT NULL AUTO_INCREMENT,
  `eventId` int(11) NOT NULL,
  `message` varchar(200) NOT NULL,
  PRIMARY KEY (`dump`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dialogue`
--

LOCK TABLES `dialogue` WRITE;
/*!40000 ALTER TABLE `dialogue` DISABLE KEYS */;
INSERT INTO `dialogue` VALUES (1,1,'Hello there <user>!'),(2,2,'It\'s a sunny Sunday morning and Liam wants to play with you!'),(3,3,'Liam has missed you so much!'),(4,4,'Just wait right here.'),(5,5,'I\'ll call him out.'),(6,6,'Oh, wow!'),(7,7,'Hi, <user>!'),(8,8,'How have you been?'),(9,9,'I feel sick.'),(10,10,'I have been <symptoms>.'),(11,11,'Oh, dear!'),(12,12,'I think you are sick!'),(13,13,'You should rest and drink some medicine.'),(14,14,'But I want to play with <user>!'),(15,15,'No, no...'),(16,16,'Playing will make you feel tired and you might get worse.'),(17,17,'<user>, what do you think?'),(18,0,'If you really want to...'),(19,0,'I\'ll be right here if you need me.'),(20,0,'Oh, no!'),(21,0,'I think I\'m going to faint.'),(22,0,'Let\'s go back to Sarah.'),(23,0,'I don\'t think I can handle this.'),(24,0,'Hey, kiddos!'),(25,0,'Gosh, Liam!'),(26,0,'You\'re <symptoms>. '),(27,0,'You should rest.'),(28,0,'I think you have <sickness>.'),(29,0,'What\'s that?'),(30,0,'I don\'t know that.'),(31,0,'<user>, do you know what <sickness> is?'),(32,0,'Fine, I\'m going to rest but <user>, will you stay with us?'),(33,0,'Yeah, I\'ll entertain <user> as you sleep.'),(34,0,'Go take your nap now.'),(35,0,'Good afternoon!'),(36,0,'I just woke up and what a refreshing nap that was!'),(37,0,'I feel better now, but I still feel a little <symptoms>.'),(38,0,'Oh, I think I know what you have now.'),(39,0,'What?'),(40,0,'You show signs of <sickness>.'),(41,0,'Well, I want to play!'),(42,0,'Well, <sickness> is caused by <causes>.'),(43,0,'You can get sick if they get inside your <body-part-affected>.'),(44,0,'<body-part-affected> <body-part-definition>.'),(45,0,'Can you prevent that?'),(46,0,'Yes, of course!'),(47,0,'Oh, <user>, help me out.'),(48,0,'Any clue on what preventive practices these are?'),(49,0,'To avoid having <sickness>, we have to <prevention>.'),(50,0,'Oh, I did not know that.'),(51,0,'Maybe that was how I got <sickness>.'),(52,0,'True enough, so better observe these preventive practices at all times.'),(53,0,'This way, you won\'t get <sickness> that easily.'),(54,0,'But for now, what can I do to stop <sickness> from affecting my <body-part-affected>?'),(55,0,'You have to <treatment> for him to get better.'),(56,0,'Oh, do I really have to?'),(57,0,'I think I can feel better even without doing what you just said.'),(58,0,'<user>, can you help me convince Liam to take his <treatment>?'),(59,0,'Oh well, don\'t listen to me and your <body-part-affected> will be sick and you won\'t be able to get better.'),(60,0,'Never ever?'),(61,0,'Never.'),(62,0,'Really?'),(63,0,'Hmmm... Well, let\'s see about that!'),(64,0,'Anyway, I feel that I have to rest now.'),(65,0,'Thank you for visiting us <user>!'),(66,0,'Come back a few days later and see me prove to Sarah that I don\'t need <treatment> to get better.'),(67,0,'Take care, <user!>'),(68,0,'Oh, fine... Let me have them now, please?'),(69,0,'Here you go Liam!'),(70,0,'Yes, I feel so much better now.'),(71,0,'Uhmmm... I don\'t think it works that fast Liam. '),(72,0,'You have to rest for the day, too.'),(73,0,'Rest again?'),(74,0,'Fine... I\'m feeling tired anyway.'),(75,0,'Yes, I\'ll catch up to you Liam.'),(76,0,'<user>, I\'m sorry but I have to take care of Liam now.'),(77,0,'Thank you for spending this day with us!'),(78,0,'I hope you also learned from Liam\'s experience on <sickness>.'),(79,0,'Let\'s check up on Liam together on some other day.'),(80,0,'Hopefully, he\'ll be feeling much better by then.'),(81,0,'Hi Liam!'),(82,0,'How are you?'),(83,0,'<user> is here to visit you.'),(84,0,'I still feel the same as last time you visitied me.'),(85,0,'I\'m sorry, Sarah.'),(86,0,'I didn\'t listen to you about getting <treatment>.'),(87,0,'I\'m sorry, may I have it now?'),(88,0,'Of course, Liam!'),(89,0,'Here you go.'),(90,0,'Thank you Sarah for being here to teach us about <sickness>.'),(91,0,'No problem, Liam.'),(92,0,'Get well soon!'),(93,0,'I hope both of you learned a lot about <sickness>.'),(94,0,'See you soon!'),(95,0,'I\'m all better now, thanks to you Sarah!'),(96,0,'I think we also have <user> to thank for that.'),(97,0,'Stay healthy and see you soon!');
/*!40000 ALTER TABLE `dialogue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discourse_act`
--

DROP TABLE IF EXISTS `discourse_act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discourse_act` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discourse_act`
--

LOCK TABLES `discourse_act` WRITE;
/*!40000 ALTER TABLE `discourse_act` DISABLE KEYS */;
INSERT INTO `discourse_act` VALUES (1,'default'),(2,'userFeelsSick'),(3,'userFeelsFine'),(4,'userSuggestsPlaying'),(5,'userSuggestsResting'),(6,'userDoesNotKnow'),(7,'userAnswersPartially'),(8,'userShowsApathy'),(9,'userSuggestsTakingTreatment'),(10,'userSuggestsNotTakingTreatment');
/*!40000 ALTER TABLE `discourse_act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode`
--

DROP TABLE IF EXISTS `episode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `episode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `episodeGoalId` int(11) NOT NULL,
  `eventId` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` VALUES (1,1,'1, 2, 3, 4, 5'),(2,2,'6, 7, 8'),(3,3,'9, 10, 11, 12, 13, 14, 15, 16, 17');
/*!40000 ALTER TABLE `episode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode_goal`
--

DROP TABLE IF EXISTS `episode_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `episode_goal` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_goal`
--

LOCK TABLES `episode_goal` WRITE;
/*!40000 ALTER TABLE `episode_goal` DISABLE KEYS */;
INSERT INTO `episode_goal` VALUES (1,'setSetting'),(2,'greetPlayer'),(3,'revealSymptoms'),(4,'revealAilment - positive'),(5,'revealAilment - negative'),(6,'revealAilment - neutral'),(7,'defineAilment'),(8,'reviewPrevention'),(9,'explainTreatment'),(10,'endStory');
/*!40000 ALTER TABLE `episode_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode_set`
--

DROP TABLE IF EXISTS `episode_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `episode_set` (
  `id` int(11) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  `episodeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_set`
--

LOCK TABLES `episode_set` WRITE;
/*!40000 ALTER TABLE `episode_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `episode_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `virtualAgentId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,2),(7,2),(8,2),(9,2),(10,2),(11,1),(12,1),(13,1),(14,2),(15,1),(16,1),(17,1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `imageFilePath` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (1,'sleep','‪D:\\sample.png'),(2,'drinkWater','‪D:\\sample.png'),(3,'takeMedicine','‪D:\\sample.png'),(4,'play','‪D:\\sample.png');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discourseActId` int(11) NOT NULL,
  `message` varchar(200) NOT NULL,
  `virtualAgentId` int(11) NOT NULL,
  `responseType` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
INSERT INTO `response` VALUES (1,0,'Good for you!',2,'neutral'),(2,0,'Oh, me too...',2,'neutral'),(3,0,'Oh, I see...',2,'neutral'),(4,0,'Yay! Let\'s go play <user>!',2,'negative'),(5,0,'See? <user> thinks the same way as me.',1,'positive'),(6,0,'Tell us about it...',1,'neutral'),(7,0,'That\'s right, <user>!',1,'positive'),(8,0,'Yes, some of those facts are correct.',1,'positive'),(9,0,'Come on now, <user>. Is that the right attitude? You should care because he is your playmate. Let us help him get better.',1,'negative'),(10,0,'Come on now, Liam. Don\'t pressure <user>.',1,'neutral'),(11,0,'That\'s partly true. Yes, <user>.',1,'positive'),(12,0,'Ouch, that hurts! Help me get better and we can play again.',2,'negative'),(13,0,'Yay! No need to take <treatment>.',2,'negative'),(14,0,'What now, Liam? Even <user> agrees with me.',1,'positive'),(15,0,'Come now, <user>. We have to help Liam get better because he is your playmate.',1,'negative');
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semantic`
--

DROP TABLE IF EXISTS `semantic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semantic` (
  `id` int(11) NOT NULL,
  `concept1` varchar(45) NOT NULL,
  `concept2` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semantic`
--

LOCK TABLES `semantic` WRITE;
/*!40000 ALTER TABLE `semantic` DISABLE KEYS */;
/*!40000 ALTER TABLE `semantic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sickness`
--

DROP TABLE IF EXISTS `sickness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sickness` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `definition` varchar(200) NOT NULL,
  `symptoms` varchar(200) NOT NULL,
  `causes` varchar(200) NOT NULL,
  `treatement` varchar(200) NOT NULL,
  `prevention` varchar(200) NOT NULL,
  `bodyPartId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sickness`
--

LOCK TABLES `sickness` WRITE;
/*!40000 ALTER TABLE `sickness` DISABLE KEYS */;
/*!40000 ALTER TABLE `sickness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virtual_peer`
--

DROP TABLE IF EXISTS `virtual_peer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virtual_peer` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `emotionalState` varchar(45) DEFAULT NULL,
  `isSick` tinyint(1) DEFAULT NULL,
  `imageFilePath` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virtual_peer`
--

LOCK TABLES `virtual_peer` WRITE;
/*!40000 ALTER TABLE `virtual_peer` DISABLE KEYS */;
/*!40000 ALTER TABLE `virtual_peer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sarah_kb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-30 17:46:02
