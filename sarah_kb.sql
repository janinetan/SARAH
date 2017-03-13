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
  `use` varchar(500) NOT NULL,
  `description` varchar(500) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_part`
--

LOCK TABLES `body_part` WRITE;
/*!40000 ALTER TABLE `body_part` DISABLE KEYS */;
INSERT INTO `body_part` VALUES (1,'nose','that you smell and breathe with.','is in the middle of your face',1),(2,'mouth','that is used for eating and talking.','is the natural opening through which food passes',1),(3,'stomach','that stores the food you’ve eaten, that breaks down the food into a liquidy mixture, that slowly empties that liquidy mixture of food into the small intestine.','is a stretchy sack shaped like the letter J',2),(4,'intestines','that break down the food mixture even more so your body can absorb all the vitamins, minerals, proteins, carbohydrates and fats.','are long tubes packed inside you beneath your stomach',2),(5,'lungs','that help you breathe.','are pairs of packet like organs inside your chest',3),(6,'eyes','that allows us to see.',' is about the size and shape of a ping-pong ball',1),(7,'skin','that covers and protects everything inside our body.','is the thin, protective layer that we have on the outside of our bodies',5),(8,'skin','that covers and protects everything inside our body.','is the thin, protective layer that we have on the outside of our bodies',7),(9,'eyes','that allows us to see.',' is about the size and shape of a ping-pong ball',7),(10,'nose','that you smell and breathe with.','is in the middle of your face',7),(11,'mouth','that is used for eating and talking.','is the natural opening through which food passes',7),(12,'stomach','that stores the food you’ve eaten, that breaks down the food into a liquidy mixture, that slowly empties that liquidy mixture of food into the small intestine.','is a stretchy sack shaped like the letter J',7),(13,'nose','that you smell and breathe with.','is in the middle of your face',8),(14,'throat','ensures that food proceeds to the stomach for digestion.','is the tube that leads from your mouth, through your neck, to your stomach and lungs',8),(15,'lungs','that help you breathe.','are pairs of packet like organs inside your chest',9),(16,'urinary track','that removes wastes and extra water from your body.','is a system made up of these main parts: two kidneys, two ureters',10);
/*!40000 ALTER TABLE `body_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `causes`
--

DROP TABLE IF EXISTS `causes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `causes` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `causes`
--

LOCK TABLES `causes` WRITE;
/*!40000 ALTER TABLE `causes` DISABLE KEYS */;
INSERT INTO `causes` VALUES (1,'cold-causing virus',1),(2,'bacteria',2),(3,'virus',4),(4,'dengue virus',5),(5,'virus',6),(6,'allergen',7),(7,'virus infection',8),(8,'germs',9),(9,'bacteria',10),(10,'various irritants and substances',3),(11,'bacterial infection',6);
/*!40000 ALTER TABLE `causes` ENABLE KEYS */;
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
  `keyword` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discourse_act`
--

LOCK TABLES `discourse_act` WRITE;
/*!40000 ALTER TABLE `discourse_act` DISABLE KEYS */;
INSERT INTO `discourse_act` VALUES (1,'default','0'),(2,'userFeelsSick','try hello'),(3,'userFeelsFine','0'),(4,'userSuggestsPlaying','0'),(5,'userSuggestsResting','0'),(6,'userDoesNotKnow','0'),(7,'userAnswersPartially','0'),(8,'userShowsApathy','0'),(9,'userSuggestsTakingTreatment','0'),(10,'userSuggestsNotTakingTreatment','0');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` VALUES (1,1,'1,2,da(1)'),(2,2,'3,4,5,6,7,8,da(2)'),(3,3,'9,10,11,12,13,14,15,16,17,18,19,20,da(3)'),(4,4,'21,22,23,da(4)'),(5,5,'24,25,26,27,28,29,30,da(5)'),(6,6,'31,32,14,15,33,34,35,36,37,38'),(7,7,'39,40,41,42,43,44,45');
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
INSERT INTO `episode_goal` VALUES (1,'greetPlayer'),(2,'revealSymptoms'),(3,'revealAilment'),(4,'defineAilment'),(5,'reviewPrevention'),(6,'explainTreatment'),(7,'endStory');
/*!40000 ALTER TABLE `episode_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode_set`
--

DROP TABLE IF EXISTS `episode_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `episode_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `episodesId` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_set`
--

