package com.hr.securitylab.services;

import com.hr.securitylab.database.entities.rest.UpdateRest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Joost on 1-11-2016.
 */
public class UpdateService {

    public UpdateService() {
    }

    public UpdateRest readUpdateFileContent() throws IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        UpdateRest updateRest = new UpdateRest();
        updateRest.setVersionNumber(2);
        updateRest.setFileName("init.lua");
        updateRest.setHmac(EncryptionService.computeHash(readFile()));
        updateRest.setError(null);
        return updateRest;
    }

    public String readFile() throws IOException{
        try{
            byte[] encoded = Files.readAllBytes(Paths.get("update/init.lua"));
            return new String(encoded,"UTF-8");
        }
        catch(FileNotFoundException e){
            return "File does not exist";
        }
        catch (NoSuchFileException e){
            return "File not found";
        }
    }

}
