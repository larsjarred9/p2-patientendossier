package com.speetjens.p2patientendossier.model;

import com.speetjens.p2patientendossier.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patients {

    private final String firstName, lastName, email, phone, birthDate, sex, nationality, address, zip, city, country;

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
}
