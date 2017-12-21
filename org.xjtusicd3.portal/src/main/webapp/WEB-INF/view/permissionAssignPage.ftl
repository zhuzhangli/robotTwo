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
       <div class="ibox-tools" style="width: 20%"> 
        <div class="form-group"> 
         <label class="col-sm-2 control-label" style="width: 30%">选择角色：</label> 
         <div class="col-sm-10" style="width: 70%;margin-top: -5px;"> 
          <select class="form-control m-b" name="account" style="width: 70%;height: 32px" id="roleId" onchange="selectRole()"> 
           <#list roleList as a> 
           <option value="${a.ROLEID }">${a.ROLENAME}</option> 
           </#list> 
          </select> 
         </div> 
        </div> 
       </div> 
      </div> 
      <div class="ibox-content" align="left"> 
       <div class="row" > 
        
        <div class="col-md-5" >         
         <div style="text-align: center;">
         	<h5>待获取权限</h5>
         </div>
         <table class="table table-striped table-bordered table-hover "> 
          <thead> 
           <tr> 
           	<th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="permissionCheck()">全选</th>
            <th style="text-align: center;">权限逻辑名：汉字</th> 
           </tr> 
          </thead> 
          <tbody id="tbody1">
		   <#list notObtainRolePermission as p>
           <tr class="gradeX" id=""> 
            <td style="text-align: center;width: 13%"><input type="checkbox" class="i-checks" name="selectToObtain[]" value="${p.PERMISSIONID }"></td>
            <td style="text-align: center;width: 87%" id="editLogicName${p.PERMISSIONID }">${p.PERMISSIONLOGICNAME }</td> 
           </tr> 
		   </#list>
          </tbody> 
         </table> 
        </div> 
       
        <div class="col-md-2" > 
         <span id="add_all" style=" width: 50px;  height: 30px;  margin-top: 10px;  cursor: pointer; "><input type="button" class="btn" value="&gt;&gt;" id="b1"/></span>
         <br /> 
         <span id="remove_all" style=" width: 50px;  height: 30px;  margin-top: 10px;  cursor: pointer; "><input type="button" class="btn" value="&lt;&lt;" id="b2"/></span> 
        </div> 
        
        <div class="col-md-5" style="float: right;"> 
         <div style="text-align: center;">
         	<h5>已获取权限</h5>
         </div>
         <table class="table table-striped table-bordered table-hover "> 
          <thead> 
          
           <tr> 
            <th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="permissionUnCheck()">全选</th>  
            <th style="text-align: center;">权限名</th> 
           </tr>
           
          </thead> 
          <tbody id="tbody2">
           <#list obtainRolePermission as obtain>
           <tr class="gradeX" id=""> 
            <td style="text-align: center;width: 13%"><input type="checkbox" class="i-checks" name="input1[]" value="${obtain.PERMISSIONID }"></td>
            <td style="text-align: center;width: 87%" id="editPhysicalName${obtain.PERMISSIONID }">${obtain.PERMISSIONLOGICNAME }</td> 
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
	  /* 给角色分配权限 */
	  var isCheckAll = false;
	
	  function permissionCheck() {
	
	      if (isCheckAll) {
	          $("input[name='selectToObtain[]']").each(function() {
	              this.checked = false;
	          });
	          isCheckAll = false;
	      } else {
	          $("input[name='selectToObtain[]']").each(function() {
	              this.checked = true;
	          });
	          isCheckAll = true;
	      }
	  }
	
	  /* 取消角色权限 */
	  var isCheckAll2 = false;
	
	  function permissionUnCheck() {
	
	      if (isCheckAll2) {
	          $("input[name='input1[]']").each(function() {
	              this.checked = false;
	          });
	          isCheckAll2 = false;
	      } else {
	          $("input[name='input1[]']").each(function() {
	              this.checked = true;
	          });
	          isCheckAll2 = true;
	      }
	  }
  
  
	  /* 将左边未获取权限插入右边已获取*/
	    $("#b1").click(function(){
	    	var element = document.getElementById("roleId");
	        var roleId = element.options[element.selectedIndex].value; 
	    	
	    	var checkedSubject=document.getElementsByName('selectToObtain[]');//获取到复选框的名称  
	    	var checkedIds="";
	 
	    	//因为获得的是数组，所以要循环 为每一个checked赋值  
    	    for(var i=0;i<checkedSubject.length;i++){  
    	        if(checkedSubject[i].checked == true){  
    	        	checkedIds += checkedSubject[i].value+",";  
    	        } 
    	     } 
	    		    	 
    	     $.ajax({
              type: "POST",
              url: "/org.xjtusicd3.portal/permissionChecked.html",
              data: {
            	  roleId:roleId,
            	  checkedIds:checkedIds                
              },
              dataType: "json",
              success: function(data) {             	
              	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
            	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
            	
            	
            	if(permissionList.length == 0){
    				$("#tbody1").html("");
            	}else{
            		var permissionHtml = "";
    				for (var i = 0; i < permissionList.length; i++) {
    					 permissionHtml = permissionHtml
 						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
 						+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
 						+ permissionList[i].pERMISSIONLOGICNAME
 						+"</td></tr>"
    	
 						
 						
 						$("#tbody1")[0].innerHTML = permissionHtml;    
    				}
    				
            	}
            	
            	if(obtainPermission.length == 0){
				 $("#tbody2").html("");
        		}else{
        		var permissionHtml2 = "";
				for (var i = 0; i < obtainPermission.length; i++) {
					 permissionHtml2 = permissionHtml2
						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
						+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
						+ obtainPermission[i].pERMISSIONLOGICNAME
						+"</td></tr>"
	
						$("#tbody2")[0].innerHTML = permissionHtml2;    
				} 
            }
              	
              }
             
          }) 
	    
	    });
	  
	  
	    /* 将角色已获取权限移除*/
	    $("#b2").click(function(){
	    	var element = document.getElementById("roleId");
	        var roleId = element.options[element.selectedIndex].value; 
	    	
	    	var checkedSubject=document.getElementsByName('input1[]');//获取到复选框的名称  
	    	var checkedIds="";
	 
	    	//因为获得的是数组，所以要循环 为每一个checked赋值  
    	    for(var i=0;i<checkedSubject.length;i++){  
    	        if(checkedSubject[i].checked == true){  
    	        	checkedIds += checkedSubject[i].value+",";  
    	        } 
    	    } 
    	
    	 
    	    $.ajax({
              type: "POST",
              url: "/org.xjtusicd3.portal/permissionUnChecked.html",
              data: {
            	  roleId:roleId,
            	  checkedIds:checkedIds                
              },
              dataType: "json",
              success: function(data) {
              	alert("移除成功");
              	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
            	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
            	
            	
            	if(permissionList.length == 0){
    				$("#tbody1").html("");
            	}else{
            		var permissionHtml = "";
    				for (var i = 0; i < permissionList.length; i++) {
    					 permissionHtml = permissionHtml
 						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
 						+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
 						+ permissionList[i].pERMISSIONLOGICNAME
 						+"</td></tr>"
    	
 						
 						
 						$("#tbody1")[0].innerHTML = permissionHtml;    
    				}
    				
            	}
            	
            	if(obtainPermission.length == 0){
				 $("#tbody2").html("");
        		}else{
        		var permissionHtml2 = "";
				for (var i = 0; i < obtainPermission.length; i++) {
					 permissionHtml2 = permissionHtml2
						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
						+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
						+ obtainPermission[i].pERMISSIONLOGICNAME
						+"</td></tr>"
	
						$("#tbody2")[0].innerHTML = permissionHtml2;    
				} 
            }
              }
             
           })   
	    
	    });
  </script>
  
  
  
  
  
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
        
        
        
        /* 根据不同角色获取对应的权限列表 */
        function selectRole() {
            var element = document.getElementById("roleId");
            var roleId = element.options[element.selectedIndex].value;
            
            $.ajax({
	            type: "POST",
	            url: "/org.xjtusicd3.portal/selectRolePermission.html",
	            data: {
	            	"roleId":roleId
	            },
	            dataType: "json",
	            success: function(data) {
	            	var permissionList = data.notObtainRolePermission; //获取后台角色待获取权限
	            	var obtainPermission = data.obtainPermission;  //获取后台角色已获取权限
	            	
	            	
	            	if(permissionList.length == 0){
	    				$("#tbody1").html("");
	            	}else{
	            		var permissionHtml = "";
	    				for (var i = 0; i < permissionList.length; i++) {
	    					 permissionHtml = permissionHtml
	 						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='selectToObtain[]' value='"+permissionList[i].pERMISSIONID+"'></td>"
	 						+ "<td style='text-align: center;width: 87%' id='editLogicName${p.PERMISSIONID }'>"
	 						+ permissionList[i].pERMISSIONLOGICNAME
	 						+"</td></tr>"
	    	
	 						
	 						
	 						$("#tbody1")[0].innerHTML = permissionHtml;    
	    				}
	    				
	            	}
	            	
	            	if(obtainPermission.length == 0){
    				 $("#tbody2").html("");
            		}else{
            		var permissionHtml2 = "";
    				for (var i = 0; i < obtainPermission.length; i++) {
    					 permissionHtml2 = permissionHtml2
 						+ "<tr class='gradeX'><td style='text-align: center;width: 13%'><input type='checkbox' class='i-checks' name='input1[]' value='"+obtainPermission[i].pERMISSIONID+"'></td>"
 						+ "<td style='text-align: center;width: 87%' id='editLogicName_obtainPermission[i].pERMISSIONID'>"
 						+ obtainPermission[i].pERMISSIONLOGICNAME
 						+"</td></tr>"
    	
 						$("#tbody2")[0].innerHTML = permissionHtml2;    
    				} 
	            }
	            }
	        }) 	   
            
  
        }
    </script>  
 </body>
</html>