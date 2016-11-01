package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.Polling;
import com.hr.securitylab.database.entities.hibernate.User;
import com.hr.securitylab.database.entities.rest.PollingRest;
import com.hr.securitylab.database.entities.rest.Response;

/**
 * Created by joost on 31-10-2016.
 */
public class PollingService {

    public PollingService() {
    }

    public PollingRest checkPollingTable(String productId) {
        return new PollingRest("vibrate", DatabaseFactory.getPollingService().findPollingByProductId(productId).isVibrate(), null);
    }

    public void updateVibrateColumnForUser(User user){
        Polling polling = user.getProduct().getPolling();
        polling.setVibrate(!polling.isVibrate());
        DatabaseFactory.getPollingService().saveOrUpdate(polling);

        System.out.println("Vibrate changed to: " + polling.isVibrate());
    }
}
