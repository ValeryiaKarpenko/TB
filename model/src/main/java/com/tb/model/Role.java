
package com.tb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.tb.enums.UserRole;
import com.tb.model.api.AbstractBaseEntity;

@Entity
@Table(name = "roles", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "role_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "roles_id_seq")
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    private UserRole name;

}
