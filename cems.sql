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
  `testCode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`testCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activetest`
--

LOCK TABLES `activetest` WRITE;
/*!40000 ALTER TABLE `activetest` DISABLE KEYS */;
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
  `average` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aftertestinfo`
--

LOCK TABLES `aftertestinfo` WRITE;
/*!40000 ALTER TABLE `aftertestinfo` DISABLE KEYS */;
INSERT INTO `aftertestinfo` VALUES ('010101',10,10,2,0,2,'2023-06-18','f82b','50.0'),('010202',120,120,3,0,3,'2023-06-18','012b','66.67'),('010204',90,90,3,0,3,'2023-06-18','fd1d','66.67'),('020309',20,20,1,1,2,'2023-06-18','92d4','10.0'),('020410',80,80,1,0,1,'2023-06-18','ff74','0.0'),('030505',13,63,0,1,1,'2023-06-18','abd6','0.0'),('030511',1,1,1,0,1,'2023-06-18','659b','40.0'),('030512',2,2,0,1,1,'2023-06-18','0eb0',NULL),('030613',4,4,2,0,2,'2023-06-18','9cdb','0.0'),('030614',10,10,2,0,2,'2023-06-18','0376','40.0'),('030615',13,73,2,0,2,'2023-06-18','c382','50.0'),('040703',1,1,0,2,2,'2023-06-18','29fa',NULL),('040716',5,5,2,0,2,'2023-06-18','8962','0.0'),('040807',30,30,2,0,2,'2023-06-18','c834','25.0'),('040808',10,10,2,0,2,'2023-06-18','ee06','85.0');
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
INSERT INTO `answersofstudent` VALUES (4,'010101',' 010102',2),(4,'010101',' 010103',1),(4,'010202',' 010104',1),(4,'010202',' 010213',3),(4,'010202',' 010214',3),(4,'010202','010101',3),(4,'030614',' 030523',4),(4,'030614',' 030524',3),(4,'030614',' 030525',2),(4,'030614',' 030626',1),(4,'030614',' 030627',2),(4,'030614',' 030628',3),(4,'030615',' 030626',4),(4,'040703',' 040712',3),(4,'040807',' 040816',2),(4,'040807',' 040817',2),(4,'040808',' 040712',2),(4,'040808',' 040715',2),(5,'010101',' 010102',4),(5,'010101',' 010103',3),(5,'010202',' 010104',1),(5,'010202',' 010213',3),(5,'010202',' 010214',3),(5,'010202','010101',3),(5,'020309',' 020318',0),(5,'020309',' 020319',0),(5,'020309',' 020320',0),(5,'030511',' 030524',3),(5,'030511',' 030525',3),(5,'030511',' 030626',0),(5,'030511',' 030627',0),(5,'030511',' 030628',0),(6,'010202',' 010104',1),(6,'010202',' 010213',4),(6,'010202',' 010214',3),(6,'010202','010101',4),(6,'020309',' 020318',2),(6,'020309',' 020319',0),(6,'020309',' 020320',0),(6,'030614',' 030523',3),(6,'030614',' 030524',2),(6,'030614',' 030525',3),(6,'030614',' 030626',1),(6,'030614',' 030627',4),(6,'030614',' 030628',1),(6,'030615',' 030626',0),(6,'040703',' 040712',0),(6,'040807',' 040816',1),(6,'040807',' 040817',3),(6,'040808',' 040712',1),(6,'040808',' 040715',4);
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
INSERT INTO `course` VALUES ('01','01','Math','Algebra'),('01','02','Math','Hedva'),('02','03','Software','Phyton'),('02','04','Software','C++'),('03','05','Biology','Anatomy'),('03','06','Biology','Microbiology'),('04','07','Music','Piano'),('04','08','Music','Guitar');
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
INSERT INTO `lecturersubject` VALUES ('10','04'),('11','03'),('12','03'),('2','01'),('3','01'),('7','02'),('8','04'),('9','02');
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
INSERT INTO `question` VALUES (' 010102','01','Algebra','2X2=?',2,'May Caspi','1','2','3','4','4'),(' 010103','01','Algebra','3X3=?',3,'May Caspi','9','12','15','6','1'),(' 010104','01','Algebra','How much is 3+3?',4,'Fredi Bulshtein','6','5','3','2','1'),(' 010213','01','Hedva','How much is 10-8?',13,'Fredi Bulshtein','10	','3','7','2','4'),(' 010214','01','Hedva','How much is 3*4?',14,'Fredi Bulshtein','15','17','12','4','3'),(' 020318','02','Phyton','What is the output of the following code snippet?\nprint(2 + 3 * 4 - 6)\n',18,'Roman Gury','9','14','20','23','2'),(' 020319','02','Phyton','What is the purpose of the range() function in Python?',19,'Roman Gury','It generates a sequence of numbers.','It performs mathematical calculations.','It converts strings to integers.','It retrieves data from an external source.','1'),(' 020320','02','Phyton','What is the correct way to comment a single line in Python?',20,'Roman Gury','/* Comment */','<!-- Comment -->',' # Comment','// Comment','3'),(' 020421','02','C++','What is the purpose of the cin object in C++?',21,'Roman Gury','To output data to the console','To read input from the user','To perform mathematical calculations','To define user-defined functions','2'),(' 020422','02','C++','What is C++?',22,'Roman Gury','An object-oriented programming language','A kind of beer','An horror movie','My mother','1'),(' 030523','03','Anatomy','Which of the following is the largest organ in the human body?',23,'Charles Darwin','Heart','Brain','Liver','Skin','4'),(' 030524','03','Anatomy','What is the scientific name for the collarbone?',24,'Charles Darwin','Humerus','Tibia','Clavicle','Femur','3'),(' 030525','03','Anatomy','How many chambers does the human heart have?',25,'Charles Darwin','2','3','4','5','3'),(' 030626','03','Microbiology','Which of the following is NOT a type of microorganism?',26,'Charles Darwin','Bacteria','Fungi','Virus','All of the above are types of microorganisms','4'),(' 030627','03','Microbiology','What is the primary function of antibiotics?',27,'Charles Darwin','Stimulate immune response','Destroy viruses','Kill or inhibit the growth of bacteria','Treat fungal infections','3'),(' 030628','03','Microbiology','What is the basic structure of a virus?',28,'Charles Darwin','Cell wall, cell membrane, and cytoplasm','Nucleus, mitochondria, and ribosomes','DNA or RNA enclosed in a protein coat','Cilia, flagella, and pili','3'),(' 040712','04','Piano','Which composer is known for his famous piano composition \"Für Elise\"?',12,'Tanya Garbuz','Wolfgang Amadeus Mozart','Ludwig van Beethoven','Frederic Chopin','Johann Sebastian Bach','2'),(' 040715','04','Piano','Which term refers to the soft pedal on a piano that reduces the volume of the sound produced?',15,'Tanya Garbuz','Sustain pedal','Una corda pedal','Damper pedal','Forte pedal','3'),(' 040816','04','Guitar','What is the name of the part of a guitar that is used to adjust the pitch of the strings?',16,'Tanya Garbuz','Headstock','Bridge','Fretboard','Tuning pegs','4'),(' 040817','04','Guitar','Which type of guitar is typically associated with a hollow or semi-hollow body design?',17,'Tanya Garbuz','Acoustic guitar','Electric guitar','Classical guitar','Bass guitar','2'),('010101','01','Algebra','How much is 2+2?',1,'Fredi Bulshtein','1','2','3','4','4'),('010106','01','Algebra','What is the value of x in the equation 2x + 5 = 15?',6,'May Caspi','5','7','8','10','1'),('010107','01','Algebra','Which of the following expressions is equivalent to 3(x - 2)?',7,'May Caspi','3x - 2','3x - 6',' 3x + 2','3x + 6','2'),('010108','01','Algebra','What is the value of 3 + 2x when x = 4?',8,'May Caspi','12','9','10','11','4'),('010111','01','Algebra','How much is 2?',11,'Fredi Bulshtein','its 2		','its 3','its 4','its 5','1'),('010205','01','Hedva','The answer is 1',5,'May Caspi','hello','hi','amigo','ci','1'),('010209','01','Hedva','The anser is 3',9,'May Caspi','1','2','3','4','3'),('010210','01','Hedva','The answer is 4',10,'May Caspi','1','2','3','4','4');
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
INSERT INTO `studentscourse` VALUES ('4','Algebra'),('4','Guitar'),('4','Hedva'),('4','Microbiology'),('4','Piano'),('5','Algebra'),('5','Anatomy'),('5','Hedva'),('5','Phyton'),('6','C++'),('6','Guitar'),('6','Hedva'),('6','Microbiology'),('6','Phyton'),('6','Piano');
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
INSERT INTO `studentstest` VALUES ('4','010101','01','Algebra','C','50','Noa Krispin','2020','A','B','NO','1','2','','YES','00:00:29'),('4','010202','01','Hedva','C','50','Noa Krispin','2020','A','B','YES','2','4','','YES','00:00:12'),('4','010204','01','Hedva','M','100','Noa Krispin','2021','A','B','NO','5','5','','YES','00:00:13'),('4','030613','03','Microbiology','M','0','Noa Krispin','2022','A','B','NO','0','0','','YES','00:00:15'),('4','030614','03','Microbiology','C','70','Noa Krispin','2020','A','A','NO','3','6','','YES','00:00:29'),('4','030615','03','Microbiology','C','100','Noa Krispin','2016','B','B','NO','1','1','','YES','00:00:33'),('4','040703','04','Piano','C','0','Noa Krispin','2021','A','B','NO','0','1','','YES','00:00:05'),('4','040716','04','Piano','M','0','Noa Krispin','2020','A','A','NO','0','0','','YES','00:00:26'),('4','040807','04','Guitar','C','50','Noa Krispin','2022','A','B','NO','1','2','what happend to you?','YES','00:00:38'),('4','040808','04','Guitar','C','90','Noa Krispin','2021','B','B','NO','1','2','Good job!','YES','00:00:53'),('5','010101','01','Algebra','C','50','Abed Tayer','2020','A','B','NO','1','2','','YES','00:00:17'),('5','010202','01','Hedva','C','50','Abed Tayer','2020','A','B','YES','2','4','You cheated','YES','00:00:49'),('5','010204','01','Hedva','M','0','Abed Tayer','2021','A','B','NO','0','0','','YES','00:00:06'),('5','020309','02','Phyton','C','0','Abed Tayer','2020','A','B','NO','0','3','','YES','00:01:43'),('5','030505','03','Anatomy','M','0','Abed Tayer','2013','A','B','NO','0','0','','YES','00:-49:-22'),('5','030511','03','Anatomy','C','40','Abed Tayer','2021','A','B','NO','2','5','You failed me','YES','00:02:06'),('5','030512','03','Anatomy','M','0','Abed Tayer','2021','A','B','NO','0','5','','YES','00:02:00'),('6','010202','01','Hedva','C','100','Shay Garbuz','2020','A','B','NO','4','4','Good job!!','YES','00:00:16'),('6','010204','01','Hedva','M','100','Shay Garbuz','2021','A','B','NO','5','5','','YES','00:00:04'),('6','020309','02','Phyton','C','20','Shay Garbuz','2020','A','B','NO','1','3','Talk to me after class','YES','00:04:07'),('6','020410','02','C++','M','0','Shay Garbuz','2020','A','B','NO','0','0','','YES','00:00:35'),('6','030613','03','Microbiology','M','0','Shay Garbuz','2022','A','B','NO','0','0','','YES','00:00:03'),('6','030614','03','Microbiology','C','10','Shay Garbuz','2020','A','A','NO','1','6','Please email me about a meeting','YES','00:00:26'),('6','030615','03','Microbiology','C','0','Shay Garbuz','2016','B','B','NO','0','1','','YES','00:01:34'),('6','040703','04','Piano','C','0','Shay Garbuz','2021','A','B','NO','0','1','','YES','00:01:00'),('6','040716','04','Piano','M','0','Shay Garbuz','2020','A','A','NO','0','0','','YES','00:00:19'),('6','040807','04','Guitar','C','0','Shay Garbuz','2022','A','B','NO','0','2','You are now allowed in my class anymore','YES','00:00:38'),('6','040808','04','Guitar','C','80','Shay Garbuz','2021','B','B','NO','0','2','Grade changed to 50','YES','00:00:45');
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
INSERT INTO `subject` VALUES ('01','Math'),('02','Software'),('03','Biology'),('04','Music');
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
  `subjectID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`subject`,`courseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('01','010101','10','May Caspi','Math','Algebra','null','C','null','2020','A','B','01'),('02','010202','120','Fredi Bulshtein','Math','Hedva','The test contains 4 questions in level 3','C','Good luck students!','2020','A','B','01'),('04','010204','90','Fredi Bulshtein','Math','Hedva','Manual test','M','Good luck students!','2021','A','B','01'),('06','010206','10','May Caspi','Math','Hedva','Good Luck	','M','Good Luck','2019','A','B','01'),('09','020309','20','Roman Gury','Software','Phyton','null','C','null','2020','A','B','02'),('10','020410','80','Roman Gury','Software','C++','i love C++	','M','Good luck!','2020','A','B','02'),('05','030505','63','Charles Darwin','Biology','Anatomy','null','M','null','2013','A','B','03'),('11','030511','1','Charles Darwin','Biology','Anatomy','good luck','C','good luck','2021','A','B','03'),('12','030512','2','Charles Darwin','Biology','Anatomy','null','M','null','2021','A','B','03'),('13','030613','4','Charles Darwin','Biology','Microbiology','note for lecturer	','M','note for lecturer','2022','A','B','03'),('14','030614','10','Charles Darwin','Biology','Microbiology','Good luck	','C','Good luck','2020','A','A','03'),('15','030615','73','Charles Darwin','Biology','Microbiology','Good luck	','C','Good luck','2016','B','B','03'),('03','040703','1','Tanya Garbuz','Music','Piano','null','C','null','2021','A','B','04'),('16','040716','5','Tanya Garbuz','Music','Piano','null','M','null','2020','A','A','04'),('07','040807','30','Tanya Garbuz','Music','Guitar','no notes	','C','De Re Mi Fa','2022','A','B','04'),('08','040808','10','Tanya Garbuz','Music','Guitar','Hey lecturers','C','Hey Students','2021','B','B','04');
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
INSERT INTO `testquestion` VALUES (' 010102','2',50,'2X2=?','010101','Algebra','01','May Caspi'),(' 010103','3',50,'3X3=?','010101','Algebra','01','May Caspi'),(' 010213','13',25,'How much is 10-8?','010202','Hedva',' 01','Fredi Bulshtein'),(' 010214','14',25,'How much is 3*4?','010202','Hedva',' 01','Fredi Bulshtein'),(' 020318','18',20,'What is the output of the following code snippet?\nprint(2 + 3 * 4 - 6)\n','020309','Phyton',' 02','Roman Gury'),(' 020319','19',40,'What is the purpose of the range() function in Python?','020309','Phyton',' 02','Roman Gury'),(' 020320','20',40,'What is the correct way to comment a single line in Python?','020309','Phyton',' 02','Roman Gury'),(' 020421','21',100,'What is the purpose of the cin object in C++?','020410','C++',' 02','Roman Gury'),(' 030523','23',10,'Which of the following is the largest organ in the human body?','030614','Anatomy','03','Charles Darwin'),(' 030524','24',20,'What is the scientific name for the collarbone?','030511','Anatomy','03','Charles Darwin'),(' 030524','24',10,'What is the scientific name for the collarbone?','030614','Anatomy','03','Charles Darwin'),(' 030525','25',100,'How many chambers does the human heart have?','030505','Anatomy','03','Charles Darwin'),(' 030525','25',20,'How many chambers does the human heart have?','030511','Anatomy','03','Charles Darwin'),(' 030525','25',10,'How many chambers does the human heart have?','030614','Anatomy','03','Charles Darwin'),(' 030626','26',20,'Which of the following is NOT a type of microorganism?','030511','Microbiology','03','Charles Darwin'),(' 030626','26',10,'Which of the following is NOT a type of microorganism?','030614','Microbiology','03','Charles Darwin'),(' 030626','26',100,'Which of the following is NOT a type of microorganism?','030615','Microbiology','03','Charles Darwin'),(' 030627','27',20,'What is the primary function of antibiotics?','030511','Microbiology','03','Charles Darwin'),(' 030627','27',100,'What is the primary function of antibiotics?','030512','Microbiology','03','Charles Darwin'),(' 030627','27',50,'What is the primary function of antibiotics?','030613','Microbiology','03','Charles Darwin'),(' 030627','27',10,'What is the primary function of antibiotics?','030614','Microbiology','03','Charles Darwin'),(' 030628','28',20,'What is the basic structure of a virus?','030511','Microbiology','03','Charles Darwin'),(' 030628','28',50,'What is the basic structure of a virus?','030613','Microbiology','03','Charles Darwin'),(' 030628','28',50,'What is the basic structure of a virus?','030614','Microbiology','03','Charles Darwin'),(' 040712','12',100,'Which composer is known for his famous piano composition \"Für Elise\"?','040703','Piano','04','Tanya Garbuz'),(' 040712','12',50,'Which composer is known for his famous piano composition \"Für Elise\"?','040716','Piano','04','Tanya Garbuz'),(' 040712','12',90,'Which composer is known for his famous piano composition \"Für Elise\"?','040808','Piano',' 04','Tanya Garbuz'),(' 040715','15',50,'Which term refers to the soft pedal on a piano that reduces the volume of the sound produced?','040716','Piano','04','Tanya Garbuz'),(' 040715','15',10,'Which term refers to the soft pedal on a piano that reduces the volume of the sound produced?','040808','Piano',' 04','Tanya Garbuz'),(' 040816','16',50,'What is the name of the part of a guitar that is used to adjust the pitch of the strings?','040807','Guitar',' 04','Tanya Garbuz'),(' 040817','17',50,'Which type of guitar is typically associated with a hollow or semi-hollow body design?','040807','Guitar',' 04','Tanya Garbuz');
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
INSERT INTO `user` VALUES (2,'a','May','Caspi','MayCaspi@gmail.com','Lecturer','MayCaspi','May Caspi',0,'052555444'),(3,'a','Fredi','Bulshtein','Fredi@gmail.com','Head of Department/Lecturer','FrediBul','Fredi Bulshtein',0,'054222333'),(4,'a','Noa','Krispin','NoaKrispin@email.com','Student','NoaKrisp','Noa Krispin',0,'050111999'),(5,'a','Abed','Tayer','AbedTayer','Student','AbedTayer','Abed Tayer',1,'053000111'),(6,'a','Shay','Garbuz','ShayGarbuz@gmail.com','Student','ShayGarbuz','Shay Garbuz',0,'051111100'),(7,'a','Itizik','Cohen','ItizikCohen@gmail.com','Head of Department','ItizikCo','Itizik Cohen',0,'052555555'),(8,'a','Tanya','Garbuz','Tanya@gmail.com','Lecturer','Tanya','Tanya Garbuz',0,'056223311'),(9,'a','Roman','Gury','RomanGury@gmail.com','Lecturer','RomanGur','Roman Gury',0,'052500332'),(10,'a','Amadeus ','Mozart','Mozart@gmail.com','Head of Department','Mozart','Amadeus Mozart',0,'057888999'),(11,'a','Charles','Darwin','Darwin@gmail.com','Lecturer','Darwin','Charles Darwin',1,'052883832'),(12,'a','Albert','Einstein','Einstein@gmail.com','Head of Department','Einstein','Albert Einstein',0,'056888228');
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

-- Dump completed on 2023-06-18 14:24:25
