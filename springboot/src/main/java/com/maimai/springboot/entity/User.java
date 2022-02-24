package com.maimai.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//代替下面所有的get,set方法
//@NoArgsConstructor //创建无参构造
//@AllArgsConstructor //创建有参构造
public class User {
    private Integer id;
    private String username;
    @JsonIgnore //json中不展示password
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

}
