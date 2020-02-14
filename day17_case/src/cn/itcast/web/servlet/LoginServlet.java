package cn.itcast.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		//1、设置编码
		request.setCharacterEncoding("utf-8");
		//2、获取数据
		//2.1获取用户填写的验证码
		String verifycode=request.getParameter("verifycode");
		//2.2验证码校验
		HttpSession session = request.getSession();
		String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
		if(!checkcode_server.equalsIgnoreCase(verifycode)) {
			//验证码不正确
			//提示信息
			request.setAttribute("login_msg", "验证码错误！");
			//跳转登录页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
			return;
		}
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		User user=new User();
//		user.setUsername(username);
//		user.setPassword(password);
		//3、封装User对象
		Map<String,String[]> map=request.getParameterMap();
		User user=new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4、调用service查询
		UserService userService=new UserServiceImpl();
		User loginUser = userService.login(user);
		//5、判断是否登录成功
		if(loginUser!=null) {
			//登录成功
			//将用户存入session
			session.setAttribute("user", loginUser);
			//跳转页面
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else {
			//登录失败
			//提示信息
			request.setAttribute("login_msg", "用户名或密码错误！");
			//跳转登录页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
