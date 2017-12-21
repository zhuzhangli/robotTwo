package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.DataDictionaryPersistence;

public interface DataDictionaryPersistenceMapper extends IBaseDao<DataDictionaryPersistence, String>{
	//查看还未获取该配置的部门
	@Select("SELECT * FROM tbl_datadictionary WHERE  DATATYPE = '1' AND DATADICTIONARYID NOT IN (SELECT DEPARTMENTID FROM tbl_basicConfigure WHERE CONFIGUREID=#{0})")
	List<DataDictionaryPersistence> getUnGotDepList(String configureId);

	
	//获取所有部门信息
	@Select("SELECT * FROM tbl_datadictionary  WHERE  DATATYPE = '1'")
	List<DataDictionaryPersistence> getAllDepartment();

}
