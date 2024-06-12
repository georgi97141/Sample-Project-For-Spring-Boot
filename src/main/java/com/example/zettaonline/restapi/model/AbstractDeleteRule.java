package com.example.zettaonline.restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
@Entity

public abstract class AbstractDeleteRule extends AbstractRule {

    @Column(name = "name" , unique = true, nullable = false)
    private String name;
    @Column(name = "field", nullable = false)
    private String field;
    public AbstractDeleteRule() {
    }
    public AbstractDeleteRule(int id, String name, String field) {
        this.id = id;
        this.name = name;
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

    @Override
    public void setId(int id) {
        this.id = id;
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
    public String execute(int parameter1, int parameter2) {
        // we have sql table
        // return query, not string
        return "DELETE FROM UserEntity u WHERE u." + field + " > :param1 AND u." + field + " < :param2";

        //return "DELETE FROM users WHERE " + field + " > " + parameter1 + " AND " + field + " < " + parameter2 + ";";
    }

    @Override
    public boolean matches(Integer id, String name) {
        return this.id == id && this.name.equals(name);
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

    @Override
    @JsonIgnore
    public RuleSetModel getRuleSet() {
        return ispartof;
    }

    @Override
    public void setRuleSet(RuleSetModel ruleSet) {
        this.ispartof=ruleSet;
    }
}
