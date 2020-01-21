package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "hello World";
    }

    @GetMapping(path = "/helloworld-bean")
    public UserDetails getUserDetails() {
        return new UserDetails("Ousmane", "NGOM", "PARIS");
    }
}
