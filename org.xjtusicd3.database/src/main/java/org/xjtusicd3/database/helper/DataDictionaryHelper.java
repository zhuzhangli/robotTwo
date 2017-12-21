package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.DataDictionaryPersistenceMapper;
import org.xjtusicd3.database.model.DataDictionaryPersistence;

public class DataDictionaryHelper {
	//查看还未获取该配置的部门
	public static List<DataDictionaryPersistence> getUnGotDepList(String configureId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DataDictionaryPersistenceMapper mapper = session.getMapper(DataDictionaryPersistenceMapper.class);
		List<DataDictionaryPersistence> list = mapper.getUnGotDepList( configureId);
		session.close();
		return list;
	}

	
	//获取所有部门信息
	public static List<DataDictionaryPersistence> getAllDepartment() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DataDictionaryPersistenceMapper mapper = session.getMapper(DataDictionaryPersistenceMapper.class);
		List<DataDictionaryPersistence> list = mapper.getAllDepartment();
		session.close();
		return list;
	}

}
