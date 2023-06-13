package server;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


/**
 * the class contains methods which connect to the CEMS database
 * and perform various queries on the data
 */
public class MysqlConnection {
    static Connection conn;
    static String url = "jdbc:mysql://localhost/cems?serverTimezone=IST&allowPublicKeyRetrieval=true&useSSL=false"; // replace
    // "mydatabase"
    static String username = "root"; // replace with your username


    /**
     * This hook method sets up a connection between the Server and the Data base we
     * connect to the server by passing the DB address, DB username and password
     */
    public static void connectToDb(String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
            /* handle the error */
            System.out.println("Driver definition failed");
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("SQL connection succeed");
        } catch (SQLException ex) {/* handle any errors */
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("\n");
        }
    }

    public static void update(String question) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(question);
            stmt.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * gets an array list of the questions from the CEMS database
     *
     * @param query the given SELECT query
     * @return ArrayList of the questions
     */
    public static ArrayList<Question> getQuestionsTable(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Question> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id"); // assuming your table has a column named "id" with type INT
                String subject = rs.getString("subject");
                String course_name = rs.getString("courseName");
                String question_text = rs.getString("questionText");
                String question_number = rs.getString("questionNumber");
                String author = rs.getString("author");
                String answer1 = rs.getString("answer1");
                String answer2 = rs.getString("answer2");
                String answer3 = rs.getString("answer3");
                String answer4 = rs.getString("answer4");
                String correctAnswer = rs.getString("correctAnswer");

                // public Question(String questionNumber,String questionId,String questionText,
                // String questionWrittenBy) {
                Question qeustion = new Question(question_number, id, question_text, author, subject,
                        course_name, answer1, answer2, answer3, answer4, correctAnswer);
                list.add(qeustion);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Course> getCourseList(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Course> coursesList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String subjectID = rs.getString("subjectID"); // assuming your table has a column named "id" with type INT
                String courseID = rs.getString("courseID");
                String subjectName = rs.getString("subjectName");
                String courseName = rs.getString("courseName");

                Course course = new Course(subjectID, courseID, subjectName, courseName);
                coursesList.add(course);
//
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursesList;
    }

    public static ArrayList<Subject> getSubjectList(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Subject> subjectsList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String subjectID = rs.getString("subjectID"); // assuming your table has a column named "id" with type INT
                String subjectName = rs.getString("subjectName");

                Subject subject = new Subject(subjectID, subjectName);
                subjectsList.add(subject);
//
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectsList;
    }

    public static Object authenticateUser(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id, firstName, lastName, email, role, isLoggedIn FROM user  WHERE BINARY username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve the user details from the database
                    String id = resultSet.getString("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
                    String role = resultSet.getString("role");
                    int IsLoggedIn = resultSet.getInt("isLoggedIn");

                    // Create a new ConcreteUser object with the retrieved details
                    User user = new User(id, firstName, lastName, username, password, email, role,IsLoggedIn);
                    // Set the authenticated user

                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Test> getTestTable(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Test> tests = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String testNumber = rs.getString("testNumber"); // assuming your table has a column named "id" with type INT
                String id = rs.getString("id");
                String author = rs.getString("author");
                String testDuration = rs.getString("testDuration");
                String courseName = rs.getString("courseName");
                String subject = rs.getString("subject");
                String teacherComment = rs.getString("teacherComment");
                String testType = rs.getString("testType");
                String studentComment = rs.getString("studentComment");
                String year = rs.getString("year");
                String session = rs.getString("session");
                String semester = rs.getString("semester");
                TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.C : TestTypeEnum.M);
                Test test = new Test(testNumber, id, author, testDuration, courseName,
                        teacherComment, testTypeEnum, studentComment, subject, year, session, semester);
                tests.add(test);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static ArrayList<ActiveTest> getActiveTestsTable(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<ActiveTest> activeTests = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id"); // assuming your table has a column named "id" with type INT
                int numOfQuestions = rs.getInt("numOfQuestions");
                String testDate = rs.getString("testDate");
                String startingTime = rs.getString("startingTime");
               // String timeLeft = rs.getString("timeLeft");
                String testCode = rs.getString("testCode");

                ActiveTest activeTest = new ActiveTest(id, numOfQuestions, testDate, startingTime, testCode);
                activeTests.add(activeTest);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeTests;
    }

    public static ArrayList<TestQuestion> getTestQuestionsTable(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<TestQuestion> testQuestions = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String questionID = rs.getString("questionID"); // assuming your table has a column named "id" with type INT
                String questionNumber = rs.getString("questionNumber");
                String points = rs.getString("points");
                String questionText = rs.getString("questionText");
                String testID = rs.getString("testID");
                String subject = rs.getString("subject");
                String courseName = rs.getString("courseName");
                String author = rs.getString("author");

                TestQuestion testQuestion = new TestQuestion(questionID, questionNumber, Integer.parseInt(points),
                        questionText, testID, subject, courseName, author);
                testQuestions.add(testQuestion);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testQuestions;
    }

    public static ArrayList<TestRequestForApproval> getRequestsTable(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<TestRequestForApproval> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String newDuration = rs.getString("newDuration"); // assuming your table has a column named "id" with type INT
                String explanation = rs.getString("explanation");
                String id = rs.getString("id");
                String subject = rs.getString("subject");
                String course = rs.getString("course");
                String author = rs.getString("author");
                TestRequestForApproval request = new TestRequestForApproval(id, subject, course, newDuration, explanation, author);
                list.add(request);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<StudentTest> getStudentInfo(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<StudentTest> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
//			if (!(rs.next())){
//				return null;
//			}
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String testID = rs.getString("testID");
                String subjectID = rs.getString("subjectID");
                String course = rs.getString("course");
                TestTypeEnum testType = TestTypeEnum.valueOf(rs.getString("testType")); // Convert the string value to the enum
                String grade = rs.getString("score"); // Assuming "grade" represents the grade
                String fullname = rs.getString("fullname");
                String year = rs.getString("year");
                String semester = rs.getString("semester");
                String session = rs.getString("session");
                CheatingSuspicion suspicionOfCheating = CheatingSuspicion.valueOf(rs.getString("suspicionOfCheating")); // Assuming "suspicionOfCheating" is a string representation of the enum value
                String correctAnswers = rs.getString("correctAnswers");
                String totalQuestions = rs.getString("totalQuestions");
                String lecturerComments = rs.getString("lecturerComments");
                String TestDuration = rs.getString("testDuration");
                ApprovalStatus approved = ApprovalStatus.valueOf(rs.getString("approved")); // Assuming "approved" is a string representation of the enum value

                StudentTest studentTest = new StudentTest(studentID, testID, subjectID, course, grade, fullname, year, semester, session, suspicionOfCheating, correctAnswers, totalQuestions, lecturerComments, approved, testType,TestDuration);
                list.add(studentTest);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getFullName(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String fullname = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String first = rs.getString("firstName"); // assuming your table has a column named "id" with type INT
                String last = rs.getString("lastName");
                fullname = first + " " + last;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullname;
    }

    public static ArrayList<User> getUser(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<User> getUsers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Retrieve the user details from the database
                String id = rs.getString("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int IsLoggedIn = rs.getInt("isLoggedIn");


                // Create a new ConcreteUser object with the retrieved details
                User user = new User(id, firstName, lastName, "username", "password", email, role,IsLoggedIn);
                // Set the authenticated user
                getUsers.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getUsers;
    }



    /**
     * this method closes the connection to the DB and the server
     */
    public static void closeConnection() {
        if (conn == null)
            System.out.println("Server Connection has been closed");
        else {
            try {
                conn.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }

    public static Question getQuestionData(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Question question = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id"); // assuming your table has a column named "id" with type INT
                String subject = rs.getString("subject");
                String course_name = rs.getString("courseName");
                String question_text = rs.getString("questionText");
                String question_number = rs.getString("questionNumber");
                String author = rs.getString("author");
                String answer1 = rs.getString("answer1");
                String answer2 = rs.getString("answer2");
                String answer3 = rs.getString("answer3");
                String answer4 = rs.getString("answer4");
                String correctAnswer = rs.getString("correctAnswer");

                // public Question(String questionNumber,String questionId,String questionText,
                // String questionWrittenBy) {
                question = new Question(question_number, id, question_text, author, subject,
                        course_name, answer1, answer2, answer3, answer4, correctAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return question;
    }

    public static Test getTestData(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Test test = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String testNumber = rs.getString("testNumber"); // assuming your table has a column named "id" with type INT
                String id = rs.getString("id");
                String author = rs.getString("author");
                String testDuration = rs.getString("testDuration");
                String courseName = rs.getString("courseName");
                String subject = rs.getString("subject");
                String teacherComment = rs.getString("teacherComment");
                String testType = rs.getString("testType");
                String studentComment = rs.getString("studentComment");
                String year = rs.getString("year");
                String session = rs.getString("session");
                String semester = rs.getString("semester");
                TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.C : TestTypeEnum.M);
                test = new Test(testNumber, id, author, testDuration, courseName,
                        teacherComment, testTypeEnum, studentComment, subject, year, session, semester);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }
    public static StudentCourse checkRegistered(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StudentCourse getUser = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Retrieve the user details from the database
                String studentID = rs.getString("studentID");
                String courseName = rs.getString("course");


                // Create a new ConcreteUser object with the retrieved details
                getUser = new StudentCourse(studentID, courseName);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getUser;
    }
    public static Subject SubjectName(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Subject subject = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String subjectID = rs.getString("subjectID"); // assuming your table has a column named "id" with type INT
                String subjectName = rs.getString("subjectName");
                subject = new Subject(subjectID,subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
    public static ArrayList<TestForApproval> getTestForApproval(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<TestForApproval> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String testID = rs.getString("testID");
                String subjectID = rs.getString("subjectID");
                String course = rs.getString("course");
                String grade = rs.getString("score");
                String fullname = rs.getString("fullname");
                String year = rs.getString("year");
                String semester = rs.getString("semester");
                String session = rs.getString("session");
                String suspicionOfCheatingStr = rs.getString("suspicionOfCheating");
                String correctAnswers = rs.getString("correctAnswers");
                String totalQuestions = rs.getString("totalQuestions");
                String lecturerComments = rs.getString("lecturerComments");
                String approvedStr = rs.getString("approved");
                String testType = rs.getString("testType");
                CheatingSuspicion suspicionOfCheating = CheatingSuspicion.valueOf(suspicionOfCheatingStr);
                ApprovalStatus approved = ApprovalStatus.valueOf(approvedStr);
                TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.C : TestTypeEnum.M);
                // Create TestForApproval object with all the retrieved values
                TestForApproval testForApproval = new TestForApproval(studentID, testID, subjectID, course, grade,
                        fullname, year, semester, session, suspicionOfCheating, correctAnswers,
                        totalQuestions, lecturerComments, approved,testTypeEnum);
                list.add(testForApproval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static Integer getTotalStudentsInTest(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int totalStudents = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                totalStudents = rs.getInt("totalStudents");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalStudents;
    }
    public static Integer getTotalStudentsRegisteredToTest(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowCount = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                rowCount = rs.getInt("row_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }
    public static Integer getTotalStudentsFinishedTheTest(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int finishedCount = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                finishedCount = rs.getInt("totalFinished");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finishedCount;

    }
    public static TestTypeEnum getTestType(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int finishedCount = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                String testTypeString = rs.getString("testType");
                return TestTypeEnum.valueOf(testTypeString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // or throw an exception if the test id is not found
    }
    public static String getIDreturnFullname(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String fullName = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                fullName = rs.getString("fullName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }
    public static ArrayList<String> getStudentsCourses(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> courses = new ArrayList<>();
        String singleCourse = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                singleCourse = rs.getString("course"); ;
                courses.add(singleCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public static String getStudentsAvg(String query) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String average = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                average = rs.getString("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }
}
	
