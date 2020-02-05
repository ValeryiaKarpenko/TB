package com.tb.dal.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tb.model.Cafe;

@Repository
public interface CafeDao extends JpaRepository<Cafe, Long>{

    Cafe getCafeByName(String name);

}
