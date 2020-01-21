package com.tb.dal.impl.utils;

import javax.annotation.Nonnull;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class JPAUtils {

    private JPAUtils(){}

    public static <T> Root<T> getRoot(CriteriaQuery<T> query) {
        return (Root<T>)query.getRoots().iterator().next();
    }

    public static <T> T getSingleResultOrNull(@Nonnull TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
