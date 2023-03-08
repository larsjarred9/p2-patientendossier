package com.speetjens.p2patientendossier.model;

import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Allergies {

    public final String name;

    /**
     * Allergies constructor
     * @param name String
     */

    public Allergies(String name) {
        this.name = name;
    }

    /**
     * Allergies constructor
     */
    public Allergies() {
        this.name = "";
    }

    public Allergies(ResultSet result) {
        try {
            this.name = result.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Object> getAllergies() {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM allergies");
            ResultSet result = statement.executeQuery();

            ArrayList<Object> allergies = new ArrayList<>();

            while (result.next()) {
                allergies.add(new Allergies(result));
            }

            return allergies;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public Boolean addAllergy(Allergies allergies) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO allergies (name) VALUES (?)");
            statement.setString(1, allergies.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
