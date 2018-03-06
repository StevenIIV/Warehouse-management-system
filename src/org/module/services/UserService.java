package org.module.services;

import java.util.List;


import org.module.db.BaseDao;
import org.module.entity.User;

public class UserService{
	
	//查询一条记录
	public User selectOne(Object[] paraArray) throws Exception {
		User user = new User();
		BaseDao dao = new BaseDao();
		String sql = "select id,name,password,identity from user where name=? and password=?";
		List list = dao.select(sql, 4, paraArray);
		if (!list.isEmpty()) {
			user.setId((String) ((Object[]) list.get(0))[0]);
			user.setName((String) ((Object[]) list.get(0))[1]);
			user.setPassword((String) ((Object[]) list.get(0))[2]);
			user.setIdentity((String) ((Object[]) list.get(0))[3]);
			return user;
		}
		return null;
	}
	
	//通过Id修改用户
	public int updateUserById(Object[] paraArray) throws Exception {
		int result = 0;
		BaseDao dao = new BaseDao();
		String sql = "update user set name = ?,password=? where id=?";
		result = dao.update(sql, paraArray);
		return result;
	}

}
