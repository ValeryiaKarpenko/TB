package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.AccountDao;
import com.tb.dal.api.CafeDao;
import com.tb.dal.api.UserCafeDao;
import com.tb.dto.CafeDto;
import com.tb.enums.UserType;
import com.tb.model.AccountBlockchain;
import com.tb.model.Cafe;
import com.tb.model.User;
import com.tb.model.UserCafe;
import com.tb.model.UserCafeId;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.CafeService;
import com.tb.service.impl.exceptions.BadDataException;

@Service
@Transactional
public class CafeServiceImpl implements CafeService {

    @Autowired
    private CafeDao cafeDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserCafeDao userCafeDao;

    @Autowired
    private AccountBlockchainService accountBlockchainService;

    @Override
    public void createCafe(CafeDto cafeDto, String login) {
        if (cafeDao.getCafeByName(cafeDto.getName()) != null) {
            throw new BadDataException("Name already taken!");
        }
        AccountBlockchain newAccountBlockchain = accountBlockchainService.createAccountBlockchain();
        Cafe newCafe = new Cafe();
        newCafe.merge(cafeDto);
        newCafe.setAccountBlockchain(newAccountBlockchain);
        Cafe saveCafe = cafeDao.save(newCafe);

        User user = accountDao.getAccountByLoginWithUser(login).getUser();
        UserCafeId userCafeId = new UserCafeId(user, saveCafe, UserType.OWNER);
        UserCafe userCafe = new UserCafe();
        userCafe.setUserCafeId(userCafeId);
        userCafeDao.save(userCafe);
    }
}
