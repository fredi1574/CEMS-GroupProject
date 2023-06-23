package Client;

import application.createNewTestScreen.notesScreen.InotesController;
import util.ChatIF;

import java.util.List;

public class ClientControl implements ChatIF {


	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	final Client client;
	private  ChatIF clientC;

	/**
 * Constructs an instance of the ClientConsole UI.
 *
 * @param host The host to connect to.
 * @param port The port to connect on.
 */
	public ClientControl(String host, int port) {
		client= new Client(host, port, this);
	}



	public ClientControl(ChatIF clientC) {
		this.clientC = clientC;
		client = new Client("", 5555, this);
	}

	private List<Object> response;
	private Object singleResponse;
	/**
	 * Retrieves the list of questions by subject from the client.
	 *
	 * @return The list of questions by subject.
	 */
	public List<Object> GetQuestionsBySubject() {
		response = client.questions;
		return response;
	}

	/**
	 * Retrieves the list of courses from the client.
	 *
	 * @return The list of courses.
	 */
	public List<Object> getCourses() {
		response = client.courses;
		return response;
	}

	/**
	 * Retrieves the list of subjects from the client.
	 *
	 * @return The list of subjects.
	 */
	public List<Object> getSubjects() {
		response = client.subjects;
		return response;
	}

	/**
	 * Retrieves the list of tests from the client.
	 *
	 * @return The list of tests.
	 */
	public List<Object> getTests() {
		response = client.tests;
		return response;
	}

	/**
	 * Retrieves all questions from the client.
	 *
	 * @return The list of all questions.
	 */
	public List<Object> getAllQuestions() {
		response = client.allQuestions;
		return response;
	}

	/**
	 * Retrieves all requests from the client.
	 *
	 * @return The list of all requests.
	 */
	public List<Object> getAllRequests() {
		response = client.requests;
		return response;
	}

	/**
	 * Retrieves the list of test questions from the client.
	 *
	 * @return The list of test questions.
	 */
	public List<Object> getTestQuestions() {
		response = client.testQuestions;
		return response;
	}

	/**
	 * Retrieves the list of active tests from the client.
	 *
	 * @return The list of active tests.
	 */
	public List<Object> getActiveTests() {
		response = client.activeTests;
		return response;
	}

	/**
	 * Retrieves the list of active tests by lecturer from the client.
	 *
	 * @return The list of active tests by lecturer.
	 */
	public List<Object> getActiveTestsByLecturer() {
		response = client.activeTests;
		return response;
	}

	/**
	 * Retrieves a single question from the client.
	 *
	 * @return The single question.
	 */
	public Object getSingleQuestion() {
		singleResponse = client.singleQuestion;
		return singleResponse;
	}

	/**
	 * Retrieves a single test from the client.
	 *
	 * @return The single test.
	 */
	public Object getSingleTest() {
		singleResponse = client.singleTest;
		return singleResponse;
	}

	/**
	 * Retrieves the user and course information from the client.
	 *
	 * @return The user and course information.
	 */
	public Object getUserAndCourse() {
		singleResponse = client.UserAndCourse;
		return singleResponse;
	}

	/**
	 * Retrieves the list of tests for approval from the client.
	 *
	 * @return The list of tests for approval.
	 */
	public List<Object> getTestsForApproval() {
		response = client.testsForApproval;
		return response;
	}

	/**
	 * Retrieves the number of registered students from the client.
	 *
	 * @return The number of registered students.
	 */
	public Object getNumberOfRegistered() {
		singleResponse = client.NumOfRegistered;
		return singleResponse;
	}

	/**
	 * Retrieves the number of attended students from the client.
	 *
	 * @return The number of attended students.
	 */
	public Object getNumberOfAttended() {
		singleResponse = client.NumOfAttended;
		return singleResponse;
	}

	/**
	 * Retrieves the number of finished students from the client.
	 *
	 * @return The number of finished students.
	 */
	public Object getNumberOfFinished() {
		singleResponse = client.NumOfFinished;
		return singleResponse;
	}

	/**
	 * Retrieves the student tests from the client.
	 *
	 * @return The student tests.
	 */
	public List<Object> getStudentTests() {
		response = client.studentTests;
		return response;
	}

	/**
	 * Retrieves information about a test from the client.
	 *
	 * @return Information about a test.
	 */
	public List<Object> getInfoAboutTest() {
		response = client.infoAboutTest;
		return response;
	}
	/**
	 * Waits for input from the console.  Once it is
	 * received, it sends it to the client's message handler.
	 */
	@Override
	public void accept(Object str) 
	{
	  client.handleMessageFromClientUI(str);
	}
	/**
	 *Overrides the method in the util.ChatIF interface.  It
	 * displays a message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	@Override
	public void display(String message) 
	{
		System.out.println("> " + message);
	}



}
