package com.example.sampleproject;
import com.example.sampleproject.restapi.model.AbstractRule;
import com.example.sampleproject.restapi.model.ComplexRule;
import com.example.sampleproject.restapi.model.SimpleRule;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestEntities {
    AbstractRule rule;
    @Test
    void testSimpleRule() {
        rule=new SimpleRule(0,"name","field");
        String result="SELECT u FROM UserEntity u WHERE u.field > 0 AND u.field < 0";
        assertEquals(result,rule.execute(0,0));
    }
    @Test
    void testComplexRule() {
        rule=new ComplexRule(0,"name","admin","field");
        String result="SELECT u FROM UserEntity u WHERE u.field > 0 AND u.field < 0 AND u.nationality ='BG'";
        assertEquals(result,rule.execute(0,0));
    }

}
