package com.hr.securitylab.validation;

import com.hr.securitylab.database.DatabaseFactory;

/**
 * Created by joost on 31-10-2016.
 */
public class ProductIdValidator {

    /**
     * Method which checks if:
     * - ProductId is not null
     * - ProductId is not empty
     * - ProductId only contains numbers
     * - ProductId exists
     */

    public static boolean checkIfProductIdIsValid(String productId){
        if(productId != null){
            return !productId.isEmpty() && checkIfProductIdOnlyContainsNumbers(productId) && checkIfProductIdExists(productId);
        }
        return false;
    }

    /**
     * check if the productid contains only numbers
     * productid gets parsed to an int in getEncrpytionKeyByProductId, so if it contains characters of some sort it crashes
     *
     * @param productId
     * @return
     */

    private static boolean checkIfProductIdOnlyContainsNumbers(String productId){
        return productId.matches("[0-9]+");
    }

    private static boolean checkIfProductIdExists(String productId){
        return (DatabaseFactory.getProductService().findById(productId) !=null);
    }

}
