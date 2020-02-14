package cn.itcast.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.User;

/**
 * 用户操作的DAO
 * @author admin
 *
 */
public interface UserDao {

	public List<User> findAll();
	
	public User findUserByUsernameAndPassword(String username,String password);
	
	public void add(User user);
	
	public void delete(int id);
	
	public User findById(int i);
	
	public void update(User user);
	
	/**
	 * 查询总记录数
	 * @param condition 
	 * @param condition
	 * @return
	 */
	public int findTotalCount(Map<String, String[]> condition);
	
	/**
	 * 分页查询每页记录
	 * @param start
	 * @param rows
	 * @param condition 
	 * @param condition
	 * @return
	 */
	public List<User> findByPage(int start,int rows, Map<String, String[]> condition);


	
}
