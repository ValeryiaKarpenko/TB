package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.CafeOfferDao;
import com.tb.dto.CafeOfferDto;
import com.tb.model.Cafe;
import com.tb.model.CafeOffer;
import com.tb.model.User;
import com.tb.service.api.CafeOfferService;
import com.tb.service.api.CafeService;
import com.tb.service.api.UserService;

@Service
@Transactional
public class CafeOfferServiceImpl implements CafeOfferService{
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CafeService cafeService;
    
    @Autowired
    private CafeOfferDao cafeOfferDao;

    @Override
    public void createCafeOffer(CafeOfferDto cafeOfferDto, String login) {
        User user = userService.getCurrentUser(login);
        Cafe cafe = cafeService.getCurrentCafe(cafeOfferDto.getCafeId(), user);
        CafeOffer cafeOffer = new CafeOffer();
        cafeOffer.merge(cafeOfferDto);
        cafeOffer.setCafe(cafe);
        cafeOfferDao.create(cafeOffer);
    }

}
