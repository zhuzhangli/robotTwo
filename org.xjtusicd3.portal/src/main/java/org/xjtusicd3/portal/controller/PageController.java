package org.xjtusicd3.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@RequestMapping(value="index",method=RequestMethod.GET)
    public ModelAndView  index(HttpServletRequest req){
 	   ModelAndView mv=new ModelAndView("index");
 	   return mv;
    }

	//登录页面
	 @RequestMapping(value="login",method=RequestMethod.GET)
     public ModelAndView login(){
  	   ModelAndView mv=new ModelAndView("login");
  	   return mv;
     }
	
	
	
		
	
		
	//ITIL管理流程_配置管理
	@RequestMapping(value="configurePage",method=RequestMethod.GET)
    public ModelAndView  configurePage(){
 	   ModelAndView mv=new ModelAndView("configurePage");
 	   return mv;
    }
	
	//知识库增加
	@RequestMapping(value="faqAdd",method=RequestMethod.GET)
    public ModelAndView  faqAdd(){
 	   ModelAndView mv=new ModelAndView("faqAdd");
 	   return mv;
    }


	
	
	
	
	
	
	
	
	 
	

	
	 
	
	@RequestMapping(value="spiderindex",method=RequestMethod.GET)
    public ModelAndView  spider(){
 	   ModelAndView mv=new ModelAndView("spiderindex");
 	   return mv;
    }

 
	

	 @RequestMapping(value="register",method=RequestMethod.GET)
     public ModelAndView  register(){
  	   ModelAndView mv=new ModelAndView("register");
  	   return mv;
     }


}
