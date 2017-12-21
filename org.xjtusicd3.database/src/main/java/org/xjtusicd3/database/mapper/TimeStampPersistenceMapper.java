package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.TimeStampPersistence;

public interface TimeStampPersistenceMapper extends IBaseDao<TimeStampPersistence, String>{
	//记录运行时间
	@Insert("INSERT INTO TBL_TIMESTAMP VALUES(#{0},#{1},#{2},#{3})")
	void addTimeStamp(String timeId, String path, long executionTime, long startTime);

}
