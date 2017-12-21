package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PatchPersistenceMapper;
import org.xjtusicd3.database.model.PatchPersistence;

public class PatchHelper {
	/*
	 * spider_补丁的增加
	 */
	public static void save(PatchPersistence patchSpiderPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PatchPersistenceMapper mapper = session.getMapper(PatchPersistenceMapper.class);
		mapper.save(patchSpiderPersistence);
		session.close();
	}

	
	//查找补丁信息
	public static List<PatchPersistence> getPatchInfo(String configureid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PatchPersistenceMapper mapper = session.getMapper(PatchPersistenceMapper.class);
		List<PatchPersistence> list = mapper.getPatchInfo(configureid);
		session.close();
		return list;
	}
}
