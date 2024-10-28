package com.example.demo.service;

import com.example.demo.entity.Detail;
import com.example.demo.entity.FullNewsInfo;

public interface DetailService {
    public Detail getDetailByNewsid(Integer newsid);
    public FullNewsInfo getFullNewsInfoByNewsid(Integer newsid);
}
