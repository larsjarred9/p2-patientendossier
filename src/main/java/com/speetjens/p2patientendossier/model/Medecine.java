package com.speetjens.p2patientendossier.model;

import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Medecine {

    private final String name, treats, categories, storage, dosage;

    /**
     * Medecine constructor
     * @param name String
     */
    public Medecine(String name, String treats, String categories, String storage, String dosage) {

        this.name = name;
        this.treats = treats;
        this.categories = categories;
        this.storage = storage;
        this.dosage = dosage;
    }

    /**
     * Medecine constructor
     */
    public Medecine() {

        this.name = "";
        this.treats = "";
        this.categories = "";
        this.storage = "";
        this.dosage = "";
    }

    public Medecine(ResultSet result) {
        try {
            this.name = result.getString("name");
            this.treats = result.getString("treats");
            this.categories = result.getString("categories");
            this.storage = result.getString("storage");
            this.dosage = result.getString("dosage");
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
    public String getName() {
        return name;
    }

    public String getTreats() {
        return treats;
    }

    public String getCategories() {
        return categories;
    }

    public String getStorage() {
        return storage;
    }

    public String getDosage() {
        return dosage;
    }
}
