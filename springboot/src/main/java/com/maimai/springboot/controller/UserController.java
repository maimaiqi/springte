package com.maimai.springboot.controller;

import com.maimai.springboot.entity.User;
import com.maimai.springboot.mapper.UserMapper;
import com.maimai.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping
    public Integer save(@RequestBody User user){
        //新增或者更新
        return userService.save(user);
    }

    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        List<User> all = userMapper.findAll();
        return all;
    }

    //分页查询
    //接口路径、/user/page
    //@RequestParam接收？pageNum=1&pageSize=10
    //limit第一个参数=（pageNum - 1)*pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(required = false) String username,
                                       @RequestParam(required = false) String email,
                                       @RequestParam(required = false) String address){
        pageNum = (pageNum - 1) * pageSize;
        username = "%"+username+"%";
        email = "%"+email+"%";
        address = "%"+address+"%";
        List<User> data = userMapper.selectPage(pageNum,pageSize,username,email,address);
        Integer total = userMapper.selectTotal(username,email,address);

        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }
}
