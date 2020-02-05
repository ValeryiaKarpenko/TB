package com.tb.dal.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tb.model.UserCafe;
import com.tb.model.UserCafeId;

public interface UserCafeDao extends JpaRepository<UserCafe, UserCafeId>{

}
