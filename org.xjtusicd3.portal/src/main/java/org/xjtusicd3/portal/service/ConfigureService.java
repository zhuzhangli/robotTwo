package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.BasicConfigureHelper;
import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.helper.DepartmentHelper;
import org.xjtusicd3.database.helper.DriversHelper;
import org.xjtusicd3.database.helper.PatchHelper;
import org.xjtusicd3.database.helper.PermissionHelper;
import org.xjtusicd3.database.helper.SoftHelper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.DepartmentPersistence;
import org.xjtusicd3.database.model.DriverPersistence;
import org.xjtusicd3.database.model.PatchPersistence;
import org.xjtusicd3.database.model.SoftPersistence;
import org.xjtusicd3.portal.view.ChangeIndexView;
import org.xjtusicd3.portal.view.ConfigureDriverView;
import org.xjtusicd3.portal.view.ConfigurePatchView;
import org.xjtusicd3.portal.view.ConfigureSoftView;
import org.xjtusicd3.portal.view.DepConfigureView;

public class ConfigureService 
{
	//获取所有软件配置信息
	public static List<ConfigureSoftView> getAllSofts() {
		List<ConfigureSoftView> configureSoftViews = new ArrayList<ConfigureSoftView>();
		
		List<ConfigurePersistence> softLists = ConfigureHelper.getAllSofts(0);

		for(ConfigurePersistence softList:softLists){
			ConfigureSoftView configureSoftView = new ConfigureSoftView();
			
			configureSoftView.setCONFIGUREID(softList.getCONFIGUREID());
			configureSoftView.setCONFIGURENAME(softList.getCONFIGURENAME());
			configureSoftView.setCONFIGURETYPE(softList.getCONFIGURETYPE());
			configureSoftView.setFILESIZE(softList.getFILESIZE());
			configureSoftView.setURL(softList.getURL());
			configureSoftView.setDOWNLOADTIMES(softList.getDOWNLOADTIMES());			
			configureSoftView.setPRODUCER(softList.getPRODUCER());
			configureSoftView.setCONFIGURETIME(softList.getCONFIGURETIME());
			configureSoftView.setISCONFIGURE(softList.getISCONFIGURE());
			
			//查找软件信息
			List<SoftPersistence> sList = SoftHelper.getSoftInfo(softList.getCONFIGUREID());			
			configureSoftView.setLOGO(sList.get(0).getLOGO());
			configureSoftView.setSCORE(sList.get(0).getSCORE());
			configureSoftView.setDESCRIPTION(sList.get(0).getDESCRIPTION());
			
			configureSoftViews.add(configureSoftView);
			
		}
		
		return configureSoftViews;
	}
	
	
	//获取ID对应软件信息
	public static ConfigureSoftView getSoftInfoById(String configureId) {
		ConfigureSoftView softView = new ConfigureSoftView();
		
		List<SoftPersistence> softInfo = SoftHelper.getSoftInfo(configureId);
		
		softView.setCONFIGUREID(configureId);
		softView.setINTRODUCTION(softInfo.get(0).getINTRODUCTION());
		softView.setVERSIONTYPE(softInfo.get(0).getVERSIONTYPE());
		softView.setVERSION(softInfo.get(0).getVERSION());
		softView.setSOFTTYPE(softInfo.get(0).getSOFTTYPE());
		softView.setWEBSITE(softInfo.get(0).getWEBSITE());
		
		
		return softView;
	}
	
	
	//添加至标准配置库
	public static void addToBasicCfg(String configureId) {
		
		BasicConfigureHelper.addToBasicCfg(UUID.randomUUID().toString(),configureId,null);
	}
	
	
	//将软件从标准配置库移除
	public static void deleteFromBasicCfg(String configureId) {
		
		BasicConfigureHelper.deleteFromBasicCfg(configureId);		
	}
	
	
	//更新tbl_configure表中ISCONFIGURE字段信息    1-已加至标准库  0-未加入
	public static void updateCfgStatus(String configureId, int isConfigure) {
		
		ConfigureHelper.updateCfgStatus(configureId, isConfigure);
	}
	
	
	
