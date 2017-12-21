package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ConfigurePersistence;

public interface ConfigurePersistenceMapper extends IBaseDao<ConfigurePersistence, String>{

	/*
	 * zpz_get all of information of configure
	 */
	@Select("SELECT * FROM TBL_Configure LIMIT 200")
	public List<ConfigurePersistence> getAllConfig();
	/*
	 * zyq_spider_按名字查看设备
	 */
	@Select("SELECT * FROM TBL_Configure WHERE CONFIGURENAME=#{0}")
	public List<ConfigurePersistence> getConfigure(String configurename);
	/*
	 * zyq_spider_更新软件
	 */
	@Update("UPDATE TBL_Configure SET FILESIZE=#{1},URL=#{2},DOWNLOADTIMES=#{3},PRODUCER=#{4},CONFIGURETIME=#{5} WHERE CONFIGURENAME=#{0}")
	public List<ConfigurePersistence> update_Configure(String configurename,String filesize,String url,String downloadtimes,String producer,String configuretime);
	
	/**
	 * author:
	 * abstract:获取配置信息
	 * data:2017年10月12日18:03:19
	 * @param startNumber 
	 */
	@Select("SELECT * FROM TBL_Configure ORDER BY CONFIGURETIME DESC LIMIT #{0},100")
	public List<ConfigurePersistence> getCfgs(int startNumber);
	
	//获取所有软件配置信息
	@Select("SELECT * FROM TBL_Configure,TBL_Soft WHERE TBL_Configure.CONFIGUREID = TBL_Soft.CONFIGUREID ORDER BY TBL_Configure.CONFIGURETIME DESC LIMIT #{0},200 ")
	public List<ConfigurePersistence> getAllSofts(int startNum);

	
	//获取软件总数量
	@Select("SELECT COUNT(1) FROM TBL_Configure,TBL_Soft WHERE TBL_Configure.CONFIGUREID = TBL_Soft.CONFIGUREID ")
	public int getAllSoftCounts();
	
	
	//更新tbl_configure表中ISCONFIGURE字段信息    1-已加至标准库  0-未加入
	@Update("UPDATE TBL_Configure SET ISCONFIGURE=#{1} WHERE CONFIGUREID=#{0}")
	public void updateCfgStatus(String configureId, int isConfigure);
	
	
	//获取所有驱动信息
	@Select("SELECT * FROM TBL_Configure,TBL_Driver WHERE TBL_Configure.CONFIGUREID = TBL_Driver.CONFIGUREID ORDER BY TBL_Configure.CONFIGURETIME DESC LIMIT #{0},200 ")
	public List<ConfigurePersistence> getAllDrivers(int startNum);
	
	
	//获取驱动总数量
	@Select("SELECT COUNT(1) FROM TBL_Configure,TBL_Driver WHERE TBL_Configure.CONFIGUREID = TBL_Driver.CONFIGUREID ")
	public int getAllDriverCounts();
	
	
	//获取所有补丁信息
	@Select("SELECT * FROM TBL_Configure,TBL_Patch WHERE TBL_Configure.CONFIGUREID = TBL_Patch.CONFIGUREID ORDER BY TBL_Configure.CONFIGURETIME DESC LIMIT #{0},200 ")
	public List<ConfigurePersistence> getAllPatchs(int startNum);
	
	
	//获取补丁总数量
	@Select("SELECT COUNT(1) FROM TBL_Configure,TBL_Patch WHERE TBL_Configure.CONFIGUREID = TBL_Patch.CONFIGUREID ")
	public int getAllPatchCounts();
	
	
	//通过configureId获得对应配置信息
	@Select("SELECT * FROM TBL_Configure WHERE CONFIGUREID = #{0} ")
	public List<ConfigurePersistence> getInfoByCfgId(String configureId);
	
	
	//分类查找
	@Select("SELECT * FROM TBL_Configure WHERE CONFIGURETYPE = #{0} ")
	public List<ConfigurePersistence> getCfgByType(String configureType);
	
	
	//查找特定部门特定配置信息
	@Select("SELECT * FROM TBL_Configure,TBL_BasicConfigure WHERE TBL_Configure.CONFIGUREID = TBL_BasicConfigure.CONFIGUREID AND TBL_BasicConfigure.DEPARTMENTID = #{0} AND CONFIGURETYPE = #{1}")
	public List<ConfigurePersistence> getCfgById(String departmentId, String configureType);


	
}
