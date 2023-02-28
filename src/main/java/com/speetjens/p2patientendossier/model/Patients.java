package com.speetjens.p2patientendossier.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patients {

    private final String firstName, lastName, email, phone, birthDate, sex, nationality, address, zip, city, country;


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
     * Film constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */
    public Patients(ResultSet result) throws SQLException {
        this.firstName = result.getString("firstName");
        this.lastName = result.getString("lastName");
        this.email = result.getString("email");
        this.phone = result.getString("phone");
        this.birthDate = result.getString("birthDate");
        this.sex = result.getString("sex");
        this.nationality = result.getString("nationality");
        this.address = result.getString("address");
        this.zip = result.getString("zip");
        this.city = result.getString("city");
        this.country = result.getString("country");
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
