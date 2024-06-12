package com.example.zettaonline.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rule_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "rules", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AbstractRule implements Rule,Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreignkey")
    @JsonIgnore
    protected RuleSetModel ispartof;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    public AbstractRule() {
    }

    @Override
    public String execute(int parameter1, int parameter2) {
        return "";
    }

    @Override
    public boolean matches(Integer id, String name) {
        return false;
    }

    @Override
    public String getField() {
        return "";
    }

    @Override
    public void setField(String field) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String field) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    @JsonIgnore
    public RuleSetModel getRuleSet() {
        return this.ispartof;
    }

    @Override
    public void setRuleSet(RuleSetModel ruleSet) {
            this.ispartof=ruleSet;
    }
}
