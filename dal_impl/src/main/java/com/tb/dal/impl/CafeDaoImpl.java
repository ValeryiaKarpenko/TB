package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.CafeDao;
import com.tb.model.Cafe;

@Repository
public class CafeDaoImpl extends AbstractDao<Cafe, Long> implements CafeDao{

    @Override
    public Class<Cafe> getEntityClass() {
        return Cafe.class;
    }

}
