package com.example.zettaonline.restapi.controller;

import com.example.zettaonline.restapi.model.ExecutionRequest;
import com.example.zettaonline.restapi.model.RuleSetModel;
import com.example.zettaonline.restapi.service.RuleSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RuleSetController {
    @Autowired
    private RuleSetService ruleSetService;

    private final String RULESET_NOT_FOUND = "Ruleset not found";
    private final String RULESET_CREATED = "Ruleset successfully created";
    private final String RULESET_DELETED = "Ruleset successfully deleted";
    private final String RULESET_UPDATED = "Ruleset successfully updated";
    private final String RULESET_ALREADY_EXISTS = "Ruleset Already Exists";

    @PostMapping("/rules")
    public ResponseEntity<String> createRuleSet(@RequestBody RuleSetModel ruleSet) {
        boolean createdRuleSet = ruleSetService.addRuleSetModel(ruleSet);
        return createdRuleSet ?
                new ResponseEntity<>(RULESET_CREATED, HttpStatus.CREATED) :
                new ResponseEntity<>(RULESET_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @GetMapping("/rules")
    public ResponseEntity<Set<RuleSetModel>> getAllRuleSets() {
        Set<RuleSetModel> ruleSets = ruleSetService.getRuleSetModelSet();
        return new ResponseEntity<>(ruleSets, HttpStatus.OK);
    }

    @GetMapping("/rules/{id}")
    public ResponseEntity<RuleSetModel> getRuleSetById(@PathVariable Integer id) {
        RuleSetModel ruleSet = ruleSetService.getRuleSetModelByID(id);
        return ruleSet != null ?
                new ResponseEntity<>(ruleSet, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/rules/{id}")
    public ResponseEntity<String> updateRuleSet(@PathVariable Integer id,@RequestBody RuleSetModel updatedRuleSet) {
        boolean updated = ruleSetService.changeRuleset(id,updatedRuleSet);

        return updated ?
                new ResponseEntity<>(RULESET_UPDATED, HttpStatus.OK) :
                new ResponseEntity<>(RULESET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/rules/{id}")
    public ResponseEntity<String> deleteRuleSet(@PathVariable Integer id) {
        boolean deleted = ruleSetService.removeRuleSetModelByID(id);
        return deleted ?
                new ResponseEntity<>(RULESET_DELETED, HttpStatus.OK)
                : new ResponseEntity<>(RULESET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeRules(@RequestBody List<ExecutionRequest> requests) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ExecutionRequest request : requests) {
            stringBuilder.append("ruleID:" + request.getRuleID()+"\n");
            stringBuilder.append("ruleName:" + request.getRuleName()+"\n");
            String ruleExecuted = ruleSetService.executeRule(request);
            if (ruleExecuted.equals(RuleSetService.NO_SUCH_RULE)) {
                return new ResponseEntity<>(RULESET_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            stringBuilder.append("Result of the execution:" + request.getRuleName()+"\n");
            stringBuilder.append(ruleExecuted+"\n");

        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

}
