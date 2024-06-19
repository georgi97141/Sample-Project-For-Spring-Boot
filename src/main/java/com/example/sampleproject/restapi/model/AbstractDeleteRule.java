package com.example.sampleproject.restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity

public abstract class AbstractDeleteRule extends AbstractRule {


    public AbstractDeleteRule() {
    }

    public AbstractDeleteRule(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }

    @Override
    public String execute(int parameter1, int parameter2) {
        // we have sql table
        // return query, not string
        return "DELETE FROM UserEntity u WHERE u." + field + " > :param1 AND u." + field + " < :param2";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDeleteRule that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, field);
    }

}
