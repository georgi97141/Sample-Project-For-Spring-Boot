package com.example.zettaonline.restapi.model;

public class AgeDeleteRule extends AbstractDeleteRule {
    // we could have something more but I couldn't think of.
    // Done to demonstrate basic understanding of Abstraction in OOP.
    public AgeDeleteRule(int id, String name) {
        super(id, name, "age");
    }
}
