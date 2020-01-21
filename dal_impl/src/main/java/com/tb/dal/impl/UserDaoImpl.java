package com.tb.dal.impl;

import org.springframework.stereotype.Repository;

import com.tb.dal.api.UserDao;
import com.tb.dal.impl.utils.JPAUtils;
import com.tb.model.User;
import com.tb.model.User_;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserWithRolesById(Long id) {
        return getById(id, createRolesGraph());
    }

    @Override
    public User getUserByFirstName(String firstName) {
        return getRecordByField(User_.firstName, firstName, createRolesGraph());
    }

    @Override
    public List<User> getAllUsersWithRoles() {
        CriteriaQuery<User> query = createSelectCriteriaQuery();
        JPAUtils.getRoot(query).fetch(User_.roles, JoinType.LEFT);
        return  getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public List<User> getAllFullUsers() {
        return getAll(createRolesGraph());
    }

    @Override
    public User getUserByEmail(String email) {
        return getRecordByField(User_.email, email,  createRolesGraph());
    }

    private EntityGraph<User> createRolesGraph() {
        EntityGraph<User> graph = getEntityManager().createEntityGraph(User.class);
        graph.addAttributeNodes(User_.roles);
        return graph;
    }


}
