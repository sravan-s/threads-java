import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sravan on 10/28/16.
 * CREATE DATABASE CAB_DB;
 * use CAB_DB;
 * CREATE TABLE CABS (id INT, cabName VARCHAR(30), cabDriver VARCHAR(30));
 */
public class Database {
    private static Connection con ;

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
}