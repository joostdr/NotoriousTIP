package com.hr.securitylab.database.models.dao.product;

import com.hr.securitylab.database.models.entities.Product;

public interface ProductService {

    boolean checkIfProductCodeExists(String productCode);

    boolean checkIfProductIsInUse(String productCode);

    Product findProductByProductCode(String productCode);

    boolean checkIfPinIsValid(String productCode, String pin);

    void saveOrUpdate(Product product);
}
