import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by sravan on 10/30/16.
 */

public class DbInsert {
    private static DbInsert instance = null;
    private static Connection con ;
    private static Boolean isFree = true;

    public static DbInsert getInstance() {
        if(instance == null) {
            instance = new DbInsert();
        }
        return  instance;
    }

    public  Connection getConnection() {
        if (con == null) {
            try {
                String host = "jdbc:mysql://localhost:3306/CAB_DB";
                String username = "root";
                String password = "password";
                con = DriverManager.getConnection(host, username, password );
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }

    public static boolean checkIfFree() {
        return isFree;
    }
    public static void freeMe(String threadName) {
        isFree = true;
        System.out.println(threadName + "has the DB Connection");
    }
    public static void takeMe(String threadName) {
        isFree = false;
        System.out.println(threadName + "released the DB Connection");
    }
}
