package com.example.zettaonline.restapi.service;

import com.example.zettaonline.restapi.model.ExecutionRequest;
import com.example.zettaonline.restapi.dbconnector.DataBaseConnector;
import com.example.zettaonline.restapi.model.Rule;
import com.example.zettaonline.restapi.model.RuleSetModel;
import com.example.zettaonline.restapi.sqlchecker.SQLChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RuleSetService {
    private Set<RuleSetModel> ruleSetModelSet;
    private AtomicInteger idCounter;
    public static final String NO_SUCH_RULE = "NO_SUCH_RULE";
    @Autowired
    private DataBaseConnector dataBaseConnector;

    public RuleSetService() {
        this.ruleSetModelSet = new HashSet<>();
        this.idCounter = new AtomicInteger();
    }

    //GET /rules:
    public Set<RuleSetModel> getRuleSetModelSet() {
        return ruleSetModelSet;
    }

    // GET /rules/{id}:
    public RuleSetModel getRuleSetModelByID(int id) {
        Optional<RuleSetModel> optionalRuleSetModel = ruleSetModelSet
                .stream()
                .filter(ruleSetModel -> ruleSetModel.getId() == id)
                .findFirst();
        return optionalRuleSetModel.isPresent() ? optionalRuleSetModel.get() : null;
    }

    //DELETE /rules/{id}:
    public boolean removeRuleSetModelByID(int id) {
        RuleSetModel ruleSetModel = getRuleSetModelByID(id);
        if (ruleSetModel == null) {
            return false;
        }
        return ruleSetModelSet.remove(ruleSetModel);

    }

    //PUT /rules/: // I changed it a bit
    public boolean changeRuleset(int id, RuleSetModel toBeChanged) {
        RuleSetModel ruleSetModel = getRuleSetModelByID(id);
        if (ruleSetModel != null) {
            ruleSetModel.setSetName(toBeChanged.getSetName());
            ruleSetModel.setRules(toBeChanged.getRules());
            return true;
        }
        return false;

    }

    // POST /rules:
    public boolean addRuleSetModel(RuleSetModel toBeAdded) {
        boolean ruleSetPresent = isRuleSetPresent(toBeAdded);
        for (Rule r : toBeAdded.getRules()) {
            String str = r.getField();
            if (SQLChecker.containsSQL(str)) {
                // basic checker for SQL, we ofcourse need to find a better version
                // of course a real program wouldn't look like this. This is done as a demo.
                throw new IllegalArgumentException("INVALID FIELD NAME, CONTAINS SQL");
            }
        }
        // we don't want similarly named ruleSets or rulesets that contain same rules but have a different name
        if (!ruleSetPresent) {
            toBeAdded.setId(idCounter.getAndIncrement());
            ruleSetModelSet.add(toBeAdded);
        }
        return !ruleSetPresent;
    }

    private boolean isRuleSetPresent(RuleSetModel model) {
        // .equals is defined to do the same as the code on last two rows of the comment, we don't compare ID
        // because it is irrelevant in this case.:)
        //ruleSetModel.getRules().equals(model.getRules()) ||
        //ruleSetModel.getSetName().equals(model.getSetName())
        Optional<RuleSetModel> optionalRuleSetModel = ruleSetModelSet
                .stream()
                .filter(ruleSetModel -> ruleSetModel.equals(model))
                .findFirst();
        return optionalRuleSetModel.isPresent() ? true : false;
    }

    public String executeRule(ExecutionRequest executionRequest) {
        for (RuleSetModel ruleSetModel : ruleSetModelSet) {
            for (Rule rule : ruleSetModel.getRules()) {
                if (rule.matches(executionRequest.getRuleID(), executionRequest.getRuleName())) {
                    String rl = rule.execute(executionRequest.getParameter1(), executionRequest.getParameter2());
                    return dataBaseConnector.execQuery(rl);
                }
            }
        }
        return NO_SUCH_RULE;
    }
}
