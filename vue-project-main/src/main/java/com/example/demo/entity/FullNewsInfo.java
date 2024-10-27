package com.example.demo.entity;

import lombok.Data;

@Data
public class FullNewsInfo {
    private Integer newsid;
    private String content;
    private Integer classid;
    private String title;
    private String picurl;
    private String posttime;
    private Integer hits;
    private String author;
}
