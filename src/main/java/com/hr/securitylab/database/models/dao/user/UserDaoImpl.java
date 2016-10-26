package com.hr.securitylab.database.models.dao.user;

import com.hr.securitylab.database.models.entities.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Joost on 14-10-2016.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /*private Session getSession() {
        return sessionFactory.getCurrentSession();
    }*/

    public User findByUsername(String username){
        String hql = "FROM User u where u.username = :username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return result.get();
    }

    @Override
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return result.isPresent();
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        String hql = "FROM User u WHERE u.username = :username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("username",username);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return result.isPresent();
    }
}
