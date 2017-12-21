package org.xjtusicd3.portal.view;

public class PermissionView
{
	private String permissionId;  //权限ID
	private String permissionPhysicalName; //物理名
	private String permissionLogicName; //逻辑名
	private String time; //上传时间
	
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionPhysicalName() {
		return permissionPhysicalName;
	}
	public void setPermissionPhysicalName(String permissionPhysicalName) {
		this.permissionPhysicalName = permissionPhysicalName;
	}
	public String getPermissionLogicName() {
		return permissionLogicName;
	}
	public void setPermissionLogicName(String permissionLogicName) {
		this.permissionLogicName = permissionLogicName;
	}
	
	
	
}
