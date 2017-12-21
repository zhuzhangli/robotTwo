<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>编辑FAQ</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/plugins/markdown/bootstrap-markdown.min.css" /> 
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
       
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑FAQ <small> </small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
      
                    <div class="ibox-content">
          
			       <form method="get" class="form-horizontal" action=""> 
			         <#list faqList as a>
			        <div class="form-group" style="width: 80%"> 
			         <div class="col-sm-10"> 			
			        	<input id="questionId" style="visibility: hidden;" value="${a.FAQQUESTIONID}"></input>
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div>
			        
			        
			        <div class="form-group" style="width: 80%"> 
			         <label class="col-sm-2 control-label">标题：</label> 
			         <div class="col-sm-10"> 			
			        	<p id="title"  name="title" class="form-control-static">${a.FAQTITLE }</p>
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div> 
			       
			        <div class="form-group" style="width: 80%"> 
			         <label class="col-sm-2 control-label">关键字：</label> 
			         <div class="col-sm-10"> 
			          <input type="text" class="form-control" id="keywords" placeholder="在输入关键词时请使用半角逗号间隔" style="width:  69%;float: left;"  value="${a.FAQKEYWORDS }"/>
                            <div class="validate_faqadd spa3"></div> 
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div> 
			        
			        <div class="form-group" style="width: 80%"> 
			         <label class="col-sm-2 control-label">分类名：</label> 
			         <div class="col-sm-10"> 			          
			         	
			         	<p id="classifyName"  name="classifyName" class="form-control-static">${a.FAQCLASSIFYNAME }</p>
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div> 
			       
			        <div class="form-group" style="width: 80%"> 
			         <label class="col-sm-2 control-label">摘要说明：</label> 
			         <div class="col-sm-10"> 
			          <textarea class="form-control" id="description" style="width: 69%;height: 60px;float: left;">${a.FAQDESCRIPTION }</textarea>
                            <div class="validate_faqadd spa5"></div> 
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div> 
			        
			        <div class="form-group" style="width: 80%"> 
			         <label class="col-sm-2 control-label">知识正文：</label> 
			         <div class="col-sm-10"> 
			          <div class="ibox float-e-margins"> 
			           <div class="ibox-content"> 
			            <textarea id="editor" name="faqcontent" data-provide="markdown" rows="10" required="" >${a.FAQCONTENT}</textarea> 
			           </div> 
			          </div> 
			         </div> 
			        </div> 
			        <div class="hr-line-dashed"></div> 
			       </#list>
			        <div class="form-group"> 
			         <div class="col-sm-4 col-sm-offset-2"> 
			          <button class="btn btn-primary"  id="sub" data-dialog="somedialog">审核通过</button> 
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
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
        
        
        $("input").blur(function(){
    		$(".validate_faqadd").css("color","#BD362F")

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
    		var kw = /^\S{2,30}$/  
    		var dp = /^\S{2,100}$/
    		if(kw.test($("#keywords").val())&&dp.test($("#description").val())){
    			var questionId = document.getElementById("questionId").value;
            	// alert(questionId);
                var keywords = document.getElementById("keywords").value;
                // alert(keywords);
                var description = document.getElementById("description").value;
                // alert(description);
                var faqcontent = document.getElementById("editor").value;          
                // alert(faqcontent);
                $.ajax({
                    type: "POST",
                    url: "/org.xjtusicd3.portal/updateFaq.html",
                    data: {
                        "questionId":questionId,
                        "keywords": keywords,
                        "description": description,
                        "faqcontent": faqcontent
                    },
                    dataType: "json",
                    success: function(data) {
                    	alert("审核通过成功");
                    	self.location.href = "/org.xjtusicd3.portal/faqPage.html";
                    }
                   
                }) 
                return true;
    		}else{
    			if($("#keywords").val()==""){
    				$(".spa3").text('请您填写关键词')
    			}
 
    			if($("#description").val()==""){
    				$(".spa5").text('请您填写简单描述')
    			}

    			return false;
    		}
    	})

   
         
    </script>

    
    

</body>

</html>
