package com.maimai.springboot.service.impl;

import com.maimai.springboot.entity.User;
import com.maimai.springboot.mapper.UserMapper;
import com.maimai.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
