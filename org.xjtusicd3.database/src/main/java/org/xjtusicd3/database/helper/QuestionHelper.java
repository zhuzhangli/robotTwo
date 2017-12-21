package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.QuestionPersistence;

public class QuestionHelper {
	/**
	 * robot-分类
	 */
	public static List<QuestionPersistence> SecondClassify_robot(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.SecondClassify_robot(ClassifyId);
		session.close();
		return list;
	}
	
	/**
	 * author:zzl
	 * abstract:记录用户提问记录_查看用户提问是否为faq中的内容
	 * data:2017年10月22日11:42:29
	 */
	public static boolean getFaqQuestion(String comment) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		boolean list = mapper.getFaqQuestion(comment);
		session.close();
		return list;
	}
	
	/*
	 * zyq_faq_查看用户动态
	 */
	public static List<QuestionPersistence> faq_userDynamics(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_userDynamics();
		session.close();
		return list;
	}
		
	//查看自己的知识库_每次查看5条
	public static List<QuestionPersistence> personal2_faq_Limit(String userId,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit(userId,startNumber,number);
		session.close();
		return list;
	}
	
	//判断是创建知识还是修改知识
	public static boolean personal2_Ismodify(String faqquestionid, String modifynumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		boolean list = mapper.personal2_Ismodify(faqquestionid,modifynumber);
		session.close();
		return list;
	}

	//faq_按时间推荐
	public static List<QuestionPersistence> faq_recommend_Limit(int startnum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_recommend_Limit(startnum);
		session.close();
		return list;
	}	
		
	/**
	 * faq3_根据知识ID找类型classify
	 */
	public static String faqclassify(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String ClassifyId = mapper.faq3_faqclassifyId(QuestionId);
		session.close();
		return ClassifyId;
	}	
		
	/**
	 * author:zzl
	 * abstract:获取分类下faq具体信息
	 * data:2017年9月15日10:27:38
	 */
	public static List<QuestionPersistence> questionView(String parentId, int startnum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.questionView(parentId,startnum);
		session.close();
		return list;
	}	
		
	/**
	 * author:zzl
	 * abstract:推荐知识_根据收藏量推荐前4个
	 * data:2017年9月17日19:53:14
	 */
	public static List<QuestionPersistence> faqInfo_limit(String faqParentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqInfo_limit(faqParentId);
		session.close();
		return list;
	}
	
	/**
	 * 根据分类id及浏览量获取一条数据
	 */
	public static QuestionPersistence faq1_faqPersistences(String faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		QuestionPersistence list = mapper.faq1_faqPersistences(faqClassify);
		session.close();
		return list;
	}
	
	/**
	 * 根据分类id及浏览量获取5条数据
	 */
	public static List<QuestionPersistence> faq1_faqPersistences2(String faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq1_faqPersistences2(faqClassify);
		session.close();
		return list;
	}
	
	/**
	 * faq2_知识列表
	 */	
	public static List<QuestionPersistence> faq2_faqlist(String ClassifyId,int pageNow){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int a  = (pageNow-1)*5;
		List<QuestionPersistence> list = mapper.faq2_faqlist(ClassifyId,a);
		session.close();
		return list;
	}
	
	/**
	 * 根据faq问题id获取用户id
	 */
	public static String findUserIdByQuestionId(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String UserId = mapper.findUserIdByQuestionId(QuestionId);
		session.close();
		return UserId;
	}
	
	/**
	 * 获取该分类下faq信息的总数
	 */
	public static int pageTotal(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int pageTotal = mapper.pageTotal(ClassifyId);
		session.close();
		return pageTotal;
	}
	
	/**
	 * faq3_知识内容
	 */
	public static List<QuestionPersistence> faq3_faqcontent(String faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent(faqId);
		session.close();
		return list;
	}
	
	/**
	 * author:zhaoyanqing
	 * abstract:对访问FAQ页面的浏览量进行增加
	 * data:2017年9月18日 16:12:59
	 * @param faqScan 
	 */
	public static void updateFAQScan(String faqquestionid, String faqScan){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFAQScan(faqquestionid,faqScan);
		session.close();
	}
	
	/**
	 *	获取faq浏览量
	 */
	public static String getFaqScan(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String scan = mapper.getFaqScan(questionId);
		session.close();
		return scan;
	}
	
	/**
	 * abstract:faqadd_校验知识是否重复增添
	 */
	public static String faqadd_iscurrent2(String faqtitle,String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String faqQuestionId = mapper.faqadd_iscurrent(faqtitle,userId);
		session.close();
		return faqQuestionId;
	}
	
	/**
	 * spider_知识库问题的添加
	 */
	public static void save(QuestionPersistence questionPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.save(questionPersistence);
		session.close();
	}
	
	/**
	 * 查看自己的知识库
	 */
	public static List<QuestionPersistence> personal2_faq_Limit_Time(String userId,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit_Time(userId,startNumber,number,time);
		session.close();
		return list;
	}
	
	
	
	
	
	
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	

	



	public static List<QuestionPersistence> faq3_faqcontent_title(String faqtitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent_title(faqtitle);
		session.close();
		return list;
	}
	/*
	 * faqadd_校验知识是否重复增添
	 
	public static List<QuestionPersistence> faqadd_iscurrent(String faqtitle,String useremail){
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqadd_iscurrent(faqtitle,userPersistences.get(0).getUSERID());
		session.close();
		return list;
	}*/
	
	
	
	
	
	
	/*
	 * zpz_get faq information
	 */
	public static List<QuestionPersistence> getFaq(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaq();
		session.close();
		return list;
	}
	/*
	 * zpz_delete faq
	 */
	public static void deleteFAQ(String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.deleteFAQ(questionId);  
		session.close();
	}
	
	/*
	 * zyq_personal2_查看自己是否有FAQ
	 */
	public static List<QuestionPersistence> personal2_faq(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq(userId);
		session.close();
		return list;
	}

	
	
	
	/*
	 * zpz_get faq count
	 */
	public static int getFaqTotal1(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int faqTotal = mapper.FaqTotal();
		session.close();
		return faqTotal;
	}

	/*
	 * zyq_robot_查看所以faq的信息
	 */
	public static List<QuestionPersistence> getFaqTotal(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaqTotal();
		session.close();
		return list;

	}

	
	

	



	



	
	/**
	 * author:zzl
	 * abstract:获取faq对应问题的信息
	 * data:2017年9月29日08:51:28
	 */
	public static List<QuestionPersistence> faqcollection(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		System.out.println("faq3问题号："+questionId);
		List<QuestionPersistence> list = mapper.faqcollection(questionId);
		session.close();
		return list;
	}
	
	
	/**
	 * 添加到知识库
	 * @param questionid
	 * @param title
	 * @param keywords
	 * @param subspecialCategoryId
	 * @param string
	 * @param string2
	 * @param time
	 * @param description
	 * @param string3
	 * @param string4
	 * @param userid
	 */
	public static void insertIntoFaqQuestion(String questionid, String title, String keywords,
			String subspecialCategoryId, String collection, String scan, String time, String description, String modifynumber,
			String faqstate, String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.insertIntoFaqQuestion(questionid, title,  keywords,subspecialCategoryId, collection, scan,  time,  description, modifynumber ,faqstate, userid);
		session.close();
		
	}
	
	//zzl_获取待审核faq_2017年11月6日20:02:27
	public static List<QuestionPersistence> faqPendingAudits(int i) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqPendingAudits(i);
		session.close();
		return list;
	}
	
	/**
	 * 
	 * @param title
	 * @param userId
	 * @return
	 */
	public static List<QuestionPersistence> titleIsExist(String title, String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.titleIsExist(title, userId);
		session.close();
		return list;
	}
	
	/**
	 * 
	 * @param faqQuestionId
	 * @return list
	 */
	public static List<QuestionPersistence> getFaqQuestionInfo(String faqQuestionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaqQuestionInfo(faqQuestionId);
		session.close();
		return list;
	}
	
	/**
	 * 
	 * @param questionId
	 * @param keywords
	 * @param description
	 * @param modifyNum
	 * @param faqState
	 */
	public static void updateFaqInfo(String questionId, String keywords, String description, String modifyNum,
			int faqState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFaqInfo(questionId,  keywords,  description,  modifyNum,faqState);
		session.close();
		
	}
	
	
	//获取已审核faq
	public static List<QuestionPersistence> faqAudited(int i) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqAudited(i);
		session.close();
		return list;
	}

	
	
	
	
}
