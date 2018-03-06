package org.module.services;

import java.util.List;

import org.module.db.BaseDao;
public class CategoryService {
	
	//查询所有分类
	public List selectAll() throws Exception {
		BaseDao dao = new BaseDao();
		List list = dao.select("select id,name from category where 1=1 and del_flag='0' ", 2, null);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

}
