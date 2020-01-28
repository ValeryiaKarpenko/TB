package com.tb.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Cacheable
public class UserCafe implements Serializable{

    private static final long serialVersionUID = -6500833234508063383L;
    @EmbeddedId
    @Getter
    @Setter
    private UserCafeId userCafeId;
    

}
