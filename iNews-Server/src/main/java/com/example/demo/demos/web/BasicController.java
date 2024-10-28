/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */

@Controller//标记这个类为一个Spring MVC的控制器，它将处理HTTP请求
public class BasicController {

    // http://127.0.0.1:9090/hello?name=lisi
    @RequestMapping("/hello")//将HTTP请求/hello映射到这个方法
    @ResponseBody//表示方法的返回值直接写入HTTP响应体
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {//方法接受一个名为name的请求参数，默认值为"unknown user"
        return "Hello " + name;
    }

    // http://127.0.0.1:8081/user
    @RequestMapping("/user")//将HTTP请求/user映射到这个方法
    @ResponseBody//表示方法的返回值直接写入HTTP响应体。
    public User user() {
        //方法创建并返回一个User对象，其中包含预设的姓名和年龄
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8081/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8081/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute//这个方法将在每个请求处理方法之前执行，用于绑定请求参数到模型对象
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
