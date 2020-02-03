package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.AccountDao;
import com.tb.model.Account;
import com.tb.model.Account_;

@Repository
public class AccountDaoImpl extends AbstractDao<Account, Long> implements AccountDao{

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account getAccountByLogin(String login) {
        return getRecordByField(Account_.login, login, null);
    }

}
