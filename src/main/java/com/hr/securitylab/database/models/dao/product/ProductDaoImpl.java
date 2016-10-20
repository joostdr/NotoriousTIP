package com.hr.securitylab.database.models.dao.product;

import com.hr.securitylab.database.models.entities.Product;
import com.hr.securitylab.database.models.entities.User;
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
        String hql = "FROM Product p where p.productcode =:productcode";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("productcode", productCode);
        List<Product> products= new ArrayList<Product>();
        Optional<Product> result = Optional.ofNullable((Product) query.uniqueResult());
        return result.isPresent();
    }

    @Override
    public boolean checkIfProductCodeIsInUse(String productCode) {
        String hql = "FROM Product p where p.productcode =:productcode AND p.activated = true";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("productcode", productCode);
        List<Product> products= new ArrayList<Product>();
        Optional<Product> result = Optional.ofNullable((Product) query.uniqueResult());
        return result.isPresent();
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
    public void saveOrUpdate(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }
}
