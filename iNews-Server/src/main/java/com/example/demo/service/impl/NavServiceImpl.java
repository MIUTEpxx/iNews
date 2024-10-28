package com.example.demo.service.impl;

import com.example.demo.entity.Nav;
import com.example.demo.mapper.NavMapper;
import com.example.demo.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavServiceImpl implements NavService {

    @Autowired
    private NavMapper navMapper;

    @Override
    public List<Nav> getAllNavs() {
        return navMapper.getAllNavs();
    }
}
