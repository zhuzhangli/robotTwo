package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.portal.service.EventManagerService;
import org.xjtusicd3.portal.service.UserService;
import org.xjtusicd3.portal.view.EventView;
import org.xjtusicd3.portal.view.Event_AnswerView;

@Controller
/**
 * @author zzl
 * @abstract:事件管理_eventPage.ftl
 */
public class EventManagerController{
	
	/*
	 * 事件管理begin
	 */
	/**
	 * @author zzl
	 * @abstract:事件管理_eventPage.ftl
	 * @data:2017年11月4日18:28:03
	 */
	@RequestMapping(value="eventPage",method=RequestMethod.GET)
	public ModelAndView eventPage()
	{		
		ModelAndView mv = new ModelAndView("eventPage");			
		 
		//待处理事件_只显示满意度表中满意度为0的记录
		List<EventView> eventUnresolved = EventManagerService.eventUnresolved();
			
		// 已处理事件_只显示满意度表中满意度为1的记录
		List<Event_AnswerView> eventResolved = EventManagerService.eventResolved();		
		
		mv.addObject("eventUnresolved",eventUnresolved);
		mv.addObject("eventResolved",eventResolved);
		mv.addObject("UnresolvedCounts", eventUnresolved.size());
		mv.addObject("ResolvedCounts", eventResolved.size());
		
		return mv;		
	}
	
	/**
	 * @author:zzl
	 * @abstract:事件待处理_查看事件详情_showUnResolvedEvent.ftl
	 * @data:2017年11月4日21:18:29
	 */
	@RequestMapping(value="showUnResolvedEvent",method=RequestMethod.GET)
	public ModelAndView showUnResolvedEvent(String q){
		ModelAndView modelAndView = new ModelAndView("showUnResolvedEvent");
		
		//获取待处理事件详情
		EventView unResolvedEventDetail = EventManagerService.getUnResolvedEventDetail(q);
		
		modelAndView.addObject("UnResolvedEventDetail", unResolvedEventDetail);
		return modelAndView;
	}
	
	/**
	 * @author:zzl
	 * @abstract:事件已处理_查看事件详情_showResolvedEvent.ftl
	 * @data:2017年11月5日10:37:02
	 */
	@RequestMapping(value="showResolvedEvent",method=RequestMethod.GET)
	public ModelAndView showResolvedEvent(String q){
		ModelAndView modelAndView = new ModelAndView("showResolvedEvent");
		
		//获取已处理事件详情
		Event_AnswerView resolvedEventDetail = EventManagerService.getResolvedEventDetail(q);
		
		//获取一级分类名
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName();
		if (classifyPersistences == null || classifyPersistences.size()==0) {
			return null;
		}
				
		modelAndView.addObject("resolvedEventDetail", resolvedEventDetail);
		modelAndView.addObject("FirstLevel", classifyPersistences);
		
		return modelAndView;
	}
	
	/**
	 * 忽略用户提问
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ignoreUserQuestion",method=RequestMethod.POST)
	public String ignoreUserQuestion(HttpServletRequest request,HttpSession session){
	
		String userQuestionId = request.getParameter("userQuestionId");
	
		//更新tbl_robotanswer表用户问题状态 -- 2是忽略
		EventManagerService.updateQuestionState(userQuestionId,2);
		return "1";
	}
	
	/*
	 * 事件管理end
	 */
		
	
}
