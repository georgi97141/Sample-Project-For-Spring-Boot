package com.example.zettaonline.restapi.model;


import java.io.Serializable;
import java.util.Objects;


public class SimpleRule implements Rule, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String field;

    public SimpleRule(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }


    @Override
    public String execute(int parameter1, int parameter2) {
        // we have sql table
        // make it return query rather than STRING? Maybe
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleRule that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, field);
    }
}
