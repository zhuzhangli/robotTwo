package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CommunityAnswerPersistenceMapper;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public class CommunityAnswerHelper{
	//zyq_question_问题展示	 
	public static List<CommunityAnswerPersistence> question_CommunityAnswer(String communityquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer(communityquestionId);
		session.close();
		return list;
	}
	
	//获取社区问题回复数
	public static int question_CommunityAnswerCount(String communityquestionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		int count = mapper.question_CommunityAnswerCount(communityquestionid);
		session.close();
		return count;
	}
	
	//zyq_question_判断问题是否有最佳答案	 
	public static List<CommunityAnswerPersistence> question_iscurrentAnswer(String questionid,int isbest){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_iscurrentAnswer(questionid,isbest);
		session.close();
		return list;
	}
	
	//查找最佳问题的最佳答案id
	public static String findAnswerIdFromBestAnswer(String questionId, int isbestAnswer) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		String answerId = mapper.findAnswerIdFromBestAnswer(questionId,isbestAnswer);
		session.close();
		return answerId;
	}
	
	//查找问题答案用户Id
	public static String getUserIdByAnswerId(String communityanswerId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		String toUserId = mapper.getUserIdByAnswerId(communityanswerId);
		session.close();
		return toUserId;
	}
	
	//zyq_question_问题展示_最佳答案
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_best(String communityquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_best(communityquestionId);
		session.close();
		return list;
	}
	
	//zyq_question_问题展示_other		 
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_other(String communityquestionId,int startNumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_other(communityquestionId,startNumber);
		session.close();
		return list;
	}
	
	//当前用户回复总数
	public static int answerSizeByUserId(String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		int answerSize = mapper.answerSizeByUserId(userid);
		session.close();
		return answerSize;
	}
	
	//查看用户被点赞数量
	public static int getCommunityAnswerLike(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		int totalLikes = mapper.getCommunityAnswerLike(userId);
		session.close();
		return totalLikes;
	}
	
	//查看评论总数
	public static int question_CommunityAnswerSize(String communityquestionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		int answerSize = mapper.question_CommunityAnswerSize(communityquestionId);
		session.close();
		return answerSize;
	}
	
	// zyq_question_判断评论是否重复提交
	public static String question_IsCommunityAnswer(String userid,String content,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		String commentIsExist = mapper.question_IsCommunityAnswer(userid,content,questionId);
		session.close();
		return commentIsExist;
	}
	
	// zyq_question_ajax_添加评论
	public static void addComment(CommunityAnswerPersistence communityAnswerPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		mapper.save(communityAnswerPersistence);
		session.close();
	}
	
	//根据社区答案id查询答案贡献者
	public static String findUserIdByCommunityQuestionId(String communityanswerId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		String userId = mapper.question_CommunityAnswerUserId(communityanswerId);
		session.close();
		return userId;
	}
	
	// zyq_question2_ajax_设置为最佳答案
	public static void saveBestAnswer(String answerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		mapper.saveBestAnswer(answerId);
		session.close();
	}
	
	//zyq_question_问题展示_byAnswerID
	public static List<CommunityAnswerPersistence> question_CommunityAnswerId(String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswerId(communityanswerId);
		session.close();
		return list;
	}
	
	//获取用户评论
	public static List<CommunityAnswerPersistence> personal2_ReplyCommunity(String userId,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.personal2_ReplyCommunity(userId,startNumber,number);
		session.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	/*
	 * zyq_question_获取用户点评论数
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_userId(userid);
		session.close();
		return list;
	}
	
	
	

	
	/*
	 * zyq_notice_查看评论是否通知
	 */
	public static List<CommunityAnswerPersistence> notice_CommunityAnswer(String communityquestionid,int isnotice){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.notice_CommunityAnswer(communityquestionid,isnotice);
		session.close();
		return list;
	}
	
	
	//查询社区回复者信息
	public static List<CommunityAnswerPersistence> getBestAnswer(String communityquestionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.getBestAnswer(communityquestionid);
		session.close();
		return list;
	}

	

	

	

	

	

	

	
	
	

}
