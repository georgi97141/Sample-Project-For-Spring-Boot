package com.example.zettaonline;

import com.example.zettaonline.restapi.model.ExecutionRequest;
import com.example.zettaonline.restapi.model.ComplexRule;
import com.example.zettaonline.restapi.model.Rule;
import com.example.zettaonline.restapi.model.RuleSetModel;
import com.example.zettaonline.restapi.model.SimpleRule;
import com.example.zettaonline.restapi.service.RuleSetService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;

class ZettaOnlineApplicationTests {

    private RuleSetService ruleSetService;
    private RuleSetModel model;

    void loadData() {
        ruleSetService=new RuleSetService();
        model=new RuleSetModel();
        model.setSetName("model1");
        Rule rule = new ComplexRule(0,"name1","admin","salary");
        Rule rule2 = new SimpleRule(1,"name2","age");
        Set<Rule> rules = new HashSet<>();
        rules.add(rule);
        rules.add(rule2);
        model.setRules(rules);
        ruleSetService.addRuleSetModel(model);
    }
    @Test
    void addRulesetExisting(){
        loadData();
        assertEquals(false,ruleSetService.addRuleSetModel(model));
    }
    @Test
    void getRuleSetModelByID() {
        loadData();
        assertEquals(model.getSetName(),ruleSetService.getRuleSetModelByID(0).getSetName());
    }

    @Test
    void getRuleSetCount() {
        loadData();
        assertEquals(1,ruleSetService.getRuleSetModelSet().size());
    }

    @Test
    void deleteRuleSetByID() {
        loadData();
        ruleSetService.removeRuleSetModelByID(0);
        assertEquals(0,ruleSetService.getRuleSetModelSet().size());
    }
    @Test
    void deleteRuleSetNonExisting() {
        loadData();
        boolean wasRemoved= ruleSetService.removeRuleSetModelByID(9999);
        assertEquals(false,wasRemoved);
        assertEquals(1,ruleSetService.getRuleSetModelSet().size());
    }

    @Test
    void updateRuleSet() {
        loadData();
        assertEquals("model1",ruleSetService.getRuleSetModelByID(0).getSetName());
        RuleSetModel model2 = new RuleSetModel();
        model2.setSetName("myName");
        ruleSetService.changeRuleset(model2.getId(),model2);
        assertEquals("myName",ruleSetService.getRuleSetModelByID(0).getSetName());
    }

    @Test
    void executeRulesNonExisting() {
        loadData();
        ExecutionRequest executionRequest= new ExecutionRequest(0,"none",100,200);
        assertEquals("NO_SUCH_RULE",ruleSetService.executeRule(executionRequest));
    }
}
