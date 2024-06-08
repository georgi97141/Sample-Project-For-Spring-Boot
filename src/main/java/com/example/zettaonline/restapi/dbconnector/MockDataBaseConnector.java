package com.example.zettaonline.restapi.dbconnector;

public class MockDataBaseConnector implements DataBaseConnectorInterface {
    @Override
    public String execQuery(String query) {
        return "Fake response";
    }
}
