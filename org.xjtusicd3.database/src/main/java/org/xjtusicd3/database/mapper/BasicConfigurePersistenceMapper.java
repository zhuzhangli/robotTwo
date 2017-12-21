package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.BasicConfigurePersistence;

public interface BasicConfigurePersistenceMapper extends IBaseDao<BasicConfigurePersistence, String>{
	
	//查看该软件是否已添加至标准配置库
	@Select("SELECT * FROM TBL_BasicConfigure WHERE CONFIGUREID=#{0}")
	List<BasicConfigurePersistence> getCfgInfoFromBasic(String configureId);
	
	
	//添加至标准配置库
	@Insert("INSERT INTO TBL_BasicConfigure VALUES(#{0},#{1},#{2})")
	void addToBasicCfg(String basicConfigureId, String configureId, String department);

	
	//将软件从标准配置库移除
	@Delete("DELETE FROM TBL_BasicConfigure WHERE CONFIGUREID=#{0}")
	void deleteFromBasicCfg(String configureId);


	//判断标准配置表中是否已存在此部门该配置记录
	@Select("SELECT * FROM TBL_BasicConfigure WHERE CONFIGUREID=#{0} AND DEPARTMENTID=#{1}")
	List<BasicConfigurePersistence> isExist(String configureId, String departmentId);


	//通过部门ID查看标准配置信息
	@Select("SELECT * FROM TBL_BasicConfigure,TBL_Configure WHERE TBL_BasicConfigure.CONFIGUREID = TBL_Configure.CONFIGUREID  AND TBL_BasicConfigure.DEPARTMENTID = #{0}")
	List<BasicConfigurePersistence> getCfgByDepID(String departmentId);


	//移除部门配置	
	@Delete("DELETE FROM TBL_BasicConfigure WHERE CONFIGUREID=#{0} AND DEPARTMENTID=#{1}")
	void removeConfigure(String configureId, String departmentId);

}
