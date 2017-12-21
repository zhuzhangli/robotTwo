package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.AnswerPersistence;

public interface AnswerPersistenceMapper extends IBaseDao<AnswerPersistence, String>{
	//zyq_faq3_知识内容
	@Select("SELECT * FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public List<AnswerPersistence> faq3_faqContent(String QuestionId);
	
	//根据faq问题id查找用户id
	@Select("SELECT USERID FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public String findUserIdByFAQQuestionId(String faqquestionid);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zyq_notice_ajax_查询FAQ评论通知
	 */
	@Select("SELECT * FROM TBL_FAQanswer WHERE USERID=#{0}")
	public List<AnswerPersistence> notice_faqanswerList(String userId);
	
	//获取faqanswerId相对应的内容
	@Select("SELECT FAQCONTENT FROM TBL_FAQanswer WHERE FAQANSWERID=#{0}")
	public String getContentById(String faqAnswerId);
	
	
	//添加至知识库答案列表
	@Insert("INSERT INTO TBL_FAQanswer(FAQANSWERID,FAQCONTENT,FAQQUESTIONID,USERID) VALUES (#{0},#{1},#{2},#{3})")
	public void insertIntoFaqAnswer(String faqAnswerId, String faqcontent, String questionid, String userid);
	
	//zzl_根据问题号得到faq答案内容
	@Select("SELECT * FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public List<AnswerPersistence> getAnswerByQuestionId(String faqquestionid);
	
	//更新faq信息
	@Update("UPDATE TBL_FAQanswer SET FAQCONTENT=#{1}  WHERE FAQQUESTIONID=#{0}")
	public void updateFaqAnswerInfo(String questionId, String faqcontent);
































	
	
}
