package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.DriversPersistenceMapper;
import org.xjtusicd3.database.mapper.SoftPersistenceMapper;
import org.xjtusicd3.database.model.DriverPersistence;
import org.xjtusicd3.database.model.SoftPersistence;

public class DriversHelper {
	/*
	 * spider_驱动的增加
	 */
	public static void sava(DriverPersistence driversPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DriversPersistenceMapper mapper = session.getMapper(DriversPersistenceMapper.class);
		mapper.save(driversPersistence);
		session.close();
	}

	
	//查找驱动具体信息
	public static List<DriverPersistence> getDriverInfo(String configureid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DriversPersistenceMapper mapper = session.getMapper(DriversPersistenceMapper.class);
		List<DriverPersistence> list = mapper.getDriverInfo(configureid);
		session.close();
		return list;
	}
}
