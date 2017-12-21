package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;

import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.portal.service.ConfigureService;
import org.xjtusicd3.portal.service.FaqManagerService;
import org.xjtusicd3.portal.service.QuestionService;
import org.xjtusicd3.portal.view.ConfigureDriverView;
import org.xjtusicd3.portal.view.FaqView;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author zzl
 *
 */
@Controller
public class FaqManagerController {
	/**
	 * 
	 * @return mv
	 */
	@RequestMapping(value="faqPage",method=RequestMethod.GET)
    public ModelAndView  faqPage(HttpSession session,HttpServletRequest request){
 	   ModelAndView mv=new ModelAndView("faqPage");
 	   
 	   //获取当前路径
 	   String urlPath = request.getHeader("REFERER");
 	   
 	   //获取待审核FAQ
 	   List<FaqView> faqPendingAudit = FaqManagerService.faqPendingAudits();
 	  
 	   //获取已审核FAQ
 	   List<FaqView> faqAudited = FaqManagerService.faqAudited();
// 	   String result = FaqManagerService.konwStatisticsView();
// 	   int faqTotal = QuestionHelper.getFaqTotal1();
 	  
 	   session.setAttribute("urlPath", urlPath);
 	   
 	   mv.addObject("faqPendingAudit", faqPendingAudit);
 	   mv.addObject("pendCount",faqPendingAudit.size());
 	   
 	   mv.addObject("faqAudited", faqAudited);
 	   return mv;
    }

	
	/**
	 * @abstract:将已处理事件加入faq
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");
		
		//获取路径
		String url = (String) session.getAttribute("urlPath");
		
		if (username==null) {
			return "0";
		}else {
			String questionId = request.getParameter("questionId");
			String title = request.getParameter("title");
			String keywords = request.getParameter("keywords");
			String subspecialCategoryId = request.getParameter("subspecialCategoryId");
			String description = request.getParameter("description");
			String faqcontent = request.getParameter("faqcontent");

			System.out.println(faqcontent);

			
			
			//zzl_faqAdd_校验知识是否重复增添
			List<QuestionPersistence> isExist = FaqManagerService.faqAdd(title,username);

			JSONObject jsonObject = new JSONObject();
			if (isExist.size()==0) {
				//zzl_保存知识
				QuestionService.saveFAQ(username,title,keywords,subspecialCategoryId,description,faqcontent,questionId);
				//保存知识成功返回1
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;				
			}else {			
				//重复添加返回2
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}

		}
	}
	
	/**
	 * 编辑待审核问题
	 * @param session
	 * @param q
	 * @return
	 */
	@RequestMapping(value="faqEdit",method=RequestMethod.GET)
    public ModelAndView  faqEdit(HttpSession session,String q){
 	   ModelAndView mv=new ModelAndView("faqEdit");
 	   String username = (String) session.getAttribute("UserName");
 	   if (username == null) {
 		   return new ModelAndView("login");
 	   }else {
 		   //q是传过来的faq的问题ID，获取faq问题及答案信息
 		   FaqView faqList = FaqManagerService.getAllFaqInfo(q);
 		   mv.addObject("faqList", faqList);
 	   }
 	   return mv;
    }
	
	/**
	 * 更新信息及状态
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateFaq",method=RequestMethod.POST)
	public String updateFaq(HttpServletRequest request,HttpSession session){
	
		String questionId = request.getParameter("questionId");
		String keywords = request.getParameter("keywords");
		String description = request.getParameter("description");
		String faqcontent = request.getParameter("faqcontent");
		
		//更新faq问题表信息及状态 -- "2"为管理员审核通过状态
		QuestionService.updateFaqInfo(questionId,keywords,description,2,faqcontent);
		
		return "1";
	}
	
	

	//查看faq更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreFaqInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreFaqInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String faqQuestionId = request.getParameter("faqQuestionId"); 	
		
    	//获取ID对应faq信息
		
		FaqView list = FaqManagerService.getAllFaqInfo(faqQuestionId);
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreFaqInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	
	
	
	
	
	
	/**
	 * 删除待审核信息
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteFAQ",method=RequestMethod.POST)
	public String deleteFAQ(HttpServletRequest request,HttpSession session){
	
		String questionid = request.getParameter("faqQuestionId");
		String[] str = questionid.split("_");
		String questionId = str[1];
		
		
		
		//删除待审核信息 -- faqstate状态为0时，表明删除
		FaqManagerService.deleteFAQ(questionId);
		
		return "1";
	}
	

	/**
	 * @abstract:将已解决问题加入faq
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * ！！！缺少关键字
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestionToFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveCommunityQuestionToFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		
		
		//获取路径
		String url = (String) session.getAttribute("urlPath");
		
		if (username==null) {
			return "0";
		}else {			
			String communityQuestionId = request.getParameter("communityQuestionId");
			String faqTitle = request.getParameter("title");
			String faqclassifyId = request.getParameter("classifyId");
			String modifyTime = request.getParameter("problemTime");			
			String faqDescription = request.getParameter("content");
			String userId = request.getParameter("userId");
			String answerContent = request.getParameter("answerContent");
			String problemUser = request.getParameter("problemUser");

			//zzl_faqAdd_校验知识是否重复增添
			List<QuestionPersistence> isExist = FaqManagerService.faqAdd(faqTitle,problemUser);

			JSONObject jsonObject = new JSONObject();
			if (isExist.size()==0) {
				//zzl_保存知识
				QuestionService.saveCommunityQuestionToFAQ(faqTitle,null,faqclassifyId,modifyTime,faqDescription,userId,answerContent,communityQuestionId);
				//保存知识成功返回1
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;				
			}else {			
				//重复添加返回2
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}

		}
	}
	
}
