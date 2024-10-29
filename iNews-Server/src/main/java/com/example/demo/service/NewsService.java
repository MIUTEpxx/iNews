
package com.example.demo.service;
import com.example.demo.entity.News;
import java.util.List;
//服务层接口 具体实现在NewsServiceImpl
public interface  NewsService  {
    public List<News> getNewsList();
    public List<News> getNewsListByClassid(Integer  classid);
    public News getNewsByNewsid(Integer newsid);
    public List<News> getNewsListByNewsids(List<Integer> newsids);
    public int increaseNewsHits(Integer newsid);
}
