package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.RobotHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.view.FaqView;

public class QuestionService {
	/**
	 * author:zzl
	 * abstract:FAQ的增加
	 * data:2017年9月22日12:00:25
	 * @param userQuestionId 
	 */
	public static void saveFAQ(String username, String title, String keywords, String subspecialCategoryId,String description, String faqcontent, String userQuestionId) {
	
		String questionId = UUID.randomUUID().toString();

    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);

		String userId = UserHelper.getUserIdByName(username);

		//通过判断用户角色来置faqstate的状态 -- 普通用户上传置为1，其他用户上传置为2
		String roleName = UserHelper.getRoleNameByUserId(userId);
		
		if (roleName == "普通用户") {
			//添加到知识库问题列表
			//faq问题id，标题，关键字，分类号，收藏量，浏览量，更新时间，问题描述，修改次数，faq状态，用户id  --  普通用户状态为1
			QuestionHelper.insertIntoFaqQuestion(questionId,title,keywords, subspecialCategoryId,"0","0",time, description,"1","1",userId );
			System.out.println("问题表插入完毕");
			
			//添加至知识库答案列表
			AnswerHelper.insertIntoFaqAnswer(UUID.randomUUID().toString(),faqcontent,questionId,userId);
			System.out.println("答案表插入完毕");
			
			//更新已处理事件的questionstate = 1
			UserQuestionHelper.updateRobotAnswerState(userQuestionId,1);
		}else {
			QuestionHelper.insertIntoFaqQuestion(questionId,title,keywords, subspecialCategoryId,"0","0",time, description,"1","2",userId );
			System.out.println("问题表插入完毕1");
			//添加至知识库答案列表
			AnswerHelper.insertIntoFaqAnswer(UUID.randomUUID().toString(),faqcontent,questionId,userId);	
			System.out.println("答案表插入完毕1");
			//更新已处理事件的questionstate = 1
			System.out.println("问题id："+userQuestionId);
			UserQuestionHelper.updateRobotAnswerState(userQuestionId,1);
			System.out.println("已更新状态");
		}
		
			
	}

	/**
	 * 待审核faq审核通过
	 * @param questionId
	 * @param keywords
	 * @param description
	 * @param faqState 状态  1 -- 待审核；2 -- 通过审核
	 * @param faqcontent 
	 */
	public static void updateFaqInfo(String questionId, String keywords, String description, int faqState, String faqcontent) {
		//获取原始待审核faq问题信息
		List<QuestionPersistence> faqQuestionInfo = QuestionHelper.getFaqQuestionInfo(questionId);
		
		//获取原始待审核faq答案信息
		List<AnswerPersistence> faqAnswerInfo = AnswerHelper.getAnswerByQuestionId(questionId);
		
		//判断管理员有无对待审核faq进行更改
		if ((faqQuestionInfo.get(0).getFAQKEYWORDS() == keywords) &&(faqQuestionInfo.get(0).getFAQDESCRIPTION() == description) && (faqAnswerInfo.get(0).getFAQCONTENT() == faqcontent)) {
			//管理员未更改用户提交faq
			String modifyNum = faqQuestionInfo.get(0).getMODIFYNUMBER();
			
			//更新faq问题表
			QuestionHelper.updateFaqInfo(questionId, keywords, description, modifyNum,faqState );
			
			//更新faq答案表
			AnswerHelper.updateFaqAnswerInfo(questionId,faqcontent);
		}else {
			//管理员对待审核faq修改后进行提交
			String modifyNum = faqQuestionInfo.get(0).getMODIFYNUMBER();
			int modifyNum1 = Integer.parseInt(modifyNum) + 1;
			String modifyNum2 = Integer.toString(modifyNum1);
			
			
			//更新faq问题表
			QuestionHelper.updateFaqInfo(questionId, keywords, description, modifyNum2,faqState );
			
			//更新faq答案表
			AnswerHelper.updateFaqAnswerInfo(questionId,faqcontent);
		}
	}

	/**
	 * @abstract 保存社区有最佳答案问题到知识库
	 * @param faqTitle
	 * @param object
	 * @param faqclassifyId
	 * @param modifyTime
	 * @param faqDescription
	 * @param userId
	 * @param answerContent
	 * questionState  0:正常显示，1：已加入faq，2：已忽略
	 * @param communityProblemId 
	 */
	public static void saveCommunityQuestionToFAQ(String faqTitle, String faqKeyWords, String faqclassifyId,
			String modifyTime, String faqDescription, String userId, String answerContent, String communityProblemId) {
		String questionId = UUID.randomUUID().toString();
		QuestionHelper.insertIntoFaqQuestion(questionId,faqTitle,faqKeyWords, faqclassifyId,"0","0",modifyTime, faqDescription,"1","2",userId );
		System.out.println("问题表插入完毕");
		
		//添加至知识库答案列表
		AnswerHelper.insertIntoFaqAnswer(UUID.randomUUID().toString(),answerContent,questionId,userId);
		System.out.println("答案表插入完毕");
		
		//UserQuestionHelper.updateRobotAnswerState(userQuestionId,1);
		CommunityQuestionHelper.updateCommunityQuestionState(communityProblemId, 1);
		System.out.println("已更新状态");
	}



}
