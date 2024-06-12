package com.example.zettaonline.restapi.dbconnector;

import com.example.zettaonline.restapi.model.RuleSetModel;

import java.util.HashSet;
import java.util.Set;

public class MockDataBaseConnector implements DataBaseConnectorInterface {
    Set<RuleSetModel> setModels = new HashSet<>();

    @Override
    public String execQuery(String query) {
        return "Sample Response";
    }

    @Override
    public void save(RuleSetModel r) {
        // do nothing :)
        setModels.add(r);
    }

    @Override
    public RuleSetModel findById(Integer id) {
        for (RuleSetModel rs : setModels) {
            if (rs.getId() == id) {
                return rs;
            }
        }
        return null;
    }

    @Override
    public Set<RuleSetModel> findAll() {
        return setModels;
    }

    @Override
    public boolean deleteById(Integer id) {
        RuleSetModel rs = findById(id);
        if (rs != null) {
            setModels.remove(rs);
            return true;
        }
        return false;
    }
}
