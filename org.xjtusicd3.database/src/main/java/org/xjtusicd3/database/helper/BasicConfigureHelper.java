package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.BasicConfigurePersistenceMapper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;

public class BasicConfigureHelper {
	
	//查看该软件是否已添加至标准配置库
	public static List<BasicConfigurePersistence> getCfgInfoFromBasic(String configureId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		List<BasicConfigurePersistence> list = mapper.getCfgInfoFromBasic(configureId);
		session.close();
		return list;
	}


	//添加至标准配置库
	public static void addToBasicCfg(String basicConfigureId, String configureId, String departmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		mapper.addToBasicCfg( basicConfigureId,  configureId,  departmentId);
		session.close();		
	}
		
	
	//将软件从标准配置库移除
	public static void deleteFromBasicCfg(String configureId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		mapper.deleteFromBasicCfg(configureId);
		session.close();	
	}


	//判断标准配置表中是否已存在此部门该配置记录
	public static List<BasicConfigurePersistence> isExist(String configureId, String departmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		List<BasicConfigurePersistence> list = mapper.isExist(configureId, departmentId);
		session.close();
		return list;
	}

	
	//通过部门ID查看标准配置信息
	public static List<BasicConfigurePersistence> getCfgByDepID(String departmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		List<BasicConfigurePersistence> list = mapper.getCfgByDepID(departmentId);
		session.close();
		return list;
	}


	//移除部门配置	
	public static void removeConfigure(String configureId, String departmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		BasicConfigurePersistenceMapper mapper = session.getMapper(BasicConfigurePersistenceMapper.class);
		mapper.removeConfigure(configureId,departmentId);
		session.close();	
		
	}

}
