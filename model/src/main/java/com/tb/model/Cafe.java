package com.tb.model;

import javax.persistence.*;

import com.tb.model.api.AbstractBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cafes", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class Cafe extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -988452508961646591L;
    @Id
    @Column(name = "cafe_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "cafe_id_seq", sequenceName = "cafe_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "cafe_id_seq")
    @Getter
    @Setter
    private Long id;
    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_blockchain_id")
    @Getter
    @Setter
    private AccountBlockchain accountBlockchain;
}
