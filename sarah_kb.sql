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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(100) DEFAULT NULL,
  `object_category` varchar(100) DEFAULT NULL,
  `duration` varchar(100) NOT NULL,
  `episode_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (1,'play','outdoorToy','30 minutes',NULL),(2,'play','indoorToy','30 minutes',NULL),(3,'eat','food','10 minutes',NULL),(5,'scratch','facialBodyPart','1 minute',NULL),(6,'wash','hands','1 minute',NULL),(7,'sleep','sleepObject','4 hours',NULL),(8,'rest','restObject','1 hour',NULL),(9,'sanitize','sanitizer','1 minute',NULL),(10,'drink','water','3 minutes',NULL),(11,'scratch','eyes','1 minute',NULL),(12,'scratch','nose','1 minute',NULL),(13,'scratch ','mouth','1 minute',NULL);
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `motivation` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'play','fun'),(2,'play','interesting'),(3,'play','exciting'),(4,'eat','yummy'),(5,'eat','delicious'),(6,'eat','satisfying'),(7,'play freeze tag with','fun'),(8,'play freeze tag with','interesting'),(9,'play freeze tag with','exciting'),(10,'wash','refreshing'),(11,'wash','clean'),(12,'scratch','relieving'),(13,'sleep','relaxing'),(14,'sleep','refreshing'),(15,'drink','refreshing'),(16,'drink','energizing'),(17,'sanitize','sanitary'),(18,'sanitize','clean'),(19,'rest','relaxing'),(20,'rest','refreshing');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assertion`
--

