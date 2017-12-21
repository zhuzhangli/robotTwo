package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.TimeStampPersistenceMapper;

public class TimeStampHelper {

	//记录运行时间
	public static void addTimeStamp(String timeId, String path, long executionTime, long startTime) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		TimeStampPersistenceMapper mapper = session.getMapper(TimeStampPersistenceMapper.class);
		mapper.addTimeStamp(timeId,  path,  executionTime,  startTime);
		session.close();
		
	}

}
