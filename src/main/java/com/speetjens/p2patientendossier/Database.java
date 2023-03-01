package com.speetjens.p2patientendossier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection conn;

    public Database() {
        String user = "root";
        String passwd = "root";
        String cString = "jdbc:mysql://localhost:8889/winkeldb?user=" + user + "&password=" + passwd;

        try {
            this.conn = DriverManager.getConnection(cString);
        } catch (SQLException var5) {
            System.out.println("Could not establish database connection!");
        }

    }
}