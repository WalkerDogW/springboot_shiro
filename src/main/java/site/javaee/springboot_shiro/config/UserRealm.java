package site.javaee.springboot_shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import site.javaee.springboot_shiro.pojo.User;
import site.javaee.springboot_shiro.service.UserService;
import site.javaee.springboot_shiro.service.impl.UserServiceImpl;

/**
 * @author Tao
 * @create 2020/4/26 13:43
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //拿到当前登录的对象
        Subject subject =  SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //设置用户权限
        simpleAuthorizationInfo.addStringPermission(user.getPermission());
        System.out.println(user.getPermission());
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");


//        String name = "root";
//        String password = "123456";
        //前端输入的账号密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //数据库中获取账号和密码
        User user = userService.queryUserByName(usernamePasswordToken.getUsername());
//
//        if (!usernamePasswordToken.getUsername().equals(name)) {
//            return null;//抛出异常 UnknownAccountException
//        }
        if(user == null){
            return null;
        }

        //颜值加密
        /*
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                credentialsSalt, realmName);

         */

        //密码认证，shiro做
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    }
}
