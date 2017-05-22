package com.helloworld.hwblog.user.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helloworld.hwblog.user.dao.UserAccountDao;
import com.helloworld.hwblog.user.dao.UserInfoDao;
import com.helloworld.hwblog.user.entity.UserAccount;
import com.helloworld.hwblog.user.entity.UserInfo;
import com.helloworld.hwblog.user.model.LoginModel;
import com.helloworld.hwblog.user.model.LoginTwoModel;
import com.helloworld.hwblog.user.model.RegisteoneModel;
import com.helloworld.hwblog.user.model.RegistetwoModel;
import com.helloworld.hwblog.user.service.UserService;
@Service
public class UserSeviceImpl implements UserService {
	 @Autowired
	 private UserAccountDao useraccoundao;
	 @Autowired
	 private UserInfoDao userinfodao;

	public void setUseraccoundao(UserAccountDao useraccoundao) {
		this.useraccoundao = useraccoundao;
	}

	public void setUserinfodao(UserInfoDao userinfodao) {
		this.userinfodao = userinfodao;
	}

	@Override
	public boolean regist(RegisteoneModel resgite) {
		// TODO Auto-generated method stub
		//判断重复密码是否相同
		if(!resgite.getPassword().equals(resgite.getRepassword())) return false;
		//是否同名
		if(useraccoundao.getAcUser(resgite.getUsername(),resgite.getPassword())!=null) return false;
		
		// 
				/*String checkcode_session = (String) request.getSession().getAttribute(
						"checkcode_session");
				request.getSession().removeAttribute("checkcode_session");
				if (resgite.getCheckcode() == null || !resgite.getCheckcode().equals(checkcode_session)) {
					// 验证码错误
					request.setAttribute("msg", "验证码错误");
					return false;
				} */
				else {
					
					UserAccount useraccount=new UserAccount();
					useraccount.setUsername(resgite.getUsername());
					useraccount.setPassword(resgite.getPassword());
					useraccount.setEmail(resgite.getEmail());		
					useraccoundao.addAcUser(useraccount);
					//
					UserInfo userinfo=new UserInfo( resgite.getUsername(), "icon_phtot", 1,
							new Date(), "location blog", null, "basketball",
							resgite.getUsername(),0);
					  userinfodao.addInUser(userinfo);
		            
		              userinfo= userinfodao.getInUser(resgite.getUsername());

		              HttpServletRequest request= ServletActionContext.getRequest();
		              request.getSession().setAttribute("reginfo", userinfo);
		         
		               return true;
					}
		
		
		
	}

	@Override
	public boolean regist2(RegistetwoModel resgite) {
		// TODO Auto-generated method stub
		//获取第一个model
		HttpServletRequest request= ServletActionContext.getRequest();
        request.getSession().getAttribute("reginfo");
        //把获得的session变为一个对象
        UserInfo userinfotwo=(UserInfo)request.getSession().getAttribute("reginfo");
		userinfodao.updateInUser(new UserInfo(resgite.getNickName(),resgite.getIcon(),resgite.getSex(),
				resgite.getBirthday(),resgite.getAddress(),resgite.getQqAcount(),
				resgite.getHobby(),userinfotwo.getUsername(),0));
		//System.out.println("userInfo"+userinfotwo);
		return true;
	}

	@Override
	public boolean login(LoginModel login) {
		 // 传递用户名 和密码 到DAO
		//return userdao.getUser(login.getUsername(),login.getPassword());
		HttpServletRequest request= ServletActionContext.getRequest();
		//转化为String类型
        String cCode=(String)request.getSession().getAttribute("checkCode");
        if(!cCode.equals(login.getCheckcode())) return false;
		 UserAccount user=useraccoundao.getAcUser(login.getUsername(),login.getPassword());
		 if(user==null) return false;
		 //从userInfo表里面查出来封装到LogintwoModel
		 UserInfo userinfo=userinfodao.getInUser(login.getUsername());
		 LoginTwoModel logintwomodel=new LoginTwoModel(userinfo.getUsername(),userinfo.getNickName(),userinfo.getIcon());
		 //存入session中
		 HttpServletRequest request2= ServletActionContext.getRequest();
		 request2.getSession().setAttribute("loginUser", logintwomodel);
		 return true;
		        
	}

}
