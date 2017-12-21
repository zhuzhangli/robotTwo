package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.ChangeService;
import org.xjtusicd3.portal.view.EquipmentComputerView;

@Controller
public class ChangeController {
	//ITIL管理流程_变更管理
	@RequestMapping(value="changePage",method=RequestMethod.GET)
    public ModelAndView  changePage(){
 	   ModelAndView mv=new ModelAndView("changePage");
 	   
 	   //获取当前用户配置信息
 	   List<Map<String, Object>> ClientDeviceList = ChangeService.findCurrentCfgInfo();
 	   
 	  //######  加入硬件和软件比较信息     ###### 
       GetDeviceInfo(ClientDeviceList);
 	   
       mv.addObject("ClientDeviceList", ClientDeviceList);
 	   
 	   return mv;
    }

	private void GetDeviceInfo(List<Map<String, Object>> TableList_hardware) {
        // 设备是否被修改标志
        Integer Changeflag;
        
        // 用于比较的临时字段
        String tep_cmp_register;
        String tep_cmp_current;
        
        // 遍历列表  得到所有设备
		for(int i=0;i<TableList_hardware.size();i++)
		{
			// 初始化当前设备的改变标志   0-未改变   1-改变了   2-未找到
			Changeflag = 0;
			
			// 设备表中的信息
			Map<String, Object> map = TableList_hardware.get(i);				
			String macAddress =(String) map.get("MACADDRESS"); 
			
			// 根据MAC地址查找当前设备资源表  返回  CurrentClientDevice 客户机对象
			EquipmentComputerView temp_CurrentDeviceClient = ChangeService.FindEquipmentFromBasicByMac(macAddress);
			
			// 分为 找到 和 没找到 
			if(temp_CurrentDeviceClient != null)
			{
				System.out.println("当前设备已找到对应设备");
				System.out.println(temp_CurrentDeviceClient.getMACADDRESS());
				System.out.println("结束");
				
				
				/************************   硬件是否改变功能      ******************************/
				// 对返回对象的属性值逐一进行比较  并将副本属性加入到原先的map对象中
				// 要对比的属性有七个  只要一个返回 true（即属性发生了改变）则列表“硬件是否改变” 标志就要显示 “是”
				Boolean tem_falg1 = AddCurrentDeviceAttrToMap(map,"CPU",temp_CurrentDeviceClient);
			    Boolean tem_falg2 =	AddCurrentDeviceAttrToMap(map,"RAM",temp_CurrentDeviceClient);
				Boolean tem_falg3 = AddCurrentDeviceAttrToMap(map,"HARDDRIVER",temp_CurrentDeviceClient);
				Boolean tem_falg4 = AddCurrentDeviceAttrToMap(map,"NETWORKCARD",temp_CurrentDeviceClient);
				Boolean tem_falg5 = AddCurrentDeviceAttrToMap(map,"MOTHERBOARD",temp_CurrentDeviceClient);
				Boolean tem_falg6 = AddCurrentDeviceAttrToMap(map,"GRAPHICCARD",temp_CurrentDeviceClient);
				Boolean tem_falg7 = AddCurrentDeviceAttrToMap(map,"OSNAME",temp_CurrentDeviceClient);
				// 加入硬件是否改变标志    这里只是一个设备的硬件改变标志
				if(tem_falg1||tem_falg2||tem_falg3||tem_falg4||tem_falg5||tem_falg6||tem_falg7)
				{
					 
					map.put("Hardware_Changeflag", 1);
				}
				else
				{
					map.put("Hardware_Changeflag", 0);
				}
				
				
				/************************   软件是否改变功能      ******************************/
		/*		System.out.println("进入软件信息配置阶段");
				Integer i_DeviceSoftConfigChangeFlag = 0;
				
				//######  返回设备对应的软件列表     ######
		        List<Map<String, Object>> TableList_software = DevCfgService.FindDeviceSoftwareConfigsByDeviceID(temp_DeviceID);
		       
		        if(TableList_software.size()==0)
		        {
			        map.put("AllSoftware_Changeflag", 2);
		        }
		        else
		        {
			        //######  改造返回列表并返回    整个软件是否改变   的标志     ######
			        i_DeviceSoftConfigChangeFlag = DevCfgService.GetDeviceSoftwareConfigsInfo(TableList_software,temp_DeviceID);

			        if(i_DeviceSoftConfigChangeFlag == 1)
			        {
			        	// 当前设备表中有设备  但当前配置表中有该设备的配置   但配置不一致
			        	map.put("AllSoftware_Changeflag", 1);
			        }
			        else
			        {
			        	// 当前设备表中有设备  但当前配置表中有该设备的配置   而且配置一致
			        	map.put("AllSoftware_Changeflag", 0);
			        }
		        } */       
			}
			else
			{
				System.out.println("当前设备表中没有对应设备   ");
				map.put("Hardware_Changeflag", 2);
				map.put("AllSoftware_Changeflag", 3);
			}
	
/**************************************************   测试结果输出代码      输出当前软件配置    ****************************************************/				
//			// 在 map 对象中加入是否改变的标志位
//			Iterator entries = map.entrySet().iterator();  
//			System.out.println("##############################   输出客户机展示页面传入的map 第"+ i+ "条设备#####################################");
//			System.out.println("**********  这是一条设备信息     *********");
//			while (entries.hasNext()) 
//			{  	
//			    Map.Entry entry = (Map.Entry) entries.next();   
//			    String key = (String)entry.getKey();  
//			    Object value = entry.getValue();  			  
//			    System.out.println("Key = " + key + ", Value = " + value);   			  
//			}  
/**************************************************   测试结果输出代码      结束    *********************************************************/	
		}
		
	}

	
	
