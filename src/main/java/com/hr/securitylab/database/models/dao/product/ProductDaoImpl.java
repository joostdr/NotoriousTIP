package com.hr.securitylab.database.models.dao.product;

import com.hr.securitylab.database.models.entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean checkIfProductCodeExists(String productCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productcode",productCode));
        return (Optional.ofNullable((Product) criteria.uniqueResult())).isPresent();
    }

    @Override
    public boolean checkIfProductCodeIsInUse(String productCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productcode",productCode));
        Optional<Product> result = Optional.ofNullable((Product) criteria.uniqueResult());
        return result.isPresent() && result.get().isActivated();
    }

    @Override
    public Product findProductByProductCode(String productCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productcode",productCode));
        return (Product) criteria.uniqueResult();
    }

    @Override
    public boolean checkIfPinIsValid(String productCode, String pin) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productcode",productCode));
        Optional<Product> result = Optional.ofNullable((Product) criteria.uniqueResult());
        return result.isPresent() && result.get().getPin().equals(pin);
    }

    @Override
    public Product findById(String productId) {
        Optional<Product> result = Optional.ofNullable(sessionFactory.getCurrentSession().get(Product.class, Integer.parseInt(productId)));
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public void saveOrUpdate(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }
}
