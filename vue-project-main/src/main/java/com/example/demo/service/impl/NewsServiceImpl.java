package com.example.demo.service.impl;


import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//作为服务层的具体实现，NewsServiceImpl 实现了 NewsService 接口中定义的所有方法，包含具体的业务逻辑处理代码。
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getNewsByClassId(Integer classid){
        return newsMapper.getNewsByClassId(classid);
    }
    @Override
    public News getNewsById(Integer id){
        return newsMapper.getNewsById(id);
    }
    @Override
    public List<News> getNewsByIds(List<Integer> newsids){
        List<News> newsList = new ArrayList<>();
        for(Integer id : newsids){
            newsList.add(newsMapper.getNewsById(id));
        }
        return newsList;
    }
}
