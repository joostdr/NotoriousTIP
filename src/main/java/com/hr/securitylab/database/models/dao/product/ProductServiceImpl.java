package com.hr.securitylab.database.models.dao.product;

import com.hr.securitylab.database.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfProductCodeExists(String productCode) {
        return productDao.checkIfProductCodeExists(productCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfProductIsInUse(String productCode) {
        return productDao.checkIfProductCodeIsInUse(productCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product findProductByProductCode(String productCode) {
        return productDao.findProductByProductCode(productCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfPinIsValid(String productCode, String pin) {
        return productDao.checkIfPinIsValid(productCode, pin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Product product) {
        productDao.saveOrUpdate(product);
    }
}
