package com.example.zettaonline;
import com.example.zettaonline.restapi.model.AbstractRule;
import com.example.zettaonline.restapi.model.ComplexRule;
import com.example.zettaonline.restapi.model.RuleSetModel;
import com.example.zettaonline.restapi.model.SimpleRule;
import com.example.zettaonline.restapi.service.RuleSetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

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
