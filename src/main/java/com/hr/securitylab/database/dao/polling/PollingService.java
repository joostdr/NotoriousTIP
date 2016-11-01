package com.hr.securitylab.database.dao.polling;

import com.hr.securitylab.database.entities.hibernate.Polling;

/**
 * Created by joost on 31-10-2016.
 */
public interface PollingService {

    public Polling findPollingByProductId(String productId);

    public void saveOrUpdate(Polling polling);

}
