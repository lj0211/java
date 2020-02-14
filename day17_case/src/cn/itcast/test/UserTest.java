package cn.itcast.test;

import java.util.List;

import org.junit.Test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;

public class UserTest {
	
	private UserDao userDao=new UserDaoImpl();

	@Test
	public void test() {
//		List<User> users=userDao.findAll();
//		User user=userDao.findUserByUsernameAndPassword("lisi", "123");
//		userDao.add(user);
//		System.out.println(user);
//		System.out.println(users);
//		userDao.delete(4);
//		User user=userDao.findById(1);
//		System.out.println(user);
		
		User user=new User();
		user.setId(1);
		user.setName("张三");
		user.setGender("男");
		user.setAge(33);
		user.setAddress("中国");
		user.setQq("123456");
		user.setEmail("123456@qq.com");
		userDao.update(user);
		
	}
}
