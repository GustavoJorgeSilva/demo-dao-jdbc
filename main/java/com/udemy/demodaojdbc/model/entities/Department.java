package com.udemy.demodaojdbc.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private int id;
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Department: " + name + " ");
        sb.append("Id department: " + id);


        return sb.toString();
    }
}