DROP TABLE IF EXISTS `assertion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assertion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(100) NOT NULL,
  `concept1` varchar(100) NOT NULL,
  `concept2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assertion`
--

LOCK TABLES `assertion` WRITE;
/*!40000 ALTER TABLE `assertion` DISABLE KEYS */;
INSERT INTO `assertion` VALUES (1,'hasProperty','hands','clean'),(2,'hasProperty','hands','dirty'),(3,'hasProperty','body','tired'),(4,'hasProperty','body ','rested'),(5,'hasProperty','body','sweaty'),(6,'oppositeOf','clean','dirty'),(7,'hasProperty','body','hydrated'),(8,'hasProperty','body','dehydrated'),(9,'oppositeOf','tired','rested'),(10,'oppositeOf','hydrated','dehydrated'),(11,'oppositeOf','sweaty','not sweaty'),(12,'is','clean','good'),(13,'is','dirty','bad'),(14,'is','tired','bad'),(15,'is','rested','good'),(16,'is','sweaty','bad'),(17,'is','not sweaty','good'),(18,'is','hydrated','good'),(19,'is','dehydrated','bad');
/*!40000 ALTER TABLE `assertion` ENABLE KEYS */;
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
INSERT INTO `body_part` VALUES (1,'nose','that you smell and breathe with.','is in the middle of your face',1),(2,'mouth','that is used for eating and talking.','is the natural opening through which food passes',1),(3,'stomach','that stores the food you’ve eaten, that breaks down the food into a liquidy mixture, that slowly empties that liquidy mixture of food into the small intestine.','is a stretchy sack shaped like the letter J',2),(4,'intestines','that break down the food mixture even more so your body can absorb all the vitamins, minerals, proteins, carbohydrates and fats.','are long tubes packed inside you beneath your stomach',2),(5,'lungs','that help you breathe.','are pairs of packet like organs inside your chest',3),(6,'eyes','that allows us to see.','are  about the size and shape of a ping-pong ball',1),(7,'skin','that covers and protects everything inside our body.','is the thin, protective layer that we have on the outside of our bodies',5),(8,'skin','that covers and protects everything inside our body.','is the thin, protective layer that we have on the outside of our bodies',7),(9,'eyes','that allows us to see.','are about the size and shape of a ping-pong ball',7),(10,'nose','that you smell and breathe with.','is in the middle of your face',7),(11,'mouth','that is used for eating and talking.','is the natural opening through which food passes',7),(12,'stomach','that stores the food you’ve eaten, that breaks down the food into a liquidy mixture, that slowly empties that liquidy mixture of food into the small intestine.','is a stretchy sack shaped like the letter J',7),(13,'nose','that you smell and breathe with.','is in the middle of your face',8),(14,'throat','ensures that food proceeds to the stomach for digestion.','is the tube that leads from your mouth, through your neck, to your stomach and lungs',8),(15,'lungs','that help you breathe.','are pairs of packet like organs inside your chest',9),(16,'urinary track','that removes wastes and extra water from your body.','is a system made up of these main parts: two kidneys, two ureters',10),(17,'skin','that covers and protects everything inside our body.','is the thin, protective layer that we have on the outside of our bodies',4),(18,'nose','that you smell and breathe with.','is in the middle of your face',4),(19,'mouth','that is used for eating and talking.','is the natural opening through which food passes',4),(20,'nose','that you smell and breathe with.','is in the middle of your face',6),(21,'mouth','that is used for eating and talking.','is the natural opening through which food passes',6);
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
INSERT INTO `causes` VALUES (1,'cold-causing virus',1),(2,'bacteria',2),(3,'virus',4),(4,'dengue virus',5),(5,'virus',6),(6,'allergen',7),(7,'virus infection',8),(8,'germs',9),(9,'bacteria',10),(10,'different irritants',3),(11,'bacterial infection',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` VALUES (1,1,'61,2,da(1)'),(2,2,'3,4,5,6,7,8,da(2)'),(3,3,'9,10,11,62,63,12,13,14,15,16,64,17,18,19,20,da(3)'),(4,4,'21,22,23,da(3)'),(5,5,'24,25,26,27,28,29,30,da(4)'),(6,6,'31,32,65,38,14,66,33,34,35,16,37,67'),(7,7,'39,40,41,42,43,44,45'),(8,8,'46,47'),(9,9,'48,49'),(10,10,'50,51,52,da(8)'),(11,11,'14,15,53,54'),(12,12,'1,55'),(13,13,'56,60,59,57,58');
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
INSERT INTO `episode_goal` VALUES (1,'greetPlayer'),(2,'revealSymptoms'),(3,'revealAilment'),(4,'defineAilment'),(5,'reviewPrevention'),(6,'explainTreatment'),(7,'endStory'),(8,'inviteToDoActivity'),(9,'doActivity'),(10,'reverseCondition');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_set`
--

LOCK TABLES `episode_set` WRITE;
/*!40000 ALTER TABLE `episode_set` DISABLE KEYS */;
INSERT INTO `episode_set` VALUES (1,'12,8,13,9,1,2,3,4,5,6,7');
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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,NULL,1),(2,NULL,1),(3,1,1),(4,1,1),(5,2,1),(6,2,1),(7,NULL,1),(8,NULL,1),(9,1,1),(10,1,1),(11,1,1),(12,1,1),(13,1,1),(14,2,1),(15,1,1),(16,2,1),(17,2,1),(18,2,1),(19,2,1),(20,NULL,1),(21,NULL,1),(22,NULL,1),(23,NULL,1),(24,NULL,1),(25,NULL,1),(26,NULL,1),(27,NULL,1),(28,NULL,1),(29,NULL,1),(30,NULL,1),(31,1,1),(32,1,1),(33,2,1),(34,2,1),(35,2,1),(37,2,1),(39,NULL,1),(40,1,1),(41,1,1),(42,1,1),(43,1,1),(44,2,1),(45,2,1),(46,NULL,1),(47,NULL,1),(48,NULL,2),(49,NULL,1),(50,NULL,1),(51,NULL,1),(52,NULL,1),(53,2,1),(54,1,1),(55,NULL,1),(56,2,2),(57,1,1),(58,NULL,1),(59,2,1),(60,2,1),(61,NULL,1),(62,1,1),(63,1,1),(64,2,1),(65,NULL,1),(66,2,1),(67,2,1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `location` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (4,1,'park'),(5,2,'park'),(6,3,'park'),(7,4,'park'),(8,5,'park'),(9,5,'school'),(10,5,'home'),(11,6,'park'),(12,6,'school'),(13,6,'home'),(14,7,'home'),(15,8,'park'),(16,9,'park'),(17,9,'school'),(18,9,'home'),(19,10,'park'),(20,10,'school'),(21,10,'home'),(22,3,'school'),(23,3,'home'),(24,11,'park'),(25,11,'home'),(26,11,'school'),(27,12,'park'),(28,12,'home'),(29,12,'school'),(30,13,'park'),(31,13,'home'),(32,13,'school');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
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
  PRIMARY KEY (`eventId`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'mentionSetting + greetUser + introduceSarahAndLiam + introduceLiam'),(2,'greetUser + user-askFeeling'),(3,'agreeWithPlayer + stateSymptom'),(4,'suggestBothRest'),(5,'commentRejoiceFiller + sadRemark + stateNegativeStatus + stateSymptom'),(6,'respondToStatus + suggestLiamRest'),(7,'expressPlayfulness'),(8,'commentNegativeFiller + user-askPlay'),(9,'counterPlay'),(10,'ignoreSarah + suggestPlayUser'),(11,'succumb + remindSick + transitionToPlay'),(12,'commentNegativeFiller + stateNegativeStatus + transitionToSarah'),(13,'greetKids + expressWorry + suggestRest + stateSickness'),(14,'expressDelight'),(15,'succumb'),(16,'succumb + stateRest '),(17,'greetUser + commentRest + stateTraceOfSymptom'),(18,'stateSickness'),(19,'expressNaivety'),(20,'user-askSickness'),(21,'stateCauses + stateBodyPart + explainBodyPart + reiterateActions'),(22,'askPrevention'),(23,'stateAffirmation + user-askPrevention'),(24,'statePrevention'),(25,'expressNaivety + expressWonder'),(26,'stateAgreement + encouragePrevention + concludePrevention'),(27,'transitionToTreatment'),(28,'stateTreatment'),(29,'expressDisbelief + counterTreatment'),(30,'user-askTreatment'),(31,'stateWarning'),(32,'expressStubbornness + comeBack'),(33,'giveTreatment'),(34,'commentRejoiceFiller+ stateHealed'),(35,'suggestFullDayRest'),(37,'stateLiamCare + expressAppreciation + stateMoral'),(38,'expressFarewell'),(39,'welcomeBack + greetUser'),(40,'expressSick + expressRegret + takeTreatment'),(41,'expressEmpathy'),(42,'thankSarah'),(43,'wishGoodHealth + stateMoral + expressFarewell'),(44,'stateHealed + thankSarah'),(45,'thankUser + stateMoral + wishGoodHealth + expressFarewell'),(46,'inquireActivity'),(47,'inviteActivity'),(48,'narrateAction + commentFunKids'),(49,'stateMotivationAction'),(50,'expressWorryCondition + suggestRestAction'),(51,'expressPlayfulAction + stateCurCondition + wantCurAction'),(52,'askReverseAction'),(53,'succumbPlayAction + suggestActionToUser'),(54,'commentPositiveFillerPlayAction'),(55,'commentPositiveFiller + greetUser'),(56,'narrateActivity'),(57,'narrateNotDoingActivity + indicateSick'),(58,'commentRejoiceFiller + liamNarrateActivity'),(59,'commentRejoiceFiller + describePastActivity'),(60,'commentPositiveFillerPlayAction + doneActivity'),(61,'greetUser + mentionSetting'),(62,'inviteActivity'),(63,'narrateAction'),(64,'narrateRest'),(65,'expressAppreciation'),(66,'succumb'),(67,'expressFarewell');
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
  `category` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `verb` varchar(100) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `connector1` varchar(45) NOT NULL,
  `connector2` varchar(45) NOT NULL,
  `connector3` varchar(45) NOT NULL,
  `connector4` varchar(45) NOT NULL,
  `connector5` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (2,'indoorToy','ball','throw','ball','the','the','the','the','the'),(3,'indoorToy','stuffed toy','hug','stuff_toy','the','the','the','the','the'),(4,'outdoorToy','mud','splash','mud','in the','in the','in the','in the','in the'),(5,'outdoorToy','slide','ride','slide','the','the','the','the','the'),(6,'outdoorToy','sandcastle','build','sandcastle','a','a','a','a','a'),(7,'food','sandwich','eat','sandwich','a','a','a','a','a'),(8,'food','fruit','eat','fruits','some','some','some','some','some'),(9,'bodyPart','hands','wash','wash_hands','our','their','his','my','your'),(10,'facialBodyPart','eyes','rub','scratch_face','our','their','his','my','your'),(11,'facialBodyPart','nose','scratch','scratch_nose','our','their','his','my','your'),(12,'facialBodyPart','face','scratch','scratch_face','our','their','his','my','your'),(13,'sanitizer','alcohol','spray','sanitize_hands','some','some','some','some','some'),(14,'sanitizer','alcohol','rub','sanitize_hands','some','some','some','some','some'),(15,'sleepObject','bed','sleep','sleep','on the','on the','on the','on the','on the'),(16,'restObject','bench','rest','bench','on the','on the','on the','on the','on the'),(17,'facialBodyPart','mouth','scratch','scratch_face','our','their','his','my','your');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postcondition`
