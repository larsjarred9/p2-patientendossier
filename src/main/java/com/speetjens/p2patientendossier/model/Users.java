package com.speetjens.p2patientendossier.model;
import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {

    private final String username, password, role;

    /**
     * @param username String
     * @param password String
     * @param role String
     */
    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * User constructor
     */
    public Users() {
        this.username = "";
        this.password = "";
        this.role = "";
    }

    public Users(ResultSet result) {
        try {
            this.username = result.getString("username");
            this.password = result.getString("password");
            this.role = result.getString("role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return String
     */
    public String getRole() {
        return role;
    }


    /**
     * Password validator
     * @param username String
     * @param password String
     * @return boolean
     */
    public boolean comparePassword(String username, String password) {

        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Users user = new Users(result);
                return user.password.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
