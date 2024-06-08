package com.example.zettaonline.restapi.dbconnector;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataBaseConnector implements DataBaseConnectorInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String execQuery(String sql) {
        try {
            Query query = entityManager.createNativeQuery(sql);
            // rather use JDBC or create entities, that is a bit of a bad practice :/
            StringBuilder stringBuilder = new StringBuilder();
            List<Object[]> resultList = query.getResultList();
            String[] columns = {"id","age","nationality","salary"};
            //maybe we need to check here for Nulls or different formatted data but this is just a demo :)
            for (Object[] row : resultList) {
                int rowCounter = 0;
                for (Object obj : row) {
                    stringBuilder.append(columns[rowCounter] + ":" + obj.toString() + "\n");
                    rowCounter++;
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }
}
