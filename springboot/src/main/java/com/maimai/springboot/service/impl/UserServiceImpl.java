package com.maimai.springboot.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maimai.springboot.controller.dto.UserDTO;
import com.maimai.springboot.entity.User;
import com.maimai.springboot.mapper.UserMapper;
import com.maimai.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maimai
 * @since 2022-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Override
    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
//        List<User> list = list(queryWrapper);
//        return list.size() !=0;
        //方法二
        try {
            User one = getOne(queryWrapper);
            return one != null;
        } catch (Exception e){
            //打印log
            LOG.error(e);
            return false;
        }
    }
}
