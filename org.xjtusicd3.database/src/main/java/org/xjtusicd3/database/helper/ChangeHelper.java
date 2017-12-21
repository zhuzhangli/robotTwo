package org.xjtusicd3.database.helper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;

public class ChangeHelper {
	//获取当前用户配置信息
	public static List<Map<String, Object>> findCurrentCfgInfo() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<Map<String, Object>> list = mapper.findCurrentCfgInfo();
		session.close();
		return list;
	}

}