--

DROP TABLE IF EXISTS `postcondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postcondition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `assertion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postcondition`
--

LOCK TABLES `postcondition` WRITE;
/*!40000 ALTER TABLE `postcondition` DISABLE KEYS */;
INSERT INTO `postcondition` VALUES (1,1,2),(4,2,2),(5,6,1),(6,7,4),(7,8,4),(8,9,1),(9,10,7);
/*!40000 ALTER TABLE `postcondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precondition`
--

DROP TABLE IF EXISTS `precondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precondition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `assertion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precondition`
--

LOCK TABLES `precondition` WRITE;
/*!40000 ALTER TABLE `precondition` DISABLE KEYS */;
INSERT INTO `precondition` VALUES (1,1,4),(2,3,1),(4,5,1),(5,6,2),(6,7,3),(7,8,3),(8,9,2),(9,10,8),(10,11,1),(11,12,1),(12,13,1);
/*!40000 ALTER TABLE `precondition` ENABLE KEYS */;
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
INSERT INTO `prevention` VALUES (1,'frequently wash our hands',1),(2,'cover our mouth with our forearm when coughing',1),(3,'avoid touching our eyes',1),(4,'avoid touching our nose',1),(5,'avoid touching our mouth',1),(8,'frequently wash our hands',2),(9,'avoid drinking raw milk',2),(10,'avoid eating raw meat or shellfish',2),(11,'clean and wash regularly',4),(12,'avoid contact with people who have chicken pox',4),(13,'use mosquito repellents',5),(14,'wear protective clothing',5),(15,'wash our hand often',6),(16,'carry hand sanitizers',6),(17,'avoid touching our nose',6),(18,'avoid touching our mouth',6),(19,'avoid toucing our eyes',6),(20,'avoid our allergens',7),(21,'take medicine given by our doctor',7),(22,'get an immunization',8),(23,'limit interaction with people who have measles',8),(24,'frequent hand cleaning',9),(26,'avoid interaction with those who are sick',9),(27,'get enough rest',9),(28,'receive proper nutrition',9),(29,'drink plenty of water',10),(30,'avoid our allergens',3),(31,'avoid difficult exercises',3),(32,'avoid stress',3),(33,'avoid extreme weather condition',3),(34,'change out of we and sweaty clothes as soon as possible',10),(35,'exercise proper hygiene',10),(36,'pee as soon as possible',10);
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
  `concept2` int(11) NOT NULL COMMENT '1- sick\n0 - not sick',
  `ruling` int(11) NOT NULL COMMENT '0 - good\n1 - bad',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semantic`
--

LOCK TABLES `semantic` WRITE;
/*!40000 ALTER TABLE `semantic` DISABLE KEYS */;
INSERT INTO `semantic` VALUES (1,'userSuggestPlaying',1,1),(2,'userSuggestPlaying',0,0),(3,'userSuggestResting',1,0),(4,'userSuggestResting',0,0),(5,'userSuggestNotTakingTreatment',1,1),(6,'userSuggestNotTakingTreatment',0,0),(7,'userSuggestTakingTreatment',1,0),(8,'userSuggestTakingTreatment',0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=476 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sentence`
--

