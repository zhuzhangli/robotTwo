package org.xjtusicd3.portal.interceptor;


import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xjtusicd3.database.helper.RoleHelper;
import org.xjtusicd3.database.helper.RolePermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.LogService;

/**
 * 
 * http://blog.csdn.net/tonytfjing/article/details/39207551
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	//private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object Handler) throws ServletException, IOException{
	
		UserPersistence user = (UserPersistence)req.getSession().getAttribute("user");
		
		//如果用户为空
		if(user == null){
			//log.info("interceptor...跳转到login页面...");
			req.getRequestDispatcher("login.html").forward(req, resp);
			return false;
		} else {
			//判断角色下royals该用户的权限：获取到request的路径和get，post
			//System.out.println("asda:   "+req.getMethod() + " : "+ req.getServletPath());
		
			String path = req.getServletPath();
			
			System.out.println("当前路径是："+path);
			
			//查询当前用户角色
			List<RolePersistence> list = RoleHelper.getRoleInfoByUserId(user.getUSERID());
			
			System.out.println("当前用户角色是："+list.get(0).getRoleName());
			
		/*	if (list.get(0).getRoleName().equals("超级管理员")) {
				//超级管理员拥有所有权限
				List<PermissionPersistence> rolePermissions = RolePermissionHelper.getAllPermission();
				System.out.println("超级管理员");
				for(PermissionPersistence rp : rolePermissions){
					 
					if (rp.getPERMISSIONPHYSICALNAME().equals(path)){
						System.out.println(rp.getPERMISSIONPHYSICALNAME());
						return true;
					}
				}			
				return false;
			}else {*/
				List<PermissionPersistence> rolePermissions = RolePermissionHelper.getRolePermission(user.getUSERID());
				//System.out.println(rolePermissions.size());
				
				for(PermissionPersistence rp : rolePermissions){
					 
					if (rp.getPERMISSIONPHYSICALNAME().equals(path)){
						System.out.println(rp.getPERMISSIONPHYSICALNAME());
						return true;
					}
				}			
				return false;
			//}
			
			
			
		}
	}
	
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, 
			ModelAndView modelAndView){
		//log.info("***********执行2：postHandle*************");
		if(modelAndView != null){
			modelAndView.addObject("var", "测试postHandle");
		}
	} 
	
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp){
		//log.info("************执行3：afterCompletion**************");
	}
	public static final String GenerateGUID(){
		  UUID uuid = UUID.randomUUID();
		  return uuid.toString();  
		 }
}
