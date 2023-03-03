package com.speetjens.p2patientendossier.model;

import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Medecine {

    private final String name;

    /**
     * Medecine constructor
     * @param name String
     */
    public Medecine(String name) {
        this.name = name;
    }

    /**
     * Medecine constructor
     */
    public Medecine() {
        this.name = "";
    }

    public Medecine(ResultSet result) {
        try {
            this.name = result.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Object> getMedecines() {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM medecine");
            ResultSet result = statement.executeQuery();

            ArrayList<Object> medecine = new ArrayList<>();

            while (result.next()) {
                medecine.add(new Medecine(result));
            }

            return medecine;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return String
     */
    public String getMedicineName() {
        return name;
    }
}
