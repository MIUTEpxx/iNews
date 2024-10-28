package com.example.demo.controller;


import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentItem;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/get_by_newsid/{newsid}")//获得newsid新闻的所有评论
    public List<CommentItem> getCommentByNewsid(@PathVariable("newsid") Integer newsid) {
        return commentService.getCommentItemByNewsid(newsid);
    }

    @GetMapping("/get_by_userid/{userid}")//获得userid用户的所有评论
    public List<CommentItem> getCommentByUserid(@PathVariable("userid") Integer userid) {
        return commentService.getCommentItemByUserid(userid);
    }

    @PostMapping("/add")//注解标记这是一个处理HTTP POST请求的方法
    public Integer insertComment(@RequestBody Comment comment) {//@RequestBod注解表示参数comment应该从HTTP请求体中绑定
        long currentTimestamp = System.currentTimeMillis() / 1000;//获取当前时间戳
        String timestampString = String.valueOf(currentTimestamp);
        comment.setPosttime(timestampString);
        System.out.println(comment.getUserid());
        return commentService.insertComment(comment);
    }
    @DeleteMapping("/delete/{commentid}")//删除评论
    public Integer deleteComment(@PathVariable("commentid") Integer commentid) {
        return commentService.deleteComment(commentid);
    }
    @PostMapping("/addlikes/{commentid}/{like}")//更改评论点赞量
    public Integer updateLikesBycommentid(@PathVariable("commentid") Integer commentid, @PathVariable("like") Integer like) {
        return commentService.updateLikesByCommentid(like, commentid);
    }
}

