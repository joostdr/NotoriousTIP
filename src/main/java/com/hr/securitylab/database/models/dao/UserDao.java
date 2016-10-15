package com.hr.securitylab.database.models.dao;

import com.hr.securitylab.database.models.entities.User;

/**
 * Created by Joost on 14-10-2016.
 */
public interface UserDao {

    User findByUsername(String username);

}
