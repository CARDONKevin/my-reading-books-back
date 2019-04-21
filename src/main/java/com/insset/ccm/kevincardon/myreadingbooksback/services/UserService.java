package com.insset.ccm.kevincardon.myreadingbooksback.services;

import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String signin(String username) {
        return jwtTokenProvider.createToken(username);
    }
}
