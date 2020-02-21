package com.tb.service.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.CafeDao;
import com.tb.dal.api.UserCafeDao;
import com.tb.dto.CafeDto;
import com.tb.enums.UserRole;
import com.tb.enums.UserType;
import com.tb.model.AccountBlockchain;
import com.tb.model.Cafe;
import com.tb.model.User;
import com.tb.model.UserCafe;
import com.tb.model.UserCafeId;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.CafeService;
import com.tb.service.api.UserService;
import com.tb.service.impl.exceptions.BadDataException;
import com.tb.service.impl.exceptions.DataNotFoundException;
import com.tb.service.impl.exceptions.PermissionDeniedException;

@Service
@Transactional
public class CafeServiceImpl implements CafeService {

    private static final String NOT_FOUND_MSG = "Cafe with {0} {1} not found";

    @Autowired
    private CafeDao cafeDao;

    @Autowired
    private UserCafeDao userCafeDao;

    @Autowired
    private UserService userService;

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

        User user = userService.getCurrentUser(login);
        saveRoleForUserInCafe(user, saveCafe, UserType.OWNER);
        userService.addRole(user, UserRole.OWNER);
    }

    @Override
    public void addWaiter(Long userId, Long cafeId, String login) {
        User user = userService.getCurrentUser(login);
        Cafe cafe = getCurrentCafe(cafeId, user);
        User waiter = userService.getUserById(userId);
        userService.addRole(waiter, UserRole.WAITER);
        saveRoleForUserInCafe(waiter, cafe, UserType.WAITER);

    }

    @Override
    public Cafe getCurrentCafe(Long id, User user) {
        Cafe cafe = cafeDao.findById(id).<DataNotFoundException>orElseThrow(() -> {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "id", id));
        });
        UserCafeId userCafeIdOwner = new UserCafeId(user, cafe, UserType.OWNER);
        UserCafeId userCafeIdWaiter = new UserCafeId(user, cafe, UserType.WAITER);
        userCafeDao.findById(userCafeIdOwner)
                .orElse(userCafeDao.findById(userCafeIdWaiter).orElseThrow(PermissionDeniedException::new));
        return cafe;
    }

    @Override
    public void saveRoleForUserInCafe(User user, Cafe cafe, UserType userType) {
        UserCafeId userCafeId = new UserCafeId(user, cafe, userType);
        UserCafe userCafe = new UserCafe();
        userCafe.setUserCafeId(userCafeId);
        userCafeDao.save(userCafe);
    }
}
