package com.kveola13.springsecurity.Dao;

import com.kveola13.springsecurity.auth.ApplicationUser;

import java.util.Optional;


public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
