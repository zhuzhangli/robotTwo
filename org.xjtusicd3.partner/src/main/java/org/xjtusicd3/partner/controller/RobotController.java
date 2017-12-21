package org.xjtusicd3.partner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.RobotHelper;
import org.xjtusicd3.database.helper.TimeStampHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.RobotPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.service.RobotService;
import org.xjtusicd3.partner.service.UserQuestionService;
import org.xjtusicd3.partner.view.robot_Chat;

import com.alibaba.fastjson.JSONObject;

@Controller
public class RobotController {
	/*
	 * robot_ajax_获取机器人信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getRobotInfo"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "获取机器人信息")
	public String RobotInfo(HttpServletResponse response,HttpServletRequest request){
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//获得机器人信息
		List<RobotPersistence> list = RobotService.robotinfo();		
		String result = JsonUtil.toJsonString(list);		
		long executionTime = System.currentTimeMillis() - startTime;		
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
		return result;
	 }
	
	/**
	 * author:zhaoyanqing
	 * abstract:robot页面 点击“提问技巧”
	 * data:2017年9月17日 23:09:59
	 */
	@ResponseBody
	@RequestMapping(value={"/questionSkill"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "提问技巧")
	public String questionSkill(HttpSession session,HttpServletResponse response){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		
		//获取机器人信息
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();		
		jsonObject.put("robotInfo", robotPersistences);
		
		//用户名为空返回 0，不空返回 1
		if (username==null) {
			jsonObject.put("value", "0");
		}else {
			jsonObject.put("value", "1");
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);			
			jsonObject.put("robotUser", userPersistences);
		}
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	 }	
	
	/*
	 * robot_ajax_和机器人聊天
	 */
	@ResponseBody
	@RequestMapping(value={"/chatWithRobot"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "与机器人聊天")
	public String ChatWithRobot(HttpServletRequest request, HttpSession session) throws Exception{
		long startTime = System.currentTimeMillis();//计算开始日期
		String path = request.getServletPath();	
				
		//记录前台用户提问
		String comment = request.getParameter("comment");
		String from = request.getParameter("from");
		String username = (String) session.getAttribute("UserName");
		String questionId = UUID.randomUUID().toString();
		
		//zzl_记录用户提问记录_2017年10月22日11:23:00
		UserQuestionService.addUserQuestion(questionId,username,comment,from);
		JSONObject jsonObject = new JSONObject();
		
		//获取问题的答案
		List<robot_Chat> robot_Chats = RobotService.getRobotAnswer(comment);
		
		String answerId;
		if (robot_Chats.size()==0) {
			answerId = "00000000-0000-0000-0000-000000000000";
		}else {
			answerId = robot_Chats.get(0).getAnswerId();
		}
		
		//获取机器人信息，如欢迎语及不理解时的话语
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();		
		jsonObject.put("value", "1");
		//robotChat为推荐问题答案
		jsonObject.put("robotChat", robot_Chats);
		jsonObject.put("robotInfo", robotPersistences);
		jsonObject.put("questionId", questionId);
		jsonObject.put("answerId", answerId);
		
		if (username!=null) {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			jsonObject.put("robotUser", userPersistences);
		}else {
			List<UserPersistence> userPersistences = new ArrayList<UserPersistence>();
			jsonObject.put("robotUser", userPersistences);
		}
		String result = JsonUtil.toJsonString(jsonObject);
		long executionTime = System.currentTimeMillis() - startTime;
		//记录运行时间
		TimeStampHelper.addTimeStamp(UUID.randomUUID().toString(),path,executionTime,startTime);
		return result;
	}
	
	/**
	 * author:zzl
	 * abstract:robot页面 回复对用户有帮助
	 * data:2017年10月30日16:47:27
	 */
	@ResponseBody
	@RequestMapping(value={"/beHelpful"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "用户对回复满意")
	public String beHelpful(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		String answerId = request.getParameter("answerId");
		String questionId = request.getParameter("questionId");		
		if (answerId == null) {
			answerId = "00000000-0000-0000-0000-000000000000";
		}		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		
		//获取机器人信息
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("robotInfo", robotPersistences);
	
		//查看是否已填写过满意度
		String robotAnswerId = UserQuestionHelper.getQuertionInfo(questionId);
		
		if (robotAnswerId==null) {
			/*
			 * 用户满意SATICFACTION置为 1 
			 * 用户名为空value返回 0，不空返回 1
			 */
			if (username==null) {
				UserQuestionService.addUserSaticfaction(questionId,answerId,1);
				jsonObject.put("value", "0");
			}else {
				List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
				jsonObject.put("robotUser", userPersistences);
				UserQuestionService.addUserSaticfaction(questionId, answerId, 1);
				jsonObject.put("value", "1");
			}
		}else {
			jsonObject.put("value", "2");
		}		
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	 }
	
	/**
	 * author:zzl
	 * abstract:用户不满意回复
	 * data:2017年10月30日20:14:36
	 */
	@ResponseBody
	@RequestMapping(value={"/NoHelpful"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "用户对回复不满意")
	public String NoHelpful(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		String answerId = request.getParameter("answerId");
		String questionId = request.getParameter("questionId");
		if (answerId == null) {
			answerId = "00000000-0000-0000-0000-000000000000";
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		
		//获取机器人信息
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("robotInfo", robotPersistences);
		
		//查看是否已填写过满意度
		String robotAnswerId = UserQuestionHelper.getQuertionInfo(questionId);
		
		if (robotAnswerId ==null) {
			/*
			 * 用户不满意SATICFACTION置为 0 
			 * 用户名为空value返回 0，不空返回 1
			 */
			if (username==null) {
				UserQuestionService.addUserSaticfaction(questionId,answerId,0);
				jsonObject.put("value", "0");
			}else {
				//获取用户信息
				List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
				jsonObject.put("robotUser", userPersistences);

				UserQuestionService.addUserSaticfaction(questionId,answerId,0);
				jsonObject.put("value", "1");
			}
		}else {
			jsonObject.put("value", "2");
		}
		
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	 }
	
	
}
