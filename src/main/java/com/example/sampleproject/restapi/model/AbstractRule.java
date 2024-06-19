package com.example.sampleproject.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rule_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "rules", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AbstractRule implements Rule, Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreignkey")
    @JsonIgnore
    protected RuleSetModel ispartof;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "field", nullable = false)
    protected String field;
    @Column(name = "name", unique = true, nullable = false)
    protected String name;

    public AbstractRule() {
    }

    @Override
    public String execute(int parameter1, int parameter2) {
        return "";
    }

    @Override
    public boolean matches(Integer id, String name) {
        return this.id == id && this.name.equals(name);
    }

    @Override
    public String getField() {
        return this.field;
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
    public void setName(String field) {
        this.name = field;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public RuleSetModel getRuleSet() {
        return this.ispartof;
    }

    @Override
    public void setRuleSet(RuleSetModel ruleSet) {
        this.ispartof = ruleSet;
    }
}
