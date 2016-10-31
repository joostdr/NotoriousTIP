package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.rest.PollingRest;

/**
 * Created by joost on 31-10-2016.
 */
public class PollingService {

    public PollingService() {
    }

    public PollingRest checkPollingTable(String productId) {
        return new PollingRest("vibrate", DatabaseFactory.getPollingService().findPollingByProductId(productId).isVibrate(), null);
    }
}
