package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtObject;
import com.insset.ccm.kevincardon.myreadingbooksback.security.JwtTokenProvider;
import com.insset.ccm.kevincardon.myreadingbooksback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String IndexApp() {
        return "Welcome in backend of MyReadingsBooks";
    }

    @GetMapping(value = "/signin/{username}")
    public JwtObject SignIn(@PathVariable String username) {
        JwtObject jwtObject = new JwtObject();
        jwtObject.setToken(userService.signin(username));
        return jwtObject;
    }


}
