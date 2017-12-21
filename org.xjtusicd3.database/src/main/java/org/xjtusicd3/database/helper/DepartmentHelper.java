package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.DepartmentPersistenceMapper;
import org.xjtusicd3.database.model.DepartmentPersistence;

public class DepartmentHelper {
	//查看还未获取该配置的部门
	public static List<DepartmentPersistence> getUnGotDepList(String configureId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DepartmentPersistenceMapper mapper = session.getMapper(DepartmentPersistenceMapper.class);
		List<DepartmentPersistence> list = mapper.getUnGotDepList( configureId);
		session.close();
		return list;
	}

	
	//获取所有部门信息
	public static List<DepartmentPersistence> getAllDepartment() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DepartmentPersistenceMapper mapper = session.getMapper(DepartmentPersistenceMapper.class);
		List<DepartmentPersistence> list = mapper.getAllDepartment();
		session.close();
		return list;
	}


	//通过departmentId获得对应部门信息
	public static List<DepartmentPersistence> getInfoByDepId(String departmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DepartmentPersistenceMapper mapper = session.getMapper(DepartmentPersistenceMapper.class);
		List<DepartmentPersistence> list = mapper.getInfoByDepId(departmentId);
		session.close();
		return list;
	}

}
