package com.helloworld.hwblog.user.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.helloworld.hwblog.user.model.LoginModel;
import com.helloworld.hwblog.user.service.UserService;
import com.opensymphony.xwork2.ModelDriven;
@Controller
public class LoginAction implements ModelDriven<LoginModel> {
	@Autowired
	private UserService usersevice;
	public void setUsersevice(UserService usersevice) {
		this.usersevice = usersevice;
     }
    LoginModel logmodel=new LoginModel();
	@Override
	public LoginModel getModel() {
		// TODO Auto-generated method stub
		return logmodel;
	}
   public String login(){
	
	if(usersevice.login(logmodel))
	{     
		 
		return "success";
	}
		
	else
	{
	return "error";
	}
 }
   public String toLoginPage(){
	   int x;//定义两变量
       Random ne=new Random();//实例化一个random的对象ne
       x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
       String checkcode=String.valueOf(x);//转化为String类型
       System.out.println("产生的随机数是:"+checkcode);//输出
       //将验证码保存到session中
       HttpSession session = ServletActionContext.getRequest().getSession();
       session.setAttribute("checkCode", checkcode);
	   return "tolpage";
   }
   
}
