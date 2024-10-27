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
    public News getNewsByNewsid(Integer id){
        return newsMapper.getNewsByNewsid(id);
    }
    @Override
    public List<News> getNewsByNewsids(List<Integer> newsids){
        List<News> newsList = new ArrayList<>();
        for(Integer id : newsids){
            newsList.add(newsMapper.getNewsByNewsid(id));
        }
        return newsList;
    }
    //更新id对应新闻的浏览量信息(使其加一)
    @Override
    public int increaseNewsHits(Integer newsId) {
        return newsMapper.updateNewsHitsByNewsid(newsId);
    }

}
