package com.hr.securitylab.database.models.dao.product;

import com.hr.securitylab.database.models.entities.Product;

public interface ProductDao {

    boolean checkIfProductCodeExists(String productCode);

    boolean checkIfProductCodeIsInUse(String productCode);

    Product findProductByProductCode(String productCode);

    boolean checkIfPinIsValid(String productCode, String pin);

    Product findById(String productId);

    void saveOrUpdate(Product product);
}
