package com.example.zettaonline.restapi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleRule.class, name = "simple"),
        @JsonSubTypes.Type(value = ComplexRule.class, name = "complex"),
        @JsonSubTypes.Type(value = AgeDeleteRule.class, name = "ageDelete")
})
public interface Rule {
    String execute(int parameter1, int parameter2);

    boolean matches(Integer id, String name);

    String getField();

    void setField(String field);

    String getName();

    void setName(String field);

    int getId();

    void setId(int id);

    RuleSetModel getRuleSet();

    void setRuleSet(RuleSetModel ruleSet);
}
