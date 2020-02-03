package com.tb.dal.impl;

import java.util.List;

import javax.persistence.EntityGraph;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.UserCafeDao;
import com.tb.model.UserCafe;

@Repository
public class UserCafeDaoImpl implements UserCafeDao{

    @Override
    public Class<UserCafe> getEntityClass() {
        return UserCafe.class;
    }

    @Override
    public UserCafe create(UserCafe obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(UserCafe obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<UserCafe> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserCafe> getAll(EntityGraph<UserCafe> graph) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserCafe getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserCafe getById(Long id, EntityGraph<UserCafe> graph) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(UserCafe obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteByID(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public UserCafe toReference(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


}
