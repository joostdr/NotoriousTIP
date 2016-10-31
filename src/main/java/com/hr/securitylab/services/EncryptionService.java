package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.Product;
import com.hr.securitylab.database.models.entities.Response;
import com.hr.securitylab.exceptions.KeyNotSetException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.binary.Hex;
import org.cryptacular.codec.Base64Decoder;
import org.cryptacular.codec.HexEncoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class which handles AES 128 ECB encryption
 */
//TODO make sure request.getHeader("productid") is an integer
public class EncryptionService {

    private static SecretKey key;

    /**
     * Retrieves encryptionkey from the database for the corresponding device
     * Saves the encryptionkey in the variable key
     *
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

    public static Response decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, KeyNotSetException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        if (key == null) throw new KeyNotSetException("Key is not set");
        cipher.init(Cipher.DECRYPT_MODE, key);
        String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        return new Response(200, decryptedString);
    }

    public static Response encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES");///CBC/PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        String encryptedString = Hex.encodeHexString(encrypted);
        return new Response(200, encryptedString);
    }

    /**
     * Saves the devices ip in the database
     */

    private void saveIp(String remoteAddress, Product product) {
        product.setIp(remoteAddress);
        DatabaseFactory.getProductService().saveOrUpdate(product);
    }

    /**
     * check if the productid contains only numbers
     * productid gets parsed to an int in getEncrpytionKeyByProductId, so if it contains characters of some sort it crashes
     *
     * @param productId
     * @return
     */
    public boolean checkIfProductIdIsValid(String productId) {
        return productId.matches("[0-9]+");
    }


}
