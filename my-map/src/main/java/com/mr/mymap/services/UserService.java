package com.mr.mymap.services;

import com.mr.mymap.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User registerUser(User user);

    public void authenticate(String email, String password) throws Exception;
}
