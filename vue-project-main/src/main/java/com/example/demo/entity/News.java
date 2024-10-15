package com.example.demo.entity;
import lombok.Data;
import java.util.Date;

@Data
public class News {
    private Integer id;
    private String title;
    private String picurl;
    private String posttime;
    private Integer hits;
    private Integer classid;
    private String author;

}
