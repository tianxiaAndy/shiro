package com.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
public class ShiroRealm extends AuthenticatingRealm {
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
           //1.吧AuthenticationToken 转化为 UsernamePaswwordToken
           UsernamePasswordToken    uptoken =  (UsernamePasswordToken)token;
           //2.从UsernamePasswordToken  获取用户名
           String username = uptoken.getUsername();
          System.out.println("读出的数据为："+username);  
           //3.调用数据库方法 从数据库查询相应记录进行比对 抛出相应异常
           if("unknown".equals(username))  
        	   throw  new LockedAccountException("用户已锁定！");
           if("monster".equals(username))
                throw  new UnknownAccountException("账户不正确！");
         //5.根据用户情况封装AuthenticationInfo
         //1.principal  认证的实体信息 可以是username 也可以是数据表对应用户的实体类对象
         Object  principal = username;
         //credentials  密码 数据库获取的    MD5加密了 1024 次 无盐值
         Object  credentials = "64c8b1e43d8ba3115ab40bcea57f010b"; 
          // realmName  当前realm 对象的name  调用父类的 getName();
         String  realmName = getName();
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);  
		return info;
	}
	
	public static void main(String[] args) {
		String algorithmName = "MD5";
		String  source = "123";
        Object  salt  = null;
        int  hashIterations  = 1024;
		 SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
	   System.out.println(simpleHash.toString());
	}
	
	
	
}
