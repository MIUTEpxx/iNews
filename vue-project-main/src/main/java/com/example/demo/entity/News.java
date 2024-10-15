package com.example.demo.entity;
import lombok.Data;
import java.util.Date;

@Data
public class News {
    private Integer id;
    private String title;
    private String picurl;
    private Date posttime;
    private Integer hits;
    private Integer classid;
    private String author;
    // 空构造方法可以省略，因为@Data会生成一个无参构造方法
    // 带参数的构造方法（如果需要）
    public News(Integer id, String title, String picurl, Date posttime, Integer hits, Integer classid, String author) {
        this.id = id;
        this.title = title;
        this.picurl = picurl;
        this.posttime = posttime;
        this.hits = hits;
        this.classid = classid;
        this.author = author;
    }
}
