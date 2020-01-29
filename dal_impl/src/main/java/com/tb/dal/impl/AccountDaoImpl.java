package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.AccountDao;
import com.tb.model.Account;

@Repository
public class AccountDaoImpl extends AbstractDao<Account, Long> implements AccountDao{

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

}
