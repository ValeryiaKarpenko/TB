package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.OrderDao;
import com.tb.model.Order;

@Repository
public class OrderDaoImpl extends AbstractDao<Order, Long> implements OrderDao{

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
