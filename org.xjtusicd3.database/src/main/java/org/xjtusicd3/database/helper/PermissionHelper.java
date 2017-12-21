package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PermissionPersistenceMapper;
import org.xjtusicd3.database.mapper.RolePermissionPersistenceMapper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;


public class PermissionHelper
{
	//获取所有权限
	public static List<PermissionPersistence> getAllPermission() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.getAllPermission();
		session.close();
		return list;
	}


	//增加权限
	public static void addPermission(String permissionId, String physicalName, String logicName, String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.addPermission(permissionId, physicalName, logicName, time);
		session.close();	
	}

	
	//更改权限
	public static void updatePermission(String permissionId,  String physicalName,String logicName, String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.updatePermission(permissionId, physicalName, logicName, time);
		session.close();	
	}


	//删除权限
	public static void deletePermission(String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.deletePermission(permissionId);
		session.close();		
	}


	//获取角色还未得到的权限
	public static List<PermissionPersistence> notObtainRolePermission(String roleId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.notObtainRolePermission(roleId);
		session.close();
		return list;
		
	}


	//获取角色已得到的权限
	public static List<PermissionPersistence> obtainRolePermission(String roleId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.obtainRolePermission(roleId);
		session.close();
		return list;
	}


	
	
	
	


	//根据权限ID获取相对应的权限信息
//	public static PermissionPersistence getPermissionById(String permissionId) {
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
//		PermissionPersistence list = mapper.getPermissionById(permissionId);
//		session.close();
//		return list;
//	}
	
	
	
	
	
	
	
	
/*	
	 * get permission
	 
	public static  PermissionPersistence getPermission(String PermissionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		 PermissionPersistence list = mapper.getPermission(PermissionId);
		session.close();
		return list;
	}*/

	
}
