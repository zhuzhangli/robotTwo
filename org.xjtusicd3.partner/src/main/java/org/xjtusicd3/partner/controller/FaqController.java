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
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.ScoreHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.TimeStampHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.ScorePersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.lucene.LuceneIndex;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.service.QuestionService;
import org.xjtusicd3.partner.service.RobotService;
import org.xjtusicd3.partner.service.ScoreService;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq1_UserActive;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;
import org.xjtusicd3.partner.view.Faq_CommendView;
import org.xjtusicd3.partner.view.Faq_UserDynamics;
import org.xjtusicd3.partner.view.robot_Chat;

import com.alibaba.fastjson.JSONObject;
@Controller
public class FaqController {
	@RequestMapping(value="faq",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq首页面")
	public ModelAndView faq(HttpSession session,HttpServletRequest request,String q){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		String username = (String) session.getAttribute("UserName");
		ModelAndView mv = new ModelAndView("faq");
		String urlPath="";
		
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		
		//查询所有用户发表知识的状态
		List<Faq_UserDynamics> userDynamics = QuestionService.userDynamics();
		session.setAttribute("urlPath", urlPath);
				
		if(username==null){
			//zzl未登录用户获取推荐faq_2017年9月14日21:43:52
			int startnum = 0;
			List<Faq_CommendView> faqlists = QuestionService.faq_recommend_Limit(startnum);
			mv.addObject("faqlists", faqlists);
		}else{
			//zzl_已登录用户获取推荐faq_2017年9月14日21:43:52
			String userId = UserHelper.getUserIdByName(username);
			int startnum = 0;
			List<Faq_CommendView> faqlists = QuestionService.user_recommend_Limit(userId,startnum);				
			mv.addObject("faqlists", faqlists);		
		}		
		mv.addObject("userDynamics", userDynamics);
		
		long executionTime = System.currentTimeMillis() - startTime;		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
		return mv;		
	}
	
	/*
	 * zyq_faqadd_FAQ的增加页面
	 */
	@RequestMapping(value="faqadd",method=RequestMethod.GET)
	@SystemControllerLog(description = "FAQ的增加页面")
	public ModelAndView faqadd(HttpSession session,HttpServletRequest request){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		ModelAndView mv = new ModelAndView("faqadd");
		String urlPath = request.getHeader("REFERER");
		session.setAttribute("urlPath", urlPath);		
		long executionTime = System.currentTimeMillis() - startTime;
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
		return mv;
	}
	
	/*
	 * zyq_ajax_FAQ的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "FAQ的增加")
	public String saveFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();		
		String username = (String) session.getAttribute("UserName");
		String url = (String) session.getAttribute("urlPath");
		if (username==null) {
			return "0";
		}else {
			String title = request.getParameter("title");
			String keywords = request.getParameter("keywords");
			String subspecialCategoryId = request.getParameter("subspecialCategoryId");
			String description = request.getParameter("description");
			//String risk_prompt = request.getParameter("risk_prompt");
			String faqcontent = request.getParameter("faqcontent");
			String userId = UserHelper.getUserIdByName(username);
			//zzl_faqadd_校验知识是否重复增添
			String questionId = QuestionHelper.faqadd_iscurrent2(title,userId);
			JSONObject jsonObject = new JSONObject();
			if (questionId == null) {				
				//zzl_保存知识
				QuestionService.saveFAQ2(userId,title,keywords,subspecialCategoryId,description,faqcontent);
				jsonObject.put("value", "1");
				jsonObject.put("urlpath", url);
				String result = JsonUtil.toJsonString(jsonObject);
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				
				return result;
			}else {
				jsonObject.put("value", "2");
				jsonObject.put("urlpath", url);
				String result = JsonUtil.toJsonString(jsonObject);
				
				long executionTime = System.currentTimeMillis() - startTime;
				
				//记录运行时间
				TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
				return result;
			}
		}
	}
		
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@RequestMapping(value="faq1",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq、faq1_上侧的第二级分类")
	public ModelAndView classifyName2(HttpSession session,HttpServletRequest request,String p){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
		
		ModelAndView modelAndView = new ModelAndView("faq1");		
		//zzl_获取一级分类信息
		List<ClassifyPersistence> classifyFirstInfo = ClassifyHelper.getInfoById(p);
		//zzl_获取二级分类
		List<ClassifyPersistence> classifySecondInfo = ClassifyHelper.faq1_ClassifyName(p);
		//获取faq1底部的4个推荐栏_按照浏览量
		List<Faq1_ClassifyView> list2 = ClassifyService.faq1_ClassifyView(p);
		//今日活跃用户
		List<Faq1_UserActive> faq1_UserActives = CommentService.faq1_userActive();
		//本周活跃用户
		List<Faq1_UserActive> faq1_UserActives2 = CommentService.faq1_userActive_week();
		if (classifySecondInfo == null || classifySecondInfo.size()==0) {
			long executionTime = System.currentTimeMillis() - startTime;			
			//记录运行时间
			TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
			return null;
		}
		
		//zzl_推荐知识_根据收藏量推荐前4个_2017年9月17日19:45:11
		List<Faq_CommendView> faq_list = QuestionService.faqInfo(p);
				
		modelAndView.addObject("classifyInfo", classifyFirstInfo);
		modelAndView.addObject("faq1_list", classifySecondInfo);
		modelAndView.addObject("faq1_list2", list2);
		modelAndView.addObject("userActive", faq1_UserActives);
		modelAndView.addObject("userActiveWeek", faq1_UserActives2);
		modelAndView.addObject("faq_list", faq_list);
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);;	
		long executionTime = System.currentTimeMillis() - startTime;
		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);		
		return modelAndView;
	}
	
	/*
	 * faq2_知识列表
	 */
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq2_知识列表")
	public ModelAndView faqList(HttpSession session,HttpServletRequest request,String c){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();				
		ModelAndView modelAndView = new ModelAndView("faq2");
		//获取一级分类信息
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(c);
		//获取二级分类信息
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(c);
		//获取首次的具体faq标题信息		
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(c,1);
		//获取今日活跃用户		
		List<Faq1_UserActive> faq1_UserActives = CommentService.faq1_userActive();
		//获取本周活跃用户
		List<Faq1_UserActive> faq1_UserActives2 = CommentService.faq1_userActive_week();
		modelAndView.addObject("faq2_list", classify);
		modelAndView.addObject("faq2_list2", classify2);
		modelAndView.addObject("faq2_list3", faq2Views);
		modelAndView.addObject("userActive", faq1_UserActives);
		modelAndView.addObject("userActiveWeek", faq1_UserActives2);
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
		return modelAndView;
	}
	
	/*
	 * faq2_ajax请求更多知识列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaqList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq2_ajax请求更多知识列表")
	public String faq2list(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();			
		int pagenow = Integer.parseInt(request.getParameter("pagenow"));
		int pageNow = pagenow+1;
		String ClassifyId = request.getParameter("classifyId");
		//获取5条faq2信息
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(ClassifyId, pageNow);
		//获取该分类下faq信息的总数
		int faqTotal = QuestionHelper.pageTotal(ClassifyId);
		//总页数
		int pageTotal = (int) Math.ceil((double)faqTotal/(double)5);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagenow", pageNow);
		jsonObject.put("faqlist", faq2Views);
		jsonObject.put("pageTotal",pageTotal);
		String faq2_list = JsonUtil.toJsonString(jsonObject);		
		long executionTime = System.currentTimeMillis() - startTime;
		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);		
		return faq2_list;
	 }
	
	
	/*
	 * faq3_知识内容
	 */
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq3_知识内容")
	public ModelAndView faqContent(HttpSession session,HttpServletRequest request,String q) throws Exception{
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();			
		String username = (String) session.getAttribute("UserName");
		String userId = UserHelper.getUserIdByName(username);
		ModelAndView modelAndView = new ModelAndView("faq3");
		String classifyId = QuestionHelper.faqclassify(q);
		//获取该分类的父分类信息
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(classifyId);
		//获取该分类的信息
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(classifyId);
		//获取问题的具体信息
		List<Faq3_faqContentView> faq3Views = QuestionService.faq3_faqcontent(q);
		//获取评论总数
		int commentCount = CommentHelper.commentInfo(q);
		//获取评论信息
		List<Faq3_CommentView> faq3_CommentViews = CommentService.faq3_comment(q,0);				
		//登录FAQ 增加浏览量
		QuestionService.updateFAQScan(q);		
		//FAQ的总评分展示
		List<ScorePersistence> FAQlist = ScoreHelper.getScoreList(q);
		//获取该问题的总分
		float totalscore = ScoreHelper.getScore(q);
		int number;
		if (totalscore==0) {
			number = 1;
		}else {
			number = FAQlist.size();
		}
		float score = totalscore/number;
		modelAndView.addObject("score", score);
		if (username!=null) {	
			modelAndView.addObject("userName", username);
			//判断用户是否收藏
			String collectionId = CollectionHelper.getCollection2(userId, q);		
			modelAndView.addObject("collection", collectionId);
			//判断用户是否评分
			List<ScorePersistence> scorePersistences = ScoreHelper.getScoreList(q);
			modelAndView.addObject("scoreList", scorePersistences);
			modelAndView.addObject("scoreSize", scorePersistences.size());
			//判断是否有分享内容的权利
			List<ITPersistence> list = ITHelper.IT(userId);
			if (list.size()==0) {
				modelAndView.addObject("IsIT", "0");
			}else{
				modelAndView.addObject("IsIT", "1");
				String shareId = ShareHelper.getShareList_ID(userId,q);
				if (shareId==null) {
					modelAndView.addObject("IsShare", "0");
				}else {
					modelAndView.addObject("IsShare", "1");
				}
			}
		}
		//查看相关的问题	！！！未看
		List<robot_Chat> robot_Chats = RobotService.getRobotAnswer(faq3Views.get(0).getFaqTitle());
				
		modelAndView.addObject("commentNumber", commentCount);
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq3Views", faq3Views);
		modelAndView.addObject("comment", faq3_CommentViews);
		modelAndView.addObject("faqSimilarity", robot_Chats);
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
		return modelAndView;
	}
	
	/*
	 * zyq_faq3_ajax_FAQ评分
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQscore"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "FAQ评分")
	public String saveFAQscore(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		float score = Float.parseFloat(request.getParameter("score"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			String userId = UserHelper.getUserIdByName(username);
			//保存评分
			ScoreService.saveFAQscore(questionId, userId, score);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/*
	 * zyq_faq3_ajax_FAQ分享
	 */
	@ResponseBody
	@RequestMapping(value={"/saveShare"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "FAQ分享")
	public String saveShare(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String state = request.getParameter("state");
		String from = request.getParameter("from");
		JSONObject jsonObject = new JSONObject();
		String userId = UserHelper.getUserIdByName(username);
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (from.equals("communityQuestion")) {
				String shareId = ShareHelper.getShareList_ID2(userId, questionId);
				if (shareId==null) {
					QuestionService.saveShare2(userId, questionId);
					jsonObject.put("value", "1");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}else {
					ShareHelper.deleteShare(shareId);
					jsonObject.put("value", "2");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}
			}else {
				if (state.equals("1")) {
					//faq3分享
					QuestionService.saveShare(userId, questionId);
					jsonObject.put("value", "1");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}else {
					String shareId = ShareHelper.getShareList_ID(userId, questionId);
					if (shareId==null) {
						jsonObject.put("value", "1");
						String result = JsonUtil.toJsonString(jsonObject);
						return result;
					}else {
						ShareHelper.deleteShare(shareId);
						jsonObject.put("value", "2");
						String result = JsonUtil.toJsonString(jsonObject);
						return result;
					}
				}
			}
		}
	}
	
	/**
	 * author:zhaoyanqing	！！！
	 * abstract:用来建立luence的知识库搜索
	 * data:2017年8月20日 20:52:06
	 * @throws Exception 
	 */
	@RequestMapping(value="/faqSearch",method=RequestMethod.POST)
	@SystemControllerLog(description = "FAQ查找")
	public ModelAndView faqSearch(HttpSession session,HttpServletRequest request) throws Exception{
		String queryStr = request.getParameter("queryString");
		ModelAndView modelAndView = new ModelAndView("faqSearch");
		List<QuestionPersistence> qList = LuceneIndex.searchFAQ(queryStr);
		
		
		List<Faq2_faqContentView> faq2List = LuceneIndex.faq2_faqContentViews(qList, 0 ,qList.size());
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		modelAndView.addObject("faq2List", faq2List);
		modelAndView.addObject("queryStr", queryStr);
		modelAndView.addObject("titleNumber", qList.size());
		return modelAndView;
	}
	
	/**
	 * author:zhaoyanqing	！！！
	 * abstract:查看luence搜索更多的结果
	 * data:2017年9月13日 13:50:17
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreResult"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "获取更多FAQ结果")
	public String queryMoreResult(HttpSession session,HttpServletRequest request) throws Exception{
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if(username==null){
			jsonObject.put("value", "0");
			return JsonUtil.toJsonString(jsonObject);
		}else{
			String queryStr = request.getParameter("queryStr");
			int starNum = Integer.parseInt(request.getParameter("starNumb"));
			List<QuestionPersistence> qList = LuceneIndex.searchFAQ(queryStr);
			List<Faq2_faqContentView> faq2List = LuceneIndex.faq2_faqContentViews(qList, starNum ,qList.size());
			jsonObject.put("value", "1");
			jsonObject.put("queryList", faq2List);
			return JsonUtil.toJsonString(jsonObject);
		}
	}
		
}
