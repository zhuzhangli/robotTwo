package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ComputerPersistenceMapper; 
import org.xjtusicd3.database.model.ComputerPersistence;

public class ComputerHelper {
	//获取用户计算机信息
	public static List<ComputerPersistence> getComputerInfoById(String equipmentid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ComputerPersistenceMapper mapper = session.getMapper(ComputerPersistenceMapper.class);
		List<ComputerPersistence> computerlist = mapper.getComputerInfoById(equipmentid);
		session.close();
		return computerlist;
		
	}
	
	
	//插入信息
	public static void addUserEquipment(String equipmentId, String graphicCard, String audioCard,
			String networkCard, String motherboard, String oSName, String oSID) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ComputerPersistenceMapper mapper = session.getMapper(ComputerPersistenceMapper.class);
		mapper.addUserEquipment( equipmentId,  graphicCard,  audioCard,networkCard,  motherboard,  oSName,  oSID);
		session.close();		
	}
	
	
	//更新计算机信息
	public static void updateComputerInfo(String equipmentId, String graphicCard, String audioCard,
			String networkCard, String motherboard, String oSName, String oSID) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ComputerPersistenceMapper mapper = session.getMapper(ComputerPersistenceMapper.class);
		mapper.updateComputerInfo( equipmentId,  graphicCard,  audioCard,networkCard,  motherboard,  oSName,  oSID);
		session.close();		
	}

	
	
	
	
	
	
	//zpz_get computer information
		public static List<ComputerPersistence> getComputer()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			ComputerPersistenceMapper mapper = session.getMapper(ComputerPersistenceMapper.class);
			List<ComputerPersistence> computerlist = mapper.getComputer();
			session.close();
			return computerlist;
			

		}

		
		











		
		
	
}
