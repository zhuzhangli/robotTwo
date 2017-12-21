<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>知识库管理</title> 
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
             <li class="active"><a data-toggle="tab" href="#tab-31" aria-expanded="true">待审核FAQ【${pendCount }】</a></li> 
             <li class=""><a data-toggle="tab" href="#tab-32" aria-expanded="false">所有FAQ</a></li> 
            </ul> 
            <div class="tab-content"> 
             
             <!-- 用户计算机信息 --> 
             <div id="tab-31" class="tab-pane active"> 
              <div class="ibox-title"> 
               <h5>待审核FAQ</h5> 
               <div class="ibox-tools"> 
	               <!-- 模态框弹出权限 -->
	               <button class="btn btn-primary " onclick="window.location='faqAdd.html'">新增FAQ</button> 
               </div> 
              </div> 
              <div class="ibox-content"> 
	            
		       
		       <table class="table table-striped table-bordered table-hover dataTables-example"> 
		        <thead> 
                 <tr> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">FAQ名称</th> 
                  <th style="text-align: center;">FAQ分类名</th>
                  <th style="text-align: center;">FAQ答案</th> 
                  <th style="text-align: center;">提交用户</th>
                  <th style="text-align: center;">是否通过审核</th>
                  <th style="text-align: center;">操作</th>                 
                  <th style="text-align: center;">更多详情</th> 
  
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list faqPendingAudit as a>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 3%">${a_index+1 }</td>
                  <td style="width: 12%">${a.FAQTITLE }</td> 
                  <td style="width: 23%">${a.FAQCLASSIFYNAME}</td>
                  <td style="text-align: center;width: 22%">${a.FAQCONTENT}</td> 
                  <td style="text-align: center;width: 5%">${a.USERNAME }</td>
                  <td style="text-align: center;width: 6%"><a href="/org.xjtusicd3.portal/faqEdit.html?q=${a.FAQQUESTIONID}">审核通过</a></td>
                  <td style="text-align: center;width: 8%"><a onclick="deleteFAQ()">删除</a></td>
                  <td style="text-align: center;width: 5%" >
                  	<button class="btn btn-white btn-sm" type="button" id="computer_${a.EQUIPMENTID }" title="更多详情" onclick="lookMoreComputerInfo(this.id)" data-toggle="modal" data-target="#myModalComputer"><i class="fa fa-eye"></i></button>
                  </td> 
                  
                 </tr>
                 </#list> 
                 
                </tbody> 
		       </table>  
              </div> 
             </div> 
             
             
             <!--FAQ信息列表 --> 
             <div id="tab-32" class="tab-pane"> 
               <div class="ibox-title"> 
               <h5>FAQ信息列表</h5> 
              </div> 
              <div class="ibox-content"> 
		       <table class="table table-striped table-bordered table-hover dataTables-example"> 
		        <thead> 
                 <tr> 
                  <th style="text-align: center;">序号</th> 
                  <th style="text-align: center;">FAQ名称</th> 
                  <th style="text-align: center;">FAQ分类名</th>
                  <th style="text-align: center;">提交用户</th>
                  <th style="text-align: center;">删除</th>
                  <th style="text-align: center;">详情</th>
                 </tr> 
                </thead> 
                
                <tbody id = "option1">    
                 <#list faqAudited as b>
                 <tr class="gradeX"> 
                  <td style="text-align: center;width: 4%">${b_index+1}</td>
                  <td style="width: 36%">${b.FAQTITLE}</td> 
                  <td style="width: 8%">${b.FAQCLASSIFYNAME}</td>

                  <td style="text-align: center;width: 9%">${b.USERNAME}</td>
                  <td style="text-align: center;width: 4%"><button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="删除" id="delete_${b.FAQQUESTIONID}" onclick="deleteFAQ(this.id)"><i class="fa fa-trash-o"></i> </button></td>
                  <td style="text-align: center;width: 4%">
                  	<button class="btn btn-white btn-sm" type="button" id="${b.FAQQUESTIONID}" title="更多详情" onclick="lookMoreFaqInfo(this.id)" data-toggle="modal" data-target="#myModal"><i class="fa fa-eye"></i></button>
                  </td>
                 </tr>
                 </#list> 
                 
                </tbody> 
		       </table>  
              </div>
             </div> 
             
             <!-- 查看更多faq信息_模态框（Modal） --> 
		      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 100%"> 
		       <div class="modal-dialog" style="width: 60%"> 
		        <div class="modal-content" style="vertical-align: middle;margin-top: 100px;width: 100%"> 
		         <div class="modal-header"> 
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> 
		          <h4 class="modal-title" id="myModalLabel"> 更多faq信息 </h4> 
		         </div> 
		         
		         <form class="form-horizontal m-t" method="post" action="add" > 
		          <div class="modal-body"> 
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label" style="width: 15%">Faq名称：</label> 
		            <div class="col-sm-8"> 
		             <input id="faqTitle"  type="text" class="form-control" required="" aria-required="true"  style="width: 85%" readonly="readonly"/> 
		            </div> 
		           </div> 

		          
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label" style="width: 15%">Faq分类名：</label> 
		            <div class="col-sm-8"> 
		             <input id="faqClassifyName" type="text" class="form-control" required="" aria-required="true"  style="width: 85%" readonly="readonly"/> 
		            </div> 
		           </div>
		           
		           <div class="form-group"> 
		            <label class="col-sm-3 control-label" style="width: 15%">FAQ答案：</label> 
		            <div class="col-sm-8" id="tbody1" style="overflow-y:auto;height: 580px;width: 85%"> 
		             
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
    /* 查看更多faq信息 */
    function lookMoreFaqInfo(id) {
    	//获取权限ID
    	var faqQuestionId = document.getElementById(id).id;
    	//alert(permissionId);
    	
    	$.ajax({
            type: "POST",
            url: "/org.xjtusicd3.portal/lookMoreFaqInfo.html",
            data: {
                "faqQuestionId":faqQuestionId
            },
            dataType: "json",
            success: function(data) {
            	
            	var moreFaqInfo = data.moreFaqInfo; //获取后台json'

            	
            	$("#faqTitle").val(moreFaqInfo.fAQTITLE);
            	$("#faqClassifyName").val(moreFaqInfo.fAQCLASSIFYNAME);
            	//$("#faqContent").val(moreFaqInfo.fAQCONTENT);
            	
            	if(moreFaqInfo.length == 0){
    				$("#tbody1").html("");
            	}else{
            		var permissionHtml = "";
    				
    				permissionHtml = permissionHtml+moreFaqInfo.fAQCONTENT;
 						
 						
 						
 					$("#tbody1")[0].innerHTML = permissionHtml;    
    				
    				
            	}
            	
            	
            	

            }           
        }) 	   	    	
     }
    
   
    </script>
    
   
 
    
   <script>
  
    
    /* 删除配置 */
	function deleteFAQ(id){  
        //获取模态框数据  
        var faqQuestionId = document.getElementById(id).id; 	       

        if (confirm("确认删除？")) {
			$.ajax({
				type : "post",
				url : "/org.xjtusicd3.portal/deleteFAQ.html",
				data : {
					"faqQuestionId" : faqQuestionId
				},
				dataType : "json",
				success : function(data) {
					alert("删除成功");
					window.location.reload(); 
				}
			});
		} else {
			return;
		} 	        
    }  
   </script> 
   

   </div> 
  </div>  
 </body>
</html>