package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xjtusicd3.database.helper.ChangeHelper;
import org.xjtusicd3.database.helper.ComputerHelper;
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.model.ComputerPersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.portal.view.EquipmentComputerView;

public class ChangeService {
	//获取当前用户配置信息
	public static List<Map<String, Object>> findCurrentCfgInfo() {
		
		List<Map<String, Object>> list = ChangeHelper.findCurrentCfgInfo();
		
		// TODO Auto-generated method stub
		return list;
	}

	
	//根据macAddress去设备标准库中查找
	public static EquipmentComputerView FindEquipmentFromBasicByMac(String macAddress) {		
		EquipmentComputerView computerView = new EquipmentComputerView();
		
		List<EquipmentPersistence> equipmentPersistences = EquipmentHelper.FindEquipmentFromBasicByMac(macAddress);
		
		for(EquipmentPersistence ePersistence:equipmentPersistences){
						
			computerView.setEQUIPMENTID(ePersistence.getEQUIPMENTID());
			computerView.setMACADDRESS(macAddress);
			computerView.setEQUIPMENTMODEL(ePersistence.getEQUIPMENTMODEL());
			computerView.setBUYTIME(ePersistence.getBUYTIME());
			computerView.setCPU(ePersistence.getCPU());
			computerView.setRAM(ePersistence.getRAM());
			computerView.setSTORAGE(ePersistence.getSTORAGE());
			computerView.setIP(ePersistence.getIP());
			computerView.setSTATE(ePersistence.getSTATE());
			
			List<ComputerPersistence> cList = ComputerHelper.getComputerInfoById(ePersistence.getEQUIPMENTID());
			
			computerView.setGRAPHICCARD(cList.get(0).getGRAPHICCARD());
			computerView.setAUDIOCARD(cList.get(0).getAUDIOCARD());
			computerView.setNETWORKCARD(cList.get(0).getNETWORKCARD());
			computerView.setMOTHERBOARD(cList.get(0).getMOTHERBOARD());
			computerView.setOSNAME(cList.get(0).getOSNAME());
			computerView.setOSID(cList.get(0).getOSID());
		}
		
		
		
		
		return computerView;
	}

}
