package org.xjtusicd3.partner.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AgreeHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.TimeStampHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.view.Faq3_CommentReplyView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class CommentController {
	/*
	 * zyq_question_ajax_点赞
	 */
	@ResponseBody
	@RequestMapping(value={"/saveAgreeAnswer2"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区问题点赞")
	public String saveAgreeAnswer2(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		
		//查找最佳问题的最佳答案id
		String answerId = CommunityAnswerHelper.findAnswerIdFromBestAnswer(questionId,1);
		
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {			
			String agreeId = AgreeHelper.getAgree(username, answerId);			
			if (agreeId == null) {
				AgreeHelper.saveAgree(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				AgreeHelper.deleteAgree(agreeId);
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	
	/*
	 * zyq_question2_ajax_点赞
	 */
	@ResponseBody
	@RequestMapping(value={"/saveAgreeAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区具体问题点赞")
	public String saveAgreeAnswer(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String answerId = request.getParameter("answerId");	
		String agreeId = AgreeHelper.getAgree(username, answerId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (agreeId == null) {
				AgreeHelper.saveAgree(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				AgreeHelper.deleteAgree(agreeId);
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	
	/*
	 * zyq_question2_ajax_收藏
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCollectionAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区问题收藏")
	public String saveCollectionAnswer(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String answerId = request.getParameter("answerId");
		String collectionId = CollectionHelper.getCollection(username, answerId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (collectionId == null) {
				CollectionHelper.saveCollection(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				CollectionHelper.deleteCollection(collectionId);
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	
	/*
	 * zyq_question2_ajax_查看更多回复
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区问题查看更多回复")
	public String getMoreComment(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			//更多回复
			List<Question2_CommunityReplayView> list = CommentService.question2_CommunityReplayViews(questionId, answerId, startnumber);
			int commentPersistences = CommentHelper.question2_getComment(questionId, answerId);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+list.size());
			jsonObject.put("commentList", list);
			jsonObject.put("totalnumber", commentPersistences);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	/*
	 * zyq_question2_ajax_设为最佳答案
	 */
	@ResponseBody
	@RequestMapping(value={"/saveBestAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区问题设置最佳答案")
	public String saveBestAnswer(HttpServletRequest request,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName");
		String answerId = request.getParameter("answerId");
		String questionId = request.getParameter("questionId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			return result;
		}else {
			//设置最佳答案
			CommentService.saveBestAnswer(questionId,answerId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 			
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);			
			return result;
		}
	}
	
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	@ResponseBody
	@RequestMapping(value={"/saveComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	@SystemControllerLog(description = "faq3添加评论")
	public String saveComment(HttpServletRequest request,HttpServletResponse response,HttpSession session){	
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();				
		String username = (String) session.getAttribute("UserName");
		String faqQuestionId = request.getParameter("faqQuestionId");
		String comment = request.getParameter("comment");
		String faquserId = request.getParameter("faquserId");		
		if (username==null) {
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);			
			return "0";
		}else {
			String userId = UserHelper.getUserIdByName(username);	
			//添加评论
			CommentService.addComment(userId,faqQuestionId,comment,faquserId);
			long executionTime = System.currentTimeMillis() - startTime;		
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);		
			return "1";
		}
	 }
	
	/*
	 * zyq_question2_ajax_添加评论的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	@SystemControllerLog(description = "社区问题添加评论的回复")
	public String saveCommunityComment(HttpServletRequest request,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		String content = request.getParameter("content");
		JSONObject jsonObject = new JSONObject();
		/**
		 * 用户名为空，返回value = 0；
		 * 用户未提交过回复，将回复存入数据库，并返回value = 1；
		 * 用户重复提交回复，返回value = 2
		 * */
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			return result;
		}else {
			//获取登录用户信息
			String userId = UserHelper.getUserIdByName(username);
			//判断用户(userId)对问题答案(Id为answerId)的回复是否重复提交
			String commentId = CommentHelper.question2_getComment2(answerId, userId, content,questionId);
			if (commentId == null) {
				//保存用户评论
				CommentService.saveCommunityComment(userId, questionId, content, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 				
				long executionTime = System.currentTimeMillis() - startTime;				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 				
				long executionTime = System.currentTimeMillis() - startTime;				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
			}
		}
	}
	
	/*
	 * zyq_faq3_ajax_添加知识库评论的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFaqComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq3添加知识库评论的回复")
	public String saveFaqComment(HttpServletRequest request,HttpSession session){	
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();			
		String username = (String) session.getAttribute("UserName");  
		String questionId = request.getParameter("questionId");
		String commentId = request.getParameter("commentId");
		String content = request.getParameter("comment");
		String duo = request.getParameter("duo");//判断是回复评论还是回复回复
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);			
			return result;
		}else {
			String userId = UserHelper.getUserIdByName(username);
			//判断回复是否重复提交
			String userid = CommentHelper.faq3_getComment(commentId,userId,content,questionId);
			if (userid == null) {
				//保存评论
				CommentService.saveFaqComment(userId, questionId, content, commentId,duo);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 				
				long executionTime = System.currentTimeMillis() - startTime;				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 				
				long executionTime = System.currentTimeMillis() - startTime;
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
			}
		}
	}
		
	/*
	 * zyq_faq3_ajax_删除自己的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq3删除自己的回复")
	public String deleteReply(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String commentId = request.getParameter("commentId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			CommentHelper.deleteReply(commentId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
		
	/**
	 * zyq_question2_ajax_收藏
	 * faq3_收藏
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCollectionFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "FAQ问题收藏")
	public String saveCollectionFAQ(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");		
		String userId = UserHelper.getUserIdByName(username);
		//查看是否已收藏
		String collectionId = CollectionHelper.getCollection2(userId, questionId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (collectionId == null) {
				//收藏
				CollectionHelper.saveCollection2(userId, questionId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				//取消收藏
				CollectionHelper.deleteCollection(collectionId);
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	
	/*
	 * zyq_faq3_ajax_获得更多评论
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq3获得更多评论")
	public String queryMoreComment(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			//评论总数
			int commentCount = CommentHelper.getComment2(questionId,"0");
			//获取评论列表
			List<Faq3_CommentView> faq3_CommentViews = CommentService.faq3_comment(questionId,startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+faq3_CommentViews.size());
			jsonObject.put("totalnumber", commentCount);
			jsonObject.put("commentList", faq3_CommentViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	/*
	 * zyq_faq3_ajax_获得更多回复
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq3获得更多回复")
	public String queryMoreReply(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String commentId = request.getParameter("commentid");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(commentId);
			List<Faq3_CommentReplyView> faq3_CommentReplyViews = CommentService.faq3_CommentReplyViews(commentId,startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+faq3_CommentReplyViews.size());
			jsonObject.put("totalnumber", commentTotalnumber);
			jsonObject.put("commentList", faq3_CommentReplyViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}

	/*
	 * zyq_question_ajax_添加评论
	 */
	/*@ResponseBody
	@RequestMapping(value={"/addComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	@SystemControllerLog(description = "社区问题添加评论")
	public String addComment(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName"); 
		String url = (String) session.getAttribute("urlPath");
		String questionId = request.getParameter("questionId");
		String commentContent = request.getParameter("commentContent");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			long executionTime = System.currentTimeMillis() - startTime;
			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			
			return result;
		}else {
			//判断评论是否重复提交
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username); 
			String commentIsExist = CommunityAnswerHelper.question_IsCommunityAnswer(userPersistences.get(0).getUSERID(), commentContent,questionId);
			if (commentIsExist==null) {
				CommunityService.addComment(userPersistences.get(0).getUSERID(), commentContent, questionId);
				jsonObject.put("value", "1");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
				
			}else{
				jsonObject.put("value", "2");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
			}
		}
	}
*/
	/*
	 * zyq_question2_ajax_添加评论的回复的回复
	 */
	/*@ResponseBody
	@RequestMapping(value={"/saveCommunityReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	@SystemControllerLog(description = "社区问题添加评论的回复的回复")
	public String saveCommunityReply(HttpServletRequest request,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName"); 		
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		String content = request.getParameter("content");
		String toUserName = request.getParameter("tousername");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			
			long executionTime = System.currentTimeMillis() - startTime;
			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			//判断回复的回复是否重复提交
			List<UserPersistence> userPersistences2 = UserHelper.getEmail_name(toUserName);
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment3(answerId, userPersistences.get(0).getUSERID(), content,questionId,userPersistences2.get(0).getUSERID());
			if (commentPersistences.size()==0) {
				CommentService.saveCommunityReply(userPersistences.get(0).getUSERID(), questionId, content, answerId,userPersistences2.get(0).getUSERID());
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				
				return result;
			}
		}
	}
	*/

}
