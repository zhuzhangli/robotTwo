package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	//判断用户名是否已存在
	@Select("SELECT COUNT(USERID) FROM TBL_User WHERE USERNAME = #{0}")
	public Boolean getUserInfoByName(String name);
	
	//zyq_login_ajax_注册	
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USERNAME,TBL_User.USERPASSWORD,TBL_User.USERSTATE,TBL_User.CREATETIME,TBL_User.AVATAR) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register(String userid,String name,String password,int userstate,String createTime,String userimage);
	
	//判断用户是否登录
	@Select("SELECT COUNT(USERID) FROM TBL_User WHERE USERNAME=#{0} AND USERPASSWORD=#{1} AND USERSTATE=2")
	public Boolean isLogin(String username, String password);
	
	//获取登录用户信息 
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	public List<UserPersistence> getUserListByName(String username);
	
	//修改用户信息	
	@Update("UPDATE TBL_User SET GENDER=#{1},USERBIRTHDAY=#{2},USERADDRESS=#{3},USERSIGNATURE=#{4} WHERE USERID=#{0}")
	public void updateUserInfo2(String userid, String usersex, String userbirthday, String address,String userbrief );
	
	//修改密码	
	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USERNAME=#{0}")
	public void updateUserPassword(String userid, String password);
	
	//通过username找userID
	@Select("SELECT USERID FROM TBL_User WHERE USERNAME=#{0}")
	public String getUserIdByName(String username);
	
	//zzl_获取指定用户信息_2017年11月10日11:46:39
	@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	public List<UserPersistence> getUserInfoById(String userId);
	
	//通过用户Id获取用户名
	@Select("SELECT USERNAME FROM TBL_User WHERE USERID=#{0}")
	public String getUserNameById(String userId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	List<UserPersistence> getEmail_id(String userid);*/
	
	//zpz_通过id获取用户名
		/*@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
		public List<UserPersistence> getUserInfoById(String UserId);*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * author:zzl
	 * abstract:判断用户名是否被注册
	 * data:2017年10月30日12:43:06
	 */
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	public List<UserPersistence> getName(String name);
	
	
	
	
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	List<UserPersistence> getEmail_name(String username);
	
	
	//zzl_后台管理员登录_2017年10月31日09:17:13
	@Select("SELECT * FROM TBL_User WHERE USEREMAIL=#{0} AND USERPASSWORD=#{1} AND ROLEID = '168ab2db-ea09-44d9-bf50-af9879cb43cb'")
	List<UserPersistence> getEmail2(String param1,String param2);
	@Select("SELECT * FROM TBL_User WHERE USEREMAIL=#{0} AND VERIFICATIONCODE=#{1}")
	List<UserPersistence> getEmail3(String param1,String param2);
	//zyq_验证码通过
	@Update("UPDATE TBL_User SET TBL_User.USERSTATE='1' WHERE USEREMAIL=#{0}")
	public void updateUserState(String useremail);
	//zyq_验证码没有通过 删除
	@Delete("DELETE FROM TBL_User WHERE TBL_User.USEREMAIL=#{0}")
	public void deleteUser(String useremail);
	 
	//zpz_获取用户部分信息
	@Select("SELECT USERNAME,USERPASSWORD,USEREMAIL FROM TBL_User")
	List<UserPersistence> getUser();
	//zpz_获取用户所有信息
	@Select("SELECT * FROM TBL_User")
	List<UserPersistence> getAllUserInfo();

	
	//zyq_上传图片
	@Update("UPDATE TBL_User SET TBL_User.AVATAR=#{1} WHERE USERNAME=#{0}")
	public void updateUserImage(String username, String path);
	//zyq_用户个人信息完善
	@Update("UPDATE TBL_User SET USERNAME=#{1},GENDER=#{2},USERBIRTHDAY=#{3},USERADDRESS=#{4},USERSIGNATURE=#{5} WHERE USEREMAIL=#{0}")
	public void updateUserInfo(String email, String username, String usersex, String userbirthday, String address,String userbrief);
	//zyq_用户个人密码修改
	/*@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USEREMAIL=#{0}")
	public void updateUserPassword(String email, String password);*/
	
	/*
	 * zyq_message_ajax_获得用户基本信息*/
	 
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	public List<UserPersistence> getUserInfo(String username);
	
	
	//zpz edit user information
//	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USEREMAIL=#{0}")
//	public List<UserPersistence> updateUserInfo(String username,String email,String address,String Createtime);
	@Update("UPDATE TBL_User SET USERNAME=#{1} WHERE USERID=#{0}")
	public void updateUser(String userid,String username);
	
	
	/**
	 * author:zhaoyanqing
	 * abstract:注册的用户信息同时添加到普通用户表
	 * data:2017年9月19日 19:49:45
	 */
	public List<UserPersistence> addGeneralUser(String userid);
	
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USERPASSWORD,TBL_User.USERNAME,TBL_User.USERSTATE,TBL_User.AVATAR,TBL_User.ROLEID) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register2(String userid, String password, String username, int userstate, String userimage,
			String roleid);
	
	
	/**
	 * author:zzl
	 * abstract:获取登录用户id
	 * data:2017年9月21日10:18:57
	 */
	@Select("SELECT USERID FROM TBL_User WHERE USERNAME=#{0} OR USEREMAIL=#{0}")
	public String loginUserInfo(String nameOrEmail);
	
	
	
	
	
	
	
	
	

	@Select("SELECT ROLENAME FROM TBL_User,TBL_Role WHERE TBL_User.ROLEID = TBL_Role.ROLEID AND USERID=#{0}")
	public String getRoleNameByUserId(String userId);

	//zzl_获取所有待审核用户_2017年11月9日21:29:09
	@Select("SELECT * FROM TBL_User WHERE USERSTATE = #{0} ORDER BY CREATETIME DESC")
	public List<UserPersistence> getAllUsers(int userState);


	//zzl_获取所有普通用户信息 -- 即USERSTATE = 2 且 角色名是普通用户_2017年11月9日22:34:22
	@Select("SELECT * FROM TBL_User ,TBL_Role WHERE TBL_User.ROLEID = TBL_Role.ROLEID AND USERSTATE = #{0} AND TBL_Role.ROLENAME=#{1} ORDER BY TBL_User.CREATETIME DESC ")
	public List<UserPersistence> getUserLists(int userState, String roleName);

	//zzl_更改用户状态_2017年11月10日10:33:30
	@Update("UPDATE TBL_User SET USERSTATE = #{1} WHERE USERID=#{0}")
	public void updateUserState2(String userId, int userState);

	

	
	//更新员工角色
	@Update("UPDATE TBL_User SET ROLEID = #{1} WHERE USERID=#{0}")
	public void updateUserRole(String userId, String roleId);
	

	
	
	

}
