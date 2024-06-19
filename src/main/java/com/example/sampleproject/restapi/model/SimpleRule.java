package com.example.sampleproject.restapi.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@DiscriminatorValue("simple")
public class SimpleRule extends AbstractRule {

    public SimpleRule() {
    }

    public SimpleRule(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }


    @Override
    public String execute(int parameter1, int parameter2) {
        // we have sql table
        // make it return query rather than STRING? Maybe
        return "SELECT u FROM UserEntity u WHERE u." + field + " > " + parameter1 + " AND u." + field + " < " + parameter2;
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
