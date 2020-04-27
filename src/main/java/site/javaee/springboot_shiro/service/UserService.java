package site.javaee.springboot_shiro.service;

import site.javaee.springboot_shiro.pojo.User;

/**
 * @author Tao
 * @create 2020/4/27 11:41
 */

public interface UserService {
    public User queryUserByName(String username);
}
