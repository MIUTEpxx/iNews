package com.example.demo.service.impl;

import com.example.demo.entity.History;
import com.example.demo.entity.HistoryItem;
import com.example.demo.entity.News;
import com.example.demo.mapper.HistoryMapper;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.HistoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<HistoryItem> getALLHistoryById(Integer userid){
        //根据用户id获得对应所有的历史记录,将对应新闻信息和上次浏览时间储存于HistoryItem中
        List<HistoryItem> historyItems = new ArrayList<>();
        List<History> histories = historyMapper.getALLHistoryById(userid);
        for (History history : histories) {
            News news = newsMapper.getNewsById(history.getNewsid());
            HistoryItem historyItem = new HistoryItem();
            historyItem.setNewsid(history.getNewsid());
            historyItem.setLooktime(history.getLooktime());
            historyItem.setAuthor(news.getAuthor());
            historyItem.setTitle(news.getTitle());
            historyItem.setPicurl(news.getPicurl());
            historyItems.add(historyItem);
        }
        return historyItems;
    }

    @Override
    public History getOneHistoryById(Integer userid,Integer newsid){
        //根据用户id和新闻id查找具体的一条历史记录
        return historyMapper.getOneHistoryById(userid, newsid);
    }

    @Override
    public boolean insertFavorite(Integer userid,Integer newsid,String looktime){
        //添加新的历史记录
        if(historyMapper.insertFavorite(userid, newsid, looktime)>0) return true;
        else return false;
    }
    @Override
    public boolean updateHistoryById( Integer userid, Integer newsid, String looktime){
        //更新 根据用户id和新闻id查找具体的一条历史记录的上次浏览时间
        if(historyMapper.updateHistoryById(userid, newsid, looktime)>0) return true;
        else return false;
    }
}
