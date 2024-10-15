package com.example.demo.mapper;

import com.example.demo.entity.Nav;
import com.example.demo.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsMapper {

    @Select("SELECT * FROM newslist")
    List<Nav> getAllNews();

    @Select("SELECT * FROM newslist WHERE classid = #{classid}")
    List<News> getNewsByClassId(@Param("classid") Integer classid);
}
