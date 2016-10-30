package com.hr.securitylab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Class which contains the security configuration for spring
 * - Determines which endpoints require authentication and which ones do not
 * - Configure userDetailsService in combination with DaoAuthenticationProvider for database authentication
 * - Configure password encryption using BCrypt
 */

@Configuration

public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * ApiWebSecurityConfigurationAdapter is responsible for securing the API endpoints
     * API endpoints > all endpoints with mapping /api/...
     * @Order annotations allow us to have two seperate methods for securing endpoints > 1 method for securing API requests and 1 method for securing Web requests
     */
    @Configuration
    @Order(1)
    //TODO /on and /off shouldm't be permit all
    //TODO implement 404 error page handling
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable() //disable cross side request forgery
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/on", "/api/off").permitAll() //endpoints which don't require httpbasic authentication
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic(); //apply httpBasic to the api endpoints
        }
    }
    /**
     * FormLoginWebSecurityConfigurerAdapter is responsible for securing the Web endpoints
     * Web endpoints > all endpoints which user can navigate to through the webapp
     */
    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/register","/resetpassword", "/main", "/").permitAll() //endpoints which don't require the user to be logged in
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login") //the mapping of the login page
                        .permitAll()
                        .and()
                    .formLogin()
                        .defaultSuccessUrl("/", true) //after successful login redirect to home page
                        .and()
                    .logout()
                        .logoutSuccessUrl("/")
                        .permitAll();
        }
    }

    /**
     * Method to enable BCrypt password encoding
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

    }


}