package org.xjtusicd3.portal.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.xjtusicd3.database.helper.RoleHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.RolePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.UserService;
import org.xjtusicd3.portal.view.UserView;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author zzl
 *
 */
@Controller
public class UserController 
{
	//login admin
	@RequestMapping(value="adminLogin",method=RequestMethod.POST)
	public String adminLogin(HttpSession session,UserView userView,HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("nameOrEmail");
		String psw = request.getParameter("userPassword");
		String password = StringToMd5(psw);
		System.out.println(password);
		Boolean isExist = UserHelper.isLogin(username, password);
		if (isExist == false) 
		{
			return "redirect:login.html";
		}else 
		{	
			//zzl_查找登录用户信息
			List<UserPersistence> list = UserHelper.getUserInfo(username);
			
			session.setAttribute("nameOrEmail", username);
			session.setAttribute("UserName", list.get(0).getUSERNAME());
			request.getSession().setAttribute("user", list.get(0));
			
			return "redirect:index.html" ;
		}	
	}
	
	
	
	
	
	
	/**
	 * @author zzl
	 * @abstract:用户管理_userPage.ftl
	 * @data:2017年11月9日21:13:13
	 */
	@RequestMapping(value="userPage",method=RequestMethod.GET)
	public ModelAndView userPage()
	{
		ModelAndView mv = new ModelAndView("userPage");
		
		//获取所有待审核员工信息
		List<UserView> pendingAuditUsers = UserService.getAllPendingAuditUsers();
		
		//获取所有普通用户信息
		List<UserView> ordinaryUsers = UserService.getAllOrdinaryUsers();
		
		//获取所有运维人员信息
		List<UserView> ITUsers = UserService.getAllITUsers();
		
		//获取所有管理员信息
		List<UserView> adminUsers = UserService.getAllAdminUsers();		
			
		//获取所有无效用户信息
		List<UserView> inValidtUsers = UserService.getAllInValidtUsers();
		
		
		mv.addObject("pendingAuditUsers",pendingAuditUsers);
		mv.addObject("pendingAuditCounts", pendingAuditUsers.size());
		
		mv.addObject("ordinaryUsers",ordinaryUsers);
		mv.addObject("pordinaryUserCounts", ordinaryUsers.size());
		
		mv.addObject("ITUsers",ITUsers);
		mv.addObject("ITUserCounts", ITUsers.size());
		
		mv.addObject("adminUsers",adminUsers);
		mv.addObject("adminUserCounts", adminUsers.size());
		
		mv.addObject("inValidtUsers",inValidtUsers);
		mv.addObject("inValidtUserCounts", inValidtUsers.size());
		return mv;
		
	}
	
	
	/**
	 * 用户通过审核
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/throughAudit",method=RequestMethod.POST)
	public String throughAudit(HttpServletRequest request,HttpSession session){
	
		String userId = request.getParameter("userId");
	
		//更新user表用户状态 -- "2"为管理员审核通过状态
		UserService.updateUserState(userId,2);
		
		return "1";
	}
	
	
	/**
	 * 用户未通过审核
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/noAudit",method=RequestMethod.POST)
	public String noAudit(HttpServletRequest request,HttpSession session){
	
		String userId = request.getParameter("userId");
	
		//更新user表用户状态 -- "2"为管理员审核通过状态
		UserService.updateUserState(userId,0);
		
		return "1";
	}
	
	
	/**
	 * 查看用户详情
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="showUserInfo",method=RequestMethod.GET)
	public ModelAndView showUserInfo(String u){
		ModelAndView mv = new ModelAndView("showUserInfo");
		
		//获取指定用户信息
		UserView userInfo = UserService.getUserInfoById(u);
		
		mv.addObject("userInfo", userInfo);
		return mv;
	}
	
	
	
	/**
	 * 批量审核用户通过
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectUserPass",method=RequestMethod.GET)
	public String selectUserPass(HttpServletRequest request){
		System.out.println("进入批量审核"); 
		
		String id=request.getParameter("checkedIds");    //获取前台隐藏域存着的选中的复选框的value
        String checkedIds[]=id.split(","); //进行分割存到数组

        
        for(int i =0;i<checkedIds.length;i++){
            if(!checkedIds[i].equals("")){
              //  System.out.println(checkedIds[i]);
              UserService.updateUserState(checkedIds[i],2);
            }
        }
		
	
		return "1";
	}
	
	/**
	 * 批量审核用户不通过
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectUserUnPass",method=RequestMethod.GET)
	public String selectUserUnPass(HttpServletRequest request){
		System.out.println("进入批量审核"); 
		
		String id=request.getParameter("checkedIds");    //获取前台隐藏域存着的选中的复选框的value
        String checkedIds[]=id.split(","); //进行分割存到数组

        
        for(int i =0;i<checkedIds.length;i++){
            if(!checkedIds[i].equals("")){
              //  System.out.println(checkedIds[i]);
              UserService.updateUserState(checkedIds[i],0);
            }
        }
		
	
		return "1";
	}
	
	
	
	//更换员工角色
	@ResponseBody
	@RequestMapping(value="/changeRole",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String changeRole(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String userId = request.getParameter("userId");
		
		//获取该员工本身角色外的其他角色
		List<RolePersistence> list = RoleHelper.getUnGotRoleList(userId);
		
		
		System.out.println("长度"+list.size());
		
		JSONObject jsonObject = new JSONObject();
		
		if (list.size() != 0) {			
			jsonObject.put("value", "1");
			jsonObject.put("list", list);
			jsonObject.put("userId", userId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {						
			jsonObject.put("value", "2");
			jsonObject.put("userId", userId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	
	//更新员工角色
	@ResponseBody
	@RequestMapping(value="/updateUserRole",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String updateUserRole(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String userId = request.getParameter("userId");
		String roleId = request.getParameter("roleId");
		

		JSONObject jsonObject = new JSONObject();
		
		if (userId!=null && roleId!=null) {
			//更新员工角色
			UserHelper.updateUserRole(userId,roleId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zpz_show User information
	 */
	@RequestMapping(value="userindex",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("userindex");
		List<UserPersistence> userlist = UserService.getAllUserList();
		mv.addObject("allUserList",userlist);
		return mv;
		
	}
	
	
	
	
	/*
	 * ZPZ_deleteUser
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteUser"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public void deleteUser(HttpServletRequest request){
		String userEmail = request.getParameter("userEmail");
		UserHelper.deleteUser(userEmail);
		System.out.println(userEmail);
	}


	/*
	 * zpz_addUserInfo
	 */
	@RequestMapping(value="addUserInformation",method=RequestMethod.GET)
	public ModelAndView addUserInformation(){
//		List<UserPersistence> userPersistences = UserHelper.getEmail_id();
		ModelAndView modelAndView = new ModelAndView("addUserInformation");
//		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}

	/*
	 * zpz_editUserInfo
	 */
	@RequestMapping(value="editUserInformation",method=RequestMethod.GET)
	public ModelAndView editUserInformation(String u){
		List<UserPersistence> userPersistences = UserHelper.getUserInfoById(u);
		ModelAndView modelAndView = new ModelAndView("editUserInformation");
		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}
	
	/*
	 * zpz_editUserInfo2
	 */
	@RequestMapping(value="editUserInformation2",method=RequestMethod.POST)
	public ModelAndView editUserInformation2(HttpServletRequest request){
		System.out.println("asdasdasda");
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		UserHelper.updateUser(userid, username);
		ModelAndView modelAndView = new ModelAndView("userindex");
		List<UserPersistence> userlist = UserService.getAllUserList();
		modelAndView.addObject("allUserList",userlist);
		return modelAndView;
	}
	//psw转md5
	public static String StringToMd5(String psw) {  
        {  
            try {  
                MessageDigest md5 = MessageDigest.getInstance("MD5");  
                md5.update(psw.getBytes("UTF-8"));  
                byte[] encryption = md5.digest();  
  
                StringBuffer strBuf = new StringBuffer();  
                for (int i = 0; i < encryption.length; i++) {  
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {  
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));  
                    } else {  
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));  
                    }  
                }  
  
                return strBuf.toString();  
            } catch (NoSuchAlgorithmException e) {  
                return "";  
            } catch (UnsupportedEncodingException e) {  
                return "";  
            }  
        }  
    } 
	
}
