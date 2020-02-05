package com.tb.dal.api;

import com.tb.model.Account;

public interface AccountDao extends GenericDao<Account, Long>{

    Account getAccountByLogin(String login);
    
    Account getAccountByLoginWithUser(String login);

}
