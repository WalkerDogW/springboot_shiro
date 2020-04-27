package site.javaee.springboot_shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import site.javaee.springboot_shiro.pojo.User;

/**
 * @author Tao
 * @create 2020/4/27 11:29
 */
@Repository
@Mapper
public interface UserMapper {
    public User queryUserByName(String username);
}
