package application.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    static Connection conn;

    /**
     * This hook method sets up a connection between the Server and the Data base we
     * connect to the server by passing the DB address, DB username and password
     */
    public static void connectToDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
            /* handle the error */
            System.out.println("Driver definition failed");
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST&useSSL=false", "root", "Aa123456");
            System.out.println("SQL connection succeed");
        } catch (SQLException ex) {/* handle any errors */
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("\n");
        }
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
	
