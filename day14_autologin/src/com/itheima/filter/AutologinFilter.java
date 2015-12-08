package com.itheima.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.domain.User;
import com.itheima.util.DaoUtils;

public class AutologinFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		//1.只有未登录的用户才能自动登陆
		if(req.getSession(false)==null||req.getSession().getAttribute("user")==null){
			//2.只有带了自动登陆cookie的用户才能自动登陆
			Cookie[]cs=req.getCookies();
			Cookie findC=null;
			if(cs!=null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						findC=c;
						break;
					}
				}
			}
			if(findC!=null){
				//3.自动登录Cookie中保存的用户名密码都需要是正确的才能自动登陆
				String name=findC.getValue().split(":")[0];
				String password=findC.getValue().split(":")[1];
				String sql="select * from user where name = ? and password = ?";
				User user=null;
				try {
					QueryRunner runner=new QueryRunner(DaoUtils.getSource());
					user=runner.query(sql, new BeanHandler<User>(User.class),name,password);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(user!=null)
					req.getSession().setAttribute("user", user);
			}
		}
		//无论是否自动登陆,都放行资源
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
