package com.tb.service.api;

import com.tb.dto.CafeDto;

public interface CafeService {
    
    void createCafe(CafeDto cafeDto, String login);

}
