package server;

import client.Client;
import common.ConnectToClients;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CemsServer extends AbstractServer {
    // This holds the list of the connected clients to the server and their status
    static ObservableList<ConnectToClients> clientList = FXCollections.observableArrayList();
    //Constructors ****************************************************
    public String passwordSQL;
    Object obj;
    MsgHandler<Object> msg;
    Question question;
    Test test;
    TestQuestion testQuestion;

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public CemsServer(int port, String passwordSQL) {
        super(port);
        this.passwordSQL = passwordSQL;
    }

    /**
     * This method updates the Connected\Disconnect clients to our Server Each time
     * a user Connect\disconnects we update the Connect Users in our Server GUI if a
     * user connects his status is "Connected" and if he disconnects status changes
     * to "Disconnected"
     *
     * @param client
     * @param connectionStatus
     */
    static void updateClientList(ConnectionToClient client, String connectionStatus) {
        for (int i = 0; i < clientList.size(); i++) {
            /* Comparing clients by IP addresses */
            try {
                if (clientList.get(i).getIp().equals(client.getInetAddress().getHostAddress())) {
                    clientList.get(i).setStatus(connectionStatus);
                    clientList.remove(i);
                }
            } catch (NullPointerException ex) {
                System.out.println("Error! Client not found");
            }
        }

        //In both cases of Connect and Disconnected, we will need to add Client into the
        //list so this function covers both of them simultaneously
        try {
            clientList.add(new ConnectToClients(client.getInetAddress().getHostAddress(),
                    client.getInetAddress().getHostName(), connectionStatus));
        } catch (NullPointerException ex) {
            System.out.println("Error! Client not found");
        }
    }

    /**
     * gets a list of clients connected to the server
     *
     * @return an observable list of the connected clients
     */
    public static ObservableList<ConnectToClients> getClientList() {
        return clientList;
    }

    public static void setClientList(ObservableList<ConnectToClients> clientList) {
        CemsServer.clientList = clientList;
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server starts listening for connections.
     */
    protected void serverStarted() {
        System.out.println("Server Started\n" + "Server listening for connections on port " + getPort());

        try {
            MysqlConnection.connectToDb(passwordSQL);
        } catch (Exception ex) {
            System.out.println("Error! Connection Failed");
        }
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server stops listening for connections.
     */
    protected void serverStopped() {
        try {
            super.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        MysqlConnection.closeConnection();

    }

    //	@Override
//	protected void clientConnected(ConnectionToClient client) {
//		updateClientList(client,"Connected");
//		try {
//			 client.sendToClient(new common.MsgHandler(common.TypeMsg.Connected, null));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	synchronized protected void clientDisconnected(ConnectionToClient client) {
//		updateClientList(client,"Disconnected");
//	}
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        MsgHandler<Object> messageFromClient = (MsgHandler<Object>) msg;
        System.out.println("Message received from " + client);
        try {
            switch (messageFromClient.getType()) {
                case Connected:
                    updateClientList(client, "Connected");
                    client.sendToClient(new MsgHandler(TypeMsg.Connected, null));
                    break;

                case Disconnected:
                    updateClientList(client, "Disconnected");
                    client.sendToClient(new MsgHandler<>(TypeMsg.Disconnected, null));
                    break;

                case GetQuestionsBySubject:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    ArrayList<Question> list = MysqlConnection.getQuestionsTable("SELECT obj.* " +
                            "FROM question obj " +
                            "JOIN lecturersubject ls ON obj.subject = ls.subjectID " +
                            "JOIN user u ON u.id = ls.id " +
                            "WHERE u.username = + '" + obj + "'");
                    client.sendToClient(new MsgHandler<>(TypeMsg.QuestionsBySubjectImported, list));
                    break;

                case EditQuestion:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = this.msg.getMsg();
                    if (obj instanceof Question) {
                        this.question = (Question) obj;
                        String updateQuery = "UPDATE cems.question SET questionNumber='" + question.getQuestionNumber() + "'," +
                                " subject='" + question.getSubject() + "'," +
                                " courseName='" + question.getCourseName() + "'," +
                                " questionText='" + question.getQuestionText() + "'," +
                                " author='" + question.getAuthor() + "'," +
                                " answer1='" + question.getAnswer1() + "'," +
                                " answer2='" + question.getAnswer2() + "'," +
                                " answer3='" + question.getAnswer3() + "'," +
                                " correctAnswer='" + question.getCorrectAnswer() + "'," +
                                " answer4='" + question.getAnswer4() + "'" +
                                " WHERE id='" + question.getId() + "'";

                        MysqlConnection.update(updateQuery);
                        client.sendToClient(new MsgHandler<>(TypeMsg.QuestionUpdated, null));
                    }
                    break;

                case DeleteQuestion:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (Question) this.msg.getMsg();
                    this.question = (Question) obj;
                    String DeleteQuery = "DELETE FROM cems.question WHERE id='" + question.getId() + "'";
                    ;
                    MysqlConnection.update(DeleteQuery);
                    client.sendToClient(new MsgHandler<>(TypeMsg.QuestionDeleted, null));
                    break;

                case TryLogin:
                    this.msg = (MsgHandler<Object>) msg;
                    List<Object> details = (List<Object>) this.msg.getMsg();
                    String Username = (String) details.get(0);
                    String Password = (String) details.get(1);
                    Object user = MysqlConnection.authenticateUser(Username, Password);
                    if (user instanceof User) {
                        client.setName(Username);
                        client.setInfo(client.getName(), ((User) user).getRole());
                    }
                    client.sendToClient(new MsgHandler<>(TypeMsg.LoginResponse, user));
                    break;

                case importSubjects:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = this.msg.getMsg();
                    ArrayList<Subject> importSubjects = MysqlConnection.getSubjectList("SELECT * " +
                            "FROM subject s " +
                            "JOIN lecturersubject ls ON s.subjectID = ls.subjectID " +
                            "JOIN user u ON u.id = ls.id " +
                            "WHERE u.username = '" + obj.toString() + "'");
                    client.sendToClient(new MsgHandler<>(TypeMsg.SubjectsimportSuccess, importSubjects));
                    break;

                case importCourses:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = this.msg.getMsg();
                    ArrayList<Course> importCourses = MysqlConnection.getCourseList("SELECT * " +
                            "FROM course AS c " +
                            "JOIN lecturersubject AS ls ON c.subjectID = ls.subjectID " +
                            "JOIN user AS u ON ls.id = u.id " +
                            "WHERE u.username = '" + obj.toString() + "'");

                    client.sendToClient(new MsgHandler<>(TypeMsg.CoursesimportSuccess, importCourses));
                    break;

                case GetAllQuestions:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    ArrayList<Question> allQuestions = MysqlConnection.getQuestionsTable("SELECT * FROM question");
                    client.sendToClient(new MsgHandler<>(TypeMsg.allQuestionImported, allQuestions));
                    break;

                case AddNewQuestion:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (Question) this.msg.getMsg();
                    Question question = (Question) obj;
                    try {
                        String newQuery = "INSERT INTO cems.question (id, subject, courseName, questionText, questionNumber, author, answer1, answer2, answer3, correctAnswer, answer4) " +
                                "VALUES ('" + question.getId() + "', '" +
                                question.getSubject() + "', '" +
                                question.getCourseName() + "', '" +
                                question.getQuestionText() + "', '" +
                                question.getQuestionNumber() + "', '" +
                                question.getAuthor() + "', '" +
                                question.getAnswer1() + "', '" +
                                question.getAnswer2() + "', '" +
                                question.getAnswer3() + "', '" +
                                question.getCorrectAnswer() + "', '" +
                                question.getAnswer4() + "')";
                        MysqlConnection.update(newQuery);
                    } catch (Exception ignored) {
                    }
                    client.sendToClient(new MsgHandler<>(TypeMsg.QuestionAddedSuccessfuly, null));
                    break;

                case GetCourseTable:

                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    ArrayList<Course> courseList = MysqlConnection.getCourseTable("SELECT * FROM cems.course WHERE subjectID = " +
                            "(SELECT subjectid FROM cems.lecturersubject WHERE id = " + obj + ")");;
                    client.sendToClient(new MsgHandler<>(TypeMsg.CourseTableResponse, courseList));
                    break;

                case GetTestTable:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();

                    ArrayList<Test> testList = MysqlConnection.getTestTable("select * from cems.test;");
                    client.sendToClient(new MsgHandler<>(TypeMsg.TestTableResponse, testList));
                    break;

                case AddNewTest:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = this.msg.getMsg();
                    if (obj instanceof Test) {
                        this.test = (Test) obj;
                        String newQuery = "INSERT INTO cems.test (testNumber, id, testDuration, author, subject, courseName, " +
                                "teacherComment, testType, studentComment, semester, year, session, testCode) " +
                                "VALUES ('" + test.getTestNumber() + "', " +
                                "'" + test.getId() + "', " +
                                "'" + test.getTestDuration() + "', " +
                                "'" + test.getAuthor() + "', " +
                                "'" + test.getSubject() + "', " +
                                "'" + test.getCourseName() + "', " +
                                "'" + test.getTeacherComments() + "', " +
                                "'" + test.getTestType() + "', " +
                                "'" + test.getStudentComments() + "', " +
                                "'" + test.getSemester() + "', " +
                                "'" + test.getYear() + "', " +
                                "'" + test.getSession() + "', " +
                                "'" + test.getTestCode() + "') ";
                        MysqlConnection.update(newQuery);
                        client.sendToClient(new MsgHandler<>(TypeMsg.AddNewTestResponse, null));
                    }
                    break;

                case AddNewTestQuestion:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = this.msg.getMsg();
                    if (obj instanceof TestQuestion) {
                        this.testQuestion = (TestQuestion) obj;
                        String newQuery = "INSERT INTO cems.testquestion (questionID, questionNumber, points, " +
                                "questionText, testID, courseName, subject, author) " +
                                "VALUES ('" + testQuestion.getQuestionID() + "', " +
                                "'" + testQuestion.getQuestionNumber() + "', " +
                                "'" + testQuestion.getPoints() + "', " +
                                "'" + testQuestion.getQuestionText() + "', " +
                                "'" + testQuestion.getTestID() + "', " +
                                "'" + testQuestion.getCourseName() + "', " +
                                "'" + testQuestion.getSubject() + "', " +
                                "'" + testQuestion.getAuthor() + "') ";
                        MysqlConnection.update(newQuery);
                        client.sendToClient(new MsgHandler<>(TypeMsg.AddNewTestQuestionsResponse, null));
                        break;
                    }
                case GetRequestsBySubject:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    ArrayList<TestRequestForApproval> listOfRequests = MysqlConnection.getRequestsTable("SELECT tr.* " +
                            "FROM testrequest AS tr " +
                            "JOIN lecturersubject AS ls ON tr.subject = ls.subjectid " +
                            "JOIN user AS u ON ls.id = u.id " +
                            "WHERE u.username =  + '" + obj + "'");
                    client.sendToClient(new MsgHandler<>(TypeMsg.RequestImportedSuccessfully, listOfRequests));
                    break;
                case ApproveRequestByHeadOfDepartment:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    String approvedRequest = "DELETE FROM cems.testrequest WHERE id='" + obj + "'";
                    MysqlConnection.update(approvedRequest);
                    client.sendToClient(new MsgHandler<>(TypeMsg.RequestIsApproved, null));
                    break;
                case DeclineRequestByHeadOfDepartment:
                    this.msg = (MsgHandler<Object>) msg;
                    this.obj = (String) this.msg.getMsg();
                    String declinedRequest = "DELETE FROM cems.testrequest WHERE id='" + obj + "'";
                    MysqlConnection.update(declinedRequest);
                    client.sendToClient(new MsgHandler<>(TypeMsg.RequestIsDeclined, null));
                    break;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}