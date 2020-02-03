package com.tb.dal.api;

import javax.persistence.EntityGraph;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, P extends Serializable> {

    Class<T> getEntityClass();

    T create(T obj);

    void update(T obj);

    List<T> getAll();

    List<T> getAll(EntityGraph<T> graph);

    T getById(P id);

    T getById(P id, EntityGraph<T> graph);

    void delete(T obj);

    void deleteByID(P id);

    T toReference(P id);

}
