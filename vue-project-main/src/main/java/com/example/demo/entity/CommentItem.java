package com.example.demo.entity;

import lombok.Data;
//评论条所有信息
@Data
public class CommentItem {
    private Integer commentid;
    private Integer userid;
    private Integer newsid;
    private Integer respondid;
    private Integer likes;
    private String posttime;
    private String content;
    private String picurl;
    private String nickname;
    private String newstitle;
}
