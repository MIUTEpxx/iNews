package com.example.demo.service.impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentItem;
import com.example.demo.entity.News;
import com.example.demo.entity.User;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;

    @Override
    public List<CommentItem> getCommentItemByNewsid(Integer newsid){
        News news=newsService.getNewsByNewsid(newsid);
        String newsTitle = news.getTitle();
        List<Comment> commentList=commentMapper.getCommentByNewsid(newsid);
        List<CommentItem> commentItemList=new ArrayList<CommentItem>();
        for(Comment comment:commentList){
            User user = userService.getUserByUserid(comment.getUserid());
            CommentItem commentItem=new CommentItem();
            commentItem.setCommentid(comment.getCommentid());
            commentItem.setNewsid(comment.getNewsid());
            commentItem.setUserid(comment.getUserid());
            commentItem.setContent(comment.getContent());
            commentItem.setLikes(comment.getLikes());
            commentItem.setRespondid(comment.getRespondid());
            commentItem.setPosttime(comment.getPosttime());
            commentItem.setPicurl(user.getPicurl());
            commentItem.setNickname(user.getNickname());
            commentItem.setNewstitle(newsTitle);
            commentItemList.add(commentItem);
        }
        return commentItemList;
    }
    @Override
    public List<CommentItem> getCommentItemByUserid(Integer userid){
        User user=userService.getUserByUserid(userid);
        List<Comment> commentList=commentMapper.getCommentByUserid(userid);
        List<CommentItem> commentItemList=new ArrayList<CommentItem>();
        for(Comment comment:commentList){
            News news=newsService.getNewsByNewsid(comment.getNewsid());
            CommentItem commentItem=new CommentItem();
            commentItem.setCommentid(comment.getCommentid());
            commentItem.setNewsid(comment.getNewsid());
            commentItem.setUserid(comment.getUserid());
            commentItem.setContent(comment.getContent());
            commentItem.setLikes(comment.getLikes());
            commentItem.setRespondid(comment.getRespondid());
            commentItem.setPosttime(comment.getPosttime());
            commentItem.setPicurl(user.getPicurl());
            commentItem.setNickname(user.getNickname());
            commentItem.setNewstitle(news.getTitle());
            commentItemList.add(commentItem);
        }
        return commentItemList;
    }
    @Override
    public Integer insertComment(Comment comment){
        return commentMapper.insertComment(comment);
    }
    @Override
    public Integer deleteComment(Integer commentid){
        return commentMapper.deleteComment(commentid);
    }
    @Override
    public Integer updateLikesByCommentid(Integer like, Integer commentid){
        return commentMapper.updateLikesByCommentid(like,commentid);
    }
}
