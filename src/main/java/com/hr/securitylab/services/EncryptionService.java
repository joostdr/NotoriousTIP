package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.Product;
import com.hr.securitylab.database.models.entities.Response;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by joost on 27-10-2016.
 */
//TODO make sure request.getHeader("productid") is an integer
public class EncryptionService {

    private SecretKey key;

    /**
     * Retrieves encryptionkey from the database for the corresponding device
     * Saves the encryptionkey in the variable key
     * @param request
     * @return
     */
    public Response getKey(HttpServletRequest request) {
        String productId = request.getHeader("productId");
        Product product = DatabaseFactory.getProductService().findById(productId);
        String keyString = product.getEncryption_key();
        if (keyString == null) return new Response(400, "ID does not exist");
        saveIp(request.getRemoteAddr(), product);
        byte[] encodedKey = Base64.getDecoder().decode(keyString);
        key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return new Response(200, "Key saved");
    }

    /**
     * Saves the devices ip in the database
     */

    public void saveIp(String remoteAddress, Product product){
        product.setIp(remoteAddress);
        DatabaseFactory.getProductService().saveOrUpdate(product);
    }

    /**
     * check if the productid contains only numbers
     * productid gets parsed to an int in getEncrpytionKeyByProductId, so if it contains characters of some sort it crashes
     * @param productId
     * @return
     */
    public boolean checkIfProductIdIsValid(String productId){
        //return productId.contains("[0-9]+");
        boolean result = productId.matches("[0-9]+");
        System.out.println(result);
        return result;
    }

}