	//获取所有驱动信息
	public static List<ConfigureDriverView> getAllDrivers() {
		List<ConfigureDriverView> configureDriverViews = new ArrayList<ConfigureDriverView>();
		
		//获取所有驱动信息
		List<ConfigurePersistence> DriverLists = ConfigureHelper.getAllDrivers(0);

		for(ConfigurePersistence DriverList:DriverLists){
			ConfigureDriverView configureDriverView = new ConfigureDriverView();
			
			configureDriverView.setCONFIGUREID(DriverList.getCONFIGUREID());
			configureDriverView.setCONFIGURENAME(DriverList.getCONFIGURENAME());
			configureDriverView.setCONFIGURETYPE(DriverList.getCONFIGURETYPE());
			configureDriverView.setFILESIZE(DriverList.getFILESIZE());
			configureDriverView.setURL(DriverList.getURL());
			configureDriverView.setDOWNLOADTIMES(DriverList.getDOWNLOADTIMES());			
			configureDriverView.setPRODUCER(DriverList.getPRODUCER());
			configureDriverView.setCONFIGURETIME(DriverList.getCONFIGURETIME());
			configureDriverView.setISCONFIGURE(DriverList.getISCONFIGURE());
			
			//查找驱动具体信息
			List<DriverPersistence> dList = DriversHelper.getDriverInfo(DriverList.getCONFIGUREID());
			configureDriverView.setOS(dList.get(0).getOS());
			configureDriverView.setDRIVERTYPE(dList.get(0).getDRIVERTYPE());
			configureDriverView.setFITNESS(dList.get(0).getFITNESS());
			configureDriverView.setDRIVERINTRODUCTION(dList.get(0).getDRIVERINTRODUCTION());
			
			configureDriverViews.add(configureDriverView);
			
		}
		
		return configureDriverViews;
	}

	
	//获取ID对应驱动信息
	public static ConfigureDriverView getDriverInfoById(String configureId) {
		ConfigureDriverView driverView = new ConfigureDriverView();
		
		List<DriverPersistence> driverInfo = DriversHelper.getDriverInfo(configureId);
		
		driverView.setCONFIGUREID(configureId);
		
		System.out.println("驱动的应用："+driverInfo.get(0).getFITNESS());
		driverView.setFITNESS(driverInfo.get(0).getFITNESS());
				
		return driverView;
	}
	
	
	//获取所有补丁信息 
	public static List<ConfigurePatchView> getAllPatchs() {
		List<ConfigurePatchView> configurePatchViews = new ArrayList<ConfigurePatchView>();
		
		//获取所有补丁信息
		List<ConfigurePersistence> patchLists = ConfigureHelper.getAllPatchs(0);

		for(ConfigurePersistence patchList:patchLists){
			ConfigurePatchView configurePatchView = new ConfigurePatchView();
			
			configurePatchView.setCONFIGUREID(patchList.getCONFIGUREID());
			configurePatchView.setCONFIGURENAME(patchList.getCONFIGURENAME());
			configurePatchView.setCONFIGURETYPE(patchList.getCONFIGURETYPE());
			configurePatchView.setFILESIZE(patchList.getFILESIZE());
			configurePatchView.setURL(patchList.getURL());
			configurePatchView.setDOWNLOADTIMES(patchList.getDOWNLOADTIMES());			
			configurePatchView.setPRODUCER(patchList.getPRODUCER());
			configurePatchView.setCONFIGURETIME(patchList.getCONFIGURETIME());
			configurePatchView.setISCONFIGURE(patchList.getISCONFIGURE());
			
			//查找补丁信息
			List<PatchPersistence> pList = PatchHelper.getPatchInfo(patchList.getCONFIGUREID());			
			configurePatchView.setOS(pList.get(0).getOS());
			configurePatchView.setLANGUAGE(pList.get(0).getLANGUAGE());
			configurePatchView.setPATCHINTRODUCTION(pList.get(0).getPATCHINTRODUCTION());
			
			configurePatchViews.add(configurePatchView);			
		}
		
		return configurePatchViews;
	}
	
	
	//获取ID对应补丁信息
	public static ConfigurePatchView getPatchInfoById(String configureId) {
		ConfigurePatchView patchView = new ConfigurePatchView();
		
		List<PatchPersistence> patchInfo = PatchHelper.getPatchInfo(configureId);
		
		patchView.setCONFIGUREID(configureId);
		patchView.setOS(patchInfo.get(0).getOS());
		patchView.setLANGUAGE(patchInfo.get(0).getLANGUAGE());
		patchView.setPATCHINTRODUCTION(patchInfo.get(0).getPATCHINTRODUCTION());
		
		return patchView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static String getPartConfig() 
//	{
//		String result = "";
//		List<ConfigurePersistence> list = ConfigureHelper.getPartConfig();
//		for (int i = 0; i < list.size(); i++) 
//		{
//			result += "{\"id\":\""+(i+1)+"\",\"configureProducer\":\""+list.get(i).getPRODUCER()+"\",\"configureName\":\""+list.get(i).getCONFIGURENAME()+"\",\"configureType\":\""+list.get(i).getCONFIGURETYPE()+"\"}";
//			if(i < list.size()-1)
//			{
//				result += ",";
//			}else
//			{
//				result += "";
//			}
//		}
//		System.out.println(result);
//		return result;
//	}
//	public static void main(String[] args) 
//	{
//		getPartConfig();
//	}
	public static List<ConfigurePersistence> getConfigure() 
	{
		List<ConfigurePersistence> configurelist = ConfigureHelper.getAllConfig();
		return configurelist;
		
	}

	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:50:36
	 * @param startNumber 
	 */
	public static List<ChangeIndexView> getConfigureHistory(int startNumber) {
		// TODO Auto-generated method stub
				List<ChangeIndexView> changeIndexViews = new ArrayList<ChangeIndexView>();
				List<ConfigureHistoryPersistence> configureHistoryPersistences = ConfigureHelper.getUpdateCfgs(startNumber);
				for(ConfigureHistoryPersistence configureHistoryPersistence:configureHistoryPersistences){
					ChangeIndexView changeIndexView = new ChangeIndexView();
					List<ConfigurePersistence> configurePersistences =ConfigureHelper.getCfgs(startNumber);
					changeIndexView.setCONFIGURENAME(configurePersistences.get(0).getCONFIGURENAME());
					changeIndexView.setVERSION(configureHistoryPersistence.getVERSION());
					changeIndexView.setURL(configureHistoryPersistence.getURL());
					changeIndexView.setUPDATETIME(configureHistoryPersistence.getUPDATETIME());
					changeIndexView.setREMARKS(configureHistoryPersistence.getREMARKS());
					changeIndexViews.add(changeIndexView);
				}
				
				return changeIndexViews;
			}

	
	//将软件按选定的部门添加至标准配置表中
	public static void addSoftToDepartment(String configureId, String departmentId) {	
		//判断标准配置表中是否已存在此部门该配置记录
		List<BasicConfigurePersistence> list = BasicConfigureHelper.isExist(configureId,departmentId);
		if(list.size()==0){
			String basicConfigureId = UUID.randomUUID().toString();
			
			BasicConfigureHelper.addToBasicCfg(basicConfigureId, configureId, departmentId);
		}
	}


	//通过部门ID查看标准配置信息
	public static List<DepConfigureView> getCfgByDepID(String departmentId) {
		List<DepConfigureView> configureViews = new ArrayList<DepConfigureView>();
		
		//通过部门ID查看标准配置信息
		List<BasicConfigurePersistence> cfgLists = BasicConfigureHelper.getCfgByDepID(departmentId);
		
		for(BasicConfigurePersistence cfgList:cfgLists){
			DepConfigureView configureView = new DepConfigureView();
			
			configureView.setCONFIGUREID(cfgList.getCONFIGUREID());
			
			//通过configureId获得对应配置信息
			List<ConfigurePersistence> cList = ConfigureHelper.getInfoByCfgId(cfgList.getCONFIGUREID());
			configureView.setCONFIGURENAME(cList.get(0).getCONFIGURENAME());
			configureView.setCONFIGURETYPE(cList.get(0).getCONFIGURETYPE());
			
			configureView.setDEPARTMENTID(cfgList.getDEPARTMENTID());
			
			configureViews.add(configureView);
		}
				
		return configureViews;
	}


	//分类查找
	public static List<DepConfigureView> getCfgByType(String configureType) {
		List<DepConfigureView> configureViews = new ArrayList<DepConfigureView>();
		
		//分类查找
		List<ConfigurePersistence> cfgLists = ConfigureHelper.getCfgByType(configureType);
		
		for(ConfigurePersistence cfgList:cfgLists){
			DepConfigureView configureView = new DepConfigureView();
			
			configureView.setCONFIGUREID(cfgList.getCONFIGUREID());
			
			//通过configureId获得对应配置信息
			List<ConfigurePersistence> cList = ConfigureHelper.getInfoByCfgId(cfgList.getCONFIGUREID());
			configureView.setCONFIGURENAME(cList.get(0).getCONFIGURENAME());
			
			configureViews.add(configureView);
		}
				
		return configureViews;
	}


	//查找特定部门软件配置信息
	public static List<DepConfigureView> getSoftCfgById(String departmentId) {
		List<DepConfigureView> configureViews = new ArrayList<DepConfigureView>();
		
		String configureType = "软件";
		
		//查找特定部门软件配置信息
		List<ConfigurePersistence> cfgLists = ConfigureHelper.getCfgById(departmentId,configureType);
		
		for(ConfigurePersistence cfgList:cfgLists){
			DepConfigureView configureView = new DepConfigureView();
			
			configureView.setCONFIGUREID(cfgList.getCONFIGUREID());
			
			//通过configureId获得对应配置信息
			List<ConfigurePersistence> cList = ConfigureHelper.getInfoByCfgId(cfgList.getCONFIGUREID());
			configureView.setCONFIGURENAME(cList.get(0).getCONFIGURENAME());
			
			configureViews.add(configureView);
		}
				
		return configureViews;
	}

	//查找特定部门驱动配置信息
	public static List<DepConfigureView> getDriverCfgById(String departmentId) {
		List<DepConfigureView> configureViews = new ArrayList<DepConfigureView>();
		
		String configureType = "驱动";
		
		//查找特定部门软件配置信息
		List<ConfigurePersistence> cfgLists = ConfigureHelper.getCfgById(departmentId,configureType);
		
		for(ConfigurePersistence cfgList:cfgLists){
			DepConfigureView configureView = new DepConfigureView();
			
			configureView.setCONFIGUREID(cfgList.getCONFIGUREID());
			
			//通过configureId获得对应配置信息
			List<ConfigurePersistence> cList = ConfigureHelper.getInfoByCfgId(cfgList.getCONFIGUREID());
			configureView.setCONFIGURENAME(cList.get(0).getCONFIGURENAME());
			
			configureViews.add(configureView);
		}
				
		return configureViews;
	}


	//查找特定部门补丁配置信息
	public static List<DepConfigureView> getPatchCfgById(String departmentId) {
List<DepConfigureView> configureViews = new ArrayList<DepConfigureView>();
		
		String configureType = "补丁";
		
		//查找特定部门软件配置信息
		List<ConfigurePersistence> cfgLists = ConfigureHelper.getCfgById(departmentId,configureType);
		
		for(ConfigurePersistence cfgList:cfgLists){
			DepConfigureView configureView = new DepConfigureView();
			
			configureView.setCONFIGUREID(cfgList.getCONFIGUREID());
			
			//通过configureId获得对应配置信息
			List<ConfigurePersistence> cList = ConfigureHelper.getInfoByCfgId(cfgList.getCONFIGUREID());
			configureView.setCONFIGURENAME(cList.get(0).getCONFIGURENAME());
			
			configureViews.add(configureView);
		}
				
		return configureViews;
	}


	//移除部门配置	
	public static void removeConfigure(String configureId, String departmentId) {
		
		BasicConfigureHelper.removeConfigure(configureId,departmentId);
	}


	

	

	

	

	

	

	

	
	

	

	


	
}
