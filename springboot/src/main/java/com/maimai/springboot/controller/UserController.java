package com.maimai.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maimai.springboot.controller.dto.UserDTO;
import com.maimai.springboot.entity.User;
import com.maimai.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maimai
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;
    //private IUserService userService;${ table.serviceName}= IUserService,${ table.entityPath}=user

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return false;
        }
        return userService.login(userDTO);
    }
    //新增和修改
    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        return userService.list();
    }

    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeByIds(ids);
    }

    //分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){//不传的话默认置为空
        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        };
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //从数据库查询出所有的数据
        List<User> list = userService.list();
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarUrl","头像");

        //一次性写出list内的对象到excel,使用默认样式，强制输出标题
        writer.write(list,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();


    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //方式一：通过javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        //List<User> list = reader.readAll(User.class);
        //userService.saveBatch(list);
        //System.out.println(list);

        //方式二：忽略表头的中文，固定死每个字段对应哪一列，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list){
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            users.add(user);
        }
        userService.saveBatch(users);
        return true;
    }

}

