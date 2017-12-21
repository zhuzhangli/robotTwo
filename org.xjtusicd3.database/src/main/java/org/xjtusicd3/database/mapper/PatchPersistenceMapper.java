package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PatchPersistence;

public interface PatchPersistenceMapper extends IBaseDao<PatchPersistence, String>{
	//查找补丁信息
	@Select("SELECT * FROM TBL_Patch WHERE CONFIGUREID=#{0}")
	List<PatchPersistence> getPatchInfo(String configureid);

}
