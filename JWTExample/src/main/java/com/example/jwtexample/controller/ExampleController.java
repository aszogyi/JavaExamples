package com.example.jwtexample.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private")
    //@PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_VIEWER')")
    public String privateEndpoint() {
        return "This is a private endpoint.";
    }

}
