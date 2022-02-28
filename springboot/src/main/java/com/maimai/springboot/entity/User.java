package com.maimai.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//代替下面所有的get,set方法
//@NoArgsConstructor //创建无参构造
//@AllArgsConstructor //创建有参构造
@TableName(value = "sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore //json中不展示password
    private String password;
    @TableField(value = "nickname")//给字段指定别名，此处是数据库的名字
    private String nickname;//这里是项目里的名称
    private String email;
    private String phone;
    private String address;

}
