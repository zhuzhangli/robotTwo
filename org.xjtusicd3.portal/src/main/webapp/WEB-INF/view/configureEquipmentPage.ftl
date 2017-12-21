<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>设备资源库</title> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <!-- Data Tables --> 
  <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
  <!-- BEGIN GLOBAL MANDATORY STYLES --> 
  <link href="media/css/style.css" rel="stylesheet" type="text/css" /> 
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeIn"> 
   <div class="row" style="height: 100%"> 
    <div class="col-sm-6" style="width: 100%; height: 100%"> 
     <div class="tabs-container"> 
      <div class="tab-content"> 
       <div id="tab-1" class="tab-pane active"> 
        <meta charset="utf-8" /> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
        <!--360浏览器优先以webkit内核解析--> 
        <div class="wrapper wrapper-content animated fadeIn"> 
         <div class="row"> 
          <div class="col-sm-6" style="width: 100%"> 
           <div class="tabs-container"> 
            <ul class="nav nav-tabs"> 
             <li class="active"><a data-toggle="tab" href="#tab-31" aria-expanded="true">用户计算机【${computerSize }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-32" aria-expanded="false">服务器【${serverSize }】</a></li> 
            </ul> 
            <div class="tab-content"> 
             
             <!-- 用户计算机信息 --> 
             <div id="tab-31" class="tab-pane active"> 
              <div class="ibox-title"> 
               <h5>用户计算机信息</h5> 
               <div class="ibox-tools"> 
	               <!-- 模态框弹出权限 -->
	               <button class="btn btn-primary " data-toggle="modal" data-target="#myModalAdd">添加设备</button> 
               </div> 
              </div> 
              <div class="ibox-content"> 
	            
		       
		       <table class="table table-striped table-bordered table-hover dataTables-example"> 
		        <thead> 
                 <tr> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">MAC地址</th> 
                  <th style="text-align: center;">设备型号</th>
                  <th style="text-align: center;">CPU</th> 
                  <th style="text-align: center;">RAM</th>
                  <th style="text-align: center;">存储容量</th>
                  <th style="text-align: center;">IP地址</th>
                  <th style="text-align: center;">购买时间</th>                   
                  <th style="text-align: center;">更多详情</th> 
                  <th style="text-align: center;">加入标准库</th> 
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list computerList as a>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${a_index+1 }</td>
                  <td style="width: 12%">${a.MACADDRESS }</td> 
                  <td style="width: 23%">${a.EQUIPMENTMODEL }</td>
                  <td style="text-align: center;width: 22%">${a.CPU }</td> 
                  <td style="text-align: center;width: 5%">${a.RAM }</td>
                  <td style="text-align: center;width: 6%">${a.STORAGE }</td>
                  <td style="text-align: center;width: 8%">${a.IP }</td>
                  <td style="text-align: center;width: 8%">${a.BUYTIME }</td> 
                  <td style="text-align: center;width: 5%" >
                  	<button class="btn btn-white btn-sm" type="button" id="computer_${a.EQUIPMENTID }" title="更多详情" onclick="lookMoreComputerInfo(this.id)" data-toggle="modal" data-target="#myModalComputer"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 8%">
                    <button class="btn btn-white btn-sm" type="button" id="computerEdit_${a.EQUIPMENTID }" onclick="editComputerInfo(this.id)" title="编辑" data-toggle="modal" data-target="#myModalEditComputer" ><i class="glyphicon glyphicon-pencil"></i></button> 
                    <button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="computerDelete_${a.EQUIPMENTID }" onclick="deleteComputer(this.id)"></button>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
		       </table>  
              </div> 
             </div> 
             
             
             <!--服务器信息 --> 
             <div id="tab-32" class="tab-pane"> 
               <div class="ibox-title"> 
               <h5>服务器信息</h5> 
               <div class="ibox-tools"> 
	               <!-- 模态框弹出权限 -->
	               <button class="btn btn-primary " data-toggle="modal" data-target="#myModalServerAdd">添加设备</button> 
               </div> 
              </div> 
              <div class="ibox-content"> 
	            
		       
		       <table class="table table-striped table-bordered table-hover dataTables-example"> 
		        <thead> 
                 <tr> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">MAC地址</th> 
                  <th style="text-align: center;">设备型号</th>
                  <th style="text-align: center;">CPU</th> 
                  <th style="text-align: center;">RAM</th>
                  <th style="text-align: center;">存储容量</th>
                  <th style="text-align: center;">IP地址</th>
                  <th style="text-align: center;">购买时间</th>                   
                  <th style="text-align: center;">更多详情</th> 
                  <th style="text-align: center;">加入标准库</th> 
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list serverList as server>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${server_index+1 }</td>
                  <td style="width: 12%">${server.MACADDRESS }</td> 
                  <td style="width: 23%">${server.EQUIPMENTMODEL }</td>
                  <td style="text-align: center;width: 22%">${server.CPU }</td> 
                  <td style="text-align: center;width: 5%">${server.RAM }</td>
                  <td style="text-align: center;width: 6%">${server.STORAGE }</td>
                  <td style="text-align: center;width: 8%">${server.IP }</td>
                  <td style="text-align: center;width: 8%">${server.BUYTIME }</td> 
                  <td style="text-align: center;width: 5%" >
                  	<button class="btn btn-white btn-sm" type="button" id="server_${server.EQUIPMENTID }" title="更多详情" onclick="lookMoreServerInfo(this.id)" data-toggle="modal" data-target="#myModalServer"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 8%">
                    <button class="btn btn-white btn-sm" type="button" id="serverEdit_${server.EQUIPMENTID }" onclick="editServerInfo(this.id)" title="编辑" data-toggle="modal" data-target="#myModalEditServer" ><i class="glyphicon glyphicon-pencil"></i></button> 
                    <button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="serverDelete_${server.EQUIPMENTID }" onclick="deleteServer(this.id)"></button>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
		       </table>  
              </div>
             </div> 
             
             <!-- 新增用户计算机_模态框（Modal） --> 
		      <div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 增加用户计算机 </h4> 
		         </div> 
		         
		         <form class="form-horizontal m-t" method="post" action="add" > 
		          <div class="modal-body"> 
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">MAC地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="addMacAddress"  type="text" class="form-control" required="" aria-required="true" placeholder="格式如下:64:31:50:37:C5:13" /> 
		            </div> 
		           </div> 
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">设备型号：</label> 
		            <div class="col-sm-8"> 
		             <input id="addEquipmentModel"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div> 
		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">CPU：</label> 
		            <div class="col-sm-8"> 
		             <input id="addCPU" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">RAM：</label> 
		            <div class="col-sm-8"> 
		             <input id="addRAM" type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">存储容量：</label> 
		            <div class="col-sm-8"> 
		             <input id="addStorage"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">IP地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="addIP"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">购买时间：</label> 
		            <div class="col-sm-8"> 
		             <input id="addBuytime"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">显卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="addGraphicCard" type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">声卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="addAudioCard"  type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="addNetworkCard"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">主板：</label> 
		            <div class="col-sm-8"> 
		             <input id="addMotherboard"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统版本：</label> 
		            <div class="col-sm-8"> 
		             <input id="addOSName" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统ID：</label> 
		            <div class="col-sm-8"> 
		             <input id="addOSID"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           		          		          		         		        
		          </div> 
		          <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="addUserEquipment()"> 提交 </button> 
		          </div> 
		         </form> 
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal --> 
             
             
             
             <!-- 查看计算机更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalComputer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多计算机信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                 <div class="modal-body">
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">显卡：</label> 
                   <div class="col-sm-8"> 
                    <textarea id="lookComputerGraphicCard" name="logicName"  class="form-control"   aria-required="true" readonly="readonly"></textarea> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">声卡：</label> 
                   <div class="col-sm-8"> 
                    <input id="lookComputerAudioCard"  type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                 
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">网卡：</label> 
                   <div class="col-sm-8"> 
                    <input id="lookComputerNetworkCard"  type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">主板：</label> 
                   <div class="col-sm-8"> 
                    <input id="lookComputerMotherBoard"  type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">系统版本：</label> 
                   <div class="col-sm-8"> 
                    <input id="lookComputerOSName"  type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                 
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">系统ID：</label> 
                   <div class="col-sm-8"> 
                    <input id="lookComputerOSID"  type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div>                 
                 </div> 

				 <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		          </div>
                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
             
             
              <!-- 编辑计算机信息_模态框（Modal） --> 
		      <div class="modal fade" id="myModalEditComputer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 编辑用户计算机 </h4> 
		         </div> 
		         
		         <form class="form-horizontal m-t" method="post" action="add" > 
		          <div class="modal-body"> 
		           <div class="form-group" style="display: none;"> 
		            <div class="col-sm-8"> 
		             <input id="editEquiomentId" type="text" class="form-control" required="" aria-required="true"></input>
		            </div> 
		           </div>
		           		          	           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">MAC地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="editMacAddress"  type="text" class="form-control" required="" aria-required="true" placeholder="格式如下:64:31:50:37:C5:13" /> 
		            </div> 
		           </div> 
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">设备型号：</label> 
		            <div class="col-sm-8"> 
		             <input id="editEquipmentModel"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div> 
		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">CPU：</label> 
		            <div class="col-sm-8"> 
		             <input id="editCPU" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">RAM：</label> 
		            <div class="col-sm-8"> 
		             <input id="editRAM" type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">存储容量：</label> 
		            <div class="col-sm-8"> 
		             <input id="editStorage"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">IP地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="editIP"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">购买时间：</label> 
		            <div class="col-sm-8"> 
		             <input id="editBuytime"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">显卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="editGraphicCard" type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">声卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="editAudioCard"  type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="editNetworkCard"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">主板：</label> 
		            <div class="col-sm-8"> 
		             <input id="editMotherboard"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统版本：</label> 
		            <div class="col-sm-8"> 
		             <input id="editOSName" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统ID：</label> 
		            <div class="col-sm-8"> 
		             <input id="editOSID"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           		          		          		         		        
		          </div> 
		          <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="updateComputer()"> 提交 </button> 
		          </div> 
		         </form> 
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal -->
             
             
           
             <!-- 新增服务器_模态框（Modal） --> 
		      <div class="modal fade" id="myModalServerAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 增加服务器 </h4> 
		         </div> 
		         
		         <form class="form-horizontal m-t" method="post" action="add" > 
		          <div class="modal-body" style="width: 598px;height: 635px;overflow-y:scroll; "> 
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">MAC地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_MacAddress"  type="text" class="form-control" required="" aria-required="true" placeholder="格式如下:64:31:50:37:C5:13" /> 
		            </div> 
		           </div> 
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">设备型号：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_EquipmentModel"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div> 
		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">CPU：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_CPU" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">RAM：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_RAM" type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">存储容量：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_Storage"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">IP地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_IP"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">购买时间：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_Buytime"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统版本：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_OSVersion" type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">计算机名：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_computerName"  type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">PCI：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_PCI"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">USB：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_USB"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">环境变量：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_path" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">内存、交换区使用量：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_RAM_EXCHANGEAREAUSE"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           	
		           	<div class="form-group"> 
		            <label class="col-sm-3 control-label">分区使用情况：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_PARTATIONUSE"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">空闲内存：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_IDLERAM"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统运行时间、用户数：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_OS_TIME_USERNUM_LOAD" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统负载：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_OSLOAD"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">防火墙设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_FIREWALL"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">路由表设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_ROUTINGTABLE"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">已建立连接：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_HASCONTACT" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网络统计量：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_NETWORK"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">所有进程：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_PROCESS"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">实时进程数量：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_REALTIMEPROCESS"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">活跃用户：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_ACTIVEUSER" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">主板序列号：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_BIOS"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>	       
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="add_NETWORKCARD"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           		           	          		          		         		        
		          </div> 
		          <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="addServerEquipment()"> 提交 </button> 
		          </div> 
		         </form> 
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal --> 
             
             
             
             <!-- 查看服务器更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalServer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多服务器信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                 <div class="modal-body" style="width: 598px;height: 635px;overflow-y:scroll; ">
                  <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统版本：</label> 
		            <div class="col-sm-8"> 
		             <input id="OSVersion" type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">计算机名：</label> 
		            <div class="col-sm-8"> 
		             <input id="computerName"  type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">PCI：</label> 
		            <div class="col-sm-8"> 
		             <input id="PCI"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">USB：</label> 
		            <div class="col-sm-8"> 
		             <input id="USB"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">环境变量：</label> 
		            <div class="col-sm-8"> 
		             <input id="path" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">内存、交换区使用量：</label> 
		            <div class="col-sm-8"> 
		             <input id="RAM_EXCHANGEAREAUSE"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           	
		           	<div class="form-group"> 
		            <label class="col-sm-3 control-label">分区使用情况：</label> 
		            <div class="col-sm-8"> 
		             <input id="PARTATIONUSE"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">空闲内存：</label> 
		            <div class="col-sm-8"> 
		             <input id="IDLERAM"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统运行时间、用户数：</label> 
		            <div class="col-sm-8"> 
		             <input id="OS_TIME_USERNUM_LOAD" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统负载：</label> 
		            <div class="col-sm-8"> 
		             <input id="OSLOAD"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">防火墙设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="FIREWALL"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">路由表设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="ROUTINGTABLE"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">已建立连接：</label> 
		            <div class="col-sm-8"> 
		             <input id="HASCONTACT" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网络统计量：</label> 
		            <div class="col-sm-8"> 
		             <input id="NETWORK"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">所有进程：</label> 
		            <div class="col-sm-8"> 
		             <input id="PROCESS"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">实时进程数量：</label> 
		            <div class="col-sm-8"> 
		             <input id="REALTIMEPROCESS"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">活跃用户：</label> 
		            <div class="col-sm-8"> 
		             <input id="ACTIVEUSER" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">主板序列号：</label> 
		            <div class="col-sm-8"> 
		             <input id="BIOS"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>	       
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="NETWORKCARD"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
                 </div> 

				 <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		          </div>
                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
             
             
             <!-- 编辑服务器_模态框（Modal） --> 
		      <div class="modal fade" id="myModalEditServer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 增加服务器 </h4> 
		         </div> 
		         
		         <form class="form-horizontal m-t" method="post" action="add" > 
		          <div class="modal-body" style="width: 598px;height: 635px;overflow-y:scroll; "> 
		          <div class="modal-body"> 
		           <div class="form-group" style="display: none;"> 
		            <div class="col-sm-8"> 
		             <input id="editServerId" type="text" class="form-control" required="" aria-required="true"></input>
		            </div> 
		           </div>
		          
		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">MAC地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="MacAddress1"  type="text" class="form-control" required="" aria-required="true" placeholder="格式如下:64:31:50:37:C5:13" /> 
		            </div> 
		           </div> 
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">设备型号：</label> 
		            <div class="col-sm-8"> 
		             <input id="EquipmentModel1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div> 
		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">CPU：</label> 
		            <div class="col-sm-8"> 
		             <input id="CPU1" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">RAM：</label> 
		            <div class="col-sm-8"> 
		             <input id="RAM1" type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">存储容量：</label> 
		            <div class="col-sm-8"> 
		             <input id="Storage1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">IP地址：</label> 
		            <div class="col-sm-8"> 
		             <input id="IP1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">购买时间：</label> 
		            <div class="col-sm-8"> 
		             <input id="Buytime1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统版本：</label> 
		            <div class="col-sm-8"> 
		             <input id="OSVersion1" type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">计算机名：</label> 
		            <div class="col-sm-8"> 
		             <input id="computerName1"  type="text" class="form-control" required="" aria-required="true"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">PCI：</label> 
		            <div class="col-sm-8"> 
		             <input id="PCI1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">USB：</label> 
		            <div class="col-sm-8"> 
		             <input id="USB1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">环境变量：</label> 
		            <div class="col-sm-8"> 
		             <input id="path1" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">内存、交换区使用量：</label> 
		            <div class="col-sm-8"> 
		             <input id="RAM_EXCHANGEAREAUSE1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           	
		           	<div class="form-group"> 
		            <label class="col-sm-3 control-label">分区使用情况：</label> 
		            <div class="col-sm-8"> 
		             <input id="PARTATIONUSE1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">空闲内存：</label> 
		            <div class="col-sm-8"> 
		             <input id="IDLERAM1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统运行时间、用户数：</label> 
		            <div class="col-sm-8"> 
		             <input id="OS_TIME_USERNUM_LOAD1" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">系统负载：</label> 
		            <div class="col-sm-8"> 
		             <input id="OSLOAD1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">防火墙设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="FIREWALL1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">路由表设置：</label> 
		            <div class="col-sm-8"> 
		             <input id="ROUTINGTABLE1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">已建立连接：</label> 
		            <div class="col-sm-8"> 
		             <input id="HASCONTACT1" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网络统计量：</label> 
		            <div class="col-sm-8"> 
		             <input id="NETWORK1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">所有进程：</label> 
		            <div class="col-sm-8"> 
		             <input id="PROCESS1"  type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">实时进程数量：</label> 
		            <div class="col-sm-8"> 
		             <input id="REALTIMEPROCESS1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">活跃用户：</label> 
		            <div class="col-sm-8"> 
		             <input id="ACTIVEUSER1" type="text" class="form-control" required="" aria-required="true"  /> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">主板序列号：</label> 
		            <div class="col-sm-8"> 
		             <input id="BIOS1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>	       
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label">网卡：</label> 
		            <div class="col-sm-8"> 
		             <input id="NETWORKCARD1"  type="text" class="form-control" required="" aria-required="true" /> 
		            </div> 
		           </div>
		           		           	          		          		         		        
		          </div> 
		          <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="updateServer()"> 提交 </button> 
		          </div> 
		         </form> 
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal --> 
             
             
             
             
             
            </div> 
           </div> 
          </div> 
         </div> 
        </div> 
       </div> 
      </div> 
     </div> 
    </div> 
    <!-- 全局js --> 
    <script src="js/jquery.min.js?v=2.1.4"></script> 
    <script src="js/bootstrap.min.js?v=3.3.6"></script> 
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script> 
    <!-- Data Tables --> 
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script> 
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script> 
    <!-- 自定义js --> 
    <script src="js/content.js?v=1.0.0"></script> 
    <!-- Page-Level Scripts --> 
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable();

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable('../example_ajax.php', {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1]);
                },
                "submitdata": function (value, settings) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition(this)[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            });


        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }
    </script> 
    
    <script>
    /* 查看软件更多信息 */
    function lookMoreSoftInfo(id) {
    	//获取权限ID
    	var configureId = document.getElementById(id).id;
    	//alert(permissionId);
    	
    	$.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/lookMoreSoftInfo.html",
            data: {
                "configureId":configureId
            },
            dataType: "json",
            success: function(data) {
            	
            	var moreSoftInfo = data.moreSoftInfo; //获取后台json'
            	
            	var obj = document.getElementById("introduction");        
                obj.innerText= moreSoftInfo.iNTRODUCTION;
            	
            	$("#versionType").val(moreSoftInfo.vERSIONTYPE);
            	$("#version").val(moreSoftInfo.vERSION);
            	$("#softType").val(moreSoftInfo.sOFTTYPE);
            	$("#website").val(moreSoftInfo.wEBSITE);
            }           
        }) 	   	    	
     }
    
        
	/* 将软件添加/删除至标准库 */      
    function SoftBasicCfg(id) {  
		//获取配置软件ID
    	var configureId = document.getElementById(id).id;  
    	
        $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/SoftBasicCfg.html",
            data: {
                "configureId":configureId
            },
            dataType: "json",
            success: function(data) {
            	if(data.value=="1"){
            		document.getElementById(data.configureId).setAttribute("class","btn btn-white btn-sm fa fa-trash-o");
            	}else if(data.value=="2"){
            		document.getElementById(data.configureId).setAttribute("class","btn btn-white btn-sm fa fa-plus");
            	}
            }
           
        }) 
    } 	
    </script>
    
   
   <script>
   /* 查看驱动更多信息 */
   function lookMoreDriverInfo(id) {
   	//获取配置ID
   	var configureId = document.getElementById(id).id;
   	//alert(permissionId);
   	
   	$.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/lookMoreDriverInfo.html",
           data: {
               "configureId":configureId
           },
           dataType: "json",
           success: function(data) {
           	
           	var moreDriverInfo = data.moreDriverInfo; //获取后台json'
           	
           	var obj = document.getElementById("driverIntroduction");        
            obj.innerText= moreDriverInfo.fITNESS;

           }           
       }) 	   	    	
    }
   
   
   /* 将驱动添加至标准库 OR 从标准库删除 */      
   function driverBasicCfg(id) {  
	//获取配置驱动ID
   	var configureId = document.getElementById(id).id;  
   	
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/driverBasicCfg.html",
           data: {
               "configureId":configureId
           },
           dataType: "json",
           success: function(data) {
           	if(data.value=="1"){
           		document.getElementById("driver_"+data.configureId).setAttribute("class","btn btn-white btn-sm fa fa-trash-o");
           	}else if(data.value=="2"){
           		document.getElementById("driver_"+data.configureId).setAttribute("class","btn btn-white btn-sm fa fa-plus");
           	}
           }
          
       }) 
   } 	
   </script> 
    
   <script>
   
   /* 增加用户设备 */      
   function addUserEquipment() {  
       //获取模态框数据  
       var macAddress = $('#addMacAddress').val();  
       var equipmentModel = $('#addEquipmentModel').val();
       var CPU = $('#addCPU').val();
       var RAM = $('#addRAM').val();
       var storage = $('#addStorage').val();
       var IP = $('#addIP').val();
       var buytime = $('#addBuytime').val();
       var graphicCard = $('#addGraphicCard').val();
       var audioCard = $('#addAudioCard').val();
       var networkCard = $('#addNetworkCard').val();
       var motherboard = $('#addMotherboard').val();
       var OSName = $('#addOSName').val();
       var OSID = $('#addOSID').val();
       
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/addUserEquipment.html",
           data: {
               "macAddress":macAddress,
               "equipmentModel":equipmentModel,
               "CPU":CPU,
               "RAM":RAM,
               "storage":storage,
               "IP":IP,
               "buytime":buytime,
               "graphicCard":graphicCard,
               "audioCard":audioCard,
               "networkCard":networkCard,
               "motherboard":motherboard,
               "OSName":OSName,
               "OSID":OSID
           },
           dataType: "json",
           success: function(data) {
           if(data == "1"){
        	   alert("添加成功");
               window.location.reload();
           }else if(data == "0")
	           alert("macAddress重复添加");
	           window.location.reload();
           }
          
       }) 
   }   
   
   /* 查看更多计算机信息 */
    function lookMoreComputerInfo(id) {
    	//获取配置ID
    	var equipmentId = document.getElementById(id).id;
    	
    	$.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/lookMoreComputerInfo.html",
            data: {
                "equipmentId":equipmentId
            },
            dataType: "json",
            success: function(data) {
            	
            	var moreComputerInfo = data.moreComputerInfo; //获取后台json'
            	
            	/* var obj = document.getElementById("introduction");        
                obj.innerText= moreSoftInfo.iNTRODUCTION; */
            	
            	$("#lookComputerGraphicCard").val(moreComputerInfo.gRAPHICCARD);
            	$("#lookComputerAudioCard").val(moreComputerInfo.aUDIOCARD);
            	$("#lookComputerNetworkCard").val(moreComputerInfo.nETWORKCARD);
            	$("#lookComputerMotherBoard").val(moreComputerInfo.mOTHERBOARD);
            	$("#lookComputerOSName").val(moreComputerInfo.oSNAME);
            	$("#lookComputerOSID").val(moreComputerInfo.oSID);
            }           
        }) 	   	    	
     }
   
   
    /* 获取要编辑的计算机信息 */
    function editComputerInfo(id) {
    	//获取权限ID
    	var equipmentId = document.getElementById(id).id;
    	//alert(permissionId);
    	
    	$.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/editComputerInfo.html",
            data: {
                "equipmentId":equipmentId
            },
            dataType: "json",
            success: function(data) {
            	
            	var editComputerInfo = data.editComputerInfo; //获取后台json'
            	
            	/* var obj = document.getElementById("introduction");        
                obj.innerText= moreSoftInfo.iNTRODUCTION; */
                $("#editEquiomentId").val(editComputerInfo.eQUIPMENTID);
            	$("#editMacAddress").val(editComputerInfo.mACADDRESS);
            	$("#editEquipmentModel").val(editComputerInfo.eQUIPMENTMODEL);
            	$("#editCPU").val(editComputerInfo.cPU);
            	$("#editRAM").val(editComputerInfo.rAM);
            	$("#editStorage").val(editComputerInfo.sTORAGE);
            	$("#editIP").val(editComputerInfo.iP);
            	$("#editBuytime").val(editComputerInfo.bUYTIME);
                
            	$("#editGraphicCard").val(editComputerInfo.gRAPHICCARD);
            	$("#editAudioCard").val(editComputerInfo.aUDIOCARD);
            	$("#editNetworkCard").val(editComputerInfo.nETWORKCARD);
            	$("#editMotherboard").val(editComputerInfo.mOTHERBOARD);
            	$("#editOSName").val(editComputerInfo.oSNAME);
            	$("#editOSID").val(editComputerInfo.oSID);
            }           
        })
     }
    
    
    /* 提交计算机更改  */ 
    function updateComputer() {  
    	//获取模态框数据  
    	var equipmentId = $('#editEquiomentId').val(); 
        var macAddress = $('#editMacAddress').val();  
        var equipmentModel = $('#editEquipmentModel').val();
        var CPU = $('#editCPU').val();
        var RAM = $('#editRAM').val();
        var storage = $('#editStorage').val();
        var IP = $('#editIP').val();
        var buytime = $('#editBuytime').val();
        var graphicCard = $('#editGraphicCard').val();
        var audioCard = $('#editAudioCard').val();
        var networkCard = $('#editNetworkCard').val();
        var motherboard = $('#editMotherboard').val();
        var OSName = $('#editOSName').val();
        var OSID = $('#editOSID').val();

        $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/updateComputer.html",
            data: {
            	"equipmentId":equipmentId,
            	"macAddress":macAddress,
                "equipmentModel":equipmentModel,
                "CPU":CPU,
                "RAM":RAM,
                "storage":storage,
                "IP":IP,
                "buytime":buytime,
                "graphicCard":graphicCard,
                "audioCard":audioCard,
                "networkCard":networkCard,
                "motherboard":motherboard,
                "OSName":OSName,
                "OSID":OSID
            },
            dataType: "json",
            success: function(data) {
            	
            	alert("更改成功");
            	window.location.reload();
            }
           
        }) 	        
    }  
    
    /* 删除配置 */
	function deleteComputer(id){  
        //获取模态框数据  
        var equipmentId = document.getElementById(id).id; 	       

        $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/deleteComputer.html",
            data: {
                "equipmentId":equipmentId
            },
            dataType: "json",
            success: function(data) {
            	alert("删除成功");
            	window.location.reload();
            }
           
        }) 	        
    }  
   </script> 
   
   
   <script>
   
   /* 增加服务器设备 */      
   function addServerEquipment() {  
       //获取模态框数据  
       var macAddress = $('#add_MacAddress').val();  
       var equipmentModel = $('#add_EquipmentModel').val();
       var CPU = $('#add_CPU').val();
       var RAM = $('#add_RAM').val();
       var storage = $('#add_Storage').val();
       var IP = $('#add_IP').val();
       var buytime = $('#add_Buytime').val();
     
       var osVersion = $('#add_OSVersion').val();
       var computerName = $('#add_computerName').val();
       var PCI = $('#add_PCI').val();
       var USB = $('#add_USB').val();
       var path = $('#add_path').val();
       var RAM_EXCHANGEAREAUSE = $('#add_RAM_EXCHANGEAREAUSE').val();
       
       var PARTATIONUSE = $('#add_PARTATIONUSE').val();  
       var IDLERAM = $('#add_IDLERAM').val();
       var OS_TIME_USERNUM_LOAD = $('#add_OS_TIME_USERNUM_LOAD').val();
       var OSLOAD = $('#add_OSLOAD').val();
       var FIREWALL = $('#add_FIREWALL').val();
       var ROUTINGTABLE = $('#add_ROUTINGTABLE').val();
       var HASCONTACT = $('#add_HASCONTACT').val();
       var NETWORK = $('#add_NETWORK').val();
       var PROCESS = $('#add_PROCESS').val();
       var REALTIMEPROCESS = $('#add_REALTIMEPROCESS').val();
       var ACTIVEUSER = $('#add_ACTIVEUSER').val();
       var BIOS = $('#add_BIOS').val();
       var NETWORKCARD = $('#add_NETWORKCARD').val();
       
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/addServerEquipment.html",
           data: {
               "macAddress":macAddress,
               "equipmentModel":equipmentModel,
               "CPU":CPU,
               "RAM":RAM,
               "storage":storage,
               "IP":IP,
               "buytime":buytime,
               "osVersion":osVersion,
               "computerName":computerName,
               "PCI":PCI,
               "USB":USB,
               "path":path,
               "RAM_EXCHANGEAREAUSE":RAM_EXCHANGEAREAUSE,
               "PARTATIONUSE":PARTATIONUSE,
               "IDLERAM":IDLERAM,
               "OS_TIME_USERNUM_LOAD":OS_TIME_USERNUM_LOAD,
               "OSLOAD":OSLOAD,
               "FIREWALL":FIREWALL,
               "ROUTINGTABLE":ROUTINGTABLE,
               "HASCONTACT":HASCONTACT,
               
               "NETWORK":NETWORK,
               "PROCESS":PROCESS,
               "REALTIMEPROCESS":REALTIMEPROCESS,
               "ACTIVEUSER":ACTIVEUSER,
               "BIOS":BIOS,
               "NETWORKCARD":NETWORKCARD
           },
           dataType: "json",
           success: function(data) {
           	alert("添加成功");
           	window.location.reload();
           }
          
       }) 
   }   
   
   
   /* 查看更多服务器信息 */
   function lookMoreServerInfo(id) {
   	//获取配置ID
   	var equipmentId = document.getElementById(id).id;
   
   	$.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/lookMoreServerInfo.html",
           data: {
               "equipmentId":equipmentId
           },
           dataType: "json",
           success: function(data) {
           	
           	var moreServerInfo = data.moreServerInfo; //获取后台json'
           	
           	/* var obj = document.getElementById("introduction");        
               obj.innerText= moreSoftInfo.iNTRODUCTION; */
           	
           	$("#OSVersion").val(moreServerInfo.oSVERSION);
           	$("#computerName").val(moreServerInfo.cOMPUTERNAME);
           	$("#PCI").val(moreServerInfo.pCI);
           	$("#USB").val(moreServerInfo.uSB);
           	
           	$("#path").val(moreServerInfo.pATH);
           	$("#RAM_EXCHANGEAREAUSE").val(moreServerInfo.rAM_EXCHANGEAREAUSE);
           	$("#PARTATIONUSE").val(moreServerInfo.pARTATIONUSE);
           	$("#IDLERAM").val(moreServerInfo.iDLERAM);
           	$("#OS_TIME_USERNUM_LOAD").val(moreServerInfo.oS_TIME_USERNUM_LOAD);
           	
           	$("#OSLOAD").val(moreServerInfo.oSLOAD);
           	$("#FIREWALL").val(moreServerInfo.fIREWALL);
           	$("#ROUTINGTABLE").val(moreServerInfo.rOUTINGTABLE);
           	$("#HASCONTACT").val(moreServerInfo.hASCONTACT);
           	$("#NETWORK").val(moreServerInfo.nETWORK);
           	
           	$("#PROCESS").val(moreServerInfo.pROCESS);
           	$("#REALTIMEPROCESS").val(moreServerInfo.rEALTIMEPROCESS);
           	$("#ACTIVEUSER").val(moreServerInfo.aCTIVEUSER);
           	$("#BIOS").val(moreServerInfo.bIOS);
           	$("#NETWORKCARD").val(moreServerInfo.nETWORKCARD);
           }           
       }) 	   	    	
    }
   
   
   
   /* 获取要编辑的服务器信息 */
   function editServerInfo(id) {
   	//获取设备ID
   	var equipmentId = document.getElementById(id).id;
   	//alert(permissionId);
   	
   	$.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/editServerInfo.html",
           data: {
               "equipmentId":equipmentId
           },
           dataType: "json",
           success: function(data) {
           	
           	var editServerInfo = data.editServerInfo; //获取后台json'
           	

			$("#editServerId").val(editServerInfo.eQUIPMENTID);
           	$("#MacAddress1").val(editServerInfo.mACADDRESS);
           	$("#EquipmentModel1").val(editServerInfo.eQUIPMENTMODEL);
           	$("#CPU1").val(editServerInfo.cPU);
           	$("#RAM1").val(editServerInfo.rAM);
           	$("#Storage1").val(editServerInfo.sTORAGE);
           	$("#IP1").val(editServerInfo.iP);
           	$("#Buytime1").val(editServerInfo.bUYTIME);           	
           	$("#OSVersion1").val(editServerInfo.oSVERSION);
           	$("#computerName1").val(editServerInfo.cOMPUTERNAME);
           	$("#PCI1").val(editServerInfo.pCI);
           	$("#USB1").val(editServerInfo.uSB);           	
           	$("#path1").val(editServerInfo.pATH);
           	$("#RAM_EXCHANGEAREAUSE1").val(editServerInfo.rAM_EXCHANGEAREAUSE);
           	$("#PARTATIONUSE1").val(editServerInfo.pARTATIONUSE);
           	$("#IDLERAM1").val(editServerInfo.iDLERAM);
           	$("#OS_TIME_USERNUM_LOAD1").val(editServerInfo.oS_TIME_USERNUM_LOAD);          	
           	$("#OSLOAD1").val(editServerInfo.oSLOAD);
           	$("#FIREWALL1").val(editServerInfo.fIREWALL);
           	$("#ROUTINGTABLE1").val(editServerInfo.rOUTINGTABLE);
           	$("#HASCONTACT1").val(editServerInfo.hASCONTACT);
           	$("#NETWORK1").val(editServerInfo.nETWORK);           	
           	$("#PROCESS1").val(editServerInfo.pROCESS);
           	$("#REALTIMEPROCESS1").val(editServerInfo.rEALTIMEPROCESS);
           	$("#ACTIVEUSER1").val(editServerInfo.aCTIVEUSER);
           	$("#BIOS1").val(editServerInfo.bIOS);
           	$("#NETWORKCARD1").val(editServerInfo.nETWORKCARD);
           }           
       })
    }
   
   
   /* 提交服务器更改  */ 
   function updateServer() {  
   	   //获取模态框数据  
   	   var equipmentId = $('#editServerId').val(); 
       var macAddress = $('#MacAddress1').val();  
       var equipmentModel = $('#EquipmentModel1').val();
       var CPU = $('#CPU1').val();
       var RAM = $('#RAM1').val();
       var storage = $('#Storage1').val();
       var IP = $('#IP1').val();
       var buytime = $('#Buytime1').val();
       
       var osVersion = $('#OSVersion1').val();
       var computerName = $('#computerName1').val();
       var PCI = $('#PCI1').val();
       var USB = $('#USB1').val();
       var path = $('#path1').val();
       var RAM_EXCHANGEAREAUSE = $('#RAM_EXCHANGEAREAUSE1').val();      
       var PARTATIONUSE = $('#PARTATIONUSE1').val();
       var IDLERAM = $('#IDLERAM1').val();
       var OS_TIME_USERNUM_LOAD = $('#OS_TIME_USERNUM_LOAD1').val();
       var OSLOAD = $('#OSLOAD1').val();
       var FIREWALL = $('#FIREWALL1').val();
       var ROUTINGTABLE = $('#ROUTINGTABLE1').val();
       var HASCONTACT = $('#HASCONTACT1').val();
       var NETWORK = $('#NETWORK1').val();
       var PROCESS = $('#PROCESS1').val();
       var REALTIMEPROCESS = $('#REALTIMEPROCESS1').val();
       var ACTIVEUSER = $('#ACTIVEUSER1').val();
       var BIOS = $('#BIOS1').val();
       var NETWORKCARD = $('#NETWORKCARD1').val();
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/updateServer.html",
           data: {
        	   "equipmentId":equipmentId,
        	   "macAddress":macAddress,
               "equipmentModel":equipmentModel,
               "CPU":CPU,
               "RAM":RAM,
               "storage":storage,
               "IP":IP,
               "buytime":buytime,
               "osVersion":osVersion,
               "computerName":computerName,
               "PCI":PCI,
               "USB":USB,
               "path":path,
               "RAM_EXCHANGEAREAUSE":RAM_EXCHANGEAREAUSE,
               "PARTATIONUSE":PARTATIONUSE,
               "IDLERAM":IDLERAM,
               "OS_TIME_USERNUM_LOAD":OS_TIME_USERNUM_LOAD,
               "OSLOAD":OSLOAD,
               "FIREWALL":FIREWALL,
               "ROUTINGTABLE":ROUTINGTABLE,
               "HASCONTACT":HASCONTACT,
               
               "NETWORK":NETWORK,
               "PROCESS":PROCESS,
               "REALTIMEPROCESS":REALTIMEPROCESS,
               "ACTIVEUSER":ACTIVEUSER,
               "BIOS":BIOS,
               "NETWORKCARD":NETWORKCARD
           },
           dataType: "json",
           success: function(data) {
           	
           	alert("更改成功");
           	window.location.reload();
           }
          
       }) 	        
   } 
   
   
   /* 删除服务器配置 */
	function deleteServer(id){  
       //获取模态框数据  
       var equipmentId = document.getElementById(id).id; 	       

       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/deleteServer.html",
           data: {
               "equipmentId":equipmentId
           },
           dataType: "json",
           success: function(data) {
           	alert("删除成功");
           	window.location.reload();
           }
          
       }) 	        
   }  
   
   
   </script>
	
   </div> 
  </div>  
 </body>
</html>