package application.Server;

import application.MinimizeButton;
import application.ScreenManager;
import application.common.ConnectToClients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

        this.connectToClients.setItems(CemsServer.getClientList());
        consoleStreamIntoGUI();
        this.txtIP.setText(getIp());
        this.txtPort.setText("5555");
        this.txtDBname.setText("jdbc:mysql://localhost/Cems?serverTimezone=IST");
        this.txtUsername.setText("root");
        this.txtPassword.setText("Aa123456");
        this.btnDisconnect.setDisable(true);
        setTableColumns();
    }


    private void setTableColumns() {
        this.IP.setCellValueFactory(new PropertyValueFactory<ConnectToClients, String>("ip"));
        this.Host.setCellValueFactory(new PropertyValueFactory<ConnectToClients, String>("host"));
        this.Status.setCellValueFactory(new PropertyValueFactory<ConnectToClients, String>("Status"));
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
