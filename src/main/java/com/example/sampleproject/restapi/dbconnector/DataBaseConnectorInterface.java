package com.example.sampleproject.restapi.dbconnector;

import com.example.sampleproject.restapi.model.RuleSetModel;

import java.util.Set;

public interface DataBaseConnectorInterface {
 String execQuery(String query);
 void save(RuleSetModel model);
 RuleSetModel findById(Integer id);
 Set<RuleSetModel> findAll();
 boolean deleteById(Integer id);
}
