package cn.itcast.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

/**
 * Servlet implementation class findUserByPageServlet
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		//1、获取参数
		String currentPage=request.getParameter("currentPage");//当前页码
		String rows = request.getParameter("rows");//每页显示条数
		if(currentPage==null||"".equals(currentPage)) {
			currentPage="1";
		}
		if(rows==null||"".equals(rows)) {
			rows="5";
		}
		
		//获取条件查询的参数
		Map<String, String[]> condition = request.getParameterMap();
		request.setAttribute("condition",condition);//将查询条件存入request
		//2、调用service来查询
		UserService service=new UserServiceImpl();
		PageBean<User> pb=service.findUserByPage(currentPage,rows,condition);
		
		//System.out.println(pb);
		//3、将pageBean存入request
		request.setAttribute("pb",pb);
		//4、转发到list.jsp
		request.getRequestDispatcher("/list.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
