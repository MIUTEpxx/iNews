package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentItem;

import java.util.List;

public interface CommentService {
    public List<CommentItem> getCommentListItemByNewsid(Integer newsid);
    public List<CommentItem> getCommentListItemByUserid(Integer userid);
    public Integer insertComment(Comment comment);
    public Integer deleteComment(Integer commentid);
    public Integer updateLikesByCommentid(Integer like,Integer commentid);
}
