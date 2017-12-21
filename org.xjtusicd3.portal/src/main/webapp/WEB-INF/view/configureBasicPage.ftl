<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title> 配置管理</title> 
  <meta name="keywords" content="" /> 
  <meta name="description" content="" /> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <!-- Data Tables --> 
  <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeIn"> 
   <div class="row"> 
    <div class="col-sm-12"> 
     <div class="ibox float-e-margins"> 
      <div class="ibox-title"> 
       <h5>标准配置信息</h5> 
       <div class="ibox-tools" style="width: 20%"> 
        <div class="form-group"> 
         <label class="col-sm-2 control-label" style="width: 30%">选择部门：</label> 
         <div class="col-sm-10" style="width: 70%;margin-top: -5px;"> 
          <select class="form-control m-b" name="account" style="width: 70%;height: 32px" id="departmentId" onchange="selectDepartment()"> 
           <#list depList as a> 
           <option value="${a.DEPARTMENTID }">${a.DEPARTMENTNAME}</option> 
           </#list> 
          </select> 
         </div> 
        </div> 
       </div> 
      </div> 
      <div class="ibox-content" align="left"> 
       <div class="row" > 
        
        
        <div class="col-md-4" >         
         <div style="text-align: center;">
         	<h5>软件标准配置</h5>
         </div>
         <table class="table table-striped table-bordered table-hover "> 
          <thead> 
           <tr> 
           	<th style="text-align: center;width: 10%">序号</th>
            <th style="text-align: center;width: 80%">名称</th> 
            <th style="text-align: center;width: 10%">操作</th>
           </tr> 
          </thead> 
          <tbody id="tbody1">
           <#list softList as cfg>
           <tr class="gradeX" id=""> 
            <td style="text-align: center;width: 10%">${cfg_index+1 }</td>
            <td style="text-align: center;width: 80%">${cfg.CONFIGURENAME }</td> 
            <td style="text-align: center;width: 10%">
            	<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="soft_${cfg.CONFIGUREID }" onclick="remove(this.id)"></button>
            </td>
           </tr> 
           </#list>
          </tbody> 
         </table> 
        </div> 
       
  
       
        <div class="col-md-4" > 
           <div style="text-align: center;">
         	<h5>驱动标准配置</h5>
         </div>
         <table class="table table-striped table-bordered table-hover "> 
          <thead> 
           <tr> 
           	<th style="text-align: center;width: 10%">序号</th>
            <th style="text-align: center;width: 80%">名称</th> 
            <th style="text-align: center;width: 10%">操作</th>
           </tr> 
          </thead> 
          <tbody id="tbody2">
		    <#list driverList as cfg1>
           <tr class="gradeX" id=""> 
            <td style="text-align: center;width: 10%">${cfg1_index+1 }</td>
            <td style="text-align: center;width: 80%">${cfg1.CONFIGURENAME }</td> 
         	<td style="text-align: center;width: 10%">
         		<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="driver_${cfg1.CONFIGUREID }" onclick="remove(this.id)"></button>
         	</td>
           </tr> 
		   </#list>
          </tbody> 
         </table> 
        </div> 
        
    
        
        <div class="col-md-4" style="float: right;"> 
         <div style="text-align: center;">
         	<h5>补丁标准配置</h5>
         </div>
         <table class="table table-striped table-bordered table-hover "> 
          <thead> 
          
           <tr> 
           	<th style="text-align: center;width: 10%">序号</th>
            <th style="text-align: center;width: 80%">名称</th> 
            <th style="text-align: center;width: 10%">操作</th>
           </tr> 
           
          </thead> 
          <tbody id="tbody3">
           <#list patchList as cfg2>
           <tr class="gradeX" id=""> 
            <td style="text-align: center;width: 10%">${cfg2_index+1 }</td>
            <td style="text-align: center;width: 80%">${cfg2.CONFIGURENAME }</td> 
            <td style="text-align: center;width: 10%">
            	<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="patch_${a.EQUIPMENTID }" onclick="remove(this.id)"></button>
            </td>
           </tr> 
 		  </#list>
          </tbody> 
         </table> 
        </div> 
   
       
       </div> <!-- row结束 -->
      
      </div> <!-- ibox-content结束 -->
     
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
        
        
        
        /* 根据不同部门获取对应的配置列表 */
        function selectDepartment() {
            var element = document.getElementById("departmentId");
            var departmentId = element.options[element.selectedIndex].value;
            
            $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/selectDepartmentCfg.html",
	            data: {
	            	"departmentId":departmentId
	            },
	            dataType: "json",
	            success: function(data) {
	            	var softList = data.softList; //获取后台该部门软件配置
	            	var driverList = data.driverList;  //获取后台该部门驱动配置
	            	var patchList = data.patchList;  //获取后台该部门驱动配置
	            	
	            	
	            	if(softList.length == 0){
	    				$("#tbody1").html("");
	            	}else{
	            		var permissionHtml = "";
	    				for (var i = 0; i < softList.length; i++) {
	    					 permissionHtml = permissionHtml
	 						+'<tr class="gradeX" id=""> '
	 						+'<td style="text-align: center;width: 10%">'+(i+1)+'</td>'
	 						+'<td style="text-align: center;width: 80%">'+softList[i].cONFIGURENAME+'</td>'
	 						+'<td style="text-align: center;width: 10%">'
	 						+'<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="soft_'+softList[i].cONFIGUREID+'" onclick="remove(this.id)"></button></td>'
	 						+'</tr> '
	 						
	 						$("#tbody1")[0].innerHTML = permissionHtml;    
	    				}
	    				
	            	}
	            	
	            	if(driverList.length == 0){
    				 $("#tbody2").html("");
            		}else{
            		var permissionHtml2 = "";
    				for (var i = 0; i < driverList.length; i++) {
    					 permissionHtml2 = permissionHtml2
    					 +'<tr class="gradeX" id=""> '
	 						+'<td style="text-align: center;width: 10%">'+(i+1)+'</td>'
	 						+'<td style="text-align: center;width: 80%">'+driverList[i].cONFIGURENAME+'</td>'
	 						+'<td style="text-align: center;width: 10%">'
	 						+'<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="driver_'+driverList[i].cONFIGUREID+'" onclick="remove(this.id)"></button></td>'
	 						+'</tr> '
 						+"</td></tr>"
    	
 						$("#tbody2")[0].innerHTML = permissionHtml2;    
    				} 
	            }
	            	
            	if(patchList.length == 0){
    				 $("#tbody3").html("");
            		}else{
            		var permissionHtml3 = "";
    				for (var i = 0; i < patchList.length; i++) {
    					 permissionHtml3 = permissionHtml3
    					 +'<tr class="gradeX" id=""> '
	 						+'<td style="text-align: center;width: 10%">'+(i+1)+'</td>'
	 						+'<td style="text-align: center;width: 80%">'+patchList[i].cONFIGURENAME+'</td>'
	 						+'<td style="text-align: center;width: 10%">'
	 						+'<button class="btn btn-white btn-sm fa fa-trash-o" data-toggle="tooltip" data-placement="top" title="从标准配置移除" id="patch_'+patchList[i].cONFIGUREID+'" onclick="remove(this.id)"></button></td>'
	 						+'</tr> '
 						+"</td></tr>"
    	
 						$("#tbody3")[0].innerHTML = permissionHtml3;    
    				} 
	            }
	            	
	            }
	        }) 	   
            
  
        }
        
        /* 移除部门配置 */
		function remove(id){  
	        //获取模态框数据  
	        var configureId = document.getElementById(id).id;
	        var element = document.getElementById("departmentId");
            var departmentId = element.options[element.selectedIndex].value;

	        $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/removeConfigure.html",
	            data: {
	                "configureId":configureId,
	                "departmentId":departmentId
	            },
	            dataType: "json",
	            success: function(data) {
	            	if(date = 1){
	            		alert("删除成功");
		            	window.location.reload();
	            	}else{
	            		alert("删除不成功");
		            	window.location.reload();
	            	}
	            	
	            }
	           
	        }) 	         
	    }   
        
        
    </script>  
 </body>
</html>