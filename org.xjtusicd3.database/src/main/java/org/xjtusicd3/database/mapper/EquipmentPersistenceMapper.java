package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.EquipmentPersistence;

public interface EquipmentPersistenceMapper extends IBaseDao<EquipmentPersistence, String>{
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:08:18
	 * abstract:根据MAC地址查看是否存在配置表中
	 */
	@Select("SELECT * FROM TBL_Equipment WHERE MACADDRESS=#{0}")
	List<EquipmentPersistence> getEquipmentList(String macaddress);

	
	//获取所有计算机信息 
	@Select("SELECT * FROM TBL_Equipment,TBL_Computer WHERE TBL_Equipment.EQUIPMENTID = TBL_Computer.EQUIPMENTID AND TBL_Equipment.STATE=1")
	List<EquipmentPersistence> getAllComupters();

	//获取计算机总数
	@Select("SELECT COUNT(1) FROM TBL_Equipment,TBL_Computer WHERE TBL_Equipment.EQUIPMENTID = TBL_Computer.EQUIPMENTID AND TBL_Equipment.STATE=1")
	int getAllComputerCounts();

	//添加至通用设备表
	@Insert("INSERT INTO TBL_Equipment VALUES(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
	void addEquipment(String equipmentId, String macAddress, String equipmentModel, String buytime, String cPU,
			String rAM, String storage, String iP, int state);

	//获取设备通用信息
	@Select("SELECT * FROM TBL_Equipment WHERE EQUIPMENTID = #{0}")
	List<EquipmentPersistence> getEquipmentInfoById(String equipmentId);

	//更改通用设备信息
	@Update("UPDATE TBL_Equipment SET MACADDRESS=#{1}, EQUIPMENTMODEL=#{2}, CPU=#{3}, RAM=#{4}, STORAGE=#{5},  IP=#{6},  BUYTIME=#{7} WHERE EQUIPMENTID=#{0}")
	void updateEquipment(String equipmentId, String macAddress, String equipmentModel, String cPU, String rAM,String storage, String iP, String buytime);

	//更改计算机状态
	@Update("UPDATE TBL_Equipment SET STATE=#{1} WHERE EQUIPMENTID=#{0}")
	void updateEquipmentState(String equipmentId, int state);

	//获取服务器信息
	@Select("SELECT * FROM TBL_Equipment,TBL_Server WHERE TBL_Equipment.EQUIPMENTID = TBL_Server.EQUIPMENTID AND TBL_Equipment.STATE=1")
	List<EquipmentPersistence> getAllServers();

	//获取服务器总数
	@Select("SELECT COUNT(1) FROM TBL_Equipment,TBL_Server WHERE TBL_Equipment.EQUIPMENTID = TBL_Server.EQUIPMENTID AND TBL_Equipment.STATE=1")
	int getAllServerCounts();
}
