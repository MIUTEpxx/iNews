package com.example.demo.mapper;


import com.example.demo.entity.Detail;
import com.example.demo.entity.Nav;
import com.example.demo.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DetailMapper {

    @Select("SELECT * FROM detail")
    List<Detail> getAllDetail();

    @Select("SELECT * FROM detail WHERE id = #{id}")
    Detail getDetailById(@Param("id") Integer id);
}
