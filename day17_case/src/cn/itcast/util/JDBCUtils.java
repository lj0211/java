package cn.itcast.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * JDBC工具类，使用Druid连接池
 * @author admin
 *
 */
public class JDBCUtils {
	
	private static DataSource ds;
	
	static {
		
		try {
			//1、加载配置文件
			Properties pro=new Properties();
			//使用ClassLoader加载配置文件，获取字节输入流
			InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
			pro.load(is);
			
			//2、初始化连接池对象
			ds=DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取连接池对象
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * 获取连接Connection对象
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
