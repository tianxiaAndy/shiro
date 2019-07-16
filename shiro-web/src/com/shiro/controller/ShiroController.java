package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/shiro")
@Controller
public class ShiroController {
 
	 @RequestMapping("/login")
	public  String  login(@RequestParam("name") String name, 
			 @RequestParam("pwd") String pwd) {
		 System.out.println("Nmae:"+name +"PWD:"+pwd);
		 //获得Subject  
		 Subject subject = SecurityUtils.getSubject();		 
		 if(!subject.isAuthenticated()) {		 
			 UsernamePasswordToken  token = new UsernamePasswordToken(name, pwd);
			 try {				
				 subject.login(token);
			} catch (AuthenticationException e) {
				 System.out.println("登录失败："+e);
			}
			 
		  System.out.println("----> 获取登录名 [" + subject.getPrincipal() + "] logged in successfully.");
		 }
		 
		 
		 return "redirect:/list.jsp";
	}
	 
	 
}
