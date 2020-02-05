package com.tb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.tb.dto.UserDto;
import com.tb.model.Role;
import com.tb.model.User;
import com.tb.model.api.AbstractBaseEntity;
import com.tb.model.api.MergeInstance;
import com.tb.model.api.PatchInstance;

import java.util.List;

@Entity
@Table(name = "users", schema = "PUBLIC")
@Cacheable
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractBaseEntity<Long>
        implements MergeInstance<User, UserDto>, PatchInstance<User, UserDto> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "users_id_seq")
    @Getter
    @Setter
    private Long id;
    @Column(name = "lastname", nullable = false)
    @Getter
    @Setter
    private String lastName;
    @Column(name = "firstname", nullable = false)
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    @Getter
    @Setter
    private List<Role> roles;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_blockchain_id")
    @Getter
    @Setter
    private AccountBlockchain accountBlockchain;

    @Override
    public User merge(UserDto dto) {
        this.setLastName(dto.getLastName());
        this.setFirstName(dto.getFirstName());
        if (dto.getEmail() != null) {
            this.setEmail(dto.getEmail());
        }
        return this;
    }

    @Override
    public User patch(UserDto dto) {
        if (dto.getLastName() != null) {
            this.setLastName(dto.getLastName());
        }
        if (dto.getFirstName() != null) {
            this.setFirstName(dto.getFirstName());
        }
        return this;
    }
}
