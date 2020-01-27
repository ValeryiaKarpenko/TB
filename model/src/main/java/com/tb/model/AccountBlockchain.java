package com.tb.model;

import javax.persistence.*;

import com.tb.model.api.AbstractBaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts_blockchain", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class AccountBlockchain extends AbstractBaseEntity<Long>{
    
    private static final long serialVersionUID = -6453789569709722647L;
    @Id
    @Column(name = "account_blockchain_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "account_blockchain_id_seq", sequenceName = "account_blockchain_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "account_blockchain_id_seq")
    @Getter
    @Setter
    private Long id;
    @Column(name = "address",unique = true, updatable = false)
    @Getter
    @Setter
    private String address;
    @Column(name = "mnemonic", updatable = false)
    @Getter
    @Setter
    private String mnemonic;
    @Column(name = "balance")
    @Getter
    @Setter
    private Integer balance;
 
}
