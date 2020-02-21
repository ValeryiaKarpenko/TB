package com.tb.service.api;

import com.tb.dto.CafeDto;
import com.tb.enums.UserType;
import com.tb.model.Cafe;
import com.tb.model.User;

public interface CafeService {
    
    void createCafe(CafeDto cafeDto, String login);

    Cafe getCurrentCafe(Long id, User user);

    void addWaiter(Long userId, Long cafeId, String login);

    void saveRoleForUserInCafe(User user, Cafe cafe, UserType userType);

}
