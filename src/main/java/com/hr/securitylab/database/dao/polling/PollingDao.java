package com.hr.securitylab.database.dao.polling;

import com.hr.securitylab.database.entities.hibernate.Polling;

/**
 * Created by joost on 31-10-2016.
 */
public interface PollingDao {

    public Polling findPollingByProductId(String productId);

}
