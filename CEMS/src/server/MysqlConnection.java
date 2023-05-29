package server;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

	/**
	 * executes an UPDATE query on the CEMS database
	 * @param q the given update query
	 */
	public static void update(String q) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(q);
			stmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * gets an array list of the questions from the CEMS database
	 * @param q the given SELECT query
	 * @return ArrayList of the questions
	 */
	public static ArrayList<Question> getQuestionsTable(String q) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Question> list = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {
				String id = rs.getString("id"); // assuming your table has a column named "id" with type INT
				String subject = rs.getString("subject");
				String course_name = rs.getString("courseName");
				String question_text = rs.getString("questionText");
				String question_number = rs.getString("questionNumber");
				String lecturer = rs.getString("lecturer");
				String answer1 = rs.getString("answer1");
				String answer2 = rs.getString("answer2");
				String answer3= rs.getString("answer3");
				String answer4= rs.getString("answer4");
				String correctAnswer= rs.getString("correctAnswer");

				// public Question(String questionNumber,String questionId,String questionText,
				// String questionWrittenBy) {
				Question qeustion = new Question(question_number, id, question_text, lecturer, subject,
						course_name,answer1,answer2,answer3,answer4,correctAnswer);
				list.add(qeustion);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Test> getTestTable(String q) {
		System.out.println(q);
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Test> tests = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(q);
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
				TestTypeEnum testTypeEnum = (Objects.equals(testType, "C") ? TestTypeEnum.COMPUTERIZED : TestTypeEnum.MANUAL);
				Test test = new Test(testNumber, id, author, testDuration, courseName,
						teacherComment, testTypeEnum, studentComment,subject,year,session,semester);
				tests.add(test);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tests;
	}

	public static ArrayList<Course> getCourseList(String q) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Course> coursesList = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {

				String subjectID = rs.getString("subjectID"); // assuming your table has a column named "id" with type INT
				String courseID = rs.getString("courseID");
				String subjectName = rs.getString("subjectName");
				String courseName = rs.getString("courseName");

				Course course = new Course(subjectID,courseID,subjectName,courseName);
				coursesList.add(course);
//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesList;
	}
	public static ArrayList<Subject> getSubjectList(String q) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Subject> subjectsList = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {

				String subjectID = rs.getString("subjectID"); // assuming your table has a column named "id" with type INT
				String subjectName= rs.getString("subjectName");

				Subject subject = new Subject(subjectID ,subjectName);
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
			//connectToDb("Aa123456");
			PreparedStatement statement = conn.prepareStatement("SELECT id, firstName, lastName, email, role FROM users WHERE username = ? AND password = ?");
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

					// Create a new ConcreteUser object with the retrieved details
					User user = new User(id, firstName, lastName, username, password, email,role);
					// Set the authenticated user

					return user;
				}
				else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
}
	
