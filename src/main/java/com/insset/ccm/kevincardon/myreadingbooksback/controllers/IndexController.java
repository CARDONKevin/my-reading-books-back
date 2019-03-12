package com.insset.ccm.kevincardon.myreadingbooksback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RestController
    public class ProductController {
        @GetMapping(value="/")
        public String IndexApp() {
            return "Welcome in backend of MyReadingsBooks";
        }
    }
}
