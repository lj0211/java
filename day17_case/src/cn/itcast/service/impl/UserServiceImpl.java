package cn.itcast.service.impl;

import java.util.List;
import java.util.Map;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userDao.delete(Integer.parseInt(id));
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.findById(Integer.parseInt(id));
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delSelectedUser(String[] ids) {
		// TODO Auto-generated method stub
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				userDao.delete(Integer.parseInt(id));
			}
		}
	}

	@Override
	public PageBean<User> findUserByPage(String _currentPage, String _rows,Map<String, String[]> condition) {
		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		if(currentPage<=0) {
			currentPage=1;
		}
		// 1、创建一个空的PageBean
		PageBean<User> pb = new PageBean<>();
		// 2、设置参数
		
		pb.setRows(rows);
		// 3、调用dao查询总记录数
		int totalCount = userDao.findTotalCount(condition);
		pb.setTotalCount(totalCount);
		// 4、调用dao查询List集合
		// 计算开始记录的索引
		int start = (currentPage - 1) * rows;
		List<User> list = userDao.findByPage(start, rows,condition);
		pb.setList(list);
		// 5、计算总页码
		int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows + 1);
		if(currentPage>=totalPage) {
			currentPage=totalPage;
		}
		pb.setCurrentPage(currentPage);
		pb.setTotalPage(totalPage);

		return pb;
	}

}
