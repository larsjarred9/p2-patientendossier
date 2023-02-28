package com.speetjens.p2patientendossier.model;

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
     * Film constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */
    public Users(ResultSet result) throws SQLException {
        this.username = result.getString("username");
        this.password = result.getString("password");
        this.role = result.getString("role");
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // compare password
    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }
}
