package com.cpe.emergencymanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    @GetMapping("/hello")
    public Boolean pingServer() {
        return true;
    }
}
