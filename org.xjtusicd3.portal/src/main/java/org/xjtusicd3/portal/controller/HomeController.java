package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.EventManagerService;
import org.xjtusicd3.portal.service.FaqManagerService;
import org.xjtusicd3.portal.service.ProblemManagerService;
import org.xjtusicd3.portal.service.UserService;
import org.xjtusicd3.portal.view.EventView;
import org.xjtusicd3.portal.view.FaqView;
import org.xjtusicd3.portal.view.ProblemView;
import org.xjtusicd3.portal.view.UserView;
/**
 * 
 * @author zzl
 *
 */
@Controller
public class HomeController {
	//首页
	@RequestMapping(value="homePage",method=RequestMethod.GET)
    public ModelAndView  homePage(){
 	   ModelAndView mv=new ModelAndView("homePage");
 	   //待处理事件
 	   List<EventView> eventUnresolved = EventManagerService.eventUnresolved();
 	  
 	   //待处理问题
 	   List<ProblemView> problemUnresolved = ProblemManagerService.problemUnresolved();
 	    	   
 	   //待审核FAQ
 	   List<FaqView> faqPendingAudit = FaqManagerService.faqPendingAudits();
 	   
 	   //待审核用户
 	   List<UserView> pendingAuditUsers = UserService.getAllPendingAuditUsers();
 	   
 	   mv.addObject("eventUnresolvedCounts", eventUnresolved.size());
 	   mv.addObject("problemUnresolvedCounts", problemUnresolved.size());
 	   mv.addObject("pendCounts",faqPendingAudit.size());
 	   mv.addObject("pendingAuditCounts", pendingAuditUsers.size());
 	   return mv;
    }
}
