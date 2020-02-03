package com.tb.model;

import javax.persistence.*;

import com.tb.model.api.AbstractBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractBaseEntity<Long>{
    
    private static final long serialVersionUID = 3436736142585895820L;
    @Id
    @Column(name = "account_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "account_id_seq")
    @Getter
    @Setter
    private Long id;
    @Column(name = "login", unique = true, nullable = false)
    @Getter
    @Setter
    private String login;
    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

}
