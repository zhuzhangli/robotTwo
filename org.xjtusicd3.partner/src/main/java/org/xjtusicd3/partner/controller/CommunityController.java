package org.xjtusicd3.partner.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.TimeStampHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.service.CommunityService;
import org.xjtusicd3.partner.view.Question2_CommunityView;
import org.xjtusicd3.partner.view.Question_CommunityView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class CommunityController {
	/*
	 * zyq_question页面展示
	 */
	@RequestMapping(value="question",method=RequestMethod.GET)
	@SystemControllerLog(description = "社区主页显示")
	public ModelAndView question(String c,String type,HttpServletRequest request,HttpSession session){		
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		String username = (String) session.getAttribute("UserName");		
		//获取一级分类信息
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName();		
		//社区问题展示
		List<Question_CommunityView> question_CommunityViews = CommunityService.Question_CommunityView(username,0,type,c);		
		ModelAndView mv = new ModelAndView("question");
		mv.addObject("classifyList", classifyPersistences);
		mv.addObject("communityViews", question_CommunityViews);	
		String typename = "";
		if (type.equals("all")) {
			typename="全部";
		}else if (type.equals("1")) {
			typename="已解决";
		}else if (type.equals("2")) {
			typename="待回答";
		}
		mv.addObject("typename", typename);
		mv.addObject("userName", username);		
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		
		long executionTime = System.currentTimeMillis() - startTime;
		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
		return mv;
	}
	
	
	/*
	 * zyq_ajax_question的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "社区问题增加")
	public String saveCommunityQuestion(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName");
		String url = (String) session.getAttribute("urlPath");
		JSONObject jsonObject = new JSONObject();		
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);			
			return result;
		}else {
			String title = request.getParameter("title");
			String content = request.getParameter("description");
			String classifyId = request.getParameter("check_val");			
			String userId = UserHelper.getUserIdByName(username);			
			//查看新问题是否已存在
			String questionId = CommunityQuestionHelper.question_iscurrent(userId, title);			
			if (questionId == null) {
				//数据库不存在，则添加至问题中心
				CommunityService.savaCommunityQuestion(username, title, content, classifyId);
				jsonObject.put("value", "1");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 				
				long executionTime = System.currentTimeMillis() - startTime;				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
			}else {
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
	
	
	/*
	 * zyq_question_ajax_获取更多问题
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区主页显示更多")
	public String getMoreCommunity(HttpServletRequest request,HttpSession session)
	{	
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();			
		// 用户名用于判断是否登录
		String username = (String) session.getAttribute("UserName");
		// 已经加载的数目
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		// 全部 = all、 已解决 = 1、待解决 = 2
		String type = request.getParameter("type");
		// 具体问题分类
		String c = request.getParameter("c");				
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			// 未登录  返回登录界面
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
		else{
			// 获得新的 5 条社区问题
			List<Question_CommunityView> question_CommunityViews = CommunityService.Question_CommunityView(username,startnumber,type,c);			
			// 返回分类名是c，faq父id是'0'的所有一级分类
			String faqClassifyId = ClassifyHelper.question_ClassifyListByName(c, "0");			
			if (type=="all")    //全部
			{
				int communityQuestionSize = CommunityQuestionHelper.questionSizeByClassifyId(faqClassifyId);
				jsonObject.put("totalnumber", communityQuestionSize);
			}
			else if (type=="1") // 已解决
			{
				int communityQuestionSize = CommunityQuestionHelper.questionSizeByClassifyIdLimit(faqClassifyId, 1);
				jsonObject.put("totalnumber", communityQuestionSize);
			}
			else if (type=="2") // 待解决 
			{
				int communityQuestionSize = CommunityQuestionHelper.questionSizeByClassifyIdLimit(faqClassifyId, 0);
				jsonObject.put("totalnumber", communityQuestionSize);
			}
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+question_CommunityViews.size());
			jsonObject.put("communityViews", question_CommunityViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			
			long executionTime = System.currentTimeMillis() - startTime;		
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			return result;
		}
	}
	
	/*
	 * zyq_question2_问题具体内容展示
	 */
	@RequestMapping(value="question2",method=RequestMethod.GET)
	@SystemControllerLog(description = "社区具体问题显示")
	public ModelAndView question2(HttpServletRequest request,HttpServletResponse response,String q,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName");
		ModelAndView mv = new ModelAndView("question2");
		//获取用户信息
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		String userId = UserHelper.getUserIdByName(username);
		
		//获取questionId = q 的问题详情
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(q);		
		String classifyId = communityQuestionPersistences.get(0).getCLASSIFYID();
				
		//通过分类id查找分类名
		String classifyName = ClassifyHelper.getClassifyNameById(classifyId);
				
		//相关问题
		List<CommunityQuestionPersistence> similarQuestion = CommunityQuestionHelper.selectQuestionByClassifyId(classifyId);
		
		//判断该问题是否有最佳答案
		String hasBestAnswer = CommunityAnswerHelper.findAnswerIdFromBestAnswer(q, 1);			
		if (hasBestAnswer != null) {
			//最佳答案的展示
			Question2_CommunityView question2_CommunityViews = CommunityService.question2_CommunityViews_best(username,q);
			mv.addObject("answerList_best", question2_CommunityViews);
			
			//判断是否有分享内容的权利
			List<ITPersistence> list = ITHelper.IT(userId);

			if (list.size()==0) {
				mv.addObject("IsIT", "0");
			}else{
				mv.addObject("IsIT", "1");
				String shareId = ShareHelper.getShareList_ID2(userId,q);
		
				if (shareId == null) {
					mv.addObject("IsShare", "0");
				}else {
					mv.addObject("IsShare", "1");
				}

			}
		}
				
		int startNumber = 0;
		//非最佳答案的展示
		List<Question2_CommunityView> question2_CommunityViews2 = CommunityService.question2_CommunityViews_other(username,q,startNumber);
		
		//该问题评论总个数
		int communityAnswerSize = CommunityAnswerHelper.question_CommunityAnswerSize(q);

		
		mv.addObject("userList", userPersistences);
		mv.addObject("userid", userId);
		mv.addObject("userName", userPersistences.get(0).getUSERNAME());
		mv.addObject("questionList", communityQuestionPersistences);
		mv.addObject("classifyName", classifyName);
				
		//相关问题的similarQuestion
		mv.addObject("similarQuestion", similarQuestion);
		mv.addObject("hasBestAnswer", hasBestAnswer);		
		mv.addObject("answerList_other", question2_CommunityViews2);		
		mv.addObject("communityNumber", communityAnswerSize);
		mv.addObject("_userid", communityQuestionPersistences.get(0).getUSERID());		
		long executionTime = System.currentTimeMillis() - startTime;		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);		
		return mv;
	}
	
	/*
	 * zyq_ajax_question2回复的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveReplyQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "社区问题回复")
	public String saveReplyQuestion(HttpServletRequest request,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
			
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		String url = request.getParameter("url");
		/**
		 * 当用户名为空，返回value = 0;
		 * 当用户名不空，评论未重复时，返回value = 1；
		 * 当用户名不空，评论重复时，返回value = 2；
		 */
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject);			
			long executionTime = System.currentTimeMillis() - startTime;
			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);			
			return result;
		}else {
			//获取问题评论及问题号
			String content = request.getParameter("content");
			String questionId = request.getParameter("questionId");			
			//获取登录用户信息
			String userId = UserHelper.getUserIdByName(username);			
			//判断评论是否重复提交
			String commentIsExist = CommunityAnswerHelper.question_IsCommunityAnswer(userId, content, questionId);			
			if (commentIsExist == null) {
				//评论未重复
				CommunityService.saveReplyQuestion(userId, content, questionId);
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);				
				return result;
			}else {
				//评论重复
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				long executionTime = System.currentTimeMillis() - startTime;				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);				
				return result;
			}			
		}
	}
	
	/*
	 * zyq_question2_ajax_获得更多评论
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreComment2"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "获得更多社区问题评论")
	public String queryMoreComment2(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionId);
			List<Question2_CommunityView> question2_CommunityViews = CommunityService.question2_CommunityViews_other(username, questionId, startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+question2_CommunityViews.size());
			jsonObject.put("totalnumber", answerPersistences.size());
			jsonObject.put("commentList", question2_CommunityViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			System.out.println(result);
			return result;
		}
	}
}
