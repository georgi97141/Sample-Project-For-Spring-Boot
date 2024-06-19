package com.example.sampleproject.restapi.controller;

import com.example.sampleproject.restapi.model.ExecutionRequest;
import com.example.sampleproject.restapi.model.RuleSetModel;
import com.example.sampleproject.restapi.service.RuleSetService;
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
    private final String REQUEST_SUCCESFUL = "Request Succesful";

    @PostMapping("/rules")
    public ResponseEntity<Response> createRuleSet(@RequestBody RuleSetModel ruleSet) {
        boolean createdRuleSet = ruleSetService.addRuleSetModel(ruleSet);
        return createdRuleSet ?
                new ResponseEntity<>(new Response<>(true,RULESET_CREATED,ruleSet), HttpStatus.CREATED) :
                new ResponseEntity<>(new Response<>(false,RULESET_ALREADY_EXISTS,null) , HttpStatus.CONFLICT);
    }

    @GetMapping("/rules")
    public ResponseEntity<Response> getAllRuleSets() {
        Set<RuleSetModel> ruleSets = ruleSetService.getRuleSetModelSet();
        return new ResponseEntity<>(new Response<>(true,REQUEST_SUCCESFUL,ruleSets), HttpStatus.OK);
    }

    @GetMapping("/rules/{id}")
    public ResponseEntity<Response> getRuleSetById(@PathVariable Integer id) {
        RuleSetModel ruleSet = ruleSetService.getRuleSetModelByID(id);
        return ruleSet != null ?
                new ResponseEntity<>(new Response<>(true, REQUEST_SUCCESFUL,ruleSet), HttpStatus.OK) :
                new ResponseEntity<>(new Response<>(false,RULESET_NOT_FOUND,null),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/rules/{id}")
    public ResponseEntity<Response> updateRuleSet(@PathVariable Integer id,@RequestBody RuleSetModel updatedRuleSet) {
        boolean updated = ruleSetService.changeRuleset(id,updatedRuleSet);

        return updated ?
                new ResponseEntity<>(new Response<>(true,RULESET_UPDATED,updated), HttpStatus.OK) :
                new ResponseEntity<>(new Response<>(false,RULESET_NOT_FOUND,null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/rules/{id}")
    public ResponseEntity<Response> deleteRuleSet(@PathVariable Integer id) {
        boolean deleted = ruleSetService.removeRuleSetModelByID(id);
        return deleted ?
                new ResponseEntity<>(new Response<>(true,RULESET_DELETED,null), HttpStatus.OK)
                : new ResponseEntity<>(new Response<>(true,RULESET_NOT_FOUND,null), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/execute")
    public ResponseEntity<Response> executeRules(@RequestBody List<ExecutionRequest> requests) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ExecutionRequest request : requests) {
            String ruleExecuted = ruleSetService.executeRule(request);
            if (ruleExecuted.equals(RuleSetService.NO_SUCH_RULE)) {
                return new ResponseEntity<>(new Response<>(false,RULESET_NOT_FOUND,null), HttpStatus.NOT_FOUND);
            }
              stringBuilder.append(ruleExecuted);
        }
        return new ResponseEntity<>(new Response<>(true,REQUEST_SUCCESFUL,stringBuilder), HttpStatus.OK);
    }

}
