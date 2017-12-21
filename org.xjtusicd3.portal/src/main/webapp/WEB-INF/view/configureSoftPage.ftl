<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>软件资源库</title> 
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
             <li class="active"><a data-toggle="tab" href="#tab-31" aria-expanded="true">软件【${softSize }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-32" aria-expanded="false">驱动【${driverSize }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-33" aria-expanded="false">补丁【${patchSize }】</a></li> 
            </ul> 
            <div class="tab-content"> 
             
             <!-- 软件信息 --> 
             <div id="tab-31" class="tab-pane active"> 
              <div class="ibox-title"> 
               <h5>软件信息</h5> 
               <div class="ibox-tools"> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"></th> 
                  <th style="text-align: center;">名称</th> 
                  <th style="text-align: center;">简介</th>
                  <th style="text-align: center;">生厂商</th> 
                  <th style="text-align: center;">更新时间</th>
                  <th style="text-align: center;">评分</th>
                  <th style="text-align: center;">文件大小</th> 
                  <th style="text-align: center;">下载次数</th>                   
                  <th style="text-align: center;">更多详情</th> 
                  <th style="text-align: center;">下载</th>
                  <th style="text-align: center;">加入标准库</th> 
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list softList as a>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${a.index+1 }</td>
                  <td style="width: 12%"><img class="answerImg" src="${a.LOGO }" style="width: 20px;height: 20px">${a.CONFIGURENAME }</td> 
                  <td style="width: 35%">${a.DESCRIPTION }</td>
                  <td style="text-align: center;width: 12%">${a.PRODUCER }</td> 
                  <td style="text-align: center;width: 10%">${a.CONFIGURETIME }</td>
                  <td style="text-align: center;width: 5%">${a.SCORE }</td>
                  <td style="text-align: center;width: 5%">${a.FILESIZE }</td> 
                  <td style="text-align: center;width: 5%">${a.DOWNLOADTIMES }</td> 
                  <td style="text-align: center;width: 5%" >
                  	<button class="btn btn-white btn-sm" type="button" id="soft_${a.CONFIGUREID }" title="更多详情" onclick="lookMoreSoftInfo(this.id)" data-toggle="modal" data-target="#myModalSoftLook"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 4%" >
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="下载"  ><a href="${a.URL }"><i class="fa fa-download"></i></a></button>               	
                  </td>
                  <td style="text-align: center;width: 4%">
                    <#if a.ISCONFIGURE = 0>
                    <button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="添加至标准配置" id="${a.CONFIGUREID }" onclick="SoftBasicCfg(this.id)" data-toggle="modal" data-target="#myModalSoft"></button>
                    <#else>
                    <button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="${a.CONFIGUREID }" onclick=""></button>
                 	</#if>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
               </table> 
              </div> 
             </div> 
             
             
             <!--驱动信息 --> 
             <div id="tab-32" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>驱动信息</h5> 
               <div class="ibox-tools">      
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"></th> 
                  <th style="text-align: center;">名称</th> 
                  <th style="text-align: center;">适用型号</th>
                  <th style="text-align: center;">生厂商</th> 
                  <th style="text-align: center;">更新时间</th>
                  <th style="text-align: center;">文件大小</th> 
                  <th style="text-align: center;">下载次数</th>                   
                  <th style="text-align: center;">应用</th>
                  <th style="text-align: center;">下载</th>
                  <th style="text-align: center;">加入标准库</th> 
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list driverList as driver>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${driver.index+1 }</td>
                  <td style="width: 35%">${driver.CONFIGURENAME }</td> 
                  <td style="text-align: center;width: 20%">${driver.OS }</td>
                  <td style="text-align: center;width: 10%">${driver.PRODUCER }</td> 
                  <td style="text-align: center;width: 10%">${driver.CONFIGURETIME }</td>
                  <td style="text-align: center;width: 5%">${driver.FILESIZE }</td> 
                  <td style="text-align: center;width: 5%">${driver.DOWNLOADTIMES }</td> 
                  <td style="text-align: center;width: 4%" >
                  	<button class="btn btn-white btn-sm" type="button" id="driver1_${driver.CONFIGUREID }" title="更多详情" onclick="lookMoreDriverInfo(this.id)" data-toggle="modal" data-target="#myModalDriver"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 4%" >
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="下载"  ><a href="${driver.URL }"><i class="fa fa-download"></i></a></button>               	
                  </td>
                  <td style="text-align: center;width: 4%">
                    <#if driver.ISCONFIGURE = 0>
                    <button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="添加至标准配置" id="driver_${driver.CONFIGUREID }" onclick="driverBasicCfg(this.id)" data-toggle="modal" data-target="#myModalDriverDepartment"></button>
                    <#else>
                    <button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="driver_${driver.CONFIGUREID }" onclick=""></button>
                 	</#if>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
               </table>  
              </div> 
             </div> 
             
             
             <!-- 补丁信息 --> 
             <div id="tab-33" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>补丁信息</h5> 
               <div class="ibox-tools"> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"></th> 
                  <th style="text-align: center;">名称</th> 
                  <th style="text-align: center;">适用系统</th>
                  <th style="text-align: center;">生厂商</th> 
                  <th style="text-align: center;">更新时间</th>
                  <th style="text-align: center;">语言</th>
                  <th style="text-align: center;">文件大小</th> 
                  <th style="text-align: center;">下载次数</th>                   
                  <th style="text-align: center;">更多详情</th> 
                  <th style="text-align: center;">下载</th>
                  <th style="text-align: center;">加入标准库</th> 
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list patchList as patch>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${patch.index+1 }</td>
                  <td style="width: 35%">${patch.CONFIGURENAME }</td> 
                  <td style="width: 12%">${patch.OS }</td>
                  <td style="text-align: center;width: 12%">${patch.PRODUCER }</td> 
                  <td style="text-align: center;width: 8%">${patch.CONFIGURETIME }</td>
                  <td style="text-align: center;width: 5%">${patch.LANGUAGE }</td>
                  <td style="text-align: center;width: 7%">${patch.FILESIZE }</td> 
                  <td style="text-align: center;width: 5%">${patch.DOWNLOADTIMES }</td> 
                  <td style="text-align: center;width: 5%" >
                  	<button class="btn btn-white btn-sm" type="button" id="patch1_${patch.CONFIGUREID }" title="更多详情" onclick="lookMorePatchInfo(this.id)" data-toggle="modal" data-target="#myModalPatch"><i class="fa fa-eye"></i></button>
                  </td> 
                  <td style="text-align: center;width: 4%" >
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="下载"  ><a href="${patch.URL }"><i class="fa fa-download"></i></a></button>               	
                  </td>
                  <td style="text-align: center;width: 4%">
                    <#if patch.ISCONFIGURE = 0>
                    <button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="添加至标准配置" id="patch_${patch.CONFIGUREID }" onclick="patchBasicCfg(this.id)" data-toggle="modal" data-target="#myModalPatchDepartment"></button>
                    <#else>
                    <button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="patch_${patch.CONFIGUREID }" onclick=""></button>
                 	</#if>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
               </table>  
              </div> 
             </div> 
           
                                     
             <!-- 给选定软件分配部门_模态框（Modal） --> 
		      <div class="modal fade" id="myModalSoft" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 将本软件分配给如下部门 </h4> 
		         </div> 
		        
		         <div class="modal-body">
		          <table class="table table-striped table-bordered table-hover "> 
		          <thead> 		          
		           <tr> 
		            <th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="departmentCheck()">全选</th>  
		            <th style="text-align: center;">部门名</th> 
		           </tr>		           
		          </thead> 
		         
		          <tbody id="tbody1">
		         	<input id="softId"  type="text" style="display: none;"></input>
		           
		           <tr class="gradeX" id=""> 
		            <td style="text-align: center;width: 13%"><input type="checkbox" class="i-checks" name="inputSoft[]" value=""></td>
		            <td style="text-align: center;width: 87%" id="editPhysicalName${obtain.PERMISSIONID }"></td> 
		           </tr> 
		          </tbody> 
		         </table> 		         		        
		         </div>
		         
		         <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="addSoftToBasic()"> 提交 </button> 
		         </div>
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal --> 
             
                      
             <!-- 查看软件更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalSoftLook" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多软件信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                 <div class="modal-body"> 
                  <div class="form-group" style="display: none;"> 
                   <div class="col-sm-8"> 
                    <input id="editPermissionId" name="permissionId" minlength="2" type="text" class="form-control" required="" aria-required="true" /> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">更多描述：</label> 
                   <div class="col-sm-8"> 
                    <textarea id="introduction" name="logicName"  class="form-control" style="overflow-y:auto;height: 100px"  aria-required="true" readonly="readonly"></textarea> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">版本类型：</label> 
                   <div class="col-sm-8"> 
                    <input id="versionType" name="logicName" type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                 
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">版本号：</label> 
                   <div class="col-sm-8"> 
                    <input id="version" name="logicName" type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">软件类型：</label> 
                   <div class="col-sm-8"> 
                    <input id="softType" name="logicName" type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                  
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">网址：</label> 
                   <div class="col-sm-8"> 
                    <input id="website" name="logicName" type="text" class="form-control" required="" aria-required="true" readonly="readonly"/> 
                   </div> 
                  </div> 
                 
                 </div> 

                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
             
             
             <!-- 给选定驱动分配部门_模态框（Modal） --> 
		      <div class="modal fade" id="myModalDriverDepartment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 将本驱动分配给如下部门 </h4> 
		         </div> 
		        
		         <div class="modal-body">
		          <table class="table table-striped table-bordered table-hover "> 
		          <thead> 		          
		           <tr> 
		            <th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="driverDepartmentCheck()">全选</th>  
		            <th style="text-align: center;">部门名</th> 
		           </tr>		           
		          </thead> 
		         
		          <tbody id="tbody2">
		         	<input id="driverId"  type="text" style="display: none;"></input>
		           
		           <tr class="gradeX" id=""> 
		            <td style="text-align: center;width: 13%"><input type="checkbox" class="i-checks" name="inputDriver[]" value=""></td>
		            <td style="text-align: center;width: 87%" id="editPhysicalName${obtain.PERMISSIONID }"></td> 
		           </tr> 
		          </tbody> 
		         </table> 		         		        
		         </div>
		         
		         <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="addDriverToBasic()"> 提交 </button> 
		         </div>
		        </div>
		        <!-- /.modal-content --> 
		       </div>
		       <!-- /.modal-dialog --> 
		      </div>
		      <!-- /.modal --> 
		      
             
             <!-- 查看驱动更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalDriver" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多驱动信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">适用于：</label> 
                   <div class="col-sm-8"> 
                    <textarea id="driverIntroduction"  class="form-control" style="overflow-y:auto;height: 180px"  aria-required="true" readonly="readonly"></textarea> 
                   </div> 
                  </div> 
                  <div class="modal-footer">           
                  </div> 
                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
             
             
             <!-- 查看补丁更多信息_模态框（Modal） --> 
             <div class="modal fade" id="myModalPatch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
              <div class="modal-dialog"> 
               <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
                <div class="modal-header"> 
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
                 <h4 class="modal-title" id="myModalLabel"> 更多补丁信息 </h4> 
                </div> 
                <form class="form-horizontal m-t" id=""> 
                
                  <div class="form-group"> 
                   <label class="col-sm-3 control-label">简介：</label> 
                   <div class="col-sm-8" id="patchIntroduction"> 
                    
                   </div> 
                  </div> 

				  <div class="modal-footer">           
                  </div> 
                </form> 
               </div> 
               <!-- /.modal-content --> 
              </div> 
              <!-- /.modal-dialog --> 
             </div> 
             <!-- /.modal --> 
             
             
             <!-- 给选定补丁分配部门_模态框（Modal） --> 
		      <div class="modal fade" id="myModalPatchDepartment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		       <div class="modal-dialog"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 将本补丁分配给如下部门 </h4> 
		         </div> 
		        
		         <div class="modal-body">
		          <table class="table table-striped table-bordered table-hover "> 
		          <thead> 		          
		           <tr> 
		            <th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="patchDepartmentCheck()">全选</th>  
		            <th style="text-align: center;">部门名</th> 
		           </tr>		           
		          </thead> 
		         
		          <tbody id="tbody3">
		         	<input id="patchId"  type="text" style="display: none;"></input>
		           
		           <tr class="gradeX" id=""> 
		            <td style="text-align: center;width: 13%"><input type="checkbox" class="i-checks" name="inputPatch[]" value=""></td>
		            <td style="text-align: center;width: 87%" id="editPhysicalName${obtain.PERMISSIONID }"></td> 
		           </tr> 
		          </tbody> 
		         </table> 		         		        
		         </div>
		         
		         <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="addPatchToBasic()"> 提交 </button> 
		         </div>
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
    
        
	/* 将软件添加至标准库 */      
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
            	var list = data.list; //获取后台json'
            	$("#softId").val(data.configureId);
            	
            	if(list.length == 0){
    				$("#tbody1").html("");
            	}else{
            		var permissionHtml = "";
            		
    				for (var i = 0; i < list.length; i++) {
    					 permissionHtml = permissionHtml
 						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='inputSoft[]' value='"+list[i].dATADICTIONARYID+"'></td>"
 						+ "<td style='text-align: center;width: 87%' id='"+list[i].dATADICTIONARYID+"'>"
 						+ list[i].dATANAME
 						+"</td></tr>"
 						
 						$("#tbody1")[0].innerHTML = permissionHtml;    
    				}
            	}	
            	
            }
           
        }) 
    }
	
    /* 选择软件分配的部门     全选 */
	  var isCheckAll = false;
	
	  function departmentCheck() {
	
	      if (isCheckAll) {
	          $("input[name='inputSoft[]']").each(function() {
	              this.checked = false;
	          });
	          isCheckAll = false;
	      } else {
	          $("input[name='inputSoft[]']").each(function() {
	              this.checked = true;
	          });
	          isCheckAll = true;
	      }
	  }
  
		/* 将软件分配给不同部门 */ 
	    function addSoftToBasic() {  
	    	var configureId = $('#softId').val(); 
	    	
			var checkedSubject=document.getElementsByName('inputSoft[]');//获取到复选框的名称  
	    	var checkedIds="";
	 
	    	//因为获得的是数组，所以要循环 为每一个checked赋值  
    	    for(var i=0;i<checkedSubject.length;i++){  
    	        if(checkedSubject[i].checked == true){  
    	        	checkedIds += checkedSubject[i].value+",";  
    	        } 
    	     } 
    	    $.ajax({
                type: "POST",
                url: "/org.xjtusicd3.portal/addSoftToBasic.html",
                data: {
                	configureId:configureId,
              	    checkedIds:checkedIds                
                },
                dataType: "json",
                success: function(data) {             	
        			alert("添加软件至部门成功")
        			window.location.reload();
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
        	   var list = data.list; //获取后台json'
	           	$("#driverId").val(data.configureId);
	           	
	           	if(list.length == 0){
	   				$("#tbody2").html("");
	           	}else{
	           		var permissionHtml = "";
	           		
	   				for (var i = 0; i < list.length; i++) {
	   					 permissionHtml = permissionHtml
							+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='inputDriver[]' value='"+list[i].dATADICTIONARYID+"'></td>"
							+ "<td style='text-align: center;width: 87%' id='"+list[i].dATADICTIONARYID+"'>"
							+ list[i].dATANAME
							+"</td></tr>"
							
							$("#tbody2")[0].innerHTML = permissionHtml;    
	   				}
	           	}
           }
          
       }) 
   } 	
   
   
   /* 选择驱动分配的部门     全选 */
  var isDriverCheckAll = false;

  function driverDepartmentCheck() {

      if (isDriverCheckAll) {
          $("input[name='inputDriver[]']").each(function() {
              this.checked = false;
          });
          isDriverCheckAll = false;
      } else {
          $("input[name='inputDriver[]']").each(function() {
              this.checked = true;
          });
          isDriverCheckAll = true;
      }
  }
	  
   
   /* 将驱动分配给不同部门 */ 
   function addDriverToBasic() {  
   	var configureId = $('#driverId').val(); 
   	
	var checkedSubject=document.getElementsByName('inputDriver[]');//获取到复选框的名称  
   	var checkedIds="";

   	//因为获得的是数组，所以要循环 为每一个checked赋值  
	    for(var i=0;i<checkedSubject.length;i++){  
	        if(checkedSubject[i].checked == true){  
	        	checkedIds += checkedSubject[i].value+",";  
	        } 
	     } 
	    $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/addDriverToBasic.html",
           data: {
           	    configureId:configureId,
         	    checkedIds:checkedIds                
           },
           dataType: "json",
           success: function(data) {             	
   			alert("添加驱动至部门成功")
   			window.location.reload();
           }
          
       })         
   } 
   </script> 
    
    
    
	<script>
   /* 查看补丁更多信息 */
   function lookMorePatchInfo(id) {
   	//获取配置ID
   	var configureId = document.getElementById(id).id;
   	//alert(permissionId);
   	
   	$.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/lookMorePatchInfo.html",
           data: {
               "configureId":configureId
           },
           dataType: "json",
           success: function(data) {
           	
           	var morePatchInfo = data.morePatchInfo; //获取后台json'
           	
           	var obj = document.getElementById("patchIntroduction");        
            obj.innerText= morePatchInfo.pATCHINTRODUCTION;

           }           
       }) 	   	    	
    }
   
   
   /* 将补丁添加至标准库  */      
   function patchBasicCfg(id) {  
	//获取配置补丁ID
   	var configureId = document.getElementById(id).id;  
   	
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/patchBasicCfg.html",
           data: {
               "configureId":configureId
           },
           dataType: "json",
           success: function(data) {
        	   var list = data.list; //获取后台json'
	           	$("#patchId").val(data.configureId);
	           	
	           	if(list.length == 0){
	   				$("#tbody3").html("");
	           	}else{
	           		var permissionHtml = "";
	           		
	   				for (var i = 0; i < list.length; i++) {
	   					 permissionHtml = permissionHtml
							+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='inputPatch[]' value='"+list[i].dATADICTIONARYID+"'></td>"
							+ "<td style='text-align: center;width: 87%' id='"+list[i].dATADICTIONARYID+"'>"
							+ list[i].dATANAME
							+"</td></tr>"
							
							$("#tbody3")[0].innerHTML = permissionHtml;    
	   				}
	           	}
           }
          
       }) 
   } 
   
   /* 选择补丁分配的部门     全选 */
   var isPatchCheckAll = false;

   function patchDepartmentCheck() {

       if (isPatchCheckAll) {
           $("input[name='inputPatch[]']").each(function() {
               this.checked = false;
           });
           isPatchCheckAll = false;
       } else {
           $("input[name='inputPatch[]']").each(function() {
               this.checked = true;
           });
           isPatchCheckAll = true;
       }
   }
 	  
    
    /* 将驱动分配给不同部门 */ 
    function addPatchToBasic() {  
    	var configureId = $('#patchId').val(); 
    	
 	var checkedSubject=document.getElementsByName('inputPatch[]');//获取到复选框的名称  
    	var checkedIds="";

    	//因为获得的是数组，所以要循环 为每一个checked赋值  
 	    for(var i=0;i<checkedSubject.length;i++){  
 	        if(checkedSubject[i].checked == true){  
 	        	checkedIds += checkedSubject[i].value+",";  
 	        } 
 	     } 
 	    $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/addPatchToBasic.html",
            data: {
            	configureId:configureId,
          	    checkedIds:checkedIds                
            },
            dataType: "json",
            success: function(data) {             	
    			alert("添加补丁至部门成功")
    			window.location.reload();
            }
           
        })         
    } 
   
   
   </script>
   </div> 
  </div>  
 </body>
</html>