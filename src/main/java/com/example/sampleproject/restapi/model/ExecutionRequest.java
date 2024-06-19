package com.example.sampleproject.restapi.model;
import java.io.Serializable;

public class ExecutionRequest  implements Serializable{
    private Integer ruleID;
    private String ruleName;
    private int parameter1;
    private int parameter2;

    public ExecutionRequest(Integer ruleID,String ruleName, int parameter1, int parameter2) {
        this.ruleID = ruleID;
        this.ruleName = ruleName;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public int getParameter1() {
        return parameter1;
    }

    public void setParameter1(int parameter1) {
        this.parameter1 = parameter1;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public int getParameter2() {
        return parameter2;
    }

    public void setParameter2(int parameter2) {
        this.parameter2 = parameter2;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
