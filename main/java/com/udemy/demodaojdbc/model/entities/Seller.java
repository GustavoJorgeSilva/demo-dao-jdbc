package com.udemy.demodaojdbc.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

    private int id;
    private String name;
    private String email;
    private Date birthDate;
    private double baseSalary;

    private Department department;

    public Seller() {
    }

    public Seller(int id, String name, String email, Date birthDate, double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return getId() == seller.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Seller informations: \n");
        sb.append("Name: " + name);
        sb.append(" Email: " + email);
        sb.append(" Birth Date: " + birthDate);
        sb.append(" Base Salary: " + baseSalary);
        sb.append(" Department: " + department);


        return sb.toString();
    }
}
