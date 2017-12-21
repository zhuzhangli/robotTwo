package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.filter.ValidateEmail;
import org.xjtusicd3.portal.view.UserView;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author zzl
 *
 */
public class UserService {
	
	/**
	 * @author zzl
	 * @abstract:获取所有待审核用户信息
	 * @data:2017年11月9日21:23:58
	 */
	public static List<UserView> getAllPendingAuditUsers() {
		//待审核视图
		List<UserView> pendingAuditUsers = new ArrayList<UserView>();
		
		//获取所有待审核用户 -- 即USERSTATE = 1
		List<UserPersistence> userLists = UserHelper.getAllUsers(1);
		
		for(UserPersistence userList:userLists){
			UserView pendingAuditUser = new UserView();
			pendingAuditUser.setUSERID(userList.getUSERID());
			pendingAuditUser.setUSERNAME(userList.getUSERNAME());
			pendingAuditUser.setUSEREMAIL(userList.getUSEREMAIL());
			pendingAuditUser.setGENDER(userList.getGENDER());
			pendingAuditUser.setUSERBIRTHDAY(userList.getUSERBIRTHDAY());
			pendingAuditUser.setUSERADDRESS(userList.getUSERADDRESS());
		System.out.println("yonghdizhi:"+userList.getUSERADDRESS());
			pendingAuditUser.setUSERSIGNATURE(userList.getUSERSIGNATURE());
			pendingAuditUser.setCREATETIME(userList.getCREATETIME());
			
			pendingAuditUser.setROLEID(userList.getROLEID());
			String  roleName = UserHelper.getRoleNameByUserId(userList.getUSERID());
			pendingAuditUser.setROLENAME(roleName);
			
			pendingAuditUser.setAVATAR(userList.getAVATAR());
			pendingAuditUsers.add(pendingAuditUser);
		}
				
		return pendingAuditUsers;
	}
	
	
	/**
	 * @author zzl
	 * @abstract:获取所有普通用户信息
	 * @data:2017年11月9日22:07:17
	 */
	public static List<UserView> getAllOrdinaryUsers() {
		//普通用户视图
		List<UserView> ordinaryUsers = new ArrayList<UserView>();
		
		//获取所有普通用户信息 -- 即USERSTATE = 2 且 角色名是普通用户
		List<UserPersistence> userLists = UserHelper.getUserLists(2,"普通用户");
		
		for(UserPersistence userList:userLists){
			UserView ordinaryUser = new UserView();
			ordinaryUser.setUSERID(userList.getUSERID());
			ordinaryUser.setUSERNAME(userList.getUSERNAME());
			ordinaryUser.setUSEREMAIL(userList.getUSEREMAIL());
			ordinaryUser.setGENDER(userList.getGENDER());
			ordinaryUser.setUSERBIRTHDAY(userList.getUSERBIRTHDAY());
			ordinaryUser.setUSERADDRESS(userList.getUSERADDRESS());
			ordinaryUser.setUSERSIGNATURE(userList.getUSERSIGNATURE());
			ordinaryUser.setCREATETIME(userList.getCREATETIME());
			
			ordinaryUser.setROLEID(userList.getROLEID());
			String  roleName = UserHelper.getRoleNameByUserId(userList.getUSERID());
			ordinaryUser.setROLENAME(roleName);
			
			ordinaryUser.setAVATAR(userList.getAVATAR());
			ordinaryUsers.add(ordinaryUser);
		}
				
		return ordinaryUsers;
	}
	
	
	/**
	 * @author zzl
	 * @abstract:获取所有运维人员信息
	 * @data:2017年11月9日22:47:36
	 */
	public static List<UserView> getAllITUsers() {
		//运维人员视图
		List<UserView> ITUsers = new ArrayList<UserView>();
		
		//获取所有运维人员信息 -- 即USERSTATE = 2 且 角色名是运维人员
		List<UserPersistence> userLists = UserHelper.getUserLists(2,"运维人员");
		
		for(UserPersistence userList:userLists){
			UserView ITUser = new UserView();
			ITUser.setUSERID(userList.getUSERID());
			ITUser.setUSERNAME(userList.getUSERNAME());
			ITUser.setUSEREMAIL(userList.getUSEREMAIL());
			ITUser.setGENDER(userList.getGENDER());
			ITUser.setUSERBIRTHDAY(userList.getUSERBIRTHDAY());
			ITUser.setUSERADDRESS(userList.getUSERADDRESS());
			ITUser.setUSERSIGNATURE(userList.getUSERSIGNATURE());
			ITUser.setCREATETIME(userList.getCREATETIME());
			
			ITUser.setROLEID(userList.getROLEID());
			String  roleName = UserHelper.getRoleNameByUserId(userList.getUSERID());
			ITUser.setROLENAME(roleName);
			
			ITUser.setAVATAR(userList.getAVATAR());
			ITUsers.add(ITUser);
		}
				
		return ITUsers;
	}
	
	
	/**
	 * @author zzl
	 * @abstract:获取所有管理员信息
	 * @data:2017年11月9日22:54:03
	 */
	public static List<UserView> getAllAdminUsers() {
		//管理员视图
		List<UserView> adminUsers = new ArrayList<UserView>();
		
		//获取所有管理员信息 -- 即USERSTATE = 2 且 角色名是管理员2
		List<UserPersistence> userLists = UserHelper.getUserLists(2,"管理员");
		
		for(UserPersistence userList:userLists){
			UserView adminUser = new UserView();
			adminUser.setUSERID(userList.getUSERID());
			adminUser.setUSERNAME(userList.getUSERNAME());
			adminUser.setUSEREMAIL(userList.getUSEREMAIL());
			adminUser.setGENDER(userList.getGENDER());
			adminUser.setUSERBIRTHDAY(userList.getUSERBIRTHDAY());
			adminUser.setUSERADDRESS(userList.getUSERADDRESS());
			adminUser.setUSERSIGNATURE(userList.getUSERSIGNATURE());
			adminUser.setCREATETIME(userList.getCREATETIME());
			
			adminUser.setROLEID(userList.getROLEID());
			String  roleName = UserHelper.getRoleNameByUserId(userList.getUSERID());
			adminUser.setROLENAME(roleName);
			
			adminUser.setAVATAR(userList.getAVATAR());
			adminUsers.add(adminUser);
		}
				
		return adminUsers;
	}
	
	
	/**
	 * @author zzl
	 * @abstract:获取所有无效用户信息
	 * @data:2017年11月9日22:57:57
	 */
	public static List<UserView> getAllInValidtUsers() {
		//无效用户视图
		List<UserView> inValidtUsers = new ArrayList<UserView>();
		
		//获取所有无效用户信息 -- 即USERSTATE = 0
		List<UserPersistence> userLists = UserHelper.getAllUsers(0);
		
		for(UserPersistence userList:userLists){
			UserView inValidtUser = new UserView();
			inValidtUser.setUSERID(userList.getUSERID());
			inValidtUser.setUSERNAME(userList.getUSERNAME());
			inValidtUser.setUSEREMAIL(userList.getUSEREMAIL());
			inValidtUser.setGENDER(userList.getGENDER());
			inValidtUser.setUSERBIRTHDAY(userList.getUSERBIRTHDAY());
			inValidtUser.setUSERADDRESS(userList.getUSERADDRESS());
			inValidtUser.setUSERSIGNATURE(userList.getUSERSIGNATURE());
			inValidtUser.setCREATETIME(userList.getCREATETIME());
			
			inValidtUser.setROLEID(userList.getROLEID());
			String  roleName = UserHelper.getRoleNameByUserId(userList.getUSERID());
			inValidtUser.setROLENAME(roleName);
			
			inValidtUser.setAVATAR(userList.getAVATAR());
			inValidtUsers.add(inValidtUser);
		}
				
		return inValidtUsers;
	}
	
