package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public interface CommunityQuestionPersistenceMapper extends IBaseDao<CommunityQuestionPersistence, String>
{
	//查看自己的论坛
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit(String userid,int startNumber,int number);
	
	//时间倒序显示最新5条社区问题	
	@Select("SELECT * FROM TBL_CommunityQuestion  ORDER BY TIME DESC LIMIT #{0},5")
	List<CommunityQuestionPersistence> question_getCommunity_isanswer(int startnumber);
	
	//zyq_ajax_question校验是否重复添加	 
	@Select("SELECT  COMMUNITYQUESTIONID FROM TBL_CommunityQuestion WHERE USERID=#{0} AND TITLE=#{1}")
	String question_iscurrent(String userid, String questiontitle);
	
	//zyq_ajax_question的增加	 
	@Insert("INSERT INTO TBL_CommunityQuestion(COMMUNITYQUESTIONID,TIME,TITLE,CONTENT,CLASSIFYID,USERID,SCAN,QUESTIONSTATE,ISANSWER) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
	void saveCommunityQuestion(String id, String time, String title, String content, String classifyid, String userid, String scan, int questionState,int isanswer);
	
	//返回  对应分类   的全部问题
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity(String classifyid);
	
	//获取faqClassifyId分类号下的所有社区问题总数
	@Select("SELECT COUNT(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0}")
	int questionSizeByClassifyId(String faqClassifyId);
	
	//获取faqClassifyId分类号下的所有社区问题数_根据是否已有最佳答案来划分
	@Select("SELECT COUNT(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1}")
	int questionSizeByClassifyIdLimit(String faqClassifyId, int isanswer);
	
	//zyq_question2_问题内容详情
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0} ")
	List<CommunityQuestionPersistence> question2_getCommunity(String questionId);
	
	//相关问题_2017年10月31日01:26:58
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} ORDER BY TIME DESC LIMIT 5")
	List<CommunityQuestionPersistence> selectQuestionByClassifyId(String faqclassifyid);
	
	//查看问题号为	questionId 的提问者id
	@Select("SELECT USERID FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0} ")
	String CommunityQuestion(String questionId);
	
	// zyq_question2_设为最佳答案
	@Update("UPDATE TBL_CommunityQuestion SET ISANSWER=1 WHERE COMMUNITYQUESTIONID=#{0}")
	void updateBestAnswer(String questionId);
	
	//查看自己的论坛
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit_Time(String userid,int startNumber,int number,String time);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* 
	 * 返回  对应分类   的全部问题数目
	 */
	@Select("SELECT count(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0}")
	int getCommunityQuestionNumberByClassify(String classifyid);
	/*
	 * 返回  对应分类  的   已解决或未解决  的全部问题
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2(String classifyid,int isanswer);
	
	
	/*
	 * 返回  对应分类  的   已解决或未解决  的全部问题的数目
	 */
	@Select("SELECT count(1) FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1}")
	int getCommunityQuestionNumberByClassifyAndAnswerflag(String classifyid,int isanswer);
	
	
	
	
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2_isanswer(int isanswer);
	
	/*
	 * zyq_notice_查询用户的提问
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion(String userid);
	

	/*
	 * zpz_get all community question
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER='1'")
	List<CommunityQuestionPersistence> getAllCommunityQuestion();
	
	/*
	 * zpz将community question and answer add to FAQ,then delete these
	 */
	@Select("DELETE  FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0}")
	public void deleteCommunityQuestion(String questionId);
	@Select("DELETE  FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0}")
	public void deleteCommunityAnswer(String questionId);
	
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER='0'")
	List<CommunityQuestionPersistence> getAllCommunityQuestion2();
	
	
	
	

	
	//zzl_获取问题中心中所有没有最佳答案的问题信息_2017年11月6日09:20:07
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER='0' AND QUESTIONSTATE = 0")
	List<CommunityQuestionPersistence> unResolvedProblems();
	
	//zzl_获取问题中心中所有有最佳答案的问题信息_2017年11月6日10:25:40
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER='1' AND QUESTIONSTATE = 0")
	List<CommunityQuestionPersistence> resolvedProblems();
	
	//zzl_更新社区问题状态_2017年11月12日18:38:02
	@Update("UPDATE TBL_CommunityQuestion SET QUESTIONSTATE=#{1} WHERE COMMUNITYQUESTIONID=#{0}")
	void updateCommunityQuestionState(String questionId, int questionState);

	
	

	
	
} 
