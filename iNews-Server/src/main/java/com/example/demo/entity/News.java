package com.example.demo.entity;
import lombok.Data;

@Data
public class News {
    private Integer newsid;
    private String title;
    private String picurl;
    private String posttime;
    private Integer hits;
    private Integer classid;
    private String author;

}
