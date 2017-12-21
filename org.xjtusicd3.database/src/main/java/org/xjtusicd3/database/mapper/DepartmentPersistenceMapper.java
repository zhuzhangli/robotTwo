package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.DepartmentPersistence;

public interface DepartmentPersistenceMapper extends IBaseDao<DepartmentPersistence, String>{
	//查看还未获取该配置的部门
	@Select("SELECT * FROM TBL_Department WHERE DEPARTMENTID NOT IN (SELECT DEPARTMENTID FROM tbl_basicConfigure WHERE CONFIGUREID=#{0})")
	List<DepartmentPersistence> getUnGotDepList(String configureId);

	
	//获取所有部门信息
	@Select("SELECT * FROM TBL_Department ")
	List<DepartmentPersistence> getAllDepartment();


	//通过departmentId获得对应部门信息
	@Select("SELECT * FROM TBL_Department WHERE DEPARTMENTID = #{0}")
	List<DepartmentPersistence> getInfoByDepId(String departmentId);

}
