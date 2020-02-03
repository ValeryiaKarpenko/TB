package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.CafeDao;
import com.tb.dto.CafeDto;
import com.tb.model.AccountBlockchain;
import com.tb.model.Cafe;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.CafeService;
import com.tb.service.impl.exceptions.BadDataException;

@Service
@Transactional
public class CafeServiceImpl implements CafeService{
    
    @Autowired
    private CafeDao cafeDao;
    
    @Autowired
    private AccountBlockchainService accountBlockchainService;

    @Override
    public void createCafe(CafeDto cafeDto) {
        //из токена взять пользователя
        if (cafeDao.getCafeByName(cafeDto.getName()) != null) {
            throw new BadDataException("Name already taken!");
        }
        AccountBlockchain newAccountBlockchain = accountBlockchainService.createAccountBlockchain(cafeDto.getBalance());
        Cafe newCafe = new Cafe();
        newCafe.merge(cafeDto);
        newCafe.setAccountBlockchain(newAccountBlockchain);
        cafeDao.saveAndFlush(newCafe);
    }
}
