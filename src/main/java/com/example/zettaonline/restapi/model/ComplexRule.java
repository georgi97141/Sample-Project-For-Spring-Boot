package com.example.zettaonline.restapi.model;

import java.io.Serializable;
import java.util.Objects;

public class ComplexRule implements Rule, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String privileges;
    private String nationality;
    private String field;

    public ComplexRule(int id, String name, String privileges, String field) {
        this.id = id;
        this.name = name;
        this.privileges = privileges;
        nationality = "'BG'";
        this.field = field;
    }

    @Override
    public String execute(int parameter1, int parameter2) {
        // no string but query return, would be proper
        if (privileges.equals("admin"))
            return "SELECT * FROM users WHERE " + field + " > " + parameter1 + " AND " + field + " < " + parameter2 + " AND nationality= " + nationality + ";";
        return "SELECT * FROM users WHERE " + field + " > " + parameter1 + " AND " + field + " < " + parameter2 + ";";
    }

    @Override
    public boolean matches(Integer id, String name) {
        return this.id == id && this.name.equals(name);
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public void setField(String field) {
        this.field = field;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexRule that)) return false;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(privileges, that.privileges) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, privileges, field);
    }
}
