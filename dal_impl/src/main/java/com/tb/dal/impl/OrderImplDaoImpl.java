package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.OrderItemDao;
import com.tb.model.OrderItem;

@Repository
public class OrderImplDaoImpl extends AbstractDao<OrderItem, Long> implements OrderItemDao{

    @Override
    public Class<OrderItem> getEntityClass() {
        return OrderItem.class;
    }

}
