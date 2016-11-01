package com.hr.securitylab.database.dao.polling;

import com.hr.securitylab.database.entities.hibernate.Polling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joost on 31-10-2016.
 */

@Service
public class PollingServiceImpl implements PollingService{

    private PollingDao pollingDao;

    @Autowired
    public PollingServiceImpl(PollingDao pollingDao) {
        this.pollingDao = pollingDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Polling findPollingByProductId(String productId) {
        return pollingDao.findPollingByProductId(productId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Polling polling) {
        pollingDao.saveOrUpdate(polling);
    }
}
