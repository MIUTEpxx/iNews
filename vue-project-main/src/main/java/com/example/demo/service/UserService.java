package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public int save(User user);
    public User getUserByUserid(Integer userid);
    public User getUserByNickname(String nickname);
    public User getUserByEmail(String email);
    public User getUserByPhone(String phone);
    public boolean updatePicurlByUserid(Integer userid, String picurl);
}

/*这个服务类UserService提供了一个简单的业务逻辑层，用于处理用户的保存和更新操作。
它依赖于UserMapper接口来与数据库进行交互。
这样的设计将数据访问逻辑（由UserMapper实现）与业务逻辑（由UserService实现）分离，使得代码更加模块化和易于维护*/
