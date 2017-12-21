package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RolePermissionPersistenceMapper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;

public class RolePermissionHelper
{
	//判断用户权限表中是否已存在此权限
	public static List<RolePermissionPersistence> isExist(String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<RolePermissionPersistence> list = mapper.isExist(roleId,permissionId);
		session.close();
		return list;
	}
	
	
	//为角色增加权限
	public static void addPermissionToRole(String rolePermissionId, String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		mapper.addPermissionToRole(rolePermissionId,roleId,permissionId);
		session.close();		
	}
	
	
	//移除角色已获取的权限
	public static void deletePermissionToRole(String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		mapper.deletePermissionToRole(roleId,permissionId);
		session.close();		
	}
	
	
	//超级管理员拥有所有权限
	public static List<PermissionPersistence> getAllPermission() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.getAllPermission();
		session.close();
		return list;
	}

	
	
	
	
	
	
	
	/*
	 * zpz_get role permission By UserId
	 */
	public static List<PermissionPersistence> getRolePermission(String uId)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> rolePermission = mapper.getRolePermissionByUId(uId);
		session.close();
		return rolePermission;
	}
	
	/*
	 * zpz_get role permission By UserId
	 */
	public static List<PermissionPersistence> getRolePermissionByRId(String rId)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> rolePermission = mapper.getRolePermissionByRId(rId);
		session.close();
		return rolePermission;
	}
	
	/*
	 * zpz_get all role Id
	 */
	public static List<String> getAllRoleId()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<String> roleId = mapper.getAllRoleId();
		session.close();
		return roleId;
	}

	

	














	
}
