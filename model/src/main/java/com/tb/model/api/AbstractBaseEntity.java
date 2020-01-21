package com.tb.model.api;

import java.util.Objects;

public abstract class AbstractBaseEntity<T> implements IBaseEntity<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof AbstractBaseEntity)) {
            return false;
        }

        if (!o.getClass().equals(this.getClass())) {
            return false;
        }

        T id = getId();
        AbstractBaseEntity<?> that = (AbstractBaseEntity<?>) o;

        if (id == null) {
            return false;
        }
        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append(" id = ").append(getId());
        return sb.toString();
    }
}
