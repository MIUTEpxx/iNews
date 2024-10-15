package com.example.demo.mapper;

import com.example.demo.entity.Nav;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NavMapper {
    @Select("SELECT * FROM navlist")
    List<Nav> getAllNavs();
}
