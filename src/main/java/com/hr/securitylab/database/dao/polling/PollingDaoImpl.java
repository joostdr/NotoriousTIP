package com.hr.securitylab.database.dao.polling;

import com.hr.securitylab.database.entities.hibernate.Polling;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by joost on 31-10-2016.
 */


@Repository
public class PollingDaoImpl implements PollingDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Polling findPollingByProductId(String productId) {
        String hql = "FROM Polling polling WHERE polling.product =:product_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("product_id",productId);
        Optional<Polling> result = Optional.ofNullable((Polling) query.uniqueResult());
        return result.isPresent() ? result.get() : null;
    }
}
