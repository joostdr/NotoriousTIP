package com.hr.securitylab.services;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * Created by Joost on 1-11-2016.
 */
public class UpdateService {

    public UpdateService() {
    }

    public String readUpdateFileContent() throws IOException{
        try{
            byte[] encoded = Files.readAllBytes(Paths.get("update/init.lua"));
            return new String(encoded,"UTF-8");
        }
        catch(FileNotFoundException e){
            return "Error! Failed to open the file!";
        }
        catch (NoSuchFileException e){
            return "Error! File could not be found!";
        }
    }

}
