package com.speetjens.p2patientendossier.model;

import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patients {
    private final String firstName, lastName, email, phone, birthDate, sex, nationality, address, zip, city, country;
    private int id;

    /**
     * Patient constructor
     * @param firstName String
     * @param lastName String
     * @param email String
     * @param phone String
     * @param birthDate String
     * @param sex String
     * @param nationality String
     * @param address String
     * @param zip String
     * @param city String
     * @param country String
     */
    public Patients(String firstName, String lastName, String email, String phone, String birthDate, String sex, String nationality, String address, String zip, String city, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    /**
     * Patient constructor
     * @param result ResultSet
     */
    public Patients(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.firstName = result.getString("first_name");
            this.lastName = result.getString("last_name");
            this.email = result.getString("email");
            this.phone = result.getString("phone");
            this.birthDate = result.getString("birth_date");
            this.sex = result.getString("sex");
            this.nationality = result.getString("nationality");
            this.address = result.getString("address");
            this.zip = result.getString("zip");
            this.city = result.getString("city");
            this.country = result.getString("country");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Patients() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.birthDate = "";
        this.sex = "";
        this.nationality = "";
        this.address = "";
        this.zip = "";
        this.city = "";
        this.country = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public ArrayList<Object> getPatients() {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients");
            ResultSet result = statement.executeQuery();

            ArrayList<Object> patients = new ArrayList<>();

            while (result.next()) {
                patients.add(new Patients(result));
            }

            return patients;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean addPatients(Patients patients) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patients (first_name, last_name, email, phone, birth_date, sex, nationality, address, zip, city, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, patients.getFirstName());
            statement.setString(2, patients.getLastName());
            statement.setString(3, patients.getEmail());
            statement.setString(4, patients.getPhone());
            statement.setString(5, patients.getBirthDate());
            statement.setString(6, patients.getSex());
            statement.setString(7, patients.getNationality());
            statement.setString(8, patients.getAddress());
            statement.setString(9, patients.getZip());
            statement.setString(10, patients.getCity());
            statement.setString(11, patients.getCountry());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public Boolean removePatient(String id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM patients WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
