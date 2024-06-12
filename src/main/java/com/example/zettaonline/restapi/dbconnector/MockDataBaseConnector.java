package com.example.zettaonline.restapi.dbconnector;

import com.example.zettaonline.restapi.model.RuleSetModel;

import java.util.Set;

public class MockDataBaseConnector implements DataBaseConnectorInterface {
    @Override
    public String execQuery(String query) {
        return "Fake response";
    }

    @Override
    public void save(RuleSetModel r) {
        // do nothing :)
    }

    @Override
    public RuleSetModel findById(Integer id) {
        return new RuleSetModel();
    }

    @Override
    public Set<RuleSetModel> findAll() {
        return Set.of();
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
