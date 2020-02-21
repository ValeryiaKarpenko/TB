package com.tb.service.api;

import com.tb.dto.CafeOfferDto;

public interface CafeOfferService {
    
    void createCafeOffer(CafeOfferDto cafeDto, String login);

}
