package com.tb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tb.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class UserCafeId implements Serializable {

    private static final long serialVersionUID = 5031776038537713038L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    @Getter
    @Setter
    private Cafe cafe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, unique = true)
    private UserType userType;

    public UserCafeId() {
    }

    public UserCafeId(User user, Cafe cafe, UserType userType) {
        super();
        this.user = user;
        this.cafe = cafe;
        this.userType = userType;
    }

}
