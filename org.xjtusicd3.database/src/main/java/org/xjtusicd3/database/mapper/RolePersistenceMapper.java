package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RolePersistence;

public interface RolePersistenceMapper	extends IBaseDao<RolePersistence, String>
{
	//获取所有角色信息
	@Select("SELECT * FROM TBL_Role ORDER BY ROLEID DESC")
	List<RolePersistence> getAllRoles();
	
	
	//获取该员工本身角色外的其他角色
	@Select("SELECT * FROM TBL_Role WHERE ROLEID NOT IN (SELECT ROLEID FROM tbl_user WHERE USERID =#{0} AND USERSTATE=2);")
	List<RolePersistence> getUnGotRoleList(String userId);
	
	
	//查询当前用户角色
	@Select("SELECT * FROM tbl_user,TBL_Role WHERE tbl_user.ROLEID =  TBL_Role.ROLEID AND USERID = #{0}")
	List<RolePersistence> getRoleInfoByUserId(String userId);
	
	
	
	
	
	
	
	//zpz_获取所有角色
		@Select("SELECT * FROM TBL_Role")
		List<RolePersistence> getAllRoleInfo();
		
		//zpz_get role name by RoleId
		@Select("SELECT ROLENAME FROM TBL_Role WHERE ROLEID = #{0}")
		String getRoleNameByRoleId(String Rid);

		

		
}
