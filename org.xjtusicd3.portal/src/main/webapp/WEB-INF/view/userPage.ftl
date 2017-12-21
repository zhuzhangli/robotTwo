<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>用户管理</title> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <!-- Data Tables --> 
  <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" /> 
 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
 
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
             <li class="active"><a data-toggle="tab" href="#tab-31" aria-expanded="true">待审核用户【${pendingAuditCounts }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-32" aria-expanded="false">普通用户【${pordinaryUserCounts }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-33" aria-expanded="false">运维人员【${ITUserCounts }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-34" aria-expanded="false">管理员【${adminUserCounts }】</a></li> 
          
            </ul> 
            
            
            <div class="tab-content"> 
             <!-- 待审核用户信息 -->
             <div id="tab-31" class="tab-pane active"> 
              <div class="ibox-title"> 
               <h5>待审核用户信息</h5> 
               <div class="ibox-tools"> 
                <button type="button" class="btn btn-w-m btn-success"  id="b1">批量审核通过</button>
                <button type="button" class="btn btn-w-m btn-danger" id="b2">批量审核不通过</button>               
               </div> 
              </div> 
              <div class="ibox-content">               
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  <th style="text-align: center;"><input type="checkbox" class="i-checks"  onclick="swapCheck()"></th>
                  <th style="text-align: center;">序号</th>                   
                  <th style="text-align: center;">头像</th> 
                  <th style="text-align: center;">用户名</th> 
                  <th style="text-align: center;">邮箱</th> 
                  <th style="text-align: center;">性别</th> 
                  <th style="text-align: center;">生日</th> 
                  <th style="text-align: center;">地址</th> 
                  <th style="text-align: center;">签名</th> 
                  <th style="text-align: center;">注册时间</th>
                  <th style="text-align: center;">角色名称</th>
                  <th style="text-align: center;">操作</th> 
                  <th style="text-align: center;">审核</th>             
                 </tr> 
                </thead> 
                <tbody> 
                <#list pendingAuditUsers as a>
                 <tr class="gradeX" id="${a.USERID }"> 
                  <td style="text-align: center;"><input type="checkbox" class="i-checks" name="input[]" value="${a.USERID }"></td>
                  <td style="text-align: center;">${a_index+1 }</td> 
                  <td style="text-align: center;"><img class="answerImg" src="${a.AVATAR }" style="width: 50px;height: 50px"></td> 
                  <td>${a.USERNAME }</td> 
                  <td class="center">${a.USEREMAIL }</td> 
                  <td style="text-align: center;">${a.GENDER }</td> 
                  <td>${a.USERBIRTHDAY }</td> 
                  <td>${a.USERADDRESS }</td> 
                  <td>${a.USERSIGNATURE }</td> 
                  <td class="center">${a.CREATETIME }</td>
                  <td class="center">${a.ROLENAME }</td>
                  <td style="text-align: center;" id = "">
                  	<button class="" type="button" id="${a.USERID }" onclick="throughAudit(this.id)"><i class="fa fa-check text-navy"></i></button>
                  </td> 
                  <td style="text-align: center;" id = "">                  	
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="未通过审核" id="${a.USERID }" onclick="noAudit(this.id)"><i class="fa fa-trash-o"></i>
                  </td>
                 </tr>
                 </#list> 
                </tbody> 
               </table> 
              </div> 
             </div> 
             
             <!--普通用户信息 -->
             <div id="tab-32" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>普通用户信息</h5> 
               <div class="ibox-tools"> 
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
                <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i> </a> 
                <ul class="dropdown-menu dropdown-user"> 
                 <li><a href="table_data_tables.html#">选项1</a> </li> 
                 <li><a href="table_data_tables.html#">选项2</a> </li> 
                </ul> 
                <a class="close-link"> <i class="fa fa-times"></i> </a> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
               <thead> 
                 <tr> 
                 
                  <th style="text-align: center;">序号</th>                   
                  <th style="text-align: center;">头像</th> 
                  <th style="text-align: center;">用户名</th> 
                  <th style="text-align: center;">邮箱</th> 
                  <th style="text-align: center;">性别</th> 
                  <th style="text-align: center;">生日</th> 
                  <th style="text-align: center;">地址</th> 
                  <th style="text-align: center;">签名</th> 
                  <th style="text-align: center;">注册时间</th>
                  <th style="text-align: center;">操作</th> 
                  <th style="text-align: center;">操作</th>             
                 </tr> 
                </thead> 
                <tbody> 
                <#list ordinaryUsers as a>
                 <tr class="gradeX" id="${a.USERID }"> 
                 
                  <td style="text-align: center;">${a_index+1 }</td> 
                  <td style="text-align: center;"><img class="answerImg" src="${a.AVATAR }" style="width: 50px;height: 50px"></td> 
                  <td>${a.USERNAME }</td> 
                  <td class="center">${a.USEREMAIL }</td> 
                  <td style="text-align: center;">${a.GENDER }</td> 
                  <td>${a.USERBIRTHDAY }</td> 
                  <td>${a.USERADDRESS }</td> 
                  <td>${a.USERSIGNATURE }</td> 
                  <td class="center">${a.CREATETIME }</td>
                  <td style="text-align: center;" >
                 	 <button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="角色变更" id="${a.USERID }" onclick="changeRole(this.id)" data-toggle="modal" data-target="#myModalSoft"></button>
                  </td> 
                  <td style="text-align: center;" id = ""> 
                  	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="查看用户详情" ><a class="userinfo" href="/org.xjtusicd3.portal/showUserInfo.html?u=${a.USERID}"><i class="fa fa-eye"></i></a></button>
                  </td>
                 </tr>
                 </#list> 
                </tbody>
               </table> 
              </div> 
             </div> 
             
             <!-- 运维人员信息 -->
             <div id="tab-33" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>运维人员信息</h5> 
               <div class="ibox-tools"> 
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
                <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i> </a> 
                <ul class="dropdown-menu dropdown-user"> 
                 <li><a href="table_data_tables.html#">选项1</a> </li> 
                 <li><a href="table_data_tables.html#">选项2</a> </li> 
                </ul> 
                <a class="close-link"> <i class="fa fa-times"></i> </a> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                 
                  <th style="text-align: center;">序号</th>                   
                  <th style="text-align: center;">头像</th> 
                  <th style="text-align: center;">用户名</th> 
                  <th style="text-align: center;">邮箱</th> 
                  <th style="text-align: center;">性别</th> 
                  <th style="text-align: center;">生日</th> 
                  <th style="text-align: center;">地址</th> 
                  <th style="text-align: center;">签名</th> 
                  <th style="text-align: center;">注册时间</th>
                  <th style="text-align: center;">操作</th> 
                  <th style="text-align: center;">操作</th>             
                 </tr> 
                </thead> 
                <tbody> 
                <#list ITUsers as a>
                 <tr class="gradeX" id="${a.USERID }"> 
                  
                  <td style="text-align: center;">${a_index+1 }</td> 
                  <td style="text-align: center;"><img class="answerImg" src="${a.AVATAR }" style="width: 50px;height: 50px"></td> 
                  <td>${a.USERNAME }</td> 
                  <td class="center">${a.USEREMAIL }</td> 
                  <td style="text-align: center;">${a.GENDER }</td> 
                  <td>${a.USERBIRTHDAY }</td> 
                  <td>${a.USERADDRESS }</td> 
                  <td>${a.USERSIGNATURE }</td> 
                  <td class="center">${a.CREATETIME }</td>
                  <td style="text-align: center;" id = "">
                 	<button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="角色变更" id="${a.USERID }" onclick="changeRole(this.id)" data-toggle="modal" data-target="#myModalSoft"></button>
                  </td> 
                  <td style="text-align: center;" id = ""> 
                  	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="查看该用户详情" ><a class="userinfo" href="/org.xjtusicd3.portal/showUserInfo.html?u=${a.USERID}"><i class="fa fa-eye"></i></a></button>
                  </td>
                 </tr>
                 </#list> 
                </tbody>
               </table> 
              </div> 
             </div> 
            
             <!--管理员信息  -->
             <div id="tab-34" class="tab-pane"> 
              <div class="ibox-title"> 
               <h5>管理员信息</h5> 
               <div class="ibox-tools"> 
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
                <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i> </a> 
                <ul class="dropdown-menu dropdown-user"> 
                 <li><a href="table_data_tables.html#">选项1</a> </li> 
                 <li><a href="table_data_tables.html#">选项2</a> </li> 
                </ul> 
                <a class="close-link"> <i class="fa fa-times"></i> </a> 
               </div> 
              </div> 
              <div class="ibox-content"> 
               <table class="table table-striped table-bordered table-hover dataTables-example"> 
                <thead> 
                 <tr> 
                  
                  <th style="text-align: center;">序号</th>                   
                  <th style="text-align: center;">头像</th> 
                  <th style="text-align: center;">用户名</th> 
                  <th style="text-align: center;">邮箱</th> 
                  <th style="text-align: center;">性别</th> 
                  <th style="text-align: center;">生日</th> 
                  <th style="text-align: center;">地址</th> 
                  <th style="text-align: center;">签名</th> 
                  <th style="text-align: center;">注册时间</th>
                  <th style="text-align: center;">操作</th> 
                  <th style="text-align: center;">操作</th>             
                 </tr> 
                </thead> 
                <tbody> 
                <#list adminUsers as a>
                 <tr class="gradeX" id="${a.USERID }"> 
                  
                  <td style="text-align: center;">${a_index+1 }</td> 
                  <td style="text-align: center;"><img class="answerImg" src="${a.AVATAR }" style="width: 50px;height: 50px"></td> 
                  <td>${a.USERNAME }</td> 
                  <td class="center">${a.USEREMAIL }</td> 
                  <td style="text-align: center;">${a.GENDER }</td> 
                  <td>${a.USERBIRTHDAY }</td> 
                  <td>${a.USERADDRESS }</td> 
                  <td>${a.USERSIGNATURE }</td> 
                  <td class="center">${a.CREATETIME }</td>
                  <td style="text-align: center;" id = "">
                 	 <button class="btn btn-white btn-sm fa fa-plus"  data-placement="top" title="角色变更" id="${a.USERID }" onclick="changeRole(this.id)" data-toggle="modal" data-target="#myModalSoft"></button>
                  </td> 
                  <td style="text-align: center;" id = ""> 
                  	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="查看该管理员详情" ><a class="userinfo" href="/org.xjtusicd3.portal/showUserInfo.html?u=${a.USERID}"><i class="fa fa-eye"></i></a></button>
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
		          <h4 class="modal-title" id="myModalLabel"> 为该用户分配角色 </h4> 
		         </div> 
		        
		         <div class="modal-body">
		          <table class="table table-striped table-bordered table-hover " id="table"> 
		          <thead> 		          
		           <tr>  
		            <th style="text-align: center;">角色名</th> 
		           </tr>		           
		          </thead> 
		         
		          <tbody id="tbody1">
		         	<input id="userId"  type="text" style="display: none;"></input>
		           
		           <tr class="gradeX" id=""> 
		            <td style="text-align: center;width: 87%" id="">
		            	<input type="radio" name="browser" onclick="check(this.value)" value=""><br />
		            </td> 
		            
		           </tr> 
		          </tbody> 
		          	您为该用户分配的角色是：<input type="text" id="answer" size="20">
		         </table> 		         		        
		         </div>
		         
		         <div class="modal-footer"> 
		           <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button> 
		           <button type="button" class="btn btn-primary" onclick="updateUserRole()"> 提交 </button> 
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
    /* 审核通过 */
    function throughAudit(id) {
    	
    	var userId = document.getElementById(id).id;
    	 
         $.ajax({
             type: "POST",
             url: "/org.xjtusicd3.portal/throughAudit.html",
             data: {
                 "userId":userId                
             },
             dataType: "json",
             success: function(data) {
             	alert("审核通过");
             	window.location.reload();
             }
            
         }) 
        return true;

     }
    
    /* 审核加入黑名单 */
    
    function noAudit(id) {
    	
    	var userId = document.getElementById(id).id;
    	alert(userId);
    	 
        $.ajax({
             type: "POST",
             url: "/org.xjtusicd3.portal/noAudit.html",
             data: {
                 "userId":userId                
             },
             dataType: "json",
             success: function(data) {
             	alert("加入黑名单");
             	window.location.reload();
             }
            
         })  
        return true;

     }
    
    
    /* 查看用户详情 */
	function userInfo(id) {	    	
    	var userId = document.getElementById(id).parentElement.id;
    	alert(userId);
    	 
         $.ajax({
             type: "POST",
             url: "/org.xjtusicd3.portal/showUserInfo.html",
             data: {
                 "userId":userId                
             },
             dataType: "json",
             success: function(data) {
             	alert("查看用户信息");
             	window.location.reload();
             }
            
         }) 
        return true;

     }
    
    
	 //checkbox 全选/取消全选  
    var isCheckAll = false;  
    function swapCheck() {  
        if (isCheckAll) {  
            $("input[type='checkbox']").each(function() {  
                this.checked = false;  
            });  
            isCheckAll = false;  
        } else {  
            $("input[type='checkbox']").each(function() {  
                this.checked = true;  
            });  
            isCheckAll = true;  
        }  
    }  
    
    //批量审核通过
    $("#b1").click(function(){
    	var checkedSubject=document.getElementsByName('input[]');//获取到复选框的名称  
    	var checkedIds="";
 
    	//因为获得的是数组，所以要循环 为每一个checked赋值  
    	  for(var i=0;i<checkedSubject.length;i++){  
    	        if(checkedSubject[i].checked == true){  
    	        	checkedIds += checkedSubject[i].value+",";  
    	        } 
    	    } 
    	
    	 
    	   $.ajax({
              type: "GET",
              url: "/org.xjtusicd3.portal/selectUserPass.html",
              data: {
            	  checkedIds:checkedIds                
              },
              traditional: true,//这里设置为true
              success: function(data) {
              	alert("审核成功");
              	window.location.reload();
              }
             
          })  
    
    });
    
    //批量审核不通过
    $("#b2").click(function(){
    	var checkedSubject=document.getElementsByName('input[]');//获取到复选框的名称  
    	var checkedIds="";
 
    	//因为获得的是数组，所以要循环 为每一个checked赋值  
    	  for(var i=0;i<checkedSubject.length;i++){  
    	        if(checkedSubject[i].checked == true){  
    	        	checkedIds += checkedSubject[i].value+",";  
    	        } 
    	    } 
    	
    	 
    	   $.ajax({
              type: "GET",
              url: "/org.xjtusicd3.portal/selectUserUnPass.html",
              data: {
            	  checkedIds:checkedIds                
              },
              traditional: true,//这里设置为true
              success: function(data) {
              	alert("审核未通过成功");
              	window.location.reload();
              }
             
          })  
    
    });
 
    /* 将软件添加至标准库 */      
    function changeRole(id) {  
		//获取配置软件ID
    	var userId = document.getElementById(id).id;  
    	
        $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/changeRole.html",
            data: {
                "userId":userId
            },
            dataType: "json",
            success: function(data) {
            	var list = data.list; //获取后台json'
            	$("#userId").val(data.userId);
            	
            	if(list.length == 0){
    				$("#tbody1").html("");
            	}else{
            		var permissionHtml = "";
            		
    				for (var i = 0; i < list.length; i++) {
    					 permissionHtml = permissionHtml
 						
    					 +'<tr class="gradeX" id=""><td style="text-align: center;width: 87%" >'
    					 +'<input type="radio" name="browser" onclick="check(this.value)" id="'+list[i].roleId+'" value="'+list[i].roleName+'">'+list[i].roleName+'<br /> </td>  </tr> '
						
 						$("#tbody1")[0].innerHTML = permissionHtml;    
    				}
    				
    				var permissionHtml2 = '您为该用户分配的角色是：<input type="text" id="answer" size="20">';
    				$("#table")[2].innerHTML = permissionHtml2;
            	}	
            	
            }
           
        }) 
    }
    
    function check(browser)
    {
    document.getElementById("answer").value=browser
    }
    
    
    
    /* 提交计算机更改  */ 
    function updateUserRole() {  
    	//获取模态框数据  
    	var userId = $('#userId').val(); 
    	var roleId = $('input[name="browser"]:checked ').attr('id');

        $.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/updateUserRole.html",
            data: {
            	"userId":userId,
            	"roleId":roleId
            },
            dataType: "json",
            success: function(data) {
            	if(data.value == 0){
            	alert("用户名或者角色为空");
            	window.location.reload();
            }else {
            	alert("角色替换成功");
            	window.location.reload();
			}
            }
        }) 	         
    }  
    
    
    
    
    
    </script>  
   </div>
  </div>
 </body>
</html>