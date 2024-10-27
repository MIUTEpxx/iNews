package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service//这个注解用于标记该类为一个服务组件，Spring容器会自动创建它的实例，并管理其生命周期。
public class UserServiceImpl implements UserService {
    @Autowired//使用@Autowired注解自动装配UserMapper接口的实例，这样就可以在服务类中使用它来执行数据库操作
    private UserMapper userMapper;
    @Override
    public int save(User user) {//这是一个公共方法，用于保存或更新用户信息
        if(user.getId()==null){
            return userMapper.insert(user);//插入新用户记录
        }
        else{
            return userMapper.update(user);//更新现有用户记录
        }
    }
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
    @Override
    public User getUserByNickname(String nickname) {
        return userMapper.getUserBynickname(nickname);
    }
    @Override
    public User getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }
    @Override
    public User getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }
    @Override
    public boolean updatePicurlById(Integer id, String picurl){
        //更新用户头像
        if(userMapper.updatePicurlById(id,picurl)>0) return true;
        else return false;
    }
}