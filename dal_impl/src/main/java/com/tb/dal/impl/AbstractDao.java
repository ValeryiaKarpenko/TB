package com.tb.dal.impl;

import javax.annotation.Nonnull;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.tb.dal.api.GenericDao;
import com.tb.model.api.AbstractBaseEntity;

import static com.tb.dal.impl.utils.JPAUtils.getSingleResultOrNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T extends AbstractBaseEntity<P>, P extends Serializable> implements GenericDao<T, P> {

    protected static final String LOADGRAPH_JPA_HINT_PROPERTY_NAME = "javax.persistence.loadgraph";

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public P create(T obj) {
         this.getEntityManager().persist(obj);
         return obj.getId();
    }

    @Override
    public void update(T obj) {
        this.getEntityManager().merge(obj);
    }

    @Override
    public List<T> getAll() {
        CriteriaQuery<T> query = createSelectCriteriaQuery();
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<T> getAll(EntityGraph<T> entityGraph) {
        CriteriaQuery<T> fromTable = createSelectCriteriaQuery();
        TypedQuery<T> query = entityManager.createQuery(fromTable);

        if (null != entityGraph) {
            query.setHint(LOADGRAPH_JPA_HINT_PROPERTY_NAME, entityGraph);
        }
        return query.getResultList();
    }

    @Override
    public T getById(P id) {
       return this.getEntityManager().find(this.getEntityClass(), id);
    }

    @Override
    public T getById(P id, EntityGraph<T> entityGraph) {
        Map<String, Object> hints = new HashMap<>();
        if(entityGraph != null) {
            hints.put(LOADGRAPH_JPA_HINT_PROPERTY_NAME, entityGraph);
        }
        return this.getEntityManager().find(this.getEntityClass(), id, hints);
    }

    @Override
    public void delete(T entity) {
        this.getEntityManager().remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteByID(P id) {
        T entity = entityManager.find(this.getEntityClass(), id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public T toReference(@Nonnull P id) {
        return this.entityManager.getReference(this.getEntityClass(), id);
    }

    protected <U> T getRecordByField(SingularAttribute<T, U> attribute, U fieldValue, EntityGraph<T> entityGraph){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Class<T> entityClass = this.getEntityClass();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> fromTable = criteriaQuery.from(entityClass);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery
                .select(fromTable)
                .where(criteriaBuilder.equal(fromTable.get(attribute),fieldValue)));

        if (null != entityGraph) {
            query.setHint(LOADGRAPH_JPA_HINT_PROPERTY_NAME, entityGraph);
        }

        return getSingleResultOrNull(query);

    }

    protected CriteriaQuery<T> createSelectCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Class<T> entityClass = this.getEntityClass();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return query;
    }
}
