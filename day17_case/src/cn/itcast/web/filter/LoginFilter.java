package cn.itcast.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 完成登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(request);
		//0、强制转换
		HttpServletRequest req=(HttpServletRequest)request;
		//1、判断是否是登录相关的资源
		String uri = req.getRequestURI();
		//2、判断是否包含登录相关资源路径,要注意排除掉一些css/js/图片/验证码等资源
		if(uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")) {
			//包含，用户就是想登录，放行
			chain.doFilter(request, response);
		}else {
			//不包含，需要验证用户是否登录
			//3、从session中去获取user
			Object user = req.getSession().getAttribute("user");
			if(user!=null) {
				//登录了，放行
				chain.doFilter(request, response);
			}else {
				//没有登录，跳转登录页面
				req.setAttribute("login_msg", "您尚未登录，请登录");
				req.getRequestDispatcher("/login.jsp").forward(req, response);
			}
		}
		
		
		
		
		//chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void destroy() {
		
	}

}
