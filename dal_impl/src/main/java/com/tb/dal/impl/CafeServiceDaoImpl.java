package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.CafeServiceDao;
import com.tb.model.CafeService;

@Repository
public class CafeServiceDaoImpl extends AbstractDao<CafeService, Long> implements CafeServiceDao{

    @Override
    public Class<CafeService> getEntityClass() {
        return CafeService.class;
    }

}
