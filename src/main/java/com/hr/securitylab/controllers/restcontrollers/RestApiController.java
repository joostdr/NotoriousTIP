package com.hr.securitylab.controllers.restcontrollers;

import com.hr.securitylab.database.entities.rest.PollingRest;
import com.hr.securitylab.database.entities.rest.Response;
import com.hr.securitylab.database.entities.rest.UpdateRest;
import com.hr.securitylab.services.EncryptionService;
import com.hr.securitylab.services.PollingService;
import com.hr.securitylab.services.UpdateService;
import com.hr.securitylab.validation.ProductIdValidator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Controller which handles the api endpoints
 * api endpoints:
 * - /api/poll
 */

@RestController
@RequestMapping(value = "/api")
public class RestApiController {
    private EncryptionService encryptionService;
    private PollingService pollingService;
    private UpdateService updateService;

    public RestApiController() {
        this.encryptionService = new EncryptionService();
        this.pollingService = new PollingService();
        this.updateService = new UpdateService();
    }

    /**
     * Method which gets called by the vibrator every 5 seconds
     * Sets the symmetric encryption key based on the supplied productid
     * Reads contents from the polling table
     * This way the vibrator can determine whether it needs to vibrate or not
     *
     * Also checks if the productid is valid, see {@link ProductIdValidator}
     * @param request
     * @return
     */
    @RequestMapping(value = "/poll", method = RequestMethod.POST)
    public String checkPollingTable(HttpServletRequest request) throws Exception{
        if(ProductIdValidator.checkIfProductIdIsValid(request.getHeader("productid"))){
            encryptionService.getKey(request);
            return EncryptionService.encryptPollingModel(pollingService.checkPollingTable(request.getHeader("productid")));
        }
        return "error";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UpdateRest getUpdate(HttpServletRequest request) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        if(ProductIdValidator.checkIfProductIdIsValid(request.getHeader("productid"))){
            try {
                return updateService.readUpdateFileContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public String readFile(HttpServletRequest request){
        if(ProductIdValidator.checkIfProductIdIsValid(request.getHeader("productid"))){
            try {
                return updateService.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error";
    }

}
