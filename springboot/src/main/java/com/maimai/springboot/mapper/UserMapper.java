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


    List<User> selectPage(Integer pageNum,Integer pageSize,String username);

    @Select("select count(*) from sys_user where username like #{username}")
    Integer selectTotal(String username);
}
