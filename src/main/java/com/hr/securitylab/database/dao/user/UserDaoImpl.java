package com.hr.securitylab.database.dao.user;

import com.hr.securitylab.database.entities.hibernate.Product;
import com.hr.securitylab.database.entities.hibernate.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public User findUserByUsername(String username){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.like("username",username));
        Optional<User> result = Optional.ofNullable((User) criteria.uniqueResult());
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.like("email",email));
        return (Optional.ofNullable((User) criteria.uniqueResult())).isPresent();
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.like("username",username));
        return (Optional.ofNullable((Product) criteria.uniqueResult())).isPresent();
    }
}
