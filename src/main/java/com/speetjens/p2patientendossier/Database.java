package com.speetjens.p2patientendossier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        String host = "94.130.165.25";
        String port = "3306";
        String database = "s1367_patients";
        String user = "u1367_SoHn7Emjhd";
        String password = "vK.@6MG85g3Y8KeYdc42u^Z5";
        Connection connection = null;

        // Load the driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        System.out.println("Connecting to database");

        // Connect to the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?user=" + user + "&password=" + password);
            System.out.println("Database connected");
            return connection;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
