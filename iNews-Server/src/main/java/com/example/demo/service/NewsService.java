package com.example.demo.service;


import com.example.demo.entity.News;

import java.util.List;
//服务层接口 具体实现在NewsServiceImpl
public interface  NewsService  {
    public List<News> getNewsByClassId(Integer  classid);
    public News getNewsByNewsid(Integer newsid);
    public List<News> getNewsByNewsids(List<Integer> newsids);
    public int increaseNewsHits(Integer newsid);
}
