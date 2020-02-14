package cn.itcast.web.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{

	/**
	 * 服务器关闭后，ServletContext对象被销毁。
	 * 当服务器正常关闭后该方法被调用
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContext对象被销毁了。。。");
		
	}

	/**
	 * 监听ServletContext对象创建的，服务器启动后自动创建
	 * 该方法在服务器启动后自动调用
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		//加载资源文件
		//1、获取servletContext对象
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		//2、加载资源文件
		String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
		
		//3、获取真实路径
		String realPath = servletContext.getRealPath(contextConfigLocation);
		
		//4、加载进内存
		try {
			FileInputStream fis=new FileInputStream(realPath);
			System.out.println(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("ServletContext对象被创建了。。。");
		
	}

}