LOCK TABLES `episode_set` WRITE;
/*!40000 ALTER TABLE `episode_set` DISABLE KEYS */;
INSERT INTO `episode_set` VALUES (1,'1,2,3,4,5,6,7'),(2,'1,2,3,4,5,6,7');
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
  `ruling` int(11) DEFAULT NULL COMMENT '2 - good\n1 - bad\nnull - neutral',
  `type` int(11) DEFAULT '1' COMMENT '1 - message\n0 - action',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,NULL,1),(2,NULL,1),(3,1,1),(4,1,1),(5,2,1),(6,2,1),(7,NULL,1),(8,NULL,1),(9,1,1),(10,1,1),(11,1,1),(12,1,1),(13,1,1),(14,2,1),(15,2,1),(16,2,1),(17,2,1),(18,2,1),(19,NULL,1),(20,NULL,1),(21,NULL,1),(22,NULL,1),(23,NULL,1),(24,NULL,1),(25,NULL,1),(26,NULL,1),(27,NULL,1),(28,NULL,1),(29,NULL,1),(30,NULL,1),(31,1,1),(32,1,1),(33,2,1),(34,2,1),(35,2,1),(36,2,1),(37,2,1),(38,2,1),(39,NULL,1),(40,1,1),(41,1,1),(42,1,1),(43,1,1),(44,2,1),(45,2,1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(250) NOT NULL,
  PRIMARY KEY (`eventId`),
  UNIQUE KEY `message_UNIQUE` (`message`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (22,'askPrevention'),(8,'commentNegativeFiller + counterPlay + user-askPlay'),(12,'commentNegativeFiller + stateNegativeStatus + transitionToSarah'),(2,'commentPositiveFiller + greetUser + user-askFeeling'),(34,'commentPositiveFiller + stateHealed'),(9,'counterPlay'),(14,'expressDelight'),(29,'expressDisbelief + counterTreatment'),(32,'expressDisbelief + expressStubbornness + transitionToEndDay + expressAppreciation + comeBack + expressFarewell'),(36,'expressDisbelief + succumb + stateRest'),(35,'expressDoubt + suggestFullDayRest'),(41,'expressEmpathy'),(38,'expressFarewell'),(19,'expressNaivety'),(25,'expressNaivety + expressWonder'),(7,'expressPlayfulness'),(40,'expressSick + expressRegret + takeTreatment'),(33,'giveTreatment'),(13,'greetKids + expressWorry + suggestRest + stateSickness'),(17,'greetUser + commentRest + stateTraceOfSymptom'),(1,'greetUser + mentionSetting + introduceLiam + transitionToLiam'),(10,'ignoreSarah + expressPlayfulness'),(39,'introduceLiam'),(6,'respondToStatus + suggestLiamRest'),(26,'stateAgreement + encouragePrevention + concludePrevention'),(23,'stateAgreement + user-askPrevention'),(21,'stateCauses + stateBodyPart + explainBodyPart'),(44,'stateHealed + thankSarah'),(37,'stateLiamCare + expressAppreciation + stateMoral + comeBack'),(5,'stateNegativeStatus + stateSymptom'),(24,'statePrevention'),(18,'stateSickness'),(28,'stateTreatment'),(31,'stateWarning'),(15,'succumb'),(11,'succumb + transitionToPlay'),(4,'suggestBothRest'),(3,'suggestPlay'),(42,'thankSarah'),(45,'thankUser + stateMoral + wishGoodHealth + expressFarewell'),(16,'transitionToRest'),(27,'transitionToTreatment'),(20,'user-askSickness'),(30,'user-askTreatment'),(43,'wishGoodHealth + stateMoral + expressFarewell');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
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
-- Table structure for table `prevention`
--

DROP TABLE IF EXISTS `prevention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prevention` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prevention`
--

LOCK TABLES `prevention` WRITE;
/*!40000 ALTER TABLE `prevention` DISABLE KEYS */;
INSERT INTO `prevention` VALUES (1,'frequently wash our hands',1),(2,'cover our mouth with our forearm when coughing',1),(3,'avoid touching our eyes',1),(4,'avoid touching our nose',1),(5,'avoid touching our mouth',1),(8,'frequently wash our hands',2),(9,'avoid drinking raw milk',2),(10,'avoid eating raw meat or shellfish',2),(11,'clean and wash regularly',4),(12,'avoid contact with people who have chicken pox',4),(13,'use mosquito repellents',5),(14,'wear protective clothing',5),(15,'wash our hand often',6),(16,'carry hand sanitizers',6),(17,'avoid touching our nose',6),(18,'avoid touching our mouth',6),(19,'avoid toucing our eyes',6),(20,'avoid our allergens',7),(21,'take medicine given by our doctor',7),(22,'get an immunization',8),(23,'limit interaction with people who have measles',8),(24,'frequent hand cleaning',9),(26,'avoid interaction with those who are sick',9),(27,'get enough rest',9),(28,'receive proper nutrition',9),(29,'drink plenty of water',10);
/*!40000 ALTER TABLE `prevention` ENABLE KEYS */;
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
  `ruling` int(11) NOT NULL COMMENT '0 - good\n1 - bad',
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
-- Table structure for table `sentence`
--

DROP TABLE IF EXISTS `sentence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sentence` (
  `dump` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) NOT NULL,
  `message` varchar(200) NOT NULL,
  PRIMARY KEY (`dump`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sentence`
--

LOCK TABLES `sentence` WRITE;
/*!40000 ALTER TABLE `sentence` DISABLE KEYS */;
INSERT INTO `sentence` VALUES (111,'greetUser','Hello there, <user>!'),(112,'mentionSetting','It\'s a sunny <day> morning!'),(113,'introduceLiam','Liam wants to play with you!'),(114,'transitionToLiam','Just wait right here, I\'ll call him out.'),(115,'commentPositiveFiller','Oh, wow!'),(116,'greetUser','Hi, <user>!'),(117,'user-askFeeling','How have you been?'),(118,'suggestPlay','But I think we can still go out and play!'),(119,'suggestBothRest','I think both of you should rest and [treatment] first before you go out and play.'),(120,'stateNegativeStatus','I feel <liam-status>.'),(121,'stateSymptom','I have been experiencing [symptom] lately.'),(122,'respondToStatus','Oh dear, I think you are sick!'),(123,'suggestLiamRest','You should rest and [treatment].'),(124,'expressPlayfulness','I still want to play with <user>.'),(125,'commentNegativeFiller','No, no...'),(126,'counterPlay','Playing will make you tired and you might get worse.'),(127,'user-askPlay','<user>, what do you think?'),(128,'counterPlay','You shouldn\'t play when you\'re sick.'),(129,'ignoreSarah','Ignore her.'),(130,'expressPlayfulness','Let\'s go play, <user>!'),(131,'succumb','If you really want to...'),(132,'transitionToPlay','I\'ll be right here if you need me.'),(133,'commentNegativeFiller','Oh, no!'),(134,'stateNegativeStatus','I think I\'m gonna faint.'),(135,'transitionToSarah','Let\'s go back to Sarah.'),(136,'greetKids','Hey kids!'),(137,'expressWorry','Gosh Liam, you\'re [symptom] and [symptom] are getting worse.'),(138,'suggestRest','You shoud rest.'),(139,'stateSickness','I think you have <sickness>.'),(140,'expressDelight','<user> thinks the same way as me!'),(141,'succumb','Fine, do as you say.'),(142,'transitionToRest','I\'ll entertain <user> as you sleep.'),(143,'greetUser','Hey there, <user>!'),(144,'commentRest','I just woke up and what a refreshing nap that was!'),(145,'stateTraceOfSymptom','I still feel a little [symptom].'),(146,'stateSickness','Oh, I think you show signs of <sickness>.'),(147,'expressNaivety','I did not know that.'),(148,'user-askSickness','How about you <user>, do you know what <sickness> is?'),(149,'stateCauses','Well, <sickness> is caused by [cause].'),(150,'stateBodyPart','You can get sick if they get inside your <body-part-affected>.'),(151,'explainBodyPart','<body-part-affected> <body-part-definition>.'),(152,'askPrevention','Can you prevent that?'),(153,'stateAgreement','Yes, of course.'),(154,'user-askPrevention','<user>, help me out, any clue on to prevent <sickness>?'),(155,'statePrevention','To avoid having <sickness>, we have to [prevention] and [prevention].'),(156,'expressWonder','Maybe that was how I got my <sickness>.'),(157,'stateAgreement','True enough.'),(158,'encouragePrevention','So, better observe these practices at all times.'),(159,'concludePrevention','This way, you won\'t get <sickness> that easily.'),(160,'transitionToTreatment','But for now, what can I do to stop <sickness> from affecting my <body-part-affected>?'),(161,'stateTreatment','You have to [treatment] for you to get better.'),(162,'expressDisbelief','Oh, do I really have to?'),(163,'counterTreatment','I think I can feel better even without [treatment].'),(164,'user-askTreatment','<user>, can you help me convince Liam to take his [treatment]?'),(165,'stateWarning','Oh well, don\'t listen to me and your <body-part-affected> will be sick and you won\'t be able to get better.'),(166,'expressDisbelief','Really?'),(167,'expressStubbornness','Hmmm... Well let\'s see about that!'),(168,'transitionToEndDay','Anyway, I feel that I have to rest now.'),(169,'expressAppreciation','Thank you for visiting us <user>!'),(170,'comeBack','Come back a few days later and see the <sickness> go away by then.'),(171,'expressFarewell','Take care <user>!'),(172,'succumb','Oh fine...'),(173,'giveTreatment','Here\'s your [treatment] Liam!'),(174,'commentPositiveFiller','Yes!'),(175,'stateHealed','I feel so much better now!'),(176,'expressDoubt','I don\'t think it works that way Liam.'),(177,'suggestFullDayRest','You have to rest for the day, too.'),(178,'expressDisbelief','Oh, is that so?'),(179,'succumb','Fine...'),(180,'stateRest','I\'m resting.'),(181,'stateLiamCare','I\'ll catch up to you Liam.'),(182,'expressAppreciation','Thank you for spending this day with us!'),(183,'stateMoral','I hope you also learned from Liam\'s experience on <sickness>.'),(184,'comeBack','Let\'s meet again tomorrow and see if the <sickness> will go away by then.'),(185,'expressFarewell','See you, <user>!'),(186,'introduceLiam','Hey Liam, <user> is here to visit you!'),(187,'expressSick','I still feel the same as last time you visited me.'),(188,'expressRegret','I\'m sorry Sarah, I didn\'t listen to you about getting [treatment].'),(189,'takeTreatment','I\'m sorry, may I have it now?'),(190,'expressEmpathy','Of course Liam, here you go.'),(191,'thankSarah','Thank you Sarah, for being here to teach us about sickness.'),(192,'wishGoodHealth','Get well soon!'),(193,'stateMoral','I hope both of you learned a lot about sickness.'),(194,'expressFarewell','See you soon!'),(195,'stateHealed','I feel much better now.'),(196,'thankSarah','Thank you Sarah for being so patient with us!'),(197,'thankUser','I think we also have <user> to thank for that.'),(198,'wishGoodHealth','Keep a healthy lifestyle!'),(199,'expressFarewell','\'Til next time!');
/*!40000 ALTER TABLE `sentence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sickness`
--

DROP TABLE IF EXISTS `sickness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sickness` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sickness`
--

LOCK TABLES `sickness` WRITE;
/*!40000 ALTER TABLE `sickness` DISABLE KEYS */;
INSERT INTO `sickness` VALUES (1,'Cough and cold'),(2,'Stomach ache'),(3,'Asthma'),(4,'Chicken pox'),(5,'Dengue'),(6,'Fever'),(7,'Allergy'),(8,'Measles'),(9,'Pneumonia'),(10,'Urinary tract infection (UTI)');
/*!40000 ALTER TABLE `sickness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom`
--

DROP TABLE IF EXISTS `symptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom`
--

LOCK TABLES `symptom` WRITE;
/*!40000 ALTER TABLE `symptom` DISABLE KEYS */;
INSERT INTO `symptom` VALUES (1,'runny nose',1),(2,'stuffy nose',1),(3,'sneezing',1),(4,'coughs',1),(5,'sore throat',1),(6,'scratchy throat',1),(7,'watery eyes',1),(8,'itchy eyes',1),(9,'headaches',1),(10,'body aches',1),(11,'low fever',1),(12,'loss of appetite',2),(13,'vomiting',2),(14,'diarrhea',2),(15,'stomach pains',2),(16,'stomach cramps',2),(17,'fever',2),(18,'shortness of breath',3),(19,'tightness of chest ',3),(20,'wheezes',3),(21,'constant coughs',3),(22,'fever ',4),(23,'headaches',4),(24,'rashes',4),(25,'loss of appetite',4),(26,'high fever',5),(27,'terrible headaches',5),(28,'eye pains',5),(29,'joint and muscle pains',5),(30,'tiredness',5),(31,'vomiting',5),(32,'skin rashes',5),(33,'high body temperature',6),(34,'skin rashes',7),(35,'headaches',7),(36,'sneezing',7),(37,'runny nose',7),(38,'swelling',7),(39,'diarrhea',7),(40,'coughs',8),(41,'fever',8),(42,'red eyes',8),(43,'muscle pains',8),(44,'runny nose',8),(45,'sore throat',8),(46,'white spots inside the mouth',8),(47,'measles rash',8),(48,'dry coughs',9),(49,'low fever',9),(50,'headaches',9),(51,'tiredness',9),(52,'fever',10),(53,'tiredness',10),(54,'lower stomach pain',10),(55,'sweating',6),(56,'shivering',6),(57,'headaches',6),(58,'muscle pains',6),(59,'loss of appetite',6),(60,'dehydration',6),(61,'genral weakness',6);
/*!40000 ALTER TABLE `symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sicknessId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (1,'drink water',1),(2,'gargle with warm slat water',1),(3,'use cough drops',1),(4,'use throat sprays',1),(5,'take cold medicines',1),(6,'take nasal drops',1),(7,'drink water regularly',2),(8,'eat some salty foods',2),(9,'eat foods with potassium',2),(10,'prevent itching and scratching',4),(11,'drink plenty of water',4),(12,'take painkillers for fever',4),(13,'rest',5),(14,'drink plenty of water',5),(15,'staying cool',6),(16,'rest',6),(17,'drink plenty of water',6),(18,'rest',8),(19,'drink plenty of water',8),(20,'use himidifier in the room',8),(21,'take vintamin A supplement',8),(22,'drink a lot of water',9),(23,'eat healthy food',9),(24,'take medicine',9),(25,'rest',9),(26,'drink antibiotics',10);
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
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
INSERT INTO `virtual_peer` VALUES (1,'Sarah','happy',0,'image'),(2,'Liam','sick',1,'image');
/*!40000 ALTER TABLE `virtual_peer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 18:45:35
