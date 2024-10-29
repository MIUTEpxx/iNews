package com.example.demo.controller;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    //获取所有新闻
    @GetMapping("/getAll")
    public List<News> getAll(){
        return newsService.getNewsList();
    }
    //根据newsid获取对应新闻基本信息
    @GetMapping("/classid/{classid}")
    public List<News> getNewsByClassId(@PathVariable Integer classid) {
        return newsService.getNewsListByClassid(classid);
    }
    //更新newsid对应新闻的浏览量(使其加一)
    @PutMapping("/hits/{newsid}")
    public boolean increaseNewsHits(@PathVariable("newsid") Integer newsid) {
        int result = newsService.increaseNewsHits(newsid);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
