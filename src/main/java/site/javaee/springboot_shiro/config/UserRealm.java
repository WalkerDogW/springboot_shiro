package site.javaee.springboot_shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author Tao
 * @create 2020/4/26 13:43
 */
public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");

        //数据库中获取账号和密码
        String name = "root";
        String password = "123456";

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        if (!usernamePasswordToken.getUsername().equals(name)) {
            return null;//抛出异常 UnknownAccountException
        }

        //密码认证，shiro做
        return new SimpleAuthenticationInfo("", password, "");
    }
}
