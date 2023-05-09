package application.Server;

import application.MinimizeButton;
import application.ScreenManager;
import application.TableManager;
import application.common.ConnectToClients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class ServerGuiController {

    @FXML
    private AnchorPane header;

    @FXML
    private Button Close;

    @FXML
    private Label ConnectedClients;

    @FXML
    private TextArea Console;

    @FXML
    private Label ConsoleTitle;

    @FXML
    private TableColumn<ConnectToClients, String> Host;

    @FXML
    private TableColumn<ConnectToClients, String> IP;

    @FXML
    private Label ServerConfig;

    @FXML
    private TableColumn<ConnectToClients, String> Status;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnDisconnect;

    @FXML
    private Button btnImport;

    @FXML
    private TableView<ConnectToClients> connectToClients;

    @FXML
    private Label labelDBNAME;

    @FXML
    private Label labelDBPassword;

    @FXML
    private Label labelDBUsername;

    @FXML
    private Label labelIP;

    @FXML
    private Label labelPort;

    @FXML
    private TextField txtDBname;

    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPort;

    @FXML
    private TextField txtUsername;
    private PrintStream switchConsole;

    @FXML
    void consoleStreamIntoGUI() {
        switchConsole = new PrintStream(new Console(Console));
        System.setOut(switchConsole);
        System.setErr(switchConsole);
    }

    public String getIp() {
        String ip = null;
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return ip;
    }

    @FXML
    void Connection(javafx.event.ActionEvent event) throws InterruptedException {

        ServerUI.runServer(txtIP.getText(), txtPort.getText(), txtDBname.getText(), txtUsername.getText(), txtPassword.getText());
        btnConnect.setDisable(true);
        btnDisconnect.setDisable(false);
        disableDataInput(true);

    }

    @FXML
    void Disconnection(ActionEvent event) {
        ServerUI.disconnect();
        btnDisconnect.setDisable(true);
        btnConnect.setDisable(false);
        disableDataInput(false);

    }
    //Turns off the option to pass data in the TextArea

    void disableDataInput(boolean Condi) {
        txtIP.setDisable(Condi);
        txtPort.setDisable(Condi);
        txtDBname.setDisable(Condi);
        txtUsername.setDisable(Condi);
        txtPassword.setDisable(Condi);
    }

    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("IP", "Host", "Status");

        TableManager.createTable(connectToClients, columnList);

        double[] multipliers = {0.33, 0.33, 0.33};
        TableManager.resizeColumns(connectToClients, multipliers);

        consoleStreamIntoGUI();

        this.connectToClients.setItems(CemsServer.getClientList());
        //Default data input for server
        this.txtIP.setText(getIp());
        this.txtPort.setText("5555");
        this.txtDBname.setText("jdbc:mysql://localhost/Cems?serverTimezone=IST");
        this.txtUsername.setText("root");
        this.txtPassword.setText("Aa123456");
        this.btnDisconnect.setDisable(true);

        //todo: convert line 147 to use TableManager.importData
//        TableManager.importData(connectToClients, CemsServer.getClientList());
    }

    @FXML
    void ImportData(ActionEvent event) {
        MysqlConnection.connectToDb();
    }

    public void Close(ActionEvent event) { //close button
        ServerUI.disconnect();
        System.exit(0);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
