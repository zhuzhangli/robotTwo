package org.xjtusicd3.portal.view;

/* 当前设备和客户机字段不匹配，各自都有一些独有的字段，所以不使用继承关系 */
public class CurrentDeviceClientView {
	//数据库中的内容
		private String str_id;              // 生成的UUID
		private String str_MacAddress;		// MAC地址
		private String str_DeviceModel;		// 设备型号
		private String str_BuyTime;			// 购买时间
		private String str_CPU;				// CPU型号
		private String str_RAM;				// 内存大小
		private String str_HardDisk;		// 硬盘大小
		private String str_IP;				// ip地址
		private String str_DeviceImg;    	 // 设备照片
		private String str_state;			// 设备状态
		
		
		//子类属性
		private String str_GraphicCard;     // 显卡型号
		private String str_AudioCard;		// 声卡型号
		private String str_NetworkCard;		// 网卡型号
		private String str_MotherBoard;		// 主板型号
		private String str_OSName;			// 操作系统
		private String str_OSID;		    // 操作系统ID
		
	    // 多出来的属性
		private String str_HardDriver;      // 硬件驱动
		private String str_OSType;          // 不管
		private String str_OSVersion;       // 不管
		private String str_FirstStarTime;   // 不管   第一次启动时间
		private String str_UserID;          // 用户ID
		private int int_IsNotice;           // 是否通知
	 	private String str_TimeRemarks;     // 不管     时间戳
		public String getStr_id() {
			return str_id;
		}
		public void setStr_id(String str_id) {
			this.str_id = str_id;
		}
		public String getStr_MacAddress() {
			return str_MacAddress;
		}
		public void setStr_MacAddress(String str_MacAddress) {
			this.str_MacAddress = str_MacAddress;
		}
		public String getStr_DeviceModel() {
			return str_DeviceModel;
		}
		public void setStr_DeviceModel(String str_DeviceModel) {
			this.str_DeviceModel = str_DeviceModel;
		}
		public String getStr_CPU() {
			return str_CPU;
		}
		public void setStr_CPU(String str_CPU) {
			this.str_CPU = str_CPU;
		}
		public String getStr_RAM() {
			return str_RAM;
		}
		public void setStr_RAM(String str_RAM) {
			this.str_RAM = str_RAM;
		}
		public String getStr_HardDisk() {
			return str_HardDisk;
		}
		public void setStr_HardDisk(String str_HardDisk) {
			this.str_HardDisk = str_HardDisk;
		}
		public String getStr_IP() {
			return str_IP;
		}
		public void setStr_IP(String str_IP) {
			this.str_IP = str_IP;
		}
		public String getStr_GraphicCard() {
			return str_GraphicCard;
		}
		public void setStr_GraphicCard(String str_GraphicCard) {
			this.str_GraphicCard = str_GraphicCard;
		}
		public String getStr_AudioCard() {
			return str_AudioCard;
		}
		public void setStr_AudioCard(String str_AudioCard) {
			this.str_AudioCard = str_AudioCard;
		}
		public String getStr_NetworkCard() {
			return str_NetworkCard;
		}
		public void setStr_NetworkCard(String str_NetworkCard) {
			this.str_NetworkCard = str_NetworkCard;
		}
		public String getStr_MotherBoard() {
			return str_MotherBoard;
		}
		public void setStr_MotherBoard(String str_MotherBoard) {
			this.str_MotherBoard = str_MotherBoard;
		}
		public String getStr_OSName() {
			return str_OSName;
		}
		public void setStr_OSName(String str_OSName) {
			this.str_OSName = str_OSName;
		}
		public String getStr_OSID() {
			return str_OSID;
		}
		public void setStr_OSID(String str_OSID) {
			this.str_OSID = str_OSID;
		}
		public String getStr_HardDriver() {
			return str_HardDriver;
		}
		public void setStr_HardDriver(String str_HardDriver) {
			this.str_HardDriver = str_HardDriver;
		}
		public String getStr_OSType() {
			return str_OSType;
		}
		public void setStr_OSType(String str_OSType) {
			this.str_OSType = str_OSType;
		}
		public String getStr_OSVersion() {
			return str_OSVersion;
		}
		public void setStr_OSVersion(String str_OSVersion) {
			this.str_OSVersion = str_OSVersion;
		}
		public String getStr_FirstStarTime() {
			return str_FirstStarTime;
		}
		public void setStr_FirstStarTime(String str_FirstStarTime) {
			this.str_FirstStarTime = str_FirstStarTime;
		}
		public String getStr_UserID() {
			return str_UserID;
		}
		public void setStr_UserID(String str_UserID) {
			this.str_UserID = str_UserID;
		}
		public int getInt_IsNotice() {
			return int_IsNotice;
		}
		public void setInt_IsNotice(int int_IsNotice) {
			this.int_IsNotice = int_IsNotice;
		}
		public String getStr_TimeRemarks() {
			return str_TimeRemarks;
		}
		public void setStr_TimeRemarks(String str_TimeRemarks) {
			this.str_TimeRemarks = str_TimeRemarks;
		}
		public String getStr_BuyTime() {
			return str_BuyTime;
		}
		public void setStr_BuyTime(String str_BuyTime) {
			this.str_BuyTime = str_BuyTime;
		}
		public String getStr_DeviceImg() {
			return str_DeviceImg;
		}
		public void setStr_DeviceImg(String str_DeviceImg) {
			this.str_DeviceImg = str_DeviceImg;
		}
		public String getStr_state() {
			return str_state;
		}
		public void setStr_state(String str_state) {
			this.str_state = str_state;
		}
	 	
	 	
	 	
}
