package cn.itcast.web.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.jsp.jstl.core.IteratedExpression;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println(request);
		//1、创建代理对象，增强getParameter方法
		ServletRequest proxy_req=(ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//增强getParameter方法
				//判断是不是getParameter方法
				if(method.getName().equals("getParameter")) {
					//增强返回值
					//获取返回值
					String value=(String) method.invoke(request, args);
					if(value!=null) {
						for(String str:list) {
							if(value.contains(str)) {
								value=value.replaceAll(str,"***");
							}
						}
					}
				
					return value;
					
				}
				
				//判断方法名是否是 getParameterMap
				
				//判断方法名是否是getParameterValues
				
				return method.invoke(request, args);
			}
		});
		
		//2、放行,放过去的是代理的request
		chain.doFilter(proxy_req, response);
	}

	private List<String> list=new ArrayList<String>();//敏感词汇的集合
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			//1、加载文件，获取文件的真实路径
			ServletContext context = fConfig.getServletContext();
			String realPath=context.getRealPath("/WEB-INF/classes/敏感词汇.txt");
			//2、读取文件
			BufferedReader br=new BufferedReader(new FileReader(realPath));
			//3、将文件的每一行数据添加到list中
			String line=null;
			while((line=br.readLine())!=null){
				list.add(line);
			}
			br.close();
			//System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
