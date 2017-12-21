package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CommunityQuestionPersistenceMapper;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public class CommunityQuestionHelper{
	//查看自己的论坛
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit(String userid,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion_Limit(userid,startNumber,number);
		session.close();
		return list;
	}
	
	//时间倒序显示最新5条社区问题
	public static List<CommunityQuestionPersistence> question_getCommunity_isanswer(int startnumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity_isanswer(startnumber);
		session.close();
		return list;
	}
	
	//zyq_ajax_question校验是否重复添加	 
	public static String question_iscurrent(String userid,String questiontitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		String list = mapper.question_iscurrent(userid,questiontitle);
		session.close();
		return list;
	}
	
	//zyq_ajax_question的增加
	public static void saveCommunityQuestion(String id,String time,String title,String content,String classifyid,String userid,String scan,int questionState,int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.saveCommunityQuestion(id,time,title,content,classifyid,userid,scan,questionState,isanswer);
		session.close();
	}
	
	//zyq_question_问题展示_根据类别ID 
	public static List<CommunityQuestionPersistence> question_getCommunity(String classifyid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity(classifyid);
		session.close();
		return list;
	}
	
	//获取faqClassifyId分类号下的所有社区问题数
	public static int questionSizeByClassifyId(String faqClassifyId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		int size = mapper.questionSizeByClassifyId(faqClassifyId);
		session.close();
		return size;
	}
	
	//获取faqClassifyId分类号下的所有社区问题数_根据是否已有最佳答案来划分
	public static int questionSizeByClassifyIdLimit(String faqClassifyId, int isanswer) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		int size = mapper.questionSizeByClassifyIdLimit(faqClassifyId,isanswer);
		session.close();
		return size;
	}
	
	//zyq_question2_问题内容详情
	public static List<CommunityQuestionPersistence> question2_getCommunity(String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question2_getCommunity(questionId);
		session.close();
		return list;
	}
	
	//相关问题_2017年10月31日01:26:58
	public static List<CommunityQuestionPersistence> selectQuestionByClassifyId(String faqclassifyid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.selectQuestionByClassifyId(faqclassifyid);
		session.close();
		return list;
	}
	
	//查看问题号为	questionId 的提问者id
	public static String CommunityQuestion(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		String questionUserId = mapper.CommunityQuestion(questionId);
		session.close();
		return questionUserId;
	}
	
	// zyq_question2_设为最佳答案
	public static void updateBestAnswer(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.updateBestAnswer(questionId);
		session.close();
	}
	
	/**
	 * 查看自己的论坛
	 */
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit_Time(String userid,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion_Limit_Time(userid,startNumber,number,time);
		session.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public static List<CommunityQuestionPersistence> question_getCommunity2(String classifyid,int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity2(classifyid,isanswer);
		session.close();
		return list;
	}

	public static List<CommunityQuestionPersistence> question_getCommunity2_isanswer(int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity2_isanswer(isanswer);
		session.close();
		return list;
	}
	
	/*
	 * zyq_notice_查询用户的提问
	 */
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion(userid);
		session.close();
		return list;
	}
	
	
	//zpz_获取社区问题
		public static List<CommunityQuestionPersistence> getAllCommunityQuestion()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
			List<CommunityQuestionPersistence> communityquestionlist = mapper.getAllCommunityQuestion();
			session.close();
			return communityquestionlist;
			

		}
		
	//zpz_get community problem by ID
			public static List<CommunityQuestionPersistence> getCommunityQuestionById(String communityProblemId)
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
				List<CommunityQuestionPersistence> communityquestionlist = mapper.question2_getCommunity(communityProblemId);
				session.close();
				return communityquestionlist;
			}
			
			//zpz_delete community problem by ID
			public static void deleteCommunityQuestionById(String communityProblemId)
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
				mapper.deleteCommunityQuestion(communityProblemId);
				session.close();
			 
			}
			
			//zpz_delete community answer by ID
			public static void deleteCommunityAnswerById(String communityProblemId)
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
				mapper.deleteCommunityAnswer(communityProblemId);
				session.close();
				 
			}
			
	
	//zzl_2017年10月11日20:04:00
	public static List<CommunityQuestionPersistence> getAllCommunityQuestion2() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> communityquestionlist = mapper.getAllCommunityQuestion2();
		session.close();
		return communityquestionlist;
	}
	
	
	
	

	
	
	
	
	
	//zzl_获取问题中心中所有没有最佳答案的问题信息_2017年11月6日09:19:48
	public static List<CommunityQuestionPersistence> unResolvedProblems() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> communityquestionlist = mapper.unResolvedProblems();
		session.close();
		return communityquestionlist;
	}
	
	//zzl_获取问题中心中所有有最佳答案的问题信息_2017年11月6日10:25:02
	public static List<CommunityQuestionPersistence> resolvedProblems() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> communityquestionlist = mapper.resolvedProblems();
		session.close();
		return communityquestionlist;
	}
	
	//zzl_更新社区问题状态_2017年11月12日18:37:38
	public static void updateCommunityQuestionState(String questionId, int questionState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.updateCommunityQuestionState(questionId, questionState);
		session.close();
		
	}

	

	

		
	
}