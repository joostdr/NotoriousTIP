package com.hr.securitylab.config;

import com.hr.securitylab.database.models.dao.user.UserService;
import com.hr.securitylab.database.models.entities.Role;
import com.hr.securitylab.database.models.entities.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * UserdetailsService which is used for authenticating users
 */

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    /**
     * Method which checks if an user exists based on the supplied credentials in the authentication
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        /*if(loginAttemptService.isBlocked(ip)){
            throw new RuntimeException("IP is blocked");
        }*/
        try{
            User user = userService.findUserByUsername(username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

    /**
     * Method which returns the ip made by the client
     * @return
     */

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
