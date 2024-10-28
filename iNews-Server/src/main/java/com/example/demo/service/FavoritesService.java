package com.example.demo.service;

import com.example.demo.entity.Favorites;
import com.example.demo.entity.News;

import java.util.List;

public interface FavoritesService {
    public List<Integer> getFavoritesByUserid(Integer userid);
    public boolean deleteFavorites(Integer userid,Integer newsid);
    public boolean insertFavorites(Integer userid,Integer newsid);
    public boolean checkIsFavorite(Integer userid,Integer newsid);//检查newsid新闻是否在userid用户的收藏中
    public List<News> getNewsByUserFavorites(Integer userid);
}
