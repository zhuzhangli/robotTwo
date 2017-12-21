package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.DriverPersistence;

public interface DriversPersistenceMapper extends IBaseDao<DriverPersistence, String>{
	//查找驱动具体信息
	@Select("SELECT * FROM TBL_Driver WHERE CONFIGUREID=#{0}")
	List<DriverPersistence> getDriverInfo(String configureid);

}
