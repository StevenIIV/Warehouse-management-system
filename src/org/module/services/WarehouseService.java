package org.module.services;

import java.util.List;
import java.util.Vector;

import org.module.db.BaseDao;

public class WarehouseService {
	
	//遍历所有仓库
	public List selectAll() throws Exception {
		BaseDao dao = new BaseDao();
		List list = dao.select("select id,name from warehouse where 1=1 and del_flag='0' ", 2, null);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	//遍历所有仓库返回Vector
	public Vector<Vector> selectAllVexctor() throws Exception {
		Vector<Vector> rows = new Vector<Vector>();
		BaseDao dao = new BaseDao();
		List<Object[]> list = dao.select("select id,sort,name from warehouse where 1=1 and del_flag='0'  order by sort",
				3, null);
		if (!list.isEmpty()) {
			int number = 1;
			for (Object[] object : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < object.length; i++) {
					if (i == 1) {
						temp.add(number);
					} else {
						temp.add(object[i]);
					}
				}
				rows.add(temp);
				number++;
			}
		}
		return rows;
	}
	
	//通过Id修改仓库
	public int updateById(Object[] paramArray) throws Exception {
		int result = 0;
		BaseDao dao = new BaseDao();
		result = dao.update("update warehouse set name=? where id=?", paramArray);
		return result;
	}
	
	//通过Id逻辑删除仓库
	public int deleteById(Object[] paramArray) throws Exception {
		BaseDao dao = new BaseDao();
		int result = 0;
		result = dao.update("update warehouse set del_flag='1' where id=?", paramArray);
		return result;
	}
	
	//插入仓库
	public int insertById(Object[] paramArray) throws Exception {
		BaseDao dao = new BaseDao();
		int result = 0;
		result = dao.insert("insert into warehouse(id,name) values(?,?) ", paramArray);
		return result;
	}

}
