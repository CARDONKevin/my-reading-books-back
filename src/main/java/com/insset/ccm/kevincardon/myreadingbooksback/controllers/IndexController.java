package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class IndexController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public IndexController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping(value = "/")
    public ModelAndView IndexApp() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return new ModelAndView("redirect:/connect/facebook");
        }
        return new ModelAndView("redirect:https://my-reading-books-dev.herokuapp.com/");
    }

    @DeleteMapping(value = "/connect/facebook")
    public ModelAndView Logout() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            connectionRepository.removeConnection(connectionRepository.findPrimaryConnection(Facebook.class).getKey());
        }
        return new ModelAndView("redirect:/");
    }
}

