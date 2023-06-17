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
    static String url = "jdbc:mysql://localhost/cems?serverTimezone=IST&allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";

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
    /**
     * Executes an SQL update statement.
     *
     * @param question the SQL update statement to be executed.
     */

    public static void update(String question) {
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(question);
            statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private static Statement createStatement() {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * gets an array list of the questions from the CEMS database
     *
     * @param query the given SELECT query
     * @return ArrayList of the questions
     */
    public static ArrayList<Question> getQuestionsTable(String query) {
        Statement statement = createStatement();

        ArrayList<Question> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
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

                Question question = new Question(question_number, id, question_text, author, subject,
                        course_name, answer1, answer2, answer3, answer4, correctAnswer);

                list.add(question);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * gets an array list of the Course from the CEMS database
     *
     * @param query the given SELECT query
     * @return ArrayList of the courses
     */
    public static ArrayList<Course> getCourseList(String query) {
        Statement statement = createStatement();

        ArrayList<Course> coursesList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                String subjectID = rs.getString("subjectID");
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
    /**
     * gets an array list of the Subjects from the CEMS database
     *
     * @param query the given SELECT query
     * @return ArrayList of the subjects
     */

    public static ArrayList<Subject> getSubjectList(String query) {
        Statement statement = createStatement();

        ArrayList<Subject> subjectsList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                String subjectID = rs.getString("subjectID");
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
    /**
     * Authenticates a user by checking the provided username and password against the database.
     *
     * @param username the username of the user to authenticate.
     * @param password the password of the user to authenticate.
     * @return a User object if the authentication is successful, or null if the authentication fails.
     */

    public static Object authenticateUser(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id, firstName, lastName, email, role, isLoggedIn,phoneNumber FROM user  WHERE BINARY username = ? AND password = ?");
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
                    String phone = resultSet.getString("phoneNumber");

                    return new User(id, firstName, lastName, username, password, email, role, IsLoggedIn, phone);
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
        Statement statement = createStatement();

        ArrayList<Test> tests = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String testNumber = rs.getString("testNumber");
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
                String subjectID = rs.getString("subjectID");
                TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.C : TestTypeEnum.M);
                Test test = new Test(testNumber, id, author, testDuration, courseName,
                        teacherComment, testTypeEnum, studentComment, subject, year, session, semester, subjectID);
                tests.add(test);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }
    /**
     * Retrieves a list of Test objects from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the test records from the database.
     * @return an ArrayList of Test objects retrieved from the database.
     */
    public static ArrayList<ActiveTest> getActiveTestsTable(String query) {
        Statement statement = createStatement();

        ArrayList<ActiveTest> activeTests = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
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

    /**
     * Retrieves a list of TestQuestion objects from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the test question records from the database.
     * @return an ArrayList of TestQuestion objects retrieved from the database.
     */
    public static ArrayList<TestQuestion> getTestQuestionsTable(String query) {
        Statement statement = createStatement();

        ArrayList<TestQuestion> testQuestions = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String questionID = rs.getString("questionID");
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
    /**
     * Retrieves a list of TestRequestForApproval objects from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the test request records from the database.
     * @return an ArrayList of TestRequestForApproval objects retrieved from the database.
     */
    public static ArrayList<TestRequestForApproval> getRequestsTable(String query) {
        Statement statement = createStatement();

        ArrayList<TestRequestForApproval> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String newDuration = rs.getString("newDuration");
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
        Statement statement = createStatement();

        ArrayList<StudentTest> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String testID = rs.getString("testID");
                String subjectID = rs.getString("subjectID");
                String course = rs.getString("course");
                TestTypeEnum testType = TestTypeEnum.valueOf(rs.getString("testType"));
                String grade = rs.getString("score");
                String fullName = rs.getString("fullname");
                String year = rs.getString("year");
                String semester = rs.getString("semester");
                String session = rs.getString("session");
                CheatingSuspicion suspicionOfCheating = CheatingSuspicion.valueOf(rs.getString("suspicionOfCheating"));
                String correctAnswers = rs.getString("correctAnswers");
                String totalQuestions = rs.getString("totalQuestions");
                String lecturerComments = rs.getString("lecturerComments");
                String TestDuration = rs.getString("testDuration");
                ApprovalStatus approved = ApprovalStatus.valueOf(rs.getString("approved"));

                StudentTest studentTest = new StudentTest(studentID, testID, subjectID, course, grade, fullName, year,
                        semester, session, suspicionOfCheating, correctAnswers, totalQuestions, lecturerComments,
                        approved, testType, TestDuration);

                list.add(studentTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Retrieves a list of StudentTest objects from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the student test information from the database.
     * @return an ArrayList of StudentTest objects retrieved from the database.
     */

    public static ArrayList<User> getUser(String query) {
        Statement statement = createStatement();

        ArrayList<User> getUsers = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                // Retrieve the user details from the database
                String id = rs.getString("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int IsLoggedIn = rs.getInt("isLoggedIn");
                String phone = rs.getString("phoneNumber");


                // Create a new ConcreteUser object with the retrieved details
                User user = new User(id, firstName, lastName, "username", "password", email, role, IsLoggedIn, phone);
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
    /**
     * Retrieves a Question object from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the question data from the database.
     * @return a Question object containing the retrieved question data.
     */

    public static Question getQuestionData(String query) {
        Statement statement = createStatement();

        Question question = null;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
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
    /**
     * Retrieves a Question object from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the question data from the database.
     * @return a Question object containing the retrieved question data.
     */
    public static Test getTestData(String query) {
        Statement statement = createStatement();

        Test test = null;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String testNumber = rs.getString("testNumber");
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
                String subjectID = rs.getString("subjectID");
                TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.C : TestTypeEnum.M);
                test = new Test(testNumber, id, author, testDuration, courseName,
                        teacherComment, testTypeEnum, studentComment, subject, year, session, semester, subjectID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }
    /**
     * Checks if a student is registered for a specific course based on the provided query.
     *
     * @param query the SQL query to check if the student is registered for the course.
     * @return a StudentCourse object containing the student's ID and the course name if registered,
     *         or null if not registered.
     */

    public static StudentCourse checkRegistered(String query) {
        Statement statement = createStatement();

        StudentCourse getUser = null;
        try {
            ResultSet rs = statement.executeQuery(query);
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
    /**
     * Retrieves a Subject object from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the subject data from the database.
     * @return a Subject object containing the retrieved subject data.
     */

    public static Subject SubjectName(String query) {
        Statement statement = createStatement();

        Subject subject = null;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String subjectID = rs.getString("subjectID");
                String subjectName = rs.getString("subjectName");
                subject = new Subject(subjectID, subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
    /**
     * Retrieves a list of TestForApproval objects from the database based on the provided query.
     *
     * @param query the SQL query to retrieve the test approval data from the database.
     * @return an ArrayList of TestForApproval objects containing the retrieved test approval data.
     */

    public static ArrayList<TestForApproval> getTestForApproval(String query) {
        Statement statement = createStatement();

        ArrayList<TestForApproval> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String testID = rs.getString("testID");
                String subjectID = rs.getString("subjectID");
                String course = rs.getString("course");
                String grade = rs.getString("score");
                String fullName = rs.getString("fullname");
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
                        fullName, year, semester, session, suspicionOfCheating, correctAnswers,
                        totalQuestions, lecturerComments, approved, testTypeEnum);
                list.add(testForApproval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Retrieves the total number of students in a test based on the provided query.
     *
     * @param query the SQL query to retrieve the total number of students in the test from the database.
     * @return the total number of students in the test.
     */
    public static Integer getTotalStudentsInTest(String query) {
        Statement statement = createStatement();

        int totalStudents = 0;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                totalStudents = rs.getInt("totalStudents");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalStudents;
    }
    /**
     * Retrieves the total number of students registered to a test based on the provided query.
     *
     * @param query the SQL query to retrieve the total number of registered students for the test from the database.
     * @return the total number of students registered to the test.
     */

    public static Integer getTotalStudentsRegisteredToTest(String query) {
        Statement statement = createStatement();

        int rowCount = 0;
        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                rowCount = rs.getInt("row_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }
    /**
     * Retrieves the total number of students who have finished the test based on the provided query.
     *
     * @param query the SQL query to retrieve the total number of students who finished the test from the database.
     * @return the total number of students who have finished the test.
     */

    public static Integer getTotalStudentsFinishedTheTest(String query) {
        Statement statement = createStatement();

        int finishedCount = 0;
        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                finishedCount = rs.getInt("totalFinished");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finishedCount;

    }
    /**
     * Retrieves the test type based on the provided query.
     *
     * @param query the SQL query to retrieve the test type from the database.
     * @return the test type as a {@link TestTypeEnum} value, or {@code null} if the test type is not found.
     */


    public static TestTypeEnum getTestType(String query) {
        Statement statement = createStatement();

        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                String testTypeString = rs.getString("testType");
                return TestTypeEnum.valueOf(testTypeString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // or throw an exception if the test id is not found
    }
    /**
     * Retrieves the full name based on the provided query.
     *
     * @param query the SQL query to retrieve the full name from the database.
     * @return the full name as a {@link String}, or {@code null} if the full name is not found.
     */
    public static String getIDReturnFullname(String query) {
        Statement statement = createStatement();

        String fullName = null;
        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) { // Move the cursor to the first row
                fullName = rs.getString("fullName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }
    /**
     * Retrieves the courses of the students based on the provided query.
     *
     * @param query the SQL query to retrieve the student courses from the database.
     * @return an {@link ArrayList} of student courses as {@link String}s.
     */

    public static ArrayList<String> getStudentsCourses(String query) {
        Statement statement = createStatement();

        ArrayList<String> courses = new ArrayList<>();
        String singleCourse;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                singleCourse = rs.getString("course");
                courses.add(singleCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    /**
     * Retrieves the average of students based on the provided query.
     *
     * @param query the SQL query to retrieve the average of students from the database.
     * @return the average value as a {@link String}.
     */

    public static String getStudentsAvg(String query) {
        Statement statement = createStatement();

        String average = null;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                average = rs.getString("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }
    /**
     * Retrieves the information after the test based on the provided query.
     *
     * @param query the SQL query to retrieve the information from the database.
     * @return a list of strings containing the actual duration, total finished count, and total forced finished count.
     */

    public static ArrayList<String> getAfterTestInfo(String query) {
        Statement statement = createStatement();

        ArrayList<String> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String actualDuration = rs.getString("actualDuration");
                String totalFinished = rs.getString("totalFinished");
                String totalForcedFinished = rs.getString("totalForcedFinished");
                list.add(actualDuration);
                list.add(totalFinished);
                list.add(totalForcedFinished);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}