package com.example.zettaonline.restapi.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;

public class RuleSetModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String setName;
    private Set<Rule> rules = new HashSet<>();

    public RuleSetModel() {
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        // here we don't compare IDs because having similar IDs is quite impossible,
        //rather we want to check if we have same content :)
        if (this == o) return true;
        if (!(o instanceof RuleSetModel that)) return false;
        return Objects.equals(setName, that.setName) && Objects.equals(rules, that.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setName, rules);
    }

}
