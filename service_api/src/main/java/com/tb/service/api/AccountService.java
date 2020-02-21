package com.tb.service.api;

import com.tb.dto.UserDto;
import com.tb.model.User;

public interface AccountService {

    void createAccount(UserDto userDto, User newUser);

}
