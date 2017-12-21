package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.ComputerHelper;
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.helper.ServerHelper;
import org.xjtusicd3.database.model.ComputerPersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.database.model.ServerPersistence;
import org.xjtusicd3.portal.view.EquipmentComputerView;
import org.xjtusicd3.portal.view.EquipmentServerView;

public class EquipmentService {
	//获取ID对应计算机特有信息
	public static EquipmentComputerView getComputerInfoById(String equipmentId) {
		EquipmentComputerView computerView = new EquipmentComputerView();
		
		List<ComputerPersistence> computerInfo = ComputerHelper.getComputerInfoById(equipmentId);
		
		computerView.setGRAPHICCARD(computerInfo.get(0).getGRAPHICCARD());
		computerView.setAUDIOCARD(computerInfo.get(0).getAUDIOCARD());
		computerView.setNETWORKCARD(computerInfo.get(0).getNETWORKCARD());
		computerView.setMOTHERBOARD(computerInfo.get(0).getMOTHERBOARD());
		computerView.setOSNAME(computerInfo.get(0).getOSNAME());
		computerView.setOSID(computerInfo.get(0).getOSID());
			
		return computerView;
	}

	//获取要编辑的计算机信息
	public static EquipmentComputerView getEquipmentInfoById(String equipmentId) {
		EquipmentComputerView computerView = new EquipmentComputerView();
		
		//获取设备通用信息
		List<EquipmentPersistence> equipmentInfo = EquipmentHelper.getEquipmentInfoById(equipmentId);
		computerView.setEQUIPMENTID(equipmentId);
		computerView.setMACADDRESS(equipmentInfo.get(0).getMACADDRESS());
		computerView.setEQUIPMENTMODEL(equipmentInfo.get(0).getEQUIPMENTMODEL());
		computerView.setBUYTIME(equipmentInfo.get(0).getBUYTIME());
		computerView.setCPU(equipmentInfo.get(0).getCPU());
		computerView.setRAM(equipmentInfo.get(0).getRAM());
		computerView.setSTORAGE(equipmentInfo.get(0).getSTORAGE());
		computerView.setIP(equipmentInfo.get(0).getIP());		
		
		List<ComputerPersistence> computerInfo = ComputerHelper.getComputerInfoById(equipmentId);
		
		computerView.setGRAPHICCARD(computerInfo.get(0).getGRAPHICCARD());
		computerView.setAUDIOCARD(computerInfo.get(0).getAUDIOCARD());
		computerView.setNETWORKCARD(computerInfo.get(0).getNETWORKCARD());
		computerView.setMOTHERBOARD(computerInfo.get(0).getMOTHERBOARD());
		computerView.setOSNAME(computerInfo.get(0).getOSNAME());
		computerView.setOSID(computerInfo.get(0).getOSID());
			
		return computerView;
	}

	
	//获取所有计算机信息 
	public static List<EquipmentComputerView> getAllComputers() {
		List<EquipmentComputerView> configureComputerViews = new ArrayList<EquipmentComputerView>();
		
		List<EquipmentPersistence> computerLists = EquipmentHelper.getAllComupters();

		for(EquipmentPersistence computerList:computerLists){
			EquipmentComputerView configureComputerView = new EquipmentComputerView();
			
			configureComputerView.setEQUIPMENTID(computerList.getEQUIPMENTID());
			configureComputerView.setMACADDRESS(computerList.getMACADDRESS());
			configureComputerView.setEQUIPMENTMODEL(computerList.getEQUIPMENTMODEL());
			configureComputerView.setBUYTIME(computerList.getBUYTIME());
			configureComputerView.setCPU(computerList.getCPU());
			configureComputerView.setRAM(computerList.getRAM());
			configureComputerView.setSTORAGE(computerList.getSTORAGE());
			configureComputerView.setIP(computerList.getIP());
			configureComputerView.setSTATE(computerList.getSTATE());
			
			//获取用户计算机特有信息
			List<ComputerPersistence> pList = ComputerHelper.getComputerInfoById(computerList.getEQUIPMENTID());
			
			configureComputerView.setGRAPHICCARD(pList.get(0).getGRAPHICCARD());
			configureComputerView.setAUDIOCARD(pList.get(0).getAUDIOCARD());
			configureComputerView.setNETWORKCARD(pList.get(0).getNETWORKCARD());
			configureComputerView.setMOTHERBOARD(pList.get(0).getMOTHERBOARD());
			configureComputerView.setOSNAME(pList.get(0).getOSNAME());
			configureComputerView.setOSID(pList.get(0).getOSID());
			
			configureComputerViews.add(configureComputerView);
			
		}
		
		return configureComputerViews;
	}
	
	
	//添加用户设备
	public static void addUserEquipment(String macAddress, String equipmentModel, String buytime, String CPU,
			String RAM, String storage, String IP, String graphicCard, String audioCard, String networkCard,
			String motherboard, String OSName, String OSID) {
		
		String equipmentId = UUID.randomUUID().toString();
		//state 设备状态 1-正在使用；0-停止使用
		int state = 1;
		//添加至通用设备表
		EquipmentHelper.addEquipment(equipmentId,macAddress,equipmentModel,buytime,CPU,RAM,storage,IP,state);
		
		//添加至tbl_computer表
		ComputerHelper.addUserEquipment(equipmentId,graphicCard,audioCard,networkCard,motherboard,OSName,OSID);
	}
		
	
	//更改用户计算机信息
	public static void updateComputer(String equipmentId, String macAddress, String equipmentModel, String cPU,
			String rAM, String storage, String iP, String buytime, String graphicCard, String audioCard,
			String networkCard, String motherboard, String oSName, String oSID) {
		
		//更新通用设备信息
		EquipmentHelper.updateEquipment(equipmentId,macAddress,equipmentModel,cPU,rAM,storage,iP,buytime);
		
		//更新计算机信息
		ComputerHelper.updateComputerInfo(equipmentId,graphicCard,audioCard,networkCard,motherboard,oSName,oSID);
	}
	
	
	//更改计算机状态
	public static void updateEquipmentState(String equipmentId, int state) {
		EquipmentHelper.updateEquipmentState(equipmentId,state);
		
	}

	
	//获取服务器信息
	public static List<EquipmentServerView> getAllServers() {
		List<EquipmentServerView> configureServerViews = new ArrayList<EquipmentServerView>();
		
		List<EquipmentPersistence> serverLists = EquipmentHelper.getAllServers();

		for(EquipmentPersistence serverList:serverLists){
			EquipmentServerView configureServerView = new EquipmentServerView();
			
			configureServerView.setEQUIPMENTID(serverList.getEQUIPMENTID());
			configureServerView.setMACADDRESS(serverList.getMACADDRESS());
			configureServerView.setEQUIPMENTMODEL(serverList.getEQUIPMENTMODEL());
			configureServerView.setBUYTIME(serverList.getBUYTIME());
			configureServerView.setCPU(serverList.getCPU());
			configureServerView.setRAM(serverList.getRAM());
			configureServerView.setSTORAGE(serverList.getSTORAGE());
			configureServerView.setIP(serverList.getIP());
			configureServerView.setSTATE(serverList.getSTATE());
			
			//获取服务器特有信息
			List<ServerPersistence> pList = ServerHelper.getServerInfoById(serverList.getEQUIPMENTID());
			
			configureServerView.setOSVERSION(pList.get(0).getOSVERSION());
			configureServerView.setCOMPUTERNAME(pList.get(0).getCOMPUTERNAME());
			configureServerView.setPCI(pList.get(0).getPCI());
			configureServerView.setUSB(pList.get(0).getUSB());
			configureServerView.setPATH(pList.get(0).getPATH());
			configureServerView.setRAM_EXCHANGEAREAUSE(pList.get(0).getRAM_EXCHANGEAREAUSE());
			configureServerView.setPARTATIONUSE(pList.get(0).getPARTATIONUSE());
			configureServerView.setIDLERAM(pList.get(0).getIDLERAM());
			configureServerView.setOS_TIME_USERNUM_LOAD(pList.get(0).getOS_TIME_USERNUM_LOAD());
			configureServerView.setOSLOAD(pList.get(0).getOSLOAD());
			configureServerView.setFIREWALL(pList.get(0).getFIREWALL());
			configureServerView.setROUTINGTABLE(pList.get(0).getROUTINGTABLE());
			configureServerView.setHASCONTACT(pList.get(0).getHASCONTACT());
			configureServerView.setNETWORK(pList.get(0).getNETWORK());
			configureServerView.setPROCESS(pList.get(0).getPROCESS());
			configureServerView.setREALTIMEPROCESS(pList.get(0).getREALTIMEPROCESS());
			configureServerView.setACTIVEUSER(pList.get(0).getACTIVEUSER());
			configureServerView.setBIOS(pList.get(0).getBIOS());
			configureServerView.setNETWORKCARD(pList.get(0).getNETWORKCARD());
			
			configureServerViews.add(configureServerView);		
		}
		
		return configureServerViews;
	}

	
	//添加服务器信息
	public static void addServerEquipment(String macAddress, String equipmentModel, String buytime, String cPU,
			String rAM, String storage, String iP, String osVersion, String computerName, String pCI, String uSB,
			String path, String rAM_EXCHANGEAREAUSE, String pARTATIONUSE, String iDLERAM, String oS_TIME_USERNUM_LOAD,
			String oSLOAD, String fIREWALL, String rOUTINGTABLE, String hASCONTACT, String nETWORK, String pROCESS,
			String rEALTIMEPROCESS, String aCTIVEUSER, String bIOS, String networkCard) {
		
		String equipmentId = UUID.randomUUID().toString();
		//state 设备状态 1-正在使用；0-停止使用
		int state = 1;
		//添加至通用设备表
		EquipmentHelper.addEquipment(equipmentId,macAddress,equipmentModel,buytime,cPU,rAM,storage,iP,state);
		
		//添加至tbl_Server表
		ServerHelper.addServerEquipment(equipmentId,osVersion, computerName,  pCI,  uSB,
				 path,  rAM_EXCHANGEAREAUSE,  pARTATIONUSE,  iDLERAM,  oS_TIME_USERNUM_LOAD,
				 oSLOAD,  fIREWALL,  rOUTINGTABLE,  hASCONTACT,  nETWORK,  pROCESS,
				 rEALTIMEPROCESS,  aCTIVEUSER,  bIOS,  networkCard);
		
	}

	
	//获取ID对应服务器特有信息
	public static EquipmentServerView getServerInfoById(String equipmentId) {
		EquipmentServerView serverView = new EquipmentServerView();
		
		List<ServerPersistence> serverInfo = ServerHelper.getServerInfoById(equipmentId);
		
		serverView.setEQUIPMENTID(serverInfo.get(0).getEQUIPMENTID());
		serverView.setOSVERSION(serverInfo.get(0).getOSVERSION());
		serverView.setCOMPUTERNAME(serverInfo.get(0).getCOMPUTERNAME());
		serverView.setPCI(serverInfo.get(0).getPCI());
		serverView.setUSB(serverInfo.get(0).getUSB());
		serverView.setPATH(serverInfo.get(0).getPATH());
		System.out.println("ssssssss"+serverInfo.get(0).getRAM_EXCHANGEAREAUSE());
		serverView.setRAM_EXCHANGEAREAUSE(serverInfo.get(0).getRAM_EXCHANGEAREAUSE());
		serverView.setPARTATIONUSE(serverInfo.get(0).getPARTATIONUSE());
		serverView.setIDLERAM(serverInfo.get(0).getIDLERAM());
		serverView.setOS_TIME_USERNUM_LOAD(serverInfo.get(0).getOS_TIME_USERNUM_LOAD());
		serverView.setOSLOAD(serverInfo.get(0).getOSLOAD());
		serverView.setFIREWALL(serverInfo.get(0).getFIREWALL());
		serverView.setROUTINGTABLE(serverInfo.get(0).getROUTINGTABLE());
		serverView.setHASCONTACT(serverInfo.get(0).getHASCONTACT());
		serverView.setNETWORK(serverInfo.get(0).getNETWORK());
		serverView.setPROCESS(serverInfo.get(0).getPROCESS());
		serverView.setREALTIMEPROCESS(serverInfo.get(0).getREALTIMEPROCESS());
		serverView.setACTIVEUSER(serverInfo.get(0).getACTIVEUSER());
		serverView.setBIOS(serverInfo.get(0).getBIOS());
		serverView.setNETWORKCARD(serverInfo.get(0).getNETWORKCARD());
			
		return serverView;
	}

	
	//获取要编辑的服务器信息
	public static EquipmentServerView getServiceInfoById(String equipmentId) {
		EquipmentServerView computerView = new EquipmentServerView();
		
		//获取服务器通用信息
		List<EquipmentPersistence> equipmentInfo = EquipmentHelper.getEquipmentInfoById(equipmentId);
		computerView.setEQUIPMENTID(equipmentId);
		computerView.setMACADDRESS(equipmentInfo.get(0).getMACADDRESS());
		computerView.setEQUIPMENTMODEL(equipmentInfo.get(0).getEQUIPMENTMODEL());
		computerView.setBUYTIME(equipmentInfo.get(0).getBUYTIME());
		computerView.setCPU(equipmentInfo.get(0).getCPU());
		computerView.setRAM(equipmentInfo.get(0).getRAM());
		computerView.setSTORAGE(equipmentInfo.get(0).getSTORAGE());
		computerView.setIP(equipmentInfo.get(0).getIP());	
		
		//获取服务器特有信息
		List<ServerPersistence> pList = ServerHelper.getServerInfoById(equipmentId);
		
		computerView.setOSVERSION(pList.get(0).getOSVERSION());
		computerView.setCOMPUTERNAME(pList.get(0).getCOMPUTERNAME());
		computerView.setPCI(pList.get(0).getPCI());
		computerView.setUSB(pList.get(0).getUSB());
		computerView.setPATH(pList.get(0).getPATH());
		computerView.setRAM_EXCHANGEAREAUSE(pList.get(0).getRAM_EXCHANGEAREAUSE());
		computerView.setPARTATIONUSE(pList.get(0).getPARTATIONUSE());
		computerView.setIDLERAM(pList.get(0).getIDLERAM());
		computerView.setOS_TIME_USERNUM_LOAD(pList.get(0).getOS_TIME_USERNUM_LOAD());
		computerView.setOSLOAD(pList.get(0).getOSLOAD());
		computerView.setFIREWALL(pList.get(0).getFIREWALL());
		computerView.setROUTINGTABLE(pList.get(0).getROUTINGTABLE());
		computerView.setHASCONTACT(pList.get(0).getHASCONTACT());
		computerView.setNETWORK(pList.get(0).getNETWORK());
		computerView.setPROCESS(pList.get(0).getPROCESS());
		computerView.setREALTIMEPROCESS(pList.get(0).getREALTIMEPROCESS());
		computerView.setACTIVEUSER(pList.get(0).getACTIVEUSER());
		computerView.setBIOS(pList.get(0).getBIOS());
		computerView.setNETWORKCARD(pList.get(0).getNETWORKCARD());
			
		return computerView;
	}

	
	//更改服务器信息
	public static void updateServer(String equipmentId, String macAddress, String equipmentModel, String buytime,
			String cPU, String rAM, String storage, String iP, String osVersion, String computerName, String pCI,
			String uSB, String path, String rAM_EXCHANGEAREAUSE, String pARTATIONUSE, String iDLERAM,
			String oS_TIME_USERNUM_LOAD, String oSLOAD, String fIREWALL, String rOUTINGTABLE, String hASCONTACT,
			String nETWORK, String pROCESS, String rEALTIMEPROCESS, String aCTIVEUSER, String bIOS,
			String nETWORKCARD) {
		//更新通用设备信息
		EquipmentHelper.updateEquipment(equipmentId,macAddress,equipmentModel,cPU,rAM,storage,iP,buytime);
		
		//更新服务器信息
		ServerHelper.updateServerInfo(equipmentId, osVersion,  computerName,  pCI,uSB,  path,  rAM_EXCHANGEAREAUSE,  pARTATIONUSE,  iDLERAM,
				 oS_TIME_USERNUM_LOAD,  oSLOAD,  fIREWALL,  rOUTINGTABLE,  hASCONTACT,nETWORK,  pROCESS,  rEALTIMEPROCESS,  aCTIVEUSER,  bIOS, nETWORKCARD);
		
	}

}
