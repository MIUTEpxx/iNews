package com.example.demo.service.impl;


import com.example.demo.entity.Detail;
import com.example.demo.entity.FullNewsInfo;
import com.example.demo.entity.News;
import com.example.demo.mapper.DetailMapper;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.DetailService;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailMapper detailMapper;
    private NewsService newsService;


    @Override
    public Detail getDetailById(Integer id) {
        return detailMapper.getDetailById(id);
    }
    @Override
    public FullNewsInfo getFullNewsInfoById(Integer id){
        News resNews =newsService.getNewsById(id);
        Detail resDetail =getDetailById(id);

        if (resNews != null && resDetail != null){
            FullNewsInfo resFullNewsInfo = new FullNewsInfo();
            resFullNewsInfo.setId(resNews.getId());
            resFullNewsInfo.setTitle(resNews.getTitle());
            resFullNewsInfo.setAuthor(resNews.getAuthor());
            resFullNewsInfo.setPosttime(resNews.getPosttime());
            resFullNewsInfo.setClassid(resNews.getClassid());
            resFullNewsInfo.setHits(resNews.getHits());
            resFullNewsInfo.setPicurl(resNews.getPicurl());
            resFullNewsInfo.setContent(resDetail.getContent());
            return  resFullNewsInfo;
        }
        return null;
    }

}
