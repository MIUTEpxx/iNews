package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//这个注解用于标记该接口为MyBatis的Mapper接口，这样Spring Boot在启动时可以自动扫描并创建这个接口的实现。
public interface UserMapper {

    @Select("SELECT * from sys_user")
    List<User> findAll();//从sys_user表中选择所有记录，并返回一个User对象的列表

    @Insert("INSERT into sys_user(username, password,nickname,email,phone,address) VALUES(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insert(User user);//使用@Insert注解定义了一个SQL插入操作，将一个User对象的属性插入到sys_user表中

    int update(User user);//已经在对应的User.xml映射文件中定义了相应的SQL更新语句

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);//使用@Delete注解定义了一个SQL删除操作，根据提供的用户ID删除sys_user表中的记录


    /*使用@Select注解定义了一个SQL查询，该查询实现了分页功能，并且可以根据用户名、电子邮件和地址进行模糊搜索。
    它返回一个User对象的列表，列表中的记录从pageNum开始，最多包含pageSize条记录。*/
    @Select("SELECT * from sys_user where username like concat('%',#{username},'%') " +
            "and email like concat('%',#{email},'%') and address like concat('%',#{address},'%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize, @Param("username")String username,
                          @Param("email")String email,@Param("address")String address);

    /*使用@Select注解定义了一个SQL查询，用于计算符合模糊搜索条件的总记录数。
    这个方法通常与selectPage方法一起使用，以实现分页功能。*/
    @Select("select count(*) from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') " +
            "and address like concat('%',#{address},'%')")
    Integer selectTotal(@Param("username")String username,@Param("email")String email,@Param("address")String address);
}
