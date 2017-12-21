package org.xjtusicd3.database.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AgreePersistenceMapper;
import org.xjtusicd3.database.model.AgreePersistence;

public class AgreeHelper {
	/**
	 * author:zzl
	 * abstract:question2_查看username是否对communityanswerId点赞
	 * data:2017年9月22日14:47:45
	 */
	public static String getAgree(String username,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		String userId = UserHelper.getUserIdByName(username);		
		String agreeId = mapper.getAgree(communityanswerId,userId);
		session.close();
		return agreeId;
	}
	
	/*
	 * zyq_question2_对答案点赞
	 */
	public static void saveAgree(String username,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		String userId = UserHelper.getUserIdByName(username);	
		Date date = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查找问题答案用户Id
	    String toUserId = CommunityAnswerHelper.getUserIdByAnswerId(communityanswerId);
	    
	    //判断这个赞是否为自己的
	    int isnotice = 0;
	    if (userId.equals(toUserId)) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		mapper.saveAgree(UUID.randomUUID().toString(),communityanswerId,userId,toUserId,time,isnotice);
		session.close();
	}
	
	/*
	 * zyq_question2_取消点赞
	 */
	public static void deleteAgree(String agreeid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		mapper.deleteAgree(agreeid);
		session.close();
	}
	
	public static int getAgreeSizeByAnswerId(String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		int agreeSize = mapper.getAgreeSizeByAnswerId(communityanswerId);
		session.close();
		return agreeSize;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




	
	
	/*
	 * zyq_question_查看用户点赞
	 */
	public static List<AgreePersistence> getAgreebyUserId(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		List<AgreePersistence> list = mapper.getAgreebyUserId(userid);
		session.close();
		return list;
	}
}	
