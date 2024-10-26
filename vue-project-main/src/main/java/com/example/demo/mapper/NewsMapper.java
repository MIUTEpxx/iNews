package com.example.demo.mapper;

import com.example.demo.entity.Nav;
import com.example.demo.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NewsMapper {

    @Select("SELECT * FROM newslist")
     List<News> getAllNews();

    @Select("SELECT * FROM newslist WHERE classid = #{classid} ORDER BY posttime DESC")
     List<News> getNewsByClassId(@Param("classid") Integer classid);

    @Select("SELECT * FROM newslist WHERE id = #{id}")
     News getNewsById(@Param("id") Integer id);
    //更新新闻点击次数(浏览量)
    @Update("UPDATE newslist SET hits = hits + 1 WHERE id = #{id}")
     int updateNewsHitsById(@Param("id") Integer id);
}
