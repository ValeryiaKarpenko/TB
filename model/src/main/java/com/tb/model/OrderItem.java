package com.tb.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tb.model.api.AbstractBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderItems", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends AbstractBaseEntity<Long>{
    
    private static final long serialVersionUID = 6444046681385401964L;
    @Id
    @Column(name = "order_item_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "order_items_id_seq", sequenceName = "order_items_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "order_items_id_seq")
    @Getter
    @Setter
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Getter
    @Setter
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_service_id")
    @Getter
    @Setter
    private CafeOffer cafeService;
    @Column(name = "count")
    @Getter
    @Setter
    private Integer count;
}
