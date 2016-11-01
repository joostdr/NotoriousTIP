package com.hr.securitylab.controllers.restcontrollers;

import com.hr.securitylab.database.entities.rest.PollingRest;
import com.hr.securitylab.database.entities.rest.Response;
import com.hr.securitylab.services.EncryptionService;
import com.hr.securitylab.services.PollingService;
import com.hr.securitylab.services.UpdateService;
import com.hr.securitylab.validation.ProductIdValidator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    public PollingRest checkPollingTable(HttpServletRequest request){
        if(ProductIdValidator.checkIfProductIdIsValid(request.getHeader("productid"))){
            encryptionService.getKey(request);
            return pollingService.checkPollingTable(request.getHeader("productid"));
        }
        return new PollingRest(null,false,"Productid is not valid");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String getUpdate(HttpServletRequest request){
        if(ProductIdValidator.checkIfProductIdIsValid(request.getHeader("productid"))){
            try {
                return updateService.readUpdateFileContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error";
    }

}
