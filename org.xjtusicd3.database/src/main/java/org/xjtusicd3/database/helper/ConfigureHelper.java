package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ConfigureHistoryPersistenceMapper;
import org.xjtusicd3.database.mapper.ConfigurePersistenceMapper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;

public class ConfigureHelper {
	/*
	 * zyq_spider_补丁的增加
	 */
	public static void save_Patch(ConfigurePersistence ConfigurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(ConfigurePersistence);
		session.close();
	}
	/*
	 * zyq_spider_软件的增加
	 */
	public static void save_Soft(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 *zyq_spider_驱动的增加
	 */
	public static void save_Driver(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 * zpz_获取部分的设备信息
	 */
	public static List<ConfigurePersistence> getAllConfig(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getAllConfig();
		session.close();
		return list;
	}
	/*
	 * zyq_spider_按名称查看设备
	 */
	public static List<ConfigurePersistence> getConfigure(String configurename){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getConfigure(configurename);
		session.close();
		return list;
	}
	/*
	 * zyq_spider_更新软件
	 */
	public static List<ConfigurePersistence> update_Configure(String configurename,String filesize,String url,String downloadtimes,String producer,String configuretime){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.update_Configure(configurename,filesize,url,downloadtimes,producer,configuretime);
		session.close();
		return list;
	}
	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:57:13
	 * @param startNumber 
	 */
	public static List<ConfigureHistoryPersistence> getUpdateCfgs(int startNumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigureHistoryPersistenceMapper mapper = session.getMapper(ConfigureHistoryPersistenceMapper.class);
		List<ConfigureHistoryPersistence> list = mapper.getUpdateCfgs(startNumber);
		session.close();
		return list;
	}
	/**
	 * author:
	 * abstract:获取配置信息
	 * data:2017年10月12日18:02:35
	 * @param startNumber 
	 */
	public static List<ConfigurePersistence> getCfgs(int startNumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getCfgs(startNumber);
		session.close();
		return list;
	}
	
	//获取所有软件配置信息
	public static List<ConfigurePersistence> getAllSofts(int startNum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getAllSofts(startNum);
		session.close();
		return list;
	}
	
	
	//获取软件总数量
	public static int getAllSoftCounts() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		int list = mapper.getAllSoftCounts();
		session.close();
		return list;
	}
	
	//更新tbl_configure表中ISCONFIGURE字段信息    1-已加至标准库  0-未加入
	public static void updateCfgStatus(String configureId, int isConfigure) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.updateCfgStatus(configureId,isConfigure);
		session.close();		
	}
	
	
	//获取所有驱动信息
	public static List<ConfigurePersistence> getAllDrivers(int startNum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getAllDrivers(startNum);
		session.close();
		return list;
	}
	
	
	//获取驱动总数量
	public static int getAllDriverCounts() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		int list = mapper.getAllDriverCounts();
		session.close();
		return list;
	}
	
	
	//获取所有补丁信息
	public static List<ConfigurePersistence> getAllPatchs(int startNum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getAllPatchs(startNum);
		session.close();
		return list;
	}
	
	
	//获取补丁总数量
	public static int getAllPatchCounts() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		int list = mapper.getAllPatchCounts();
		session.close();
		return list;
	}
	
	
	//通过configureId获得对应配置信息
	public static List<ConfigurePersistence> getInfoByCfgId(String configureid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getInfoByCfgId(configureid);
		session.close();
		return list;
	}
	
	
	//分类查找
	public static List<ConfigurePersistence> getCfgByType(String configureType) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getCfgByType(configureType);
		session.close();
		return list;
	}
	
	
	//查找特定部门特定配置信息
	public static List<ConfigurePersistence> getCfgById(String departmentId, String configureType) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getCfgById(departmentId,configureType);
		session.close();
		return list;
	}
	

	
}