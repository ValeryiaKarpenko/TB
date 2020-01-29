package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.AccountBlockchainDao;
import com.tb.model.AccountBlockchain;

@Repository
public class AccountBlockchainDaoImpl extends AbstractDao<AccountBlockchain, Long> implements AccountBlockchainDao{

    @Override
    public Class<AccountBlockchain> getEntityClass() {
        return AccountBlockchain.class;
    }

}
  