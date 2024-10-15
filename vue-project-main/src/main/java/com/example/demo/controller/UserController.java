package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//标记这个类为Spring MVC的控制器，并且其中的每个方法返回的对象都会直接序列化为HTTP响应体
@RequestMapping("/user")//表示这个控制器处理的所有请求都会映射到以/user为前缀的路径
public class UserController {
    @Autowired//注解用于自动装配UserMapper的实例，以便在控制器中使用它们的方法
    private UserMapper userMapper;

    @Autowired//注解用于自动装配UserService的实例，以便在控制器中使用它们的方法
    private UserService userService;

    //新增和修改
    @PostMapping//注解标记这是一个处理HTTP POST请求的方法
    public Integer save(@RequestBody User user) {//@RequestBod注解表示参数user应该从HTTP请求体中绑定
        //新增和更新
        return userService.save(user);//这个方法用于新增或更新用户信息，并调用UserService的save方法
    }

    //查询所有数据
    @GetMapping//注解标记这是一个处理HTTP GET请求的方法
    public List<User> findAll() {
        return userMapper.findAll();
    }//返回所有用户的列表，通过调用UserMapper的findAll方法实现

    @DeleteMapping("/{id}")//注解标记这是一个处理HTTP DELETE请求的方法，其中{id}是一个路径变量
    public Integer delete(@PathVariable Integer id) {//@PathVariable注解表示参数id应该从URI路径中绑定
        return userMapper.deleteById(id);//根据提供的用户ID删除用户，通过调用UserMapper的deleteById方法实现
    }

    @GetMapping("/page")//注解标记这是一个处理HTTP GET请求的方法，用于分页查询 @RequestParam注解表示参数应该从请求的查询参数中绑定
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username,
                                        @RequestParam String email, @RequestParam String address) {
        pageNum = (pageNum - 1) * pageSize;//计算分页的起始位置
        //获取分页数据和总记录数
        List<User> data = userMapper.selectPage(pageNum, pageSize, username,email,address);
        Integer total = userMapper.selectTotal(username,email,address);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
