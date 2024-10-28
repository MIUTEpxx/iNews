package com.example.demo.entity;

import lombok.Data;
//评论基础信息
@Data
public class Comment {
    private Integer commentid;
    private Integer userid;
    private Integer newsid;
    private Integer respondid;
    private Integer likes;
    private String posttime;
    private String content;
}
