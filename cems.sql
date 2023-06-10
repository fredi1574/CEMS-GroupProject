-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cems
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `activetest`
--

DROP TABLE IF EXISTS `activetest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activetest` (
  `id` varchar(45) NOT NULL,
  `numOfQuestions` int DEFAULT NULL,
  `testDate` varchar(45) DEFAULT NULL,
  `startingTime` varchar(45) DEFAULT NULL,
  `testCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activetest`
--

LOCK TABLES `activetest` WRITE;
/*!40000 ALTER TABLE `activetest` DISABLE KEYS */;
INSERT INTO `activetest` VALUES ('010203',1,'2023-06-09','16:04','799A'),('010207',4,'2023-06-09','19:21','b67e');
/*!40000 ALTER TABLE `activetest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aftertestinfo`
--

DROP TABLE IF EXISTS `aftertestinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aftertestinfo` (
  `testID` varchar(45) NOT NULL,
  `testDuration` int DEFAULT NULL,
  `actualDuration` int DEFAULT NULL,
  `totalFinished` int DEFAULT NULL,
  `totalForcedFinished` int DEFAULT NULL,
  `totalStudents` int DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `testCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aftertestinfo`
--

LOCK TABLES `aftertestinfo` WRITE;
/*!40000 ALTER TABLE `aftertestinfo` DISABLE KEYS */;
INSERT INTO `aftertestinfo` VALUES ('010104',500,0,1,0,1,'2023-06-09',NULL),('010106',1,1,1,0,1,'2023-06-09',NULL),('010202',50,0,1,0,1,'2023-06-09',NULL),('010203',120,0,9,0,19,'2023-06-09',NULL),('010205',1,2,1,1,2,'2023-06-09',NULL),('010207',3,0,0,0,0,'2023-06-09',NULL);
/*!40000 ALTER TABLE `aftertestinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answersofstudent`
--

DROP TABLE IF EXISTS `answersofstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answersofstudent` (
  `studentID` int NOT NULL,
  `testID` varchar(45) NOT NULL,
  `questionID` varchar(45) NOT NULL,
  `studentsAnswer` int DEFAULT NULL,
  PRIMARY KEY (`studentID`,`testID`,`questionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answersofstudent`
--

LOCK TABLES `answersofstudent` WRITE;
/*!40000 ALTER TABLE `answersofstudent` DISABLE KEYS */;
INSERT INTO `answersofstudent` VALUES (2,'010205','010204',2),(2,'010205','010205',0),(4,'010104','010103',1),(4,'010106','010103',3),(4,'010202','010204',2),(4,'010203','010103',3),(4,'010205','010204',2),(4,'010205','010205',0),(5,'010202','010204',2),(5,'010203','010103',3),(5,'010205','010204',2),(5,'010205','010205',1);
/*!40000 ALTER TABLE `answersofstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `subjectID` varchar(45) NOT NULL,
  `courseID` varchar(45) NOT NULL,
  `subjectName` varchar(45) DEFAULT NULL,
  `courseName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`subjectID`,`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('01','01','Math','Algebra'),('01','02','Math','Hedva'),('02','03','Software','Phyton'),('02','04','Software','C++'),('03','05','Biology','Anatomy'),('03','06','Biology','Microbiology');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturersubject`
--

DROP TABLE IF EXISTS `lecturersubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturersubject` (
  `id` varchar(45) NOT NULL,
  `subjectid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturersubject`
--

LOCK TABLES `lecturersubject` WRITE;
/*!40000 ALTER TABLE `lecturersubject` DISABLE KEYS */;
INSERT INTO `lecturersubject` VALUES ('2','01'),('3','01');
/*!40000 ALTER TABLE `lecturersubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` varchar(255) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `questionText` text,
  `questionNumber` int DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `answer4` varchar(255) DEFAULT NULL,
  `correctAnswer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('010101','01','Algebra','How much is 2+2?',1,'b b','1','2','3','4','4'),('010102','01','Algebra','How much is 1+1?',2,'b b','1','2','3','4','2'),('010103','01','Algebra','How much is 10-10?',3,'b b','10','5','0','12','3'),('010106','01','Algebra','What is the value of x in the equation 2x + 5 = 15?',6,'a a','5','7','8','10','1'),('010107','01','Algebra','Which of the following expressions is equivalent to 3(x - 2)?',7,'a a','3x - 2','3x - 6',' 3x + 2','3x + 6','2'),('010108','01','Algebra','What is the value of 3 + 2x when x = 4?',8,'a a','12','9','10','11','4'),('010204','01','Hedva','The answer is 2',4,'b b','bad','very bad','no	','ok','2'),('010205','01','Hedva','The answer is 1',5,'a a','hello','hi','amigo','ci','1'),('010209','01','Hedva','The anser is 3',9,'a a','1','2','3','4','3'),('010210','01','Hedva','The answer is 4',10,'a a','1','2','3','4','4');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentscourse`
--

DROP TABLE IF EXISTS `studentscourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentscourse` (
  `studentID` varchar(45) NOT NULL,
  `course` varchar(45) NOT NULL,
  PRIMARY KEY (`studentID`,`course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentscourse`
--

LOCK TABLES `studentscourse` WRITE;
/*!40000 ALTER TABLE `studentscourse` DISABLE KEYS */;
INSERT INTO `studentscourse` VALUES ('4','Algebra'),('4','Hedva'),('5','Hedva');
/*!40000 ALTER TABLE `studentscourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentstest`
--

DROP TABLE IF EXISTS `studentstest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentstest` (
  `studentID` varchar(255) NOT NULL,
  `testID` varchar(255) NOT NULL,
  `subjectID` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `testType` enum('C','M') DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `semester` varchar(10) DEFAULT NULL,
  `session` varchar(20) DEFAULT NULL,
  `suspicionOfCheating` enum('YES','NO') DEFAULT NULL,
  `correctAnswers` varchar(45) DEFAULT NULL,
  `totalQuestions` varchar(45) DEFAULT NULL,
  `lecturerComments` varchar(255) DEFAULT NULL,
  `approved` enum('YES','NO') DEFAULT NULL,
  `testDuration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentID`,`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentstest`
--

LOCK TABLES `studentstest` WRITE;
/*!40000 ALTER TABLE `studentstest` DISABLE KEYS */;
INSERT INTO `studentstest` VALUES ('4','010203','01','Hedva','C','0','Noa Krispin','2024','A','C','NO','0','1','','NO','00:00:08');
/*!40000 ALTER TABLE `studentstest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subjectID` varchar(45) NOT NULL,
  `subjectName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`subjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('01','Math'),('02','Software'),('03','Biology');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `testNumber` varchar(3) DEFAULT NULL,
  `id` varchar(45) NOT NULL,
  `testDuration` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `subject` varchar(45) NOT NULL,
  `courseName` varchar(45) NOT NULL,
  `teacherComment` varchar(45) DEFAULT NULL,
  `testType` enum('C','M') DEFAULT NULL,
  `studentComment` varchar(40) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `semester` varchar(45) DEFAULT NULL,
  `session` varchar(45) DEFAULT NULL,
  `testCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`subject`,`courseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('01','010101','61','b b','Math','Algebra','hey Lecturer','C','Hey Students','2023','B','A','d97a'),('04','010104','500','b b','Math','Algebra','heyyyy','C','byeeee','2025','A','B','4f62'),('06','010106','1','b b','Math','Algebra','dfss','C','sdfdfsfds','2020','A','C','019b'),('08','010108','1','a a','Math','Algebra','hi','C','bye','2018','A','B','44d0'),('02','010202','100','b b','Math','Hedva','Bye','C','Hey','2020','A','B','5d18'),('03','010203','80','b b','Math','Hedva','null','C','null','2024','A','C','799A'),('05','010205','1','a a','Math','Hedva','hi','C','bye','2018','A','B','1b1c'),('07','010207','3','a a','Math','Hedva','Easy Test','C','Easy Test','2023','B','A','b67e');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testquestion`
--

DROP TABLE IF EXISTS `testquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testquestion` (
  `questionID` varchar(45) NOT NULL,
  `questionNumber` varchar(45) DEFAULT NULL,
  `points` int DEFAULT NULL,
  `questionText` text,
  `testID` varchar(45) NOT NULL,
  `courseName` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`questionID`,`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testquestion`
--

LOCK TABLES `testquestion` WRITE;
/*!40000 ALTER TABLE `testquestion` DISABLE KEYS */;
INSERT INTO `testquestion` VALUES ('010101','1',60,'How much is 2+2?','010101','Algebra','01','b b'),('010102','2',20,'How much is 1+1?','010101','Algebra','01','b b'),('010103','3',20,'How much is 10-10?','010101','Algebra','01','b b'),('010103','3',100,'How much is 10-10?','010104','Algebra','01','b b'),('010103','3',100,'How much is 10-10?','010106','Algebra','01','b b'),('010103','3',100,'How much is 10-10?','010203','Algebra','01','b b'),('010204','4',100,'How are you?','010202','Hedva','01','b b'),('010204','4',50,'How are you?','010205','Hedva','01','b b'),('010204','4',25,'The answer is 2','010207','Hedva','01','b b'),('010205','5',50,'hola	','010205','Hedva','01','a a'),('010205','5',25,'The answer is 1','010207','Hedva','01','a a'),('010209','9',25,'The anser is 3','010207','Hedva','01','a a'),('010210','10',25,'The answer is 4','010207','Hedva','01','a a');
/*!40000 ALTER TABLE `testquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testrequest`
--

DROP TABLE IF EXISTS `testrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testrequest` (
  `newDuration` int DEFAULT NULL,
  `explanation` varchar(45) DEFAULT NULL,
  `id` varchar(45) NOT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testrequest`
--

LOCK TABLES `testrequest` WRITE;
/*!40000 ALTER TABLE `testrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `testrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `isLoggedIn` int DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'a','a','a','a','Lecturer','MayCaspi','a a',NULL,NULL),(3,'a','b','b','Fredi@gmail.com','Head of Department/Lecturer','FrediBul','Fredi Bulshtein',NULL,NULL),(4,'a','Noa','Krispin','NoaKrispin@email.com','Student','NoaKrisp','Noa Krispin',NULL,NULL),(5,'a','Abed','Tayer','AbedTayer','Student','AbedTayer','Abed Tayer',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-10 14:51:58
