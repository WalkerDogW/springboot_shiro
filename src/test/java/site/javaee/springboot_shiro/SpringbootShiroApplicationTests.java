package site.javaee.springboot_shiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.javaee.springboot_shiro.service.UserService;
import site.javaee.springboot_shiro.service.impl.UserServiceImpl;

@SpringBootTest
class SpringbootShiroApplicationTests {


    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {


        System.out.println(userService.queryUserByName("admin"));
        System.out.println(userService.queryUserByName("admin2"));
        System.out.println("-----------------------hello-------------------------------");
    }

}
