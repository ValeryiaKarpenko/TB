package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.CafeOfferDao;
import com.tb.model.CafeOffer;

@Repository
public class CafeOfferDaoImpl extends AbstractDao<CafeOffer, Long> implements CafeOfferDao{

    @Override
    public Class<CafeOffer> getEntityClass() {
        return CafeOffer.class;
    }

}
