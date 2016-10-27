package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.Product;
import com.hr.securitylab.database.models.entities.Response;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.cryptacular.codec.Base64Decoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
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
        byte[] encodedKey = keyString.getBytes();
        key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return new Response(200, "Key saved");
    }

    public String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        return decryptedString;
    }

    public String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        String encryptedString = Base64.getEncoder().encodeToString(encrypted);
        System.out.println(encryptedString);
        return encryptedString;
    }

    /**
     * Saves the devices ip in the database
     */

    private void saveIp(String remoteAddress, Product product){
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
        return productId.matches("[0-9]+");
    }



}
