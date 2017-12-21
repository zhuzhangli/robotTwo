package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AgreeHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AgreePersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;
import org.xjtusicd3.partner.view.Question2_CommunityView;
import org.xjtusicd3.partner.view.Question_CommunityView;


public class CommunityService {
	/*
	 * zyq_question_问题展示
	 * 从 startnumber 开始加载 type类型【待解决、已解决、全部】的  classifyname【具体知识库分类】的 5条记录
	 */
	public static List<Question_CommunityView> Question_CommunityView(String username,int startnumber,String type,String classifyname){
		List<Question_CommunityView> question_CommunityViews = new ArrayList<Question_CommunityView>();
		if (classifyname.equals("all")) {
			if (type.equals("all")) {//展示全部问题
				//时间倒序显示最新5条社区问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity_isanswer(startnumber);
				
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取问题评论总数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						//zzl_无最佳答案_基本信息
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityView.setCommunityNumber(communityNumber);			
						question_CommunityViews.add(question_CommunityView);
					}else {
						//zzl_有最佳答案_基本信息
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());						
						question_CommunityView.setCommunityNumber(communityNumber);
						
						//zzl_最佳答案专有显示
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreeCount));
						
						//最佳答案用户信息
						List<UserPersistence> userPersistences = UserHelper.getUserInfoById(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						
						//zzl_用户点评
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						
						//zzl_用户点赞
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						if (username!=null) {
							String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
							//判断是否点赞
							if (agreeId == null) {
								question_CommunityView.setIsLike("0");
							}else {
								question_CommunityView.setIsLike("1");
							}
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type.equals("2")) {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					question_CommunityView.setCommunityNumber(communityNumber);
					List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					question_CommunityViews.add(question_CommunityView);
					
				}
			}else if (type.equals("1")) {//展示已解决的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//获取最佳答案信息
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityView.setAnswer(list.get(0).getCONTENT());
					question_CommunityView.setCommunityNumber(communityNumber);
					
					//zzl_点赞
					List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(list2.get(0).getCOMMUNITYANSWERID());
					question_CommunityView.setLikesNumber(Integer.toString(agreeCount));
	
					//获取用户信息
					List<UserPersistence> userPersistences = UserHelper.getUserInfoById(list.get(0).getUSERID());
					question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
					question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
					question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
					
					//zzl_用户点评
					List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
					
					//zzl_用户点赞
					List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
					if (username!=null) {
						String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreeId == null) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
					}
					question_CommunityViews.add(question_CommunityView);
				}
			}
		}else {
			//zzl_分类显示
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.spider_ClassifyListByName(classifyname, "0");			
			if (type.equals("all")) {//展示全部问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity(classifyPersistences.get(0).getFAQCLASSIFYID());				
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					
					//zzl_有/无最佳答案都显示
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());					
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					
					question_CommunityView.setCommunityNumber(communityNumber);
					if (list.size()!=0) {
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getUserInfoById(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						
						//zzl_用户点评
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						
						//zzl_用户点赞
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());						
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						
						//zzl_点赞
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreeCount));
						
						if (username!=null) {
							String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
							//判断是否点赞
							if (agreeId == null) {
								question_CommunityView.setIsLike("0");
							}else {
								question_CommunityView.setIsLike("1");
							}
						}					
					}
					question_CommunityViews.add(question_CommunityView);
				}
			}else if (type.equals("2")) {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					question_CommunityView.setCommunityNumber(communityNumber);
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityViews.add(question_CommunityView);
					
				}
			}else if (type.equals("1")) {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取最佳答案信息
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					
					//zzl_有最佳答案
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityView.setAnswer(list.get(0).getCONTENT());
					
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					question_CommunityView.setCommunityNumber(communityNumber);
					
					//zzl_点赞
					List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(list2.get(0).getCOMMUNITYANSWERID());
					question_CommunityView.setLikesNumber(Integer.toString(agreeCount));
					
					//获取用户信息
					List<UserPersistence> userPersistences = UserHelper.getUserInfoById(list.get(0).getUSERID());
					question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
					question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
					question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
					
					//zzl_用户点评
					List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
					
					//zzl_用户点赞
					List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());					
					question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
					
					if (username!=null) {
						String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreeId == null) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
					}
					question_CommunityViews.add(question_CommunityView);					
				}
			}
		}

		return question_CommunityViews;
	}

	
	/**
	 * author:zzl
	 * abstract:保存问题
	 * data:2017年9月22日14:39:58
	 */
	public static void savaCommunityQuestion(String username,String title,String content,String classifyId){
		String userId = UserHelper.getUserIdByName(username);
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	CommunityQuestionHelper.saveCommunityQuestion(UUID.randomUUID().toString(),time,title,content,classifyId,userId,"0",0,0);
	}
	
	/*
	 * zyq_question2_问题展示_最佳答案的展示
	 */
	public static Question2_CommunityView question2_CommunityViews_best(String username,String questionId){
		//查找COMMUNITYQUESTIONID=questionId 且 ISBESTANSWER='1'的答案_即最佳答案
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_best(questionId);
		Question2_CommunityView question2_CommunityView = new Question2_CommunityView();
		if (communityAnswerPersistences.size()!=0) {
			
			
			question2_CommunityView.setAnswerId(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			question2_CommunityView.setAnswer(communityAnswerPersistences.get(0).getCONTENT());
			
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(communityAnswerPersistences.get(0).getUSERID());
			question2_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
			question2_CommunityView.setUserId(userPersistences.get(0).getUSERID());
			question2_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
					
			List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
			
			//查看最佳答案的评论_一次显示5条信息
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment_Limit(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID(), communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());				
			for(CommentPersistence commentPersistence:commentPersistences){
				Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();
				//获取问题答案回复用户信息
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(commentPersistence.getUSERID());
				
				question2_CommunityReplayView.setCommentId(commentPersistence.getCOMMENTID());
				question2_CommunityReplayView.setUserName(userPersistences2.get(0).getUSERNAME());
				question2_CommunityReplayView.setUserImage(userPersistences2.get(0).getAVATAR());
				question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
				question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());
							
				//如果回复人不为空
				if (commentPersistence.getTOUSERID()!=null) {
					//获取此用户回复的用户信息
					String toUserName = UserHelper.getUserNameById(commentPersistence.getTOUSERID());
					question2_CommunityReplayView.setTouserName("@"+toUserName);
				}
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			
			//获取最佳答案点赞数
			int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreeCount);
			question2_CommunityView.setLikesNumber(likeNumber);
					
			//查看最佳答案的总评论数
			int commentNumber = CommentHelper.question2_getComment(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID(), communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			String communityNumber = Integer.toString(commentNumber);
			question2_CommunityView.setCommunityNumber(communityNumber);
			
			question2_CommunityView.setTime(communityAnswerPersistences.get(0).getTIME());
			
			
			//查看username对communityanswerId的点赞
			String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreeId == null) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
			}
			//判断是否被收藏
			String collectionId = CollectionHelper.getCollection(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			if (collectionId == null) {
				question2_CommunityView.setIsCollection("0");
			}else {
				question2_CommunityView.setIsCollection("1");
			}
			
			question2_CommunityView.setReplay(question2_CommunityReplayViews);
			
			//当前用户回答总数
			int answerSize = CommunityAnswerHelper.answerSizeByUserId(communityAnswerPersistences.get(0).getUSERID());
			question2_CommunityView.setTotalAnswer(Integer.toString(answerSize));
			
			//查看用户被点赞数量
			int totalLikes= CommunityAnswerHelper.getCommunityAnswerLike(communityAnswerPersistences.get(0).getUSERID());
			question2_CommunityView.setTotalLikes(Integer.toString(totalLikes));
			
			question2_CommunityView.setIsBestAnswer(Integer.toString(communityAnswerPersistences.get(0).getISBESTANSWER()));
			
			
		}
		return question2_CommunityView;
	}
	
	/*
	 * zyq_question2_问题展示_除了最佳答案其他
	 */
	public static List<Question2_CommunityView> question2_CommunityViews_other(String username,String questionId,int startNumber){
		List<Question2_CommunityView> question2_CommunityViews = new ArrayList<Question2_CommunityView>();
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_other(questionId,startNumber);
		for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
			Question2_CommunityView question2_CommunityView = new Question2_CommunityView();
			
			question2_CommunityView.setAnswerId(communityAnswerPersistence.getCOMMUNITYANSWERID());
			question2_CommunityView.setAnswer(communityAnswerPersistence.getCONTENT());
			
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
			question2_CommunityView.setUserId(userPersistences.get(0).getUSERID());
			question2_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						
			question2_CommunityView.setTime(communityAnswerPersistence.getTIME());
						
			List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
			
			//查看评论数
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment_Limit(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());			
			for(CommentPersistence commentPersistence:commentPersistences){
				Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();				
				//获取用户信息
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(commentPersistence.getUSERID());				
				question2_CommunityReplayView.setCommentId(commentPersistence.getCOMMENTID());
				question2_CommunityReplayView.setUserName(userPersistences2.get(0).getUSERNAME());
				question2_CommunityReplayView.setUserImage(userPersistences2.get(0).getAVATAR());
				question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
				question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());				
				question2_CommunityReplayView.setUserId(userPersistences2.get(0).getUSERID());
				
				if (commentPersistence.getTOUSERID()!=null) {
					String toUserName = UserHelper.getUserNameById(commentPersistence.getTOUSERID());
					question2_CommunityReplayView.setTouserName("@"+toUserName);
				}
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			
			//获取评论数
			int commentPersistences2 = CommentHelper.question2_getComment(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());
			String communityNumber = Integer.toString(commentPersistences2);
			question2_CommunityView.setCommunityNumber(communityNumber);
			
			//获取点赞数
			int agreeCount = AgreeHelper.getAgreeSizeByAnswerId(communityAnswerPersistence.getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreeCount);
			String agreeId = AgreeHelper.getAgree(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreeId == null) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
			}
			//判断是否被收藏
			String collectionId = CollectionHelper.getCollection(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			if (collectionId == null) {
				question2_CommunityView.setIsCollection("0");
			}else {
				question2_CommunityView.setIsCollection("1");
			}
			question2_CommunityView.setLikesNumber(likeNumber);
			question2_CommunityView.setReplay(question2_CommunityReplayViews);
			//查看用户回答数量
			List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_CommunityAnswer_userId(communityAnswerPersistence.getUSERID());
			//查看用户被点赞数量
			int totalLikes = CommunityAnswerHelper.getCommunityAnswerLike(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setTotalLikes(Integer.toString(totalLikes));
			question2_CommunityView.setTotalAnswer(Integer.toString(list.size()));
			question2_CommunityView.setIsBestAnswer(Integer.toString(communityAnswerPersistence.getISBESTANSWER()));
			question2_CommunityViews.add(question2_CommunityView);
			
		}
		return question2_CommunityViews;
	}
	
	/*
	 * zyq_ajax_question2回复的增加
	 */
	public static void saveReplyQuestion(String userId,String content,String questionId){
		CommunityAnswerPersistence communityAnswerPersistence = new CommunityAnswerPersistence();
		communityAnswerPersistence.setCOMMUNITYANSWERID(UUID.randomUUID().toString());
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		communityAnswerPersistence.setTIME(time);
		communityAnswerPersistence.setCONTENT(content);
		communityAnswerPersistence.setISBESTANSWER(0);
		communityAnswerPersistence.setCOMMUNITYQUESTIONID(questionId);
		communityAnswerPersistence.setUSERID(userId);
		
		//查看问题号为	questionId 的提问者id
		String questionUserId = CommunityQuestionHelper.CommunityQuestion(questionId);
		
		int isnotice = 0;			
		//判断是否为自己回复; 如果当前登录用户id = 问题号为questionId的用户id，则isnotice = 0;  即自己回复自己置isnotice = 0;
		if (userId.equals(questionUserId)) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		communityAnswerPersistence.setISNOTICE(isnotice);
		//question2回复保存至数据库
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}
		
	/*
	 * zyq_question_ajax_添加评论
	 */
	/*public static void addComment(String userid,String content,String questionId){
		CommunityAnswerPersistence communityAnswerPersistence = new CommunityAnswerPersistence();
		communityAnswerPersistence.setCOMMUNITYANSWERID(UUID.randomUUID().toString());
	    Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		communityAnswerPersistence.setTIME(time);
		communityAnswerPersistence.setCONTENT(content);
		communityAnswerPersistence.setISBESTANSWER(0);
		communityAnswerPersistence.setCOMMUNITYQUESTIONID(questionId);
		communityAnswerPersistence.setUSERID(userid);
		//查询是否回答的是自己的问题
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(questionId);
		if (userid==communityQuestionPersistences.get(0).getUSERID()) {
			communityAnswerPersistence.setISNOTICE(0);
		}else {
			communityAnswerPersistence.setISNOTICE(1);
		}
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}*/

}
