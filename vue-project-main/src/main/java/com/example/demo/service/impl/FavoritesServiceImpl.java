package com.example.demo.service.impl;

import com.example.demo.entity.News;
import com.example.demo.mapper.FavoritesMapper;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private NewsService newsService;

    @Override
    public List<Integer> getFavoritesById(Integer userid){
        return favoritesMapper.getFavoritesById(userid);//根据用户id查询其收藏的新闻
    }
    @Override
    public boolean deleteFavorites(Integer userid,Integer newsid){
        return favoritesMapper.deleteFavorites(userid,newsid);//取消收藏
    }
    @Override
    public boolean insertFavorites(Integer userid,Integer newsid){
        return favoritesMapper.insertFavorite(userid,newsid)!=0;//新增收藏
    }
    @Override
    public boolean checkIsFavorite(Integer userid,Integer newsid){
        List<Integer> newsids = favoritesMapper.getFavoritesById(userid);
        return newsids.contains(newsid);//检查新闻newsid是否在用户userid的收藏中
    }
    @Override
    public List<News> getNewsByUserFavorites(Integer userid){
        List<Integer> newsids = favoritesMapper.getFavoritesById(userid);
        return newsService.getNewsByIds(newsids);
    }

}
