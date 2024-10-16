package com.example.demo.service;

import com.example.demo.entity.Detail;
import com.example.demo.entity.FullNewsInfo;

public interface DetailService {
    public Detail getDetailById(Integer id);
    public FullNewsInfo getFullNewsInfoById(Integer id);
}
