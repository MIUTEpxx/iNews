package com.example.demo.entity;

import lombok.Data;

//显示在历史记录页面的历史记录项的信息
@Data
public class HistoryItem {

    private Integer newsid; //新闻id
    private String tiem;    //上次浏览时间
    private String title;   //标题
    private String picurl;  //封面图片路径
    private String author;  //作者
}
