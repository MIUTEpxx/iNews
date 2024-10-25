package com.example.demo.mapper;

import com.example.demo.entity.History;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoryMapper {

    //根据用户id获得对应所有的历史记录
    @Select("SELECT* FROM history WHERE userid = #{userid}")
     List<History> getALLHistoryById(@Param("userid") Integer userid);

    //根据用户id和新闻id获得具体的一条历史记录(可用于检查用户是否浏览过某新闻)
    @Select("SELECT* FROM history WHERE userid = #{userid} AND newsid = #{newsid}")
     History getOneHistoryById(@Param("userid") Integer userid,@Param("newsid") Integer newsid);

    //新增历史记录
    @Insert("INSERT INTO history (userid,newsid,time) VALUES (#{userid},#{newsid},#{time})")
     int insertFavorite(@Param("userid") Integer userid, @Param("newsid") Integer newsid, @Param("time") String time);

    //更新已有的历史记录的上次浏览时间
    @Update("UPDATE newslist SET time = #{time} WHERE userid = #{userid} AND newsid = #{newsid}")
     int updateHistoryById(@Param("userid") Integer userid, @Param("newsid") Integer newsid, @Param("time") String time);

}
