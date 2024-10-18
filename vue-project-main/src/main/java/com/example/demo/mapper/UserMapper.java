package com.example.demo.mapper;

import com.example.demo.entity.News;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//这个注解用于标记该接口为MyBatis的Mapper接口，这样Spring Boot在启动时可以自动扫描并创建这个接口的实现。
public interface UserMapper {

    @Select("SELECT * from user")
    List<User> findAll();//从use表中选择所有记录，并返回一个User对象的列表

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE nickname = #{nickname}")
    User getUserBynickname(@Param("nickname") String nickname);

    @Insert("INSERT into user(password,nickname,email,phone) VALUES #{password},#{nickname},#{email},#{phone}")
    int insert(User user);//使用@Insert注解定义了一个SQL插入操作，将一个User对象的属性插入到use表中

    int update(User user);//已经在对应的User.xml映射文件中定义了相应的SQL更新语句

    @Delete("delete from user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);//使用@Delete注解定义了一个SQL删除操作，根据提供的用户ID删除use表中的记录


    /*使用@Select注解定义了一个SQL查询，该查询实现了分页功能，并且可以根据用户名、电子邮件和地址进行模糊搜索。
    它返回一个User对象的列表，列表中的记录从pageNum开始，最多包含pageSize条记录。*/
    @Select("SELECT * from user where nickname like concat('%',#{nickname},'%') " +
            "and email like concat('%',#{email},'%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize, @Param("nickname")String nickname,
                          @Param("email")String email);

    /*使用@Select注解定义了一个SQL查询，用于计算符合模糊搜索条件的总记录数。
    这个方法通常与selectPage方法一起使用，以实现分页功能。*/
    @Select("select count(*) from user where nickname like concat('%',#{nickname},'%') and email like concat('%',#{email},'%') ")
    Integer selectTotal(@Param("nickname")String nickname,@Param("email")String email);
}
