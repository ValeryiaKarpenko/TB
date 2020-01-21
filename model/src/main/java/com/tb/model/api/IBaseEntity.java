package com.tb.model.api;

import java.io.Serializable;

public interface IBaseEntity<T>  extends Serializable {
    /**
     * Gets the entity id.
     *
     * @return the id
     */
    T getId();

    /**
     * Sets the entity id.
     *
     * @param id the new id
     */
    void setId(T id);
}
