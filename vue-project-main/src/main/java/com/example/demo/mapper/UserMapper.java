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
    User getUserById(@Param("id") Integer id);//根据用户id获得对应用户信息

    @Select("SELECT * FROM user WHERE nickname = #{nickname}")
    User getUserBynickname(@Param("nickname") String nickname);//根据昵称获得对应用户信息

    @Select("SELECT * FROM user WHERE email = #{email}")
    User getUserByEmail(@Param("email") String email);//根据邮箱获得对应用户信息

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User getUserByPhone(@Param("phone") String phone);//根据手机号获得对应用户信息

    @Insert({ "insert into user(password, nickname, email, phone, picurl) values(#{password}, #{nickname}, #{email}, #{phone}, #{picurl})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
        //useGeneratedKeys: 当设置为 true 时，这告诉 MyBatis 在执行插入操作后，尝试检索数据库自动生成的键（通常是自增的主键）。这个功能通常用于在插入记录后获取该记录的自增 ID。
        //keyProperty: 这个属性指定了应该将检索到的自动生成的键值设置到目标对象的哪个属性上。
    int insert(User user);


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
