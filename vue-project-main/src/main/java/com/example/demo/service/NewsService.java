package com.example.demo.service;


import com.example.demo.entity.News;

import java.util.List;

public interface  NewsService  {
    List<News> getNewsByClassId(Integer  classid);
}