LOCK TABLES `sentence` WRITE;
/*!40000 ALTER TABLE `sentence` DISABLE KEYS */;
INSERT INTO `sentence` VALUES (111,'greetUser','Hello there, <user-cap>!'),(112,'mentionSetting','It\'s a sunny <day> morning!'),(113,'introduceLiam','Liam wants to play with you!'),(115,'commentPositiveFiller','Oh, wow!'),(116,'greetUser','Hi, <user-cap>!'),(117,'user-askFeeling','How have you been?'),(118,'suggestPlay','But I think we can still go out and play!'),(119,'suggestBothRest','I think both of you should rest first before you go out and play.'),(121,'stateSymptom','I have been experiencing [symptom] lately.'),(122,'respondToStatus','Oh dear, I think you are sick!'),(123,'suggestLiamRest','You should rest and [treatment].'),(124,'expressPlayfulness','But I still want to play with <user-cap>.'),(125,'commentNegativeFiller','No, no...'),(126,'counterPlay','Playing will make you tired and you might get worse.'),(128,'counterPlay','You shouldn\'t play when you\'re sick.'),(131,'succumb','If you really want to, fine.'),(132,'transitionToPlay','I\'ll be right here if you need me.'),(133,'commentNegativeFiller','Oh, no!'),(134,'stateNegativeStatus','I’m not feeling good.'),(135,'transitionToSarah','Let\'s go back to Sarah.'),(136,'greetKids','Hey kids!'),(137,'expressWorry','Gosh Liam, your [symptom] is getting worse.'),(138,'suggestRest','You should rest.'),(139,'stateSickness','I think you have <sickness>.'),(140,'expressDelight','<user-cap> thinks the same way as me!'),(142,'transitionToRest','I\'ll entertain <user-cap> as you sleep.'),(143,'greetUser','Hey there, <user-cap>!'),(144,'commentRest','I just woke up and what a refreshing nap that was!'),(145,'stateTraceOfSymptom','I still feel a little [symptom].'),(146,'stateSickness','Oh, I think you show signs of <sickness>.'),(147,'expressNaivety','I did not know that.'),(148,'user-askSickness','How about you <user-cap>, do you know what <sickness> is?'),(149,'stateCauses','Well, <sickness> is caused by [cause].'),(150,'stateBodyPart','You can get sick if they get inside your <body-part-affected>.'),(151,'reiterateActions','You probably got your sickness because you <pastAction> when your <assertion>.'),(152,'askPrevention','Can you prevent that?'),(153,'stateAgreement','Yes, I think so too.'),(154,'user-askPrevention','<user-cap>, help me out, any clue on how to prevent <sickness>?'),(155,'statePrevention','To avoid having <sickness>, we have to [prevention] and [prevention].'),(156,'expressWonder','Maybe that was how I got my <sickness>.'),(157,'stateAgreement','Indeed, that’s correct.'),(158,'encouragePrevention','So, better observe these practices at all times.'),(159,'concludePrevention','This way, you won\'t get <sickness> that easily.'),(160,'transitionToTreatment','But for now, what can I do to stop <sickness> from affecting my <body-part-affected>?'),(161,'stateTreatment','You have to [treatment] for you to get better.'),(162,'expressDisbelief','Oh, do I really have to?'),(163,'counterTreatment',' I don\'t think I need to [treatment].'),(164,'user-askTreatment','<user-cap>, do you think Liam should [treatment]?'),(165,'stateWarning','Oh well, don\'t listen to me and your <body-part-affected> will be sick and you won\'t be able to get better.'),(166,'expressDisbelief','Really?'),(167,'expressStubbornness','Hmmm... Well let\'s see about that!'),(168,'transitionToEndDay','Anyway, I feel that I have to rest now.'),(169,'expressAppreciation','Anyway, thank you for visiting us <user-cap>!'),(170,'comeBack','It will go away after a few days. Come back a few days later and see if <sickness> go away by then.'),(171,'expressFarewell','Take care <user-cap>!'),(172,'succumb','Oh fine.'),(173,'giveTreatment','Go, [treatment] Liam.'),(175,'stateHealed','I feel so much better now!'),(176,'expressDoubt','I don\'t think it works that way Liam.'),(177,'suggestFullDayRest','You have to rest for the day, too.'),(178,'expressDisbelief','Oh, is that so?'),(179,'succumb','Fine.'),(180,'stateRest','I\'m resting.'),(181,'stateLiamCare','That’s great, Liam! '),(182,'expressAppreciation','Anyway, thank you for spending this day with us!'),(183,'stateMoral','I hope you also learned from Liam\'s experience on <sickness>.'),(184,'comeBack','Let’s meet again after a few days and see if <sickness> will go away by then.'),(185,'expressFarewell','See you, <user-cap>!'),(187,'expressSick','I still feel the same as last time you visited me.'),(188,'expressRegret','I\'m sorry Sarah, I didn\'t listen to you about the need to [treatment].'),(189,'takeTreatment','I\'m sorry, may I [treatment] now?'),(190,'expressEmpathy','Of course Liam, here you go.'),(191,'thankSarah','Thank you Sarah, for being here to teach us about <sickness>.'),(192,'wishGoodHealth','Wishing you good health!'),(193,'stateMoral','I hope both of you learned a lot about <sickness>.'),(194,'expressFarewell','See you soon!'),(195,'stateHealed','I feel much better now.'),(196,'thankSarah','Thank you Sarah for being so patient with us!'),(197,'thankUser','I think we also have <user-cap> to thank for that.'),(198,'wishGoodHealth','Keep a healthy lifestyle!'),(199,'expressFarewell','\'Til next time!'),(200,'inquireActivity','Liam, what do you want to do?'),(201,'inviteActivity','<user-cap>, let’s <curAction-verb> <curAction-connector1> <curAction-object>.'),(202,'statePrecaution','Be careful, kids!'),(203,'narrateAction','Liam and <user-cap> are having fun <curAction-verb-ing> <curAction-connector2> <curAction-object>.'),(204,'commentFunKids','They look like they are having a great time.'),(205,'stateMotivationAction','<curAction-verb-ing-cap> <curAction-object> is so <curAction-motivation>!'),(207,'suggestRestAction','I think you should <reverseAction-verb> <reverseAction-object> so that your <curCondition-body> will be <reverseAction-postCondition-property>.'),(208,'expressPlayfulAction','But I want to <curAction-verb> <curAction-object> because it is so <curAction-motivation>.'),(209,'stateCurCondition','My <curCondition-body> is just a little <curCondition-property> so it\'s no big deal.'),(210,'askReverseAction','Come now <user-cap>, do you think Liam should <reverseAction-verb> <reverseAction-connector3> <reverseAction-object>?'),(211,'expressDelight','See, <user-cap> thinks the same way as me!'),(212,'succumbPlayAction','Fine, I’ll go <reverseAction-verb> <reverseAction-object>.'),(213,'commentPositiveFillerPlayAction','Yay!'),(214,'askPrevention','Are there ways to prevent <sickness>?'),(215,'askPrevention','Do you know how we can avoid <sickness>?'),(216,'askPrevention','<user-cap>, do you know a way to avoid <sickness>?'),(218,'askReverseAction','<user-cap>, Liam should <reverseAction-verb> <reverseAction-connector3> <reverseAction-object>. Don’t you think so?'),(219,'comeBack','See you again after a few days, I think I would recover from <sickness> by then.'),(220,'comeBack','Perhaps my <sickness> will go away after a few days. Let’s just meet until then.'),(221,'comeBack','Let’s meet again after a few days, I will probably feel better by then.'),(225,'commentNegativeFiller','Uh-oh.'),(227,'commentNegativeFiller','That’s not okay.'),(228,'commentNegativeFiller','That doesn’t seem good.'),(229,'commentPositiveFiller','Awesome!'),(230,'commentPositiveFiller','Great!'),(231,'commentPositiveFiller','That’s amazing!'),(232,'commentPositiveFiller','Really, wow!'),(233,'commentPositiveFiller','That’s wonderful!'),(234,'commentRest','Ahhh, that was a great rest!!'),(235,'commentRest','Sorry for leaving you for a while to take my nap.'),(236,'commentRest','I’m glad I rested because I feel better now.'),(237,'commentRest','I feel so much more okay after that nap.'),(239,'concludePrevention','By following those, you would be able to avoid having <sickness> again.'),(240,'concludePrevention','By doing so, you could lessen the chances of getting <sickness> again.'),(241,'concludePrevention','Remember to keep all those in mind so you can avoid having <sickness> again.'),(242,'concludePrevention','Now, you know what you need to do to prevent <sickness>.'),(244,'counterPlay','Playing while sick could worsen your state.'),(245,'counterPlay','You’re still sick so you shouldn’t play.'),(248,'counterTreatment','I don’t want to [treatment].'),(249,'counterTreatment','I really hate to [treatment].'),(250,'counterTreatment','I think I’ll get better even without having to [treatment].'),(252,'encouragePrevention','It is important to follow these practices.'),(253,'encouragePrevention','You better follow these practices.'),(254,'encouragePrevention','Observing these practices is a must.'),(255,'encouragePrevention','Doing all those is important for good health.'),(256,'expressAppreciation','Anyway, we really appreciated your visit! Thank you <user-cap>!'),(257,'expressAppreciation','Anyway, hope you had a great time <user-cap>!'),(258,'expressAppreciation','Anyway, we had a great time today with you, <user-cap>! '),(259,'expressDelight','See, <user-cap> shares the same opinion as me!'),(260,'expressDelight','Thankfully, <user-cap> agrees with me!'),(261,'expressDelight','See, even <user-cap> knows the right thing to do!'),(263,'expressDisbelief','Really now?'),(264,'expressDisbelief','Oh, really?'),(265,'expressDoubt','I don’t think it works that way Liam.'),(266,'expressDoubt','I don’t think so.'),(267,'expressDoubt','It’s not like that.'),(268,'expressDoubt','I doubt that.'),(269,'expressDoubt','I don’t think that’s right.'),(270,'expressEmpathy','Sure, here you go.'),(271,'expressEmpathy','Well of course Liam!'),(272,'expressEmpathy','Sure, Liam.'),(273,'expressEmpathy','Here, Liam.'),(275,'expressFarewell','Bye, until we meet again!'),(276,'expressNaivety','I’m not aware of that.'),(277,'expressNaivety','Oh, I did not know that!'),(278,'expressNaivety','Oh, now I know!'),(279,'expressNaivety','I have no idea that it’s like that.'),(281,'expressPlayfulness','Still, I want to have fun and play with <user-cap>.'),(282,'expressPlayfulness','But I think we can still go out and play!'),(283,'expressPlayfulness','But I really want to play!'),(284,'expressPlayfulness','Still, I badly want to go out and play!'),(287,'expressRegret','I’m sorry that I didn’t [treatment].'),(288,'expressRegret','Sarah, I’m sorry I didn’t listen to you about the need to [treatment].'),(290,'expressRegret','I’m sorry, I should’ve listened to you, Sarah.'),(291,'expressSick','I still don’t feel well.'),(292,'expressSick','I feel worse since the last time you visited me.'),(293,'expressSick','I feel so bad.'),(294,'expressSick','I’m not feeling good.'),(295,'expressStubbornness','You’ll see, I don’t need to [treatment] to get better.'),(296,'expressStubbornness','I don’t think I need to [treatment] to get better.'),(297,'expressStubbornness','I don’t need all those!'),(298,'expressStubbornness','I don’t want to do any of those!'),(299,'expressWonder','I think that is the reason how I got my <sickness>.'),(300,'expressWonder','Ooooh! Maybe that is the reason how I got my <sickness>.'),(301,'expressWonder','Perhaps that’s what made me sick!'),(302,'expressWonder','Maybe my <sickness> was because of that.'),(303,'expressWorry','Liam, I noticed that you’re [symptom] is getting worse.'),(305,'expressWorry','Oh my, Liam, looks like you’re [symptom] is not getting any better.'),(306,'expressWorry','Liam, your [symptom] seem to be getting worse.'),(307,'giveTreatment','Go ahead, [treatment] Liam.'),(308,'giveTreatment','[treatment-cap] now, Liam.'),(309,'giveTreatment','Liam, [treatment] now.'),(310,'giveTreatment','Liam, [treatment] please.'),(311,'greetKids','Hello kids!'),(312,'greetKids','Hi kids!'),(313,'greetKids','What’s up kids!'),(314,'greetKids','How have you been guys?'),(315,'greetKids','Hello there!'),(316,'greetUser','Oh, <user-cap>, hello!'),(317,'greetUser','<user-cap>, there you are, hi!'),(318,'ignoreSarah','Don’t mind Sarah.'),(319,'ignoreSarah','Don’t pay attention to her.'),(320,'ignoreSarah','Let’s just ignore Sarah.'),(321,'ignoreSarah','Don’t listen to her.'),(323,'introduceLiam','Liam is excited to play with you!'),(324,'introduceLiam','<user-cap>, Liam is now ready for your play date!'),(327,'inviteActivity','I want to <curAction-verb> <curAction-connector4> <curAction-object>, let’s go <user-cap>!'),(328,'inviteActivity','<user-cap>, let’s have some fun, let’s <curAction-verb> <curAction-connector1> <curAction-object>.'),(330,'inquireActivity','Liam, do you have any activities in mind?'),(331,'inquireActivity','Liam, Is there anything you want to do?'),(332,'mentionSetting','What a wonderful <day> morning!'),(333,'mentionSetting','Such a beautiful <day> morning!'),(334,'mentionSetting','What a bright <day> afternoon!'),(341,'narrateAction','Liam and <user-cap> are <curAction-verb-ing> <curAction-connector2> <curAction-object> and they look like they’re enjoying. '),(342,'narrateAction','The kids look like they’re having fun <curAction-verb-ing> <curAction-connector2> <curAction-object>.'),(343,'respondToStatus','Liam, you look sick!'),(344,'respondToStatus','I think you might be sick, Liam.'),(345,'respondToStatus','Liam, I think you look sick.'),(346,'respondToStatus','You’re looking sick right now, Liam.'),(347,'stateAgreement','True enough.'),(348,'stateAgreement','Absolutely!'),(349,'stateAgreement','That’s true.'),(350,'stateBodyPart','You might get sick if it reaches your <body-part-affected>.'),(351,'stateBodyPart','This affects your <body-part-affected>.'),(352,'stateBodyPart','This sickness means that your <body-part-affected> is unwell.'),(353,'stateBodyPart','Because you have <sickness>, your <body-part-affected> is affected.'),(356,'stateCauses','Your <sickness> seems to be caused by [cause].'),(358,'stateCurCondition','My <curCondition-body> is just a bit <curCondition-property> so it’s okay. '),(359,'stateHealed','I feel great now.'),(360,'stateHealed','I\'m feeling better now.'),(361,'stateHealed','I no longer feel sick.'),(362,'stateHealed','I feel okay now.'),(363,'stateHealed','I’m not sick anymore.'),(364,'stateLiamCare','Great! I’ll check up on you later, Liam.'),(368,'stateMoral','Hope you had fun learning about <sickness>.'),(369,'stateMoral','I wish you learned things about <sickness> from today.'),(370,'stateMotivationAction','It is so <curAction-motivation> to <curAction-verb> <curAction-object>!'),(372,'stateNegativeStatus','I feel bad.'),(373,'stateNegativeStatus','I don’t feel good.'),(374,'statePrecaution','Take care kids!'),(378,'statePrevention','We have to [prevention] and [prevention] in order to not get <sickness>.'),(379,'statePrevention','In order to prevent getting <sickness>, we have to [prevention] and [prevention].'),(380,'statePrevention','If we don’t want to have <sickness>, we should [prevention] and [prevention].'),(381,'stateRest','I’m going to sleep.'),(382,'stateRest','I’m going to take a nap.'),(383,'stateRest','I\'m going to take a rest'),(384,'stateSickness','You look sick. You look like you have <sickness>.'),(385,'stateSickness','You look like you’re not feeling well, you might have <sickness>.'),(386,'stateSymptom','I’m feeling [symptom] lately.'),(387,'stateTraceOfSymptom','I feel a bit [symptom].'),(388,'narrateActivity','Liam and <user-cap> are now <reverseAction-verb-ing> <reverseAction-connector2> <reverseAction-object>.'),(389,'commentRejoiceFiller','Great!'),(390,'describePastActivity','Since the kids already <reverseAction-verb-past> <reverseAction-connector2> <reverseAction-object>, they can now <curAction-verb> <curAction-connector2> <curAction-object>.'),(391,'narrateNotDoingActivity','Liam and <user-cap> did not <reverseAction-verb> <reverseAction-connector2> <reverseAction-object>.'),(392,'indicateSick','They might get sick.'),(393,'liamNarrateActivity','Now we can <curAction-verb> <curAction-connector1> <curAction-object>. '),(394,'commentRejoiceFiller','Awesome!'),(395,'stateReason','You probably got your sickness because you <pastAction>.'),(396,'succumb','Fine, whatever you want.'),(397,'introduceSarahAndLiam','I’m Sarah, and there’s Liam on the right.'),(398,'introduceSarahAndLiam','I’m Sarah, and there’s Liam the one in blue.'),(400,'suggestRestAction','In order for your <curCondition-body> to be <reverseAction-postCondition-property>, you should <reverseAction-verb> <reverseAction-connector5> <reverseAction-object>.'),(401,'wantCurAction','I can still <curAction-verb> <curAction-connector4> <curAction-object>.'),(402,'askReverseAction','Liam should <reverseAction-verb> <reverseAction-connector3> <reverseAction-object>, right <user-cap>?'),(403,'succumbPlayAction','Ok fine, I’ll <reverseAction-verb> <reverseAction-object>.'),(404,'succumbPlayAction','Oh fine, I’ll <reverseAction-verb> <reverseAction-object>.'),(405,'suggestActionToUser','<user-cap>, you should <reverseAction-verb> <reverseAction-connector5> <reverseAction-object> too.'),(406,'suggestActionToUser','<user-cap>, you need to <reverseAction-verb> <reverseAction-connector5> <reverseAction-object> too.'),(407,'commentPositiveFillerPlayAction','Finally!'),(408,'commentPositiveFillerPlayAction','Awesome!'),(409,'commentPositiveFillerPlayAction','Great!'),(410,'doneActivity','I’m done <reverseAction-verb-ing> <reverseAction-connector4> <reverseAction-object>.'),(412,'stateMotivationAction','<curAction-verb-ing-cap> <curAction-object> looks so <curAction-motivation>!'),(413,'greetUser','Oh! Hello <user-cap>!'),(414,'user-askFeeling','How are you feeling?'),(415,'user-askFeeling','How do you feel?'),(416,'agreeWithPlayer','Me too.'),(417,'agreeWithPlayer','I’m also sick.'),(418,'suggestBothRest','It’s better for you to rest first before playing.'),(419,'suggestBothRest','You guys should take a rest first before you play.'),(420,'sadRemark','However,'),(421,'sadRemark','Sadly,'),(422,'sadRemark','Unfortunately,'),(423,'suggestRest','You should take a break first.'),(424,'suggestRest','You should take a rest first.'),(425,'commentNegativeFiller','That’s not good.'),(426,'user-askPlay','<user-cap>, Liam shouldn\'t play, right?'),(428,'ignoreSarah','Let’s just ignore her.'),(429,'suggestPlayUser','Let’s go play, <user-cap>'),(430,'suggestPlayUser','Come on, and let’s play,  <user-cap>.'),(431,'suggestPlayUser','<user-cap>, let’s go play!'),(432,'suggestPlayUser','<user-cap>, let’s have fun!'),(433,'remindSick','But I already told you, you might get sick.'),(434,'remindSick','But I’m telling you, you might feel worse.'),(435,'remindSick','But I’m warning you, you might feel worse.'),(436,'transitionToPlay','I’ll just be here if you need me.'),(437,'transitionToPlay','Just call me if you need me.'),(438,'transitionToSarah','Let’s head back to Sarah.'),(439,'transitionToSarah','Let’s go see Sarah.'),(440,'commentRest','I just had a refreshing nap! '),(441,'stateSickness ','Oh, I think you show signs of <sickness>.'),(442,'stateSickness ','I think you have <sickness>.'),(443,'stateSickness ','You look sick. You look like you have <sickness>.'),(444,'stateSickness ','You look like you’re not feeling well, you might have <sickness>.'),(445,'user-askSickness','What about you <user-cap>, do you have any idea on what <sickness> is?'),(446,'user-askSickness','<user-cap>, any idea on what <sickness> is?'),(447,'stateAffirmation','Yes, of course.'),(448,'stateAffirmation','Absolutely!'),(449,'user-askPrevention','<user-cap>, do you have any idea on how to prevent <sickness>?'),(450,'user-askPrevention','<user-cap>, do you know how to prevent <sickness>?'),(451,'user-askPrevention','<user-cap>, do you know a way to avoid <sickness>?'),(452,'transitionToTreatment','But for now, what can I do to stop <sickness> from affecting my <body-part-affected>?'),(453,'transitionToTreatment','How do I get well from <sickness>?'),(454,'transitionToTreatment','How do I get better from <sickness>?'),(455,'stateTreatment','If you want to feel better you need to [treatment].'),(456,'stateTreatment','For you to get better you need to [treatment].'),(457,'stateTreatment','You should [treatment] in order to get well.'),(458,'counterTreatment','I don’t like to [treatment].'),(459,'user-askTreatment','<user-cap>,  do you think Liam needs to [treatment]?'),(460,'user-askTreatment','Liam needs to [treatment], right <user-cap>?'),(461,'stateWarning','You won’t get well if you don’t listen to my advice.'),(462,'stateWarning','You won’t be able to get better if you don’t follow my advice.'),(463,'suggestFullDayRest','You have to take the day off and rest first.'),(464,'suggestFullDayRest','Take a rest for the day first.'),(465,'welcomeBack','Welcome back, <user-cap>! '),(466,'takeTreatment','Can I [treatment] now?'),(467,'takeTreatment','I realized that I need to [treatment].'),(468,'thankSarah','Thanks Sarah for teaching us about <sickness>.'),(469,'thankUser','Let us also thank <user-cap> for helping.'),(470,'thankUser','Thank you <user-cap> for joining us today.'),(471,'expressWorryCondition','Liam, your <curCondition-body> is <curCondition-property> from <prevAction-verb-ing> <prevAction-object> a while ago.'),(472,'expressWorryCondition','Liam, your  <curCondition-body> became <curCondition-property> from <prevAction-verb-ing> <prevAction-object>.'),(473,'narrateRest','Liam is now taking a rest.'),(474,'narrateRest','Liam is now resting.'),(475,'narrateRest','Liam is now taking a nap.');
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
INSERT INTO `sickness` VALUES (1,'cough and cold'),(2,'stomach ache'),(3,'asthma'),(4,'chicken pox'),(5,'dengue'),(6,'fever'),(7,'allergy'),(8,'measles'),(9,'pneumonia'),(10,'urinary tract infection (UTI)');
/*!40000 ALTER TABLE `sickness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sticker`
--

DROP TABLE IF EXISTS `sticker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sticker` (
  `eventId` int(11) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `objectId` int(11) NOT NULL,
  `duration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sticker`
--

LOCK TABLES `sticker` WRITE;
/*!40000 ALTER TABLE `sticker` DISABLE KEYS */;
INSERT INTO `sticker` VALUES (0,'sleep',1,120),(0,'drinkWater',2,5),(0,'takeMedicine',3,5),(0,'play',4,60);
/*!40000 ALTER TABLE `sticker` ENABLE KEYS */;
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
INSERT INTO `symptom` VALUES (1,'runny nose',1),(2,'stuffy nose',1),(3,'sneezing',1),(4,'coughs',1),(5,'sore throat',1),(6,'scratchy throat',1),(7,'watery eyes',1),(8,'itchy eyes',1),(9,'headaches',1),(10,'body aches',1),(11,'low fever',1),(12,'loss of appetite',2),(13,'vomiting',2),(14,'diarrhea',2),(15,'stomach pains',2),(16,'stomach cramps',2),(17,'fever',2),(18,'shortness of breath',3),(19,'tightness of chest ',3),(20,'wheezes',3),(21,'constant coughs',3),(22,'fever ',4),(23,'headaches',4),(24,'rashes',4),(25,'loss of appetite',4),(26,'high fever',5),(27,'terrible headaches',5),(28,'eye pains',5),(29,'joint and muscle pains',5),(30,'tiredness',5),(31,'vomiting',5),(32,'skin rashes',5),(33,'high body temperature',6),(34,'skin rashes',7),(35,'headaches',7),(36,'sneezing',7),(37,'runny nose',7),(38,'swelling',7),(39,'diarrhea',7),(40,'coughs',8),(41,'fever',8),(42,'red eyes',8),(43,'muscle pains',8),(44,'runny nose',8),(45,'sore throat',8),(46,'white spots inside the mouth',8),(47,'measles rashes',8),(48,'dry coughs',9),(49,'low fever',9),(50,'headaches',9),(51,'tiredness',9),(52,'fever',10),(53,'tiredness',10),(54,'lower stomach pain',10),(55,'sweating',6),(56,'shivering',6),(57,'headaches',6),(58,'muscle pains',6),(59,'loss of appetite',6),(60,'dehydration',6),(61,'genral weakness',6);
/*!40000 ALTER TABLE `symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_map`
--

DROP TABLE IF EXISTS `symptom_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `symptom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_map`
--

LOCK TABLES `symptom_map` WRITE;
/*!40000 ALTER TABLE `symptom_map` DISABLE KEYS */;
INSERT INTO `symptom_map` VALUES (17,3,'stomach'),(18,3,'fever'),(19,3,'vomiting'),(20,3,'diarrhea'),(21,4,'fever'),(22,4,'rashes'),(23,4,'headache'),(24,4,'muscle pain'),(25,4,'tiredness'),(26,4,'dehydration'),(27,5,'swelling'),(28,11,'itchy eyes'),(29,11,'watery eyes'),(30,12,'runny nose'),(31,12,'stuffy nose'),(32,13,'white spots inside the mouth');
/*!40000 ALTER TABLE `symptom_map` ENABLE KEYS */;
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
INSERT INTO `treatment` VALUES (1,'drink water',1),(2,'gargle with warm salt water',1),(3,'take cough drops',1),(4,'use throat sprays',1),(5,'take cold medicines',1),(6,'take nasal drops',1),(7,'drink water regularly',2),(8,'eat some salty food',2),(9,'eat food with potassium such as banana',2),(10,'prevent itching and scratching',4),(11,'drink plenty of water',4),(12,'take painkillers for fever',4),(13,'rest',5),(14,'drink plenty of water',5),(15,'staying cool',6),(16,'rest',6),(17,'drink plenty of water',6),(18,'rest',8),(19,'drink plenty of water',8),(21,'take vitamin A supplement',8),(22,'drink a lot of water',9),(23,'eat healthy food',9),(24,'take medicine',9),(25,'rest',9),(26,'drink antibiotics',10),(27,'drink control medicines',3),(28,'use inhalers',3),(29,'take medicine',7),(30,'take preventive shots',7);
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

-- Dump completed on 2017-07-10 14:00:23
