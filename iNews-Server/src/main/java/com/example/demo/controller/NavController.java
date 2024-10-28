package com.example.demo.controller;


import com.example.demo.entity.Nav;
import com.example.demo.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {
    @Autowired
    private NavService navService;

    @GetMapping
    public List<Nav> getAllNavs() {
        return navService.getAllNavs();
    }
}
