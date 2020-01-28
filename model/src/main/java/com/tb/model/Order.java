package com.tb.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tb.model.api.AbstractBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractBaseEntity<Long>{
    
    private static final long serialVersionUID = 8436605035484292605L;
    @Id
    @Column(name = "order_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "orders_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "orders_id_seq")
    @Getter
    @Setter
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "water_id")
    @Getter
    @Setter
    private User waiter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    @Getter
    @Setter
    private Cafe cafe;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<OrderItem> orderItem;
    @Column(name = "amount")
    @Getter
    @Setter
    private Integer amount;
}
