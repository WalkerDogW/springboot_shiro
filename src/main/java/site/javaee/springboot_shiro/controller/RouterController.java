package site.javaee.springboot_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.javaee.springboot_shiro.config.ShiroQuickstart;

/**
 * @author Tao
 * @create 2020/4/23 20:27
 */
@Controller
public class RouterController {
    //首页
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    //去往登录页面
    @RequestMapping({"/toLogin"})
    public String toLogin(){
        return "views/login";
    }


    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id){
        return "views/level1/"+id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id){
        return "views/level2/"+id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id){
        return "views/level3/"+id;
    }

    //登录页面
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        //执行登录的方法
        try {
            subject.login(usernamePasswordToken);
            return "index";
        } catch (UnknownAccountException uae) {
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "views/login";
        } catch (IncorrectCredentialsException ice) {
            //密码错误
            model.addAttribute("msg","密码错误");
            return "views/login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg","账号已锁定");
            return "views/login";
        }
        catch (AuthenticationException ae) {
            model.addAttribute("msg","其他错误");
            return "views/login";
        }
    }

    //未授权页面
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }

    //注销页面
    @RequestMapping("/logout")
    public String logout(Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出");
        return "views/login";
    }
}
