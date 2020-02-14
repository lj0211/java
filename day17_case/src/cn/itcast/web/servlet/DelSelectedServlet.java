package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;

import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

/**
 * Servlet implementation class DelSelectedServlet
 */
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelSelectedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取所有的id
		String[] ids = request.getParameterValues("uid");
		//2、调用service删除
		UserService service=new UserServiceImpl();
		service.delSelectedUser(ids);
		
		//3、跳转到userListServlet
		
		response.sendRedirect(request.getContextPath()+"/userListServlet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
