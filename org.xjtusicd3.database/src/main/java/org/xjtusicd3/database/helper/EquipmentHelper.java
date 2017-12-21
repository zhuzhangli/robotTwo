package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;
import org.xjtusicd3.database.mapper.EquipmentPersistenceMapper;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;


public class EquipmentHelper {
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:12:28
	 * abstract:根据MAC地址查看是否在当前配置表中
	 */
	public static List<CurrentEquipmentPersistence> getCurrentEquipmentList(String mac){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<CurrentEquipmentPersistence> list = mapper.getCurrentEquipment(mac);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:08:18
	 * abstract:根据MAC地址查看是否存在配置表中
	 */
	public static List<EquipmentPersistence> getEquipmentList(String mac){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getEquipmentList(mac);
		session.close();
		return list;
	}
	
	
	//获取所有计算机信息 
	public static List<EquipmentPersistence> getAllComupters() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getAllComupters();
		session.close();
		return list;
	}
	
	
	//获取计算机总数
	public static int getAllComputerCounts() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		int count = mapper.getAllComputerCounts();
		session.close();
		return count;
	}
	
	
	//添加至通用设备表
	public static void addEquipment(String equipmentId, String macAddress, String equipmentModel, String buytime,
			String CPU, String RAM, String storage, String IP, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class); 
		mapper.addEquipment(equipmentId, macAddress, equipmentModel,  buytime,CPU,  RAM,  storage,  IP,  state);
		session.close();	
	}
	
	
	//获取设备通用信息
	public static List<EquipmentPersistence> getEquipmentInfoById(String equipmentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getEquipmentInfoById(equipmentId);
		session.close();
		return list;
	}
	
	
	//更改通用设备信息
	public static void updateEquipment(String equipmentId, String macAddress, String equipmentModel, String cPU,
			String rAM, String storage, String iP, String buytime) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class); 
		mapper.updateEquipment( equipmentId,  macAddress,  equipmentModel,  cPU, rAM,  storage,  iP,  buytime);
		session.close();	
	}
	
	
	//更改计算机状态
	public static void updateEquipmentState(String equipmentId, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class); 
		mapper.updateEquipmentState(equipmentId,state);
		session.close();		
	}
	
	
	//获取服务器信息
	public static List<EquipmentPersistence> getAllServers() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getAllServers();
		session.close();
		return list;
	}
	
	
	//获取服务器总数
	public static int getAllServerCounts() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		int count = mapper.getAllServerCounts();
		session.close();
		return count;
	}
	
	
	//根据macAddress去设备标准库中查找
	public static List<EquipmentPersistence> FindEquipmentFromBasicByMac(String macAddress) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getEquipmentList(macAddress);
		session.close();
		return list;
	}
}
