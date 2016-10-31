package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.Product;
import com.hr.securitylab.database.entities.rest.Response;
import com.hr.securitylab.exceptions.KeyNotSetException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class which handles AES 128 ECB encryption
 */
public class EncryptionService {

    private static SecretKey key;

    /**
     * Retrieves encryptionkey from the hibernate for the corresponding device
     * Saves the encryptionkey in the variable key
     *
     * @param request
     * @return
     */
    public void getKey(HttpServletRequest request) {
        String productId = request.getHeader("productid");
        String keyString = DatabaseFactory.getProductService().findById(productId).getEncryption_key();
        byte[] encodedKey = keyString.getBytes();
        key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
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
}
