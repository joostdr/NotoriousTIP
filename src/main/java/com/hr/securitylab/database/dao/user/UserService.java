package com.hr.securitylab.database.dao.user;

import com.hr.securitylab.database.entities.hibernate.User;

/**
 * Created by Joost on 14-10-2016.
 */
public interface UserService {
    User findUserByUsername(String username);

    void saveOrUpdate(User user);

    boolean checkIfEmailExists(String email);

    boolean checkIfUsernameExists(String username);
}
