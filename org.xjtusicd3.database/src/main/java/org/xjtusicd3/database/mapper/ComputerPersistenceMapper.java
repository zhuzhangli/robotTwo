package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ComputerPersistence;

public interface ComputerPersistenceMapper extends IBaseDao<ComputerPersistence,String>
{
	//获取用户计算机信息
	@Select("SELECT * FROM TBL_Computer WHERE EQUIPMENTID = #{0}")
	List<ComputerPersistence> getComputerInfoById(String equipmentid);
	
	//插入信息
	@Insert("INSERT INTO TBL_Computer VALUES(#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
	void addUserEquipment(String equipmentId, String graphicCard, String audioCard, String networkCard,String motherboard, String oSName, String oSID);
	
	//更新计算机信息
	@Update("UPDATE TBL_Computer SET GRAPHICCARD=#{1}, AUDIOCARD=#{2}, NETWORKCARD=#{3}, MOTHERBOARD=#{4}, OSNAME=#{5},  OSID=#{6} WHERE EQUIPMENTID=#{0}")
	void updateComputerInfo(String equipmentId, String graphicCard, String audioCard, String networkCard,String motherboard, String oSName, String oSID);

	
	
	
	
	
	//zpz_获取用户部分信息
		@Select("SELECT * FROM TBL_Computer")
		List<ComputerPersistence> getComputer();
		









		
		
}
