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
/*
*
数据库新增表:收藏表favorite (包含项:userid,newsid)
后端实现接口:
根据用户id获得收藏表里对应新闻id,
根据用户id获得对应新闻信息对象列表
增删收藏信息
检查该新闻id是否已被用户收藏
前端新增收藏列表页面:pages\user\favorites
前端新增功能:
新闻详情页增加收藏按钮,用户登录后,可实现对对应新闻的收藏或取消收藏
用户登录后,可在个人中心点击收藏图标进入收藏列表页面,以查看收藏的新闻
* */