	/*****************      工具函数             ********************/
	/* 添加当前设备中要和注册设备比较的属性值至传至前台的 map 
	 * 	Map<String, Object> map,      要传至前台的 map 
	 * 	String attr,                  传入 map 的属性值名称
	 *                 attr + "_cur"  当前设备表中的对应的 attr 属性
	 *                 attr + "_cmp"  与传入设备对应的当前设备表中的对应的 attr 属性的比较结果  1不同（改变了）   0 相同（两边一样）
	 * 	CurrentDeviceClient cur_dev   与传入设备对应的当前设备客户机对象
	 * 
	 * 返回值   传入设备和当前设备attr属性的比较结果     1不同（改变了）   0 相同（两边一样）
	 * */
	private Boolean AddCurrentDeviceAttrToMap(Map<String, Object> map, String attr,EquipmentComputerView cur_dev) {
		// 处理器  
				String tep_cmp_register = (String) map.get(attr);
				String tep_cmp_current = "";
				Boolean Changeflag;
				
				// 每个case代表要比较的参数   分别去调用当前设备的相应参数
				switch(attr)
				{	
				// 处理器  
				case "CPU":
					tep_cmp_current = cur_dev.getCPU();
					break;
				// 内存
				case "RAM":
					tep_cmp_current = cur_dev.getRAM();
					break;
				// 硬盘
				case "HARDDRIVER": 
					tep_cmp_current = cur_dev.getSTORAGE();
					break;
				// 网卡
				case "NETWORKCARD":
					tep_cmp_current = cur_dev.getNETWORKCARD();
					break;
				// 主板
				case "MOTHERBOARD":
					tep_cmp_current = cur_dev.getMOTHERBOARD();
					break;
				// 显卡 
				case "GRAPHICCARD":
					tep_cmp_current = cur_dev.getGRAPHICCARD();
					break;
				// 操作系统
				case "OSNAME":
					tep_cmp_current = cur_dev.getOSNAME();
					break;
				default:
					System.out.println("出错了！对象没匹配上");
				}
				

				// 将副本属性加入到原先的map对象中  
				/* 原属性名 + 后缀_Curr 代表当前设备对应的属性*/
				map.put(attr + "_cur", tep_cmp_current);
				if(!tep_cmp_register.equals(tep_cmp_current))
				{// 如果不相等说明设备改变了
					Changeflag = true;
					// 将属性比较结果加入到map对象中
					map.put(attr +"_cmp", 1);
				}
				else
				{
					Changeflag = false;
					map.put(attr +"_cmp", 0);
				}
				return Changeflag;
	}
}
