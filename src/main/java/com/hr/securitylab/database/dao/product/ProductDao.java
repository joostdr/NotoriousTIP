package com.hr.securitylab.database.dao.product;

import com.hr.securitylab.database.entities.hibernate.Product;

public interface ProductDao {

    boolean checkIfProductCodeExists(String productCode);

    boolean checkIfProductCodeIsInUse(String productCode);

    Product findProductByProductCode(String productCode);

    boolean checkIfPinIsValid(String productCode, String pin);

    Product findById(String productId);

    void saveOrUpdate(Product product);
}
