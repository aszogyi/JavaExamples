package com.example.simpledockerapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimpleController {
    @PostMapping("/modify")
    public String modifyString(@RequestBody String input) {
        return "Modified: " + input;
    }
}
