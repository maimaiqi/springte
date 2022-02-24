package com.maimai.springboot.mapper;

import com.maimai.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from sys_user")
    List<User> findAll();

    @Insert("INSERT INTO sys_user(username,password,nickname,email,phone,address)"+
            " VALUES (#{username}, #{password},#{nickname}, #{email}, #{phone}, #{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    List<User> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username, @Param("email") String email, @Param("address") String address);

    @Select("select count(*) from sys_user where username like #{username} and email like #{email} and address like #{address}")
    Integer selectTotal(String username,String email,String address);
}
