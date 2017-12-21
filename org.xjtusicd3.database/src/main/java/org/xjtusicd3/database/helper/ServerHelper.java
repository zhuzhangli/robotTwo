package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ServerPersistenceMapper;
import org.xjtusicd3.database.model.ServerPersistence;

public class ServerHelper {
	//获取服务器特有信息
	public static List<ServerPersistence> getServerInfoById(String equipmentid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ServerPersistenceMapper mapper = session.getMapper(ServerPersistenceMapper.class);
		List<ServerPersistence> list = mapper.getServerInfoById(equipmentid);
		session.close();
		return list;
	}
	
	
	//添加服务器信息
	public static void addServerEquipment(String equipmentId, String osVersion, String computerName, String pCI,
			String uSB, String path, String rAM_EXCHANGEAREAUSE, String pARTATIONUSE, String iDLERAM,
			String oS_TIME_USERNUM_LOAD, String oSLOAD, String fIREWALL, String rOUTINGTABLE, String hASCONTACT,
			String nETWORK, String pROCESS, String rEALTIMEPROCESS, String aCTIVEUSER, String bIOS,
			String networkCard) {
		
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ServerPersistenceMapper mapper = session.getMapper(ServerPersistenceMapper.class);
	    mapper.addServerEquipment( equipmentId,  osVersion,  computerName,  pCI,uSB,  path,  rAM_EXCHANGEAREAUSE,  pARTATIONUSE,  iDLERAM,
				 oS_TIME_USERNUM_LOAD,  oSLOAD,  fIREWALL,  rOUTINGTABLE,  hASCONTACT, nETWORK,  pROCESS,  rEALTIMEPROCESS,  aCTIVEUSER,  bIOS,networkCard);
		session.close();

		
	}
	
	
	//更新服务器信息
	public static void updateServerInfo(String equipmentId, String osVersion, String computerName, String pCI,
			String uSB, String path, String rAM_EXCHANGEAREAUSE, String pARTATIONUSE, String iDLERAM,
			String oS_TIME_USERNUM_LOAD, String oSLOAD, String fIREWALL, String rOUTINGTABLE, String hASCONTACT,
			String nETWORK, String pROCESS, String rEALTIMEPROCESS, String aCTIVEUSER, String bIOS,
			String nETWORKCARD) {
		
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ServerPersistenceMapper mapper = session.getMapper(ServerPersistenceMapper.class);
	    mapper.updateServerInfo( equipmentId,  osVersion,  computerName,  pCI,uSB,  path,  rAM_EXCHANGEAREAUSE,  pARTATIONUSE,  iDLERAM,
				 oS_TIME_USERNUM_LOAD,  oSLOAD,  fIREWALL,  rOUTINGTABLE,  hASCONTACT, nETWORK,  pROCESS,  rEALTIMEPROCESS,  aCTIVEUSER,  bIOS,nETWORKCARD);
		session.close();

		
	}
	
	
	
	
	
	
	
	
	
	
	//zpz_get server information
			public static List<ServerPersistence> getServer()
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				ServerPersistenceMapper mapper = session.getMapper(ServerPersistenceMapper.class);
				List<ServerPersistence> serverlist = mapper.getServer();
				session.close();
				return serverlist;
				

			}

			
			













			
			
}
