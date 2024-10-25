package com.example.demo.controller;

import com.example.demo.entity.News;
import com.example.demo.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/getNewsids/{userid}")//根据用户id获得收藏表里对应新闻id,
    public List<Integer> getFavoritesById(@PathVariable Integer userid) {
        return favoritesService.getFavoritesById(userid);
    }
    @GetMapping("/getNews/{userid}")//根据用户id获得对应新闻信息对象列表
    public List<News> getNewsByUserFavorites(@PathVariable Integer userid) {
        return favoritesService.getNewsByUserFavorites(userid);
    }
    @PostMapping("/add/{userid}/{newsid}")//增加收藏
    public boolean insertFavorites(@PathVariable Integer userid, @PathVariable Integer newsid) {
        return favoritesService.insertFavorites(userid, newsid);
    }
    @DeleteMapping("/delete/{userid}/{newsid}")//删除收藏
    public boolean deleteFavorites(@PathVariable Integer userid, @PathVariable Integer newsid) {
        return favoritesService.deleteFavorites(userid, newsid);
    }
    @GetMapping("/check/{userid}/{newsid}")//检查该新闻id是否已被用户收藏
    public boolean checkFavorites(@PathVariable Integer userid, @PathVariable Integer newsid) {
        return favoritesService.checkIsFavorite(userid,newsid);
    }
}
