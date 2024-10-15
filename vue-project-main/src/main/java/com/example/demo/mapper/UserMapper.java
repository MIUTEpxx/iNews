package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from sys_user")
    List<User> findAll();

    @Insert("INSERT into sys_user(username, password,nickname,email,phone,address) VALUES(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("SELECT * from sys_user where username like concat('%',#{username},'%') " +
            "and email like concat('%',#{email},'%') and address like concat('%',#{address},'%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize, @Param("username")String username,
                          @Param("email")String email,@Param("address")String address);

    @Select("select count(*) from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') " +
            "and address like concat('%',#{address},'%')")
    Integer selectTotal(@Param("username")String username,@Param("email")String email,@Param("address")String address);
}
