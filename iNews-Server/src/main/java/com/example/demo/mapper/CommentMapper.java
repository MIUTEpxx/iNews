package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM comment WHERE newsid=#{newsid}")//获取newsid新闻下的所有评论
    List<Comment> getCommentByNewsid(@Param("newsid") Integer newsid);

    @Select("SELECT * FROM comment WHERE userid=#{userid}")//获取userid用户的所有评论
    List<Comment> getCommentByUserid(@Param("userid") Integer userid);
    //新增评论
    @Insert("INSERT INTO comment (userid,newsid,respondid,posttime,content) VALUES (#{userid},#{newsid},#{respondid},#{posttime},#{content})")
    Integer insertComment(Comment comment);
    //删除评论
    @Delete("DELETE FROM comment WHERE commentid=#{commentid}")
    Integer deleteComment(@Param("commentid") Integer commentid);
    //更新点赞数据
    @Update("UPDATE comment SET likes = likes + #{like} WHERE commentid = #{commentid}")
    int updateLikesByCommentid(@Param("like") Integer like,@Param("commentid") Integer commentid);


}
