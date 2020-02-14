package cn.itcast.service;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

public interface UserService {
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 登录方法
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 保存User
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 根据id删除User
	 * @param id
	 */
	public void deleteUser(String id);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public User findUserById(String id);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 批量删除用户
	 * @param ids
	 */
	public void delSelectedUser(String[] ids);

	/**
	 * 分页条件查询
	 * @param currentPage
	 * @param rows
	 * @param condition 
	 * @return
	 */
	public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
	
	
}
