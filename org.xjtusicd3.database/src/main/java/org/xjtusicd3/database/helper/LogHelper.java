package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.LogPersistenceMapper;
import org.xjtusicd3.database.model.LogPersistence;

public class LogHelper
{
	/**
	 * 保存日志
	 * @param myLog
	 */
	public static void insertLog(LogPersistence myLog) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		mapper.save(myLog);
		session.close();
		
	}
	
	/**
	 * author:zzl
	 * abstract:获取用户日志
	 * data:2017年9月15日09:16:32
	 * @param userid 
	 */
	public static List<LogPersistence> getLogs(String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		List<LogPersistence> log = mapper.getLogs(userid);
		session.close();
		return log;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zpz_get information of advise
	 */
	public static List<LogPersistence> getLog()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		List<LogPersistence> log = mapper.getLog();
		session.close();
		return log;
		
	}

}