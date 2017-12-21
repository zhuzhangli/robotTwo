package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public interface CommunityAnswerPersistenceMapper extends IBaseDao<CommunityAnswerPersistence, String>{
	// zyq_question_问题展示
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} ")
	List<CommunityAnswerPersistence> question_CommunityAnswer(String communityquestionId);
	
	//获取社区问题回复数
	@Select("SELECT COUNT(1) FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} ")
	int question_CommunityAnswerCount(String communityquestionid);
	
	//zyq_question_判断问题是否有最佳答案
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER=#{1}")
	List<CommunityAnswerPersistence> question_iscurrentAnswer(String questionid,int isbest);
	
	//查找最佳问题的最佳答案id
	@Select("SELECT COMMUNITYANSWERID FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER=#{1}")
	String findAnswerIdFromBestAnswer(String questionId, int isbestAnswer);
	
	//查找问题答案用户Id
	@Select("SELECT USERID FROM TBL_CommunityAnswer WHERE COMMUNITYANSWERID=#{0}")
	String getUserIdByAnswerId(String communityanswerId);
	
	//zyq_question_问题展示_最佳答案
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER='1'")
	List<CommunityAnswerPersistence> question_CommunityAnswer_best(String communityquestionId);
	
	//当前用户回复总数
	@Select("SELECT COUNT(1) FROM TBL_CommunityAnswer WHERE USERID=#{0}")
	int answerSizeByUserId(String userid);
	
	//查看用户被点赞数量
	@Select("SELECT COUNT(1) FROM TBL_CommunityAnswer,TBL_Agree WHERE TBL_CommunityAnswer.COMMUNITYANSWERID=TBL_Agree.COMMUNITYANSWERID AND TBL_CommunityAnswer.USERID=#{0}")
	int getCommunityAnswerLike(String userId);

	//zyq_question_问题展示_other
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER='0' ORDER BY TIME ASC LIMIT #{1},5")
	List<CommunityAnswerPersistence> question_CommunityAnswer_other(String communityquestionId,int startNumber);
	
	//查看评论总数
	@Select("SELECT COUNT(1) FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} ")
	int question_CommunityAnswerSize(String communityquestionId);
	
	//zyq_question_判断评论是否重复提交
	@Select("SELECT COMMUNITYANSWERID FROM TBL_CommunityAnswer WHERE USERID=#{0} AND CONTENT=#{1} AND COMMUNITYQUESTIONID=#{2}")
	String question_IsCommunityAnswer(String userid, String content,String questionId);
	
	//根据社区答案id查询答案贡献者
	@Select("SELECT USERID FROM TBL_CommunityAnswer WHERE COMMUNITYANSWERID=#{0}")
	String question_CommunityAnswerUserId(String communityanswerId);
	
	//zyq_question2_ajax_设置为最佳答案
	@Update("UPDATE TBL_CommunityAnswer SET ISBESTANSWER='1' WHERE COMMUNITYANSWERID=#{0}")
	void saveBestAnswer(String answerId);
	
	// zyq_question_问题展示_byAnswerID
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYANSWERID=#{0}")
	List<CommunityAnswerPersistence> question_CommunityAnswerId(String communityanswerId);
	
	// zyq_personal2_查看问题的回答
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityAnswerPersistence> personal2_ReplyCommunity(String userId,int startNumber,int number);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
//	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER='0' ORDER BY TIME ASC")
//	List<CommunityAnswerPersistence> question_CommunityAnswer_other(String communityquestionId,int startNumber);
	


	/*
	 * zyq_question_获取用户点评论数
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0}")
	List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid);
	
	
	
	
	/*
	 * zyq_notice_查看评论是否通知
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISNOTICE=#{1}")
	List<CommunityAnswerPersistence> notice_CommunityAnswer(String communityquestionid, int isnotice);
	
	
	//查询社区回复者信息
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER = '1'")
	List<CommunityAnswerPersistence> getBestAnswer(String communityquestionid);

	

	
	
	

	

	

	

	
	

	
}
