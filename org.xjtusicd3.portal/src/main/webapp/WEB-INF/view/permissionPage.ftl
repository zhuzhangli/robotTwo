<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title> 权限管理</title> 
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
       <h5>权限列表</h5> 
      </div> 
      <div class="ibox-content"> 
       <!-- 模态框弹出权限 --> 
       <div class=""> 
        <button class="btn btn-primary " data-toggle="modal" data-target="#myModal">添加权限</button> 
       </div> 
       
       <table class="table table-striped table-bordered table-hover dataTables-example"> 
        <thead> 
         <tr> 
          <th style="text-align: center;">权限编号</th> 
          <th style="text-align: center;">权限逻辑名：汉字</th> 
          <th style="text-align: center;">权限物理名</th> 
          <th style="text-align: center;">最新修改时间</th> 
          <th style="text-align: center;">操作</th> 
         </tr> 
        </thead> 
        
        <tbody>
         <#list permissionList as a>
         <tr class="gradeX" id=""> 
          <td style="text-align: center;width: 6%">${a_index+1 }</td> 
          <td style="text-align: center;width: 38%" id="editLogicName${a.permissionId }">${a.permissionLogicName }</td> 
          <td style="text-align: center;width: 38%" id="editPhysicalName${a.permissionId }">${a.permissionPhysicalName }</td>
          <td style="text-align: center;width: 10%">${a.time }</td> 
          <td style="text-align: center;width: 8%" id=""> 
          	<button class="btn btn-white btn-sm" type="button" id="${a.permissionId }" onclick="editPermission(this.id)" title="编辑" data-toggle="modal" data-target="#myModalEdit" physicalName=""><i class="glyphicon glyphicon-pencil"></i></button> 
          	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="删除" id="${a.permissionId }" onclick="deletePermission(this.id)"><i class="fa fa-trash-o"></i> </button>
          </td> 
         </tr> 
         </#list> 
        </tbody> 
       </table> 
      
      </div> 
      
      <!-- 新增_模态框（Modal） --> 
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
       <div class="modal-dialog"> 
        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
         <div class="modal-header"> 
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button> 
          <h4 class="modal-title" id="myModalLabel"> 增加系统权限 </h4> 
         </div> 
         
         <form class="form-horizontal m-t" method="post" action="add" > 
          <div class="modal-body"> 
           <div class="form-group"> 
            <label class="col-sm-3 control-label">权限逻辑名：</label> 
            <div class="col-sm-8"> 
             <input id="addLogicName" name="addLogicName" minlength="2" type="text" class="form-control" required="" aria-required="true" placeholder="请输入权限中文描述" /> 
            </div> 
           </div> 
           <div class="form-group"> 
            <label class="col-sm-3 control-label">权限物理名：</label> 
            <div class="col-sm-8"> 
             <input id="addPhysicalName" name="addLogicName" type="text" class="form-control" required="" aria-required="true" placeholder="请输入权限action描述" /> 
            </div> 
           </div> 
          </div> 
          <div class="modal-footer"> 
           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
           <button type="button" class="btn btn-primary" onclick="addPermission()"> 提交 </button> 
          </div> 
         </form> 
        </div>
        <!-- /.modal-content --> 
       </div>
       <!-- /.modal-dialog --> 
      </div>
      <!-- /.modal --> 
      
      
      <!-- 编辑_模态框（Modal） --> 
      <div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
       <div class="modal-dialog"> 
        <div class="modal-content" style="vertical-align: middle;margin-top: 100px"> 
         <div class="modal-header"> 
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button> 
          <h4 class="modal-title" id="myModalLabel"> 编辑系统权限 </h4> 
         </div> 
         <form class="form-horizontal m-t" id=""> 
          
          <div class="modal-body"> 
           <div class="form-group" style="display: none;"> 
            <div class="col-sm-8"> 
             <input id="editPermissionId" name="permissionId" minlength="2" type="text" class="form-control" required="" aria-required="true"></input>
            </div> 
           </div> 
          
           <div class="form-group"> 
            <label class="col-sm-3 control-label">权限逻辑名：</label> 
            <div class="col-sm-8"> 
             <input id="editLogicName" name="logicName" minlength="2" type="text" class="form-control" required="" aria-required="true"></input>
            </div> 
           </div> 
           <div class="form-group"> 
            <label class="col-sm-3 control-label">权限物理名：</label> 
            <div class="col-sm-8"> 
             <input id="editPhysicalName" name="logicName" type="text" class="form-control" required="" aria-required="true" /> 
            </div> 
           </div> 
          </div>
          <div class="modal-footer"> 
           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
           <button type="button" class="btn btn-primary" onclick="update()"> 提交 </button> 
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
	
		/* 增加权限 */      
	    function addPermission() {  
	        //获取模态框数据  
	        var logicName = $('#addLogicName').val();  
	        //alert(logicName);
	        var physicalName = $('#addPhysicalName').val();  
	       // alert(physicalName);
	        $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/addPermission.html",
	            data: {
	                "logicName":logicName,
	                "physicalName":physicalName
	            },
	            dataType: "json",
	            success: function(data) {
	            	alert("添加成功");
	            	window.location.reload();
	            }
	           
	        }) 
	    }   
    
		
		
		/* 获取要编辑的权限信息 */
	    function editPermission(id) {
	    	//获取权限ID
	    	var permissionId = document.getElementById(id).id;
	    	//alert(permissionId);
	    	
	    	//获取权限逻辑名
	    	var editLogicName = document.getElementById("editLogicName"+permissionId).innerText;
	    	//alert(editLogicName)
	    	
	    	//获取权限物理名
	    	var editPhysicalName = document.getElementById("editPhysicalName"+permissionId).innerText;
	    	//alert(editPhysicalName)
	    	
	    	$("#editPermissionId").val(permissionId); 
	    	$("#editLogicName").val(editLogicName);
	    	$("#editPhysicalName").val(editPhysicalName);
	     }
		

		
		/* 提交更改  */ 
	    function update() {  
	        //获取模态框数据  
	        var permissionId = $('#editPermissionId').val();  
	        //alert(permissionId);
	        var logicName = $('#editLogicName').val(); 
	        //alert(logicName);
	        var physicalName = $('#editPhysicalName').val();
	        //alert(physicalName);

	        $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/updatePermission.html",
	            data: {
	                "permissionId":permissionId,
	                "logicName":logicName,
	                "physicalName":physicalName
	            },
	            dataType: "json",
	            success: function(data) {
	            	alert("更改成功");
	            	window.location.reload();
	            }
	           
	        }) 	        
	    }  
		
		/* 删除权限 */
		function deletePermission(id){  
	        //获取模态框数据  
	        var permissionId = document.getElementById(id).id; 	       

	        $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/deletePermission.html",
	            data: {
	                "permissionId":permissionId
	            },
	            dataType: "json",
	            success: function(data) {
	            	alert("删除成功");
	            	window.location.reload();
	            }
	           
	        }) 	        
	    }  
	</script>  
 </body>
</html>