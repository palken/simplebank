package no.ntnu.idi.simplebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSingleton {

    private static DatabaseConnectionSingleton instance = null;
    private static Connection databaseconnection;

    private DatabaseConnectionSingleton() {
        try {
            Class.forName("org.sqlite.JDBC");
            databaseconnection = DriverManager.getConnection("jdbc:sqlite:simplebank.db");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnectionSingleton getDatabaseInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return databaseconnection;
    }

}
