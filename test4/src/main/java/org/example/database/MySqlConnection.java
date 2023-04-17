package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static Connection conn;

    private MySqlConnection(){
        try{
             this.conn = DriverManager.getConnection("jdbc:mysql://localhost/test4", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(conn == null){
            new MySqlConnection();
        }
        return conn;
    }


}
