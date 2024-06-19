package com.example.sampleproject.restapi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "rulesetmodel", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "setName"))
public class RuleSetModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "setName")// naming strategy is stupid and changes this to snakecase rather to all lowercase
    private String setName;
    @OneToMany(mappedBy = "ispartof", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AbstractRule> rules = new HashSet<>();

    public RuleSetModel() {
    }

    public Set<AbstractRule> getRules() {
        return rules;
    }

    public void setRules(Set<AbstractRule> rules) {
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
