<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
  <title>增加用户信息</title> 
  <meta name="keywords" content="" /> 
  <meta name="description" content="" /> 
  <link rel="shortcut icon" href="favicon.ico" /> 
  <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet" /> 
  <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet" /> 
  <link href="css/plugins/iCheck/custom.css" rel="stylesheet" /> 
  <link href="css/animate.css" rel="stylesheet" /> 
  <link href="css/style.css?v=4.1.0" rel="stylesheet" /> 
  <link rel="stylesheet" type="text/css" href="css/plugins/markdown/bootstrap-markdown.min.css" /> 
  <script type="text/javascript" charset ="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="js/ueditor.js"></script>
  <link rel="stylesheet" type="text/css" href="js/normalize.css" />
 
 
 </head> 
 <body class="gray-bg"> 
  <div class="wrapper wrapper-content animated fadeInRight"> 
   <div class="row"> 
    <div class="col-sm-12"> 
     <div class="ibox float-e-margins"> 
      <div class="ibox-title"> 
       <h5>新增FAQ <small> </small></h5> 
       
       <div class="ibox-tools"> 
        <a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> 
        <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#"> <i class="fa fa-wrench"></i> </a> 
        <ul class="dropdown-menu dropdown-user"> 
         <li><a href="form_basic.html#">选项1</a> </li> 
         <li><a href="form_basic.html#">选项2</a> </li> 
        </ul> 
        <a class="close-link"> <i class="fa fa-times"></i> </a> 
       </div> 
     
      </div> 
      
      <div class="ibox-content"> 
       <form method="get" class="form-horizontal" action=""> 
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label"><em>*</em>标题：</label> 
         <div class="col-sm-10"> 
          <!-- <input id="title"  name="title" minlength="2" type="text" class="form-control" required="" aria-required="true" placeholder="请输入知识标题" /> --> 
          	<input type="text" class="form-control" id="title" placeholder="请输入知识标题" style="width:  69%;float: left;">
            <div class="validate_faqadd spa1"></div>
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">关键字：</label> 
         <div class="col-sm-10"> 
          <!-- <input type="text" minlength="1" class="form-control" id="keywords"  name="keywords" required="" aria-required="true" placeholder="在输入关键词时请使用半角逗号间隔" />  -->
         <input type="text" class="form-control" id="keywords" placeholder="在输入关键词时请使用半角逗号间隔" style="width:  69%;float: left;">
                            <div class="validate_faqadd spa3"></div>
         
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label">分类：</label> 
         <div class="col-sm-10"> 
          <!--获取一级分类  --> 
          <select class="select" id="specialCategoryId" onchange="selectSecondChild()" style="height: 35px;width: 180px"  name="specialCategoryId"></select> 
          <!--获取二级分类  --> 
           <select class="select" id="subspecialCategoryId" name="subspecialCategoryId" style="height: 35px;width: 180px" ></select> 
  			<div class="validate_faqadd spa4" ></div>
         </div> 
        </div> 
  
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group" style="width: 80%"> 
         <label class="col-sm-2 control-label"><em>*</em>摘要说明：</label> 
         <div class="col-sm-10"> 
          <!-- <textarea id="description" name="description" class="form-control" required="" aria-required="true"></textarea>  -->
          <textarea class="form-control" id="description" style="width: 69%;height: 60px;float: left;"></textarea>
                            <div class="validate_faqadd spa5"></div> 
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
        
        <div class="form-group" style="width: 80%;border: none;"> 
         <label class="col-sm-2 control-label"><em>*</em>知识正文：</label> 
         <div class="col-sm-10"> 
          <div class="ibox float-e-margins" style="width: 80%;float: left;border: none;"> 
           <div class="ibox-content" style="width:100%;height:100%;"> 
            <script id="editor" type="text/plain" style="width:100%;height:100%;"></script>
           </div> 
          </div>
          <div class="validate_faqadd spa6"></div>  
         </div> 
        </div> 
        <div class="hr-line-dashed"></div> 
       
        <div class="form-group"> 
         <div class="col-sm-4 col-sm-offset-2"> 
          <button class="btn btn-primary" type="submit" id="sub" data-dialog="somedialog">保存内容</button> 
          <button class="btn btn-white" type="submit"><a href="faqPage.html" >取消</a></button> 
         </div> 
        </div> 
       
       </form> 
      </div> 
     </div> 
    </div> 
   </div> 
  </div> 

    

  <!-- 全局js --> 
  <script src="js/jquery.min.js?v=2.1.4"></script> 
  <script src="js/bootstrap.min.js?v=3.3.6"></script> 
  <!-- 自定义js --> 
  <script src="js/content.js?v=1.0.0"></script> 
  <!-- simditor --> 
  <script type="text/javascript" src="js/plugins/markdown/markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/to-markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/bootstrap-markdown.js"></script> 
  <script type="text/javascript" src="js/plugins/markdown/bootstrap-markdown.zh.js"></script> 
  <!-- iCheck --> 
  <script src="js/plugins/iCheck/icheck.min.js"></script> 
  <script>
    /* 获取一级分类名 */
    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "/org.xjtusicd3.portal/getFirstLevel.html",
            dataType: "json",
            success: function(data) {
                for (var i in data) {
                    document.getElementById("specialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));
                }
            }
        });
    })

    /* 获取一级分类名对应的二级分类名 */
    function selectSecondChild() {
        var element = document.getElementById("specialCategoryId");
        var classifyId = element.options[element.selectedIndex].value;
        $.ajax({
            type: "GET",
            url: "/org.xjtusicd3.portal/getSecondLevel.html" + "?" + "classifyId=" + classifyId,
            dataType: "json",
            success: function(data) {
                document.getElementById("subspecialCategoryId").options.length = 0;
                for (var i in data) {
                    document.getElementById("subspecialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));
                }
            }
        });
    }
    

    /* $("#sub").click(function() {
       var title = document.getElementById("title").value;
       var keywords = document.getElementById("keywords").value;
       var subspecialCategoryId = document.getElementById("subspecialCategoryId").value;
       var description = document.getElementById("description").value;
       var faqcontent = document.getElementById("editor").value;
       $.ajax({
           type: "POST",
           url: "/org.xjtusicd3.portal/saveFAQ.html",
           data: {
               "title": title,
               "keywords": keywords,
               "subspecialCategoryId": subspecialCategoryId,
               "description": description,
               "faqcontent": faqcontent
           },
           dataType: "json",
           success: function(data) {
               if (data.value == "0") {
                   self.location = 'login.html';
               } else if (data.value == "1") { (function() {
                       var dlgtrigger = document.querySelector('[data-dialog]'),
                       somedialog = document.getElementById(dlgtrigger.getAttribute('data-dialog')),
                       dlg = new DialogFx(somedialog);
                       dlgtrigger.addEventListener('click', dlg.toggle.bind(dlg));
                   })();
                   document.getElementById('lasturl').innerHTML = data.url;
               } else { (function() {
                       var dlgtrigger = document.querySelector('[data-dialog]'),
                       somedialog = document.getElementById(dlgtrigger.getAttribute('data-dialog')),
                       dlg = new DialogFx(somedialog2);
                       dlgtrigger.addEventListener('click', dlg.toggle.bind(dlg));
                   })();
                   document.getElementById('lasturl').innerHTML = data.url;
               }
           }
       }) 
       return true;

    })
    
    
    
    
    function windowclose(){
		var url = document.getElementById('lasturl').innerHTML;
		self.location=url;
	}
     */
    
     window.onload=function(){
    		$("#title").focus()
    	};
     
     
    	$("input").blur(function(){
    		$(".validate_faqadd").css("color","#BD362F")
    		if($(this).is("#title")){				//faq标题判断
    			var na = /^\S{2,44}$/
    			if($("#title").val()!=""){
    				if(!(na.test($("#title").val()))){
    					$(".spa1").text("请输入2-44个字符");
    					$(this).css("border","1px solid #BD362F")
    					return false;
    				}else if(na){
    					$(".spa1").text("");
    					return true;
    				}
    			}else{
    				$(".spa1").text("");
    			}
    		}
    		if($(this).is("#keywords")){            //关键词判断
    			var kw= /^\S{2,30}$/
    			if($("#userkwone").val()!=""){
    			if(!(kw.test($("#keywords").val()))){
    				$(".spa3").text("请输入2-30个字符");
    				$(this).css("border","1px solid #BD362F")
    				return false;
    			}else if(kw){
    				$(".spa3").text("");
    				return true;
    			}
    			}else{
    				$(".spa3").text("");
    			}
    		}
    	})

    	$("textarea").blur(function(){
    		$(".validate_faqadd").css("color","#BD362F")
    		if($(this).is("#description")){				//简介的判断
    			var dp = /^\S{2,100}$/
    			if($("#description").val()!=""){
    				if(!(dp.test($("#description").val()))){
    					$(".spa5").text("请输入2-100个字符");
    					$(this).css("border","1px solid #BD362F")
    					return false;
    				}else if(dp){
    					$(".spa5").text("");
    					return true;
    				}
    			}else{
    				$(".spa5").text("");
    			}
    		}
 
    	})

    	$("input").focus(function(){
    		if($(this).is("#title")){
    			$(".spa1").text("不超过44个字符").css("color","#aaa")
    			$(this).css("border","1px solid #aaa")
    		}
    		if($(this).is("#keywords")){
    			$(".spa3").text("输入关键词用逗号隔开").css("color","#aaa")
    			$(this).css("border","1px solid #aaa")
    		}
    	})

    	$("textarea").focus(function(){
    		if($(this).is("#description")){
    			$(".spa5").text("不超过100个字符").css("color","#aaa")
    			$(this).css("border","1px solid #aaa")
    		}
    		
    	})

    	$("#sub").click(function(){
    		$(".spa2").text("");
    		$(".spa4").text("");
    		var na = /^\S{2,44}$/   
    		var kw = /^\S{2,30}$/  
    		var dp = /^\S{2,100}$/
    		if(na.test($("#title").val())&&kw.test($("#keywords").val())&&$("#subspecialCategoryId").val()&&dp.test($("#description").val())&&(UE.getEditor('editor').getContent())!=null){
    				var title = document.getElementById("title").value;
    				var keywords = document.getElementById("keywords").value;
    				var subspecialCategoryId = document.getElementById("subspecialCategoryId").value;
    				var description = document.getElementById("description").value;
    				var faqcontent = UE.getEditor('editor').getContent();
    				$.ajax({
    					type:"POST",
    					url:"/org.xjtusicd3.portal/saveFAQ.html",
    					data:{
    						"title":title,
    						"keywords":keywords,
    						"subspecialCategoryId":subspecialCategoryId,
    						"description":description,
    
    						"faqcontent":faqcontent
    					},
    					dataType:"json",
    					success:function(data){
    						if(data.value=="0"){
    							self.location='login.html';
    						}else if(data.value=="1"){
    							alert("添加成功");
    							window.location.reload();
    						}else{
    							alert("重复提交");
    							window.location.reload();
    						}
    					}
    				})
    			return true;
    		}else{
    			if($("#title").val()==""){
    				$(".spa1").text('请您填写标题')
    			}
    			if($('input:radio[name="resource"]:checked').val()==null){
    				$(".spa2").text('请您选择类型')
    			}
    			if($("#keywords").val()==""){
    				$(".spa3").text('请您填写关键词')
    			}
    			if($("#subspecialCategoryId").val()==null){
    				$(".spa4").text('请您选择知识分类')
    			} 
    			if($("#description").val()==""){
    				$(".spa5").text('请您填写简单描述')
    			}
    			if((UE.getEditor('editor').getContent())==""){
    				$(".spa6").text('请您填写知识正文')
    			}
    			return false;
    		}
    	})

    
    
    </script>   
 </body>
</html>