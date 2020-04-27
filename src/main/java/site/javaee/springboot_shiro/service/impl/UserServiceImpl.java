package site.javaee.springboot_shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.javaee.springboot_shiro.mapper.UserMapper;
import site.javaee.springboot_shiro.pojo.User;
import site.javaee.springboot_shiro.service.UserService;

/**
 * @author Tao
 * @create 2020/4/27 11:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