	/**
	 * @author zzl
	 * @param userState 
	 * @abstract:用户通过审核/加入黑名单
	 * @data:2017年11月10日10:22:34
	 */
	public static void updateUserState(String userId, int userState) {

		UserHelper.updateUserState2(userId,userState);
		
	}
	
	
	/**
	 * @author zzl
	 * @param userState 
	 * @abstract:获取指定用户信息
	 * @data:2017年11月10日11:48:56
	 */
	public static UserView getUserInfoById(String userId) {
		UserView userView = new UserView();
		
		//获取用户信息
		List<UserPersistence> userList = UserHelper.getUserInfoById(userId);
		
		userView.setUSERID(userList.get(0).getUSERID());
		userView.setUSERNAME(userList.get(0).getUSERNAME());
		userView.setUSEREMAIL(userList.get(0).getUSEREMAIL());
		userView.setGENDER(userList.get(0).getGENDER());
		userView.setUSERBIRTHDAY(userList.get(0).getUSERBIRTHDAY());
		userView.setUSERADDRESS(userList.get(0).getUSERADDRESS());
		userView.setUSERSIGNATURE(userList.get(0).getUSERSIGNATURE());
		userView.setCREATETIME(userList.get(0).getCREATETIME());
		
		userView.setROLEID(userList.get(0).getROLEID());
		String  roleName = UserHelper.getRoleNameByUserId(userList.get(0).getUSERID());
		userView.setROLENAME(roleName);
		
		userView.setAVATAR(userList.get(0).getAVATAR());
			
		return userView;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<UserPersistence> getAllUserList() 
	{
		List<UserPersistence> userlist = UserHelper.getAllUserInfo();
		return userlist;
		
	}
	
	public static void login_register(String email, String password)
	{
		UUID uuid = UUID.randomUUID();
		String identification_number =genCodes(8, 1).get(0);
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String time_stamp = format.format(date);
        String time = format2.format(date);
		String username = "会员"+ time + genCodes(6, 1).get(0); 
		//发送邮件验证信息
		ValidateEmail validateEmail = new ValidateEmail();
		try {
			validateEmail.validateEmail(email,username,identification_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//UserHelper.login_register(uuid.toString(),email,password,username,0,identification_number,time_stamp,"");
		
	}
	public static boolean validateUserState(String email)
	{
		// TODO Auto-generated method stub
		return false;
	}
	public static boolean validateEmail(String email)
	{
		// TODO Auto-generated method stub
		return false;
	}
	//随机产生一个length位的字母+数字
    public static List<String> genCodes(int length,long num){
        List<String> results=new ArrayList<String>();
        for(int j=0;j<num;j++){
            String val = "";     
            Random random = new Random();     
            for(int i = 0; i < length; i++)     
            {     
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
                if("char".equalsIgnoreCase(charOrNum)) // 字符串     
                {     
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
                    val += (char) (choice + random.nextInt(26));     
                }     
                else if("num".equalsIgnoreCase(charOrNum)) // 数字     
                {     
                    val += String.valueOf(random.nextInt(10));     
                }     
            }
            val=val.toLowerCase();
            if(results.contains(val)){
                continue;
            }else{
                results.add(val);
            }
        }
        return results;
        }


	

	


	


	


	

















	

	
}
