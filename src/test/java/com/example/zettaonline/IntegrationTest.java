package com.example.zettaonline;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.zettaonline.restapi.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private RuleSetModel ruleSet;


    @BeforeEach
    public void setUp() throws Exception {
        ruleSet = new RuleSetModel();
        ruleSet.setId(0);
        ruleSet.setSetName("ExampleRuleSet");

        Set<AbstractRule> rules = new HashSet<>();
        rules.add(new SimpleRule(1, "rule1", "age"));
        rules.add(new ComplexRule(0, "crule1", "admin", "age"));

        ruleSet.setRules(rules);
    }

    @Test
    public void putRuleSet() throws Exception {
        mockMvc.perform(post("/api/rules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ruleSet)))
                .andExpect(status().isCreated());

        RuleSetModel newRuleSet = new RuleSetModel();
        newRuleSet.setId(0);
        newRuleSet.setSetName("ExampleRuleSet");

        Set<AbstractRule> rules = new HashSet<>();
        rules.add(new SimpleRule(1, "rule1", "salary"));
        rules.add(new ComplexRule(1, "crule1", "admin", "salary"));
        newRuleSet.setRules(rules);

        mockMvc.perform(put("/api/rules/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newRuleSet))).andExpect(status().is(200));

    }

    @Test
    public void testAddAndDeleteRuleSet() throws Exception {
        mockMvc.perform(post("/api/rules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ruleSet)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/rules/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteWithoutAdd() throws Exception {
        mockMvc.perform(delete("/api/rules/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void testAddRuleSet() throws Exception {
        mockMvc.perform(post("/api/rules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ruleSet)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddRuleSetExisting() throws Exception {
        String str = objectMapper.writeValueAsString(ruleSet);

        mockMvc.perform(post("/api/rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str));

        mockMvc.perform(post("/api/rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str)).andExpect(status().is(409));

    }

    @Test
    public void getRuleSets() throws Exception {
        String str = objectMapper.writeValueAsString(ruleSet);

        mockMvc.perform(post("/api/rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str));

        MvcResult result = mockMvc.perform(get("/api/rules/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str)).andExpect(status().is(200)).andReturn();
        JsonNode expectedJson = objectMapper.readTree(str);

        assertEquals(expectedJson, objectMapper.readTree(result.getResponse().getContentAsString()));

    }

    @Test
    public void executeRules() throws Exception {
        // a bit of bad practice because we make a connection to DB. maybe we need to rollback from db or a flag;
        String str = objectMapper.writeValueAsString(ruleSet);
        ExecutionRequest executionRequest = new ExecutionRequest(1, "rule1", 12, 23);
        List<ExecutionRequest> executionRequestList = Arrays.asList(executionRequest);
        mockMvc.perform(post("/api/rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str));
        mockMvc.perform(post("/api/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(executionRequestList))).andExpect(status().is(200));
    }

}
