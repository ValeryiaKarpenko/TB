package com.tb.dal.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tb.enums.UserRole;
import com.tb.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(UserRole user);

}
