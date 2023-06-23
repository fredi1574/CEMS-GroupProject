import entity.Test;

import java.util.ArrayList;

public interface IMysqlConnection {
    void connectToDb(String password);
    Object update(String question);
    ArrayList<Test> getTestTable(String query);
    void closeConnection();
    Test getTestData(String query);
}
