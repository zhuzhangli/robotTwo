package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.RoleHelper;
import org.xjtusicd3.database.model.RolePersistence;
import org.xjtusicd3.portal.view.Permission_RoleView;

public class RoleService {

	//获取所有角色
	public static List<Permission_RoleView> getAllRoles() {
		List<Permission_RoleView> roleViews = new ArrayList<Permission_RoleView>();
		
		List<RolePersistence> rolePersistences = RoleHelper.getAllRoles();
		
		for(RolePersistence rolePersistence:rolePersistences){
			Permission_RoleView roleView = new Permission_RoleView();
			
			roleView.setROLEID(rolePersistence.getRoleId());
			roleView.setROLENAME(rolePersistence.getRoleName());
			
			roleViews.add(roleView);
		}
		return roleViews;
		
	}

}
