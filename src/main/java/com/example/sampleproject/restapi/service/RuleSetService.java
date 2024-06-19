package com.example.sampleproject.restapi.service;

import com.example.sampleproject.restapi.dbconnector.DataBaseConnectorInterface;
import com.example.sampleproject.restapi.model.AbstractRule;
import com.example.sampleproject.restapi.model.ExecutionRequest;
import com.example.sampleproject.restapi.model.Rule;
import com.example.sampleproject.restapi.model.RuleSetModel;
import com.example.sampleproject.restapi.sqlchecker.SQLChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RuleSetService {
    private Set<RuleSetModel> ruleSetModelSet;
    private AtomicInteger idCounter;
    public static final String NO_SUCH_RULE = "NO_SUCH_RULE";
    @Autowired
    private DataBaseConnectorInterface dataBaseConnector;

    public RuleSetService() {
        this.ruleSetModelSet = new HashSet<>();
        this.idCounter = new AtomicInteger();
    }

    //GET /rules:
    @Transactional
    public Set<RuleSetModel> getRuleSetModelSet() {
        return dataBaseConnector.findAll();
    }

    // GET /rules/{id}:
    @Transactional
    public RuleSetModel getRuleSetModelByID(int id) {
        return dataBaseConnector.findById(id);
    }

    //DELETE /rules/{id}:
    @Transactional
    public boolean removeRuleSetModelByID(int id) {
        RuleSetModel ruleSetModel = getRuleSetModelByID(id);
        if (ruleSetModel == null) {
            return false;
        }
        return dataBaseConnector.deleteById(id);

    }

    //PUT /rules/: // I changed it a bit
    @Transactional
    public boolean changeRuleset(int id, RuleSetModel toBeChanged) {
        RuleSetModel ruleSetModel = getRuleSetModelByID(id);
        if (ruleSetModel != null) {
            ruleSetModel.setId(id);
            ruleSetModel.setSetName(toBeChanged.getSetName());
            ruleSetModel.getRules().clear();
            ruleSetModel.getRules().addAll(toBeChanged.getRules());
            dataBaseConnector.save(ruleSetModel);
            return true;
        }
        return false;

    }

    // POST /rules:
    @Transactional
    public boolean addRuleSetModel(RuleSetModel toBeAdded) {
        ruleSetModelSet =getRuleSetModelSet();

        boolean ruleSetPresent = isRuleSetPresent(toBeAdded);
        for (AbstractRule r : toBeAdded.getRules()) {
            r.setRuleSet(toBeAdded);
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
            dataBaseConnector.save(toBeAdded);
        }
        return !ruleSetPresent;
    }
    @Transactional
    private boolean isRuleSetPresent(RuleSetModel model) {
        // .equals is defined to do the same as the code on last two rows of the comment, we don't compare ID
        // because it is irrelevant in this case.:)
        //ruleSetModel.getRules().equals(model.getRules()) ||
        //ruleSetModel.getSetName().equals(model.getSetName())
        ruleSetModelSet= dataBaseConnector.findAll();
        Optional<RuleSetModel> optionalRuleSetModel = ruleSetModelSet
                .stream()
                .filter(ruleSetModel -> ruleSetModel.getSetName().equals(model.getSetName())
                        || model.getRules().stream().anyMatch(r -> ruleSetModel.getRules().contains(r))
                        || ruleSetModelSet.equals(model))
                .findFirst();
        return optionalRuleSetModel.isPresent() ? true : false;

    }@Transactional
    public String executeRule(ExecutionRequest executionRequest) {
        ruleSetModelSet=getRuleSetModelSet();
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
