package com.example.demo.mapper;


import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoritesMapper {

    @Select("SELECT newsid FROM favorites WHERE userid = #{userid}")
    List<Integer> getFavoritesById(@Param("userid") Integer userid);

    @Delete("DELETE FROM favorites WHERE userid = #{userid} AND newsid=#{newsid}")
    boolean deleteFavorites(@Param("userid") Integer userid, @Param("newsid") Integer newsid);

    @Insert("INSERT INTO favorites (userid,newsid) VALUES (#{userid},#{newsid})")
    Integer insertFavorite(@Param("userid") Integer userid, @Param("newsid") Integer newsid);
}
