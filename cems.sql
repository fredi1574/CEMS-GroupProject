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
INSERT INTO `activetest` VALUES ('010101',3,'2007-06-20','21:00:00','ABC3');
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
  `allotedTime` int DEFAULT NULL,
  `actualTime` int DEFAULT NULL,
  `totalFinished` int DEFAULT NULL,
  `totalForcedFinished` int DEFAULT NULL,
  `totalStudents` int DEFAULT NULL,
  PRIMARY KEY (`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aftertestinfo`
--

LOCK TABLES `aftertestinfo` WRITE;
/*!40000 ALTER TABLE `aftertestinfo` DISABLE KEYS */;
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
INSERT INTO `answersofstudent` VALUES (4,'010101','010101',4),(4,'010101','010102',2),(4,'010101','010103',0);
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
INSERT INTO `question` VALUES ('010101','01','Algebra','How much is 2+2?',1,'b b','1','2','3','4','4'),('010102','01','Algebra','How much is 1+1?',2,'b b','1','2','3','4','2'),('010103','01','Algebra','How much is 10-10?',3,'b b','10','5','0','12','3'),('010204','01','Hedva','How are you?',4,'b b','bad','very bad','no	','ok','2');
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
INSERT INTO `studentscourse` VALUES ('4','Algebra');
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
  `approved` enum('Y','N') DEFAULT NULL,
  PRIMARY KEY (`studentID`,`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentstest`
--

LOCK TABLES `studentstest` WRITE;
/*!40000 ALTER TABLE `studentstest` DISABLE KEYS */;
INSERT INTO `studentstest` VALUES ('4','010101','01','Algebra','C','80','Noa Krispin','2023','B','A','NO','2','3','','N');
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
INSERT INTO `test` VALUES ('01','010101','1','b b','Math','Algebra','hey Lecturer','C','Hey Students','2023','B','A','d97a'),('02','010202','50','b b','Math','Hedva','Bye','C','Hey','2020','A','B','5d18');
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
INSERT INTO `testquestion` VALUES ('010101','1',60,'How much is 2+2?','010101','Algebra','01','b b'),('010102','2',20,'How much is 1+1?','010101','Algebra','01','b b'),('010103','3',20,'How much is 10-10?','010101','Algebra','01','b b'),('010204','4',100,'How are you?','010202','Hedva','01','b b');
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
INSERT INTO `testrequest` VALUES (60,'dsasa','010101','01','Algebra','b b');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'a','a','a','a','Lecturer','MayCaspi','a a'),(3,'a','b','b','b','Head of Department/Lecturer','FrediBul','b b'),(4,'a','Noa','Krispin','NoaKrispin@email.com','Student','NoaKrisp','Noa Krispin'),(5,'a','Abed','Tayer','AbedTayer','Student','AbedTayer','Abed Tayer');
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

-- Dump completed on 2023-06-08 20:32:27
