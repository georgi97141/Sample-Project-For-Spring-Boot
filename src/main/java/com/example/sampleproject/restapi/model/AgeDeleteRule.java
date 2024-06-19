package com.example.sampleproject.restapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("agedelete")
public class AgeDeleteRule extends AbstractDeleteRule {

    public AgeDeleteRule() {
    }

    public AgeDeleteRule(int id, String name) {
        super(id, name, "age");
    }
}
