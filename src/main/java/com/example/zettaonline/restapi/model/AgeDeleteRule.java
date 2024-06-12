package com.example.zettaonline.restapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("agedelete")
public class AgeDeleteRule extends AbstractDeleteRule {
    public AgeDeleteRule() {
    }
    // we could have something more but I couldn't think of.
    // Done to demonstrate basic understanding of Abstraction in OOP.
    public AgeDeleteRule(int id, String name) {
        super(id, name, "age");
    }
}
