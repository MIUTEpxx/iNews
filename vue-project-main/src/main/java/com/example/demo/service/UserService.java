package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service//这个注解用于标记该类为一个服务组件，Spring容器会自动创建它的实例，并管理其生命周期。
public class UserService {
    @Autowired//使用@Autowired注解自动装配UserMapper接口的实例，这样就可以在服务类中使用它来执行数据库操作
    private UserMapper userMapper;
    public int save(User user) {//这是一个公共方法，用于保存或更新用户信息

        if(user.getId()==null){
            return userMapper.insert(user);//插入新用户记录
        }
        else{
            return userMapper.update(user);//更新现有用户记录
        }
    }
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
    public User getUserByNickname(String nickname) {
        return userMapper.getUserBynickname(nickname);
    }
}
/*这个服务类UserService提供了一个简单的业务逻辑层，用于处理用户的保存和更新操作。
它依赖于UserMapper接口来与数据库进行交互。
这样的设计将数据访问逻辑（由UserMapper实现）与业务逻辑（由UserService实现）分离，使得代码更加模块化和易于维护*/
