package com.example.zettaonline.restapi.dbconnector;

import com.example.zettaonline.restapi.model.RuleSetModel;
import com.example.zettaonline.restapi.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DataBaseConnector implements DataBaseConnectorInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String execQuery(String quary) {
        try {
            TypedQuery<UserEntity> query = entityManager.createQuery(quary, UserEntity.class);
            return query.getResultList().toString();
        } catch (Exception e) {
            throw   new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public void save(RuleSetModel r) {
        entityManager.merge(r);
    }

    @Override
    public RuleSetModel findById(Integer id) {
        return entityManager.find(RuleSetModel.class, id);
    }

    @Override
    public Set<RuleSetModel> findAll() {
        TypedQuery<RuleSetModel> query = entityManager.createQuery("SELECT r FROM RuleSetModel r", RuleSetModel.class);
        return new HashSet<>(query.getResultList());

    }

    @Override
    public boolean deleteById(Integer id) {
        RuleSetModel ruleSetModel = findById(id);
        if (ruleSetModel != null) {
            entityManager.remove(ruleSetModel);
            return true;
        }
        return false;
    }
}
