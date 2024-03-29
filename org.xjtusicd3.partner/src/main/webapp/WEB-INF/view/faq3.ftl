
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <#list faq3Views as a>
    <title>${a.faqTitle}</title>
    </#list>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
        <link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/starScore.css" rel="stylesheet">
    <script type="text/javascript" src="zhao/lunbo/js/jquery.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/starScore.js"></script>
    <script type="text/javascript" src="js/browserEvent.js"></script>
	<script type="text/javascript">
    $(function(){
    	if(document.URL.indexOf("n=")>0){
    		var i=0;
    		var j=0;
    		var z=0;
    		var evt1 = document.createEvent("MouseEvents");
    		var evt2 = document.createEvent("MouseEvents");
    		var evt3 = document.createEvent("MouseEvents");
    		var noticeId = document.URL.split("n=")[1].split("&p=")[0];
    		var parentId = document.URL.split("p=")[1].split("&q=")[0];
    		//模拟点击查看更多评论
    		var commentNumber = ${commentNumber};
    		commentNumber = Math.ceil(commentNumber/5);
    		if(document.getElementById(parentId)==null){//页面没有此评论
	    		for(i=0;i<commentNumber;i++){
				    evt1.initEvent("click", true, true);
	    			document.getElementById("querymorelink").firstChild.dispatchEvent(evt1);
	    			if(document.getElementById(parentId)!=null){
	    				break;
	    			}
	    		}
	    		document.getElementById(parentId).getElementsByClassName("subCommentList")[0].style.display=="block";
	    		if(document.getElementById(noticeId)==null){
	    			for(j=0;j<100;j++){
	   					evt2.initEvent("click", true, true);   
	    				document.getElementById(parentId).getElementsByClassName("commentReplay")[0].dispatchEvent(evt2);
	    				if(document.getElementById(noticeId)!=null){
	    					break;
	    				} 
	    			}
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				}else{
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				} 
    		}else if(document.getElementById(parentId)!=null){//页面有此评论
    			var evt = document.createEvent("MouseEvents");
    			evt.initEvent("click", true, true);
	    		document.getElementById(parentId).getElementsByClassName("commentReplay")[0].dispatchEvent(evt);
				if(document.getElementById(noticeId)==null){
	    			for(z=0;z<100;z++){
	   					evt3.initEvent("click", true, true);   
	    				document.getElementById(parentId).getElementsByClassName("ac2")[0].firstChild.dispatchEvent(evt3);
	    				if(document.getElementById(noticeId)!=null){
	    					break;
	    				} 
	    			}
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				}else{
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				} 		
    		}
    	}else{
    		var parentId = document.URL.split("p=")[1].split("&q=")[0];
    		//模拟点击查看更多评论
    		var commentNumber = ${commentNumber};
    		commentNumber = Math.ceil(commentNumber/5);
    		if(document.getElementById(parentId)==null){//页面没有此评论
    			for(var i1=0;i1<commentNumber;i1++){
    				var evt4 = document.createEvent("MouseEvents");
    				evt4.initEvent("click", true, true); 
	    			document.getElementById("querymorelink").getElementsByTagName("a")[0].dispatchEvent(evt4);
	    			if(document.getElementById(parentId)!=""){
	    				break;
	    			}
	    		}
	    		mScroll(parentId);
		    	function mScroll(id){
			    	$("html,body").stop(true);
			    	$("html,body").animate({
			    		scrollTop: $("#"+id).offset().top
			    	}, 800);
			    }
    		}else{
		    	mScroll(parentId);
		    	function mScroll(id){
			    	$("html,body").stop(true);
			    	$("html,body").animate({
			    		scrollTop: $("#"+id).offset().top
			    	}, 800);
			    }
    		}
    	}
    }); 
    </script>

</head>

<body>
	<!-- 头部开始 -->
	<div class="header" id="head"> 
	<#include "inc/incTop.ftl">
   		<div class="headContent">
			<div class="headTop clearfix">
				<form class="avatar-form" action="faqSearch.html" enctype="multipart/form-data" method="post">
					<div class="queryBox">
						<input type="text" class="text" value="输入关键字" onfocus="if(this.value=='输入关键字')this.value='';" onblur="if(this.value=='')this.value='输入关键字';" id="keyWordText" onkeydown="if (event.keyCode == 13) {queryKnowledgeByKey();}" autocomplete="off" name="queryString">
						<a href="javascript:void(0);" class="queryBtn"></a>
					</div>
				</form>
				<a href="" class="logoCon">
					<img src="images/logo.jpg" class="logo">
					<span>IT运维智能化服务一体化平台——知识库</span>
				</a>
			</div>
		</div>
	</div>	
    <!-- 头部结束 -->
    
    <!-- 主体开始 -->
	<#list faq3Views as faq3Views>
    <div class="mainContent">
		<div class="contentWra clearfix">
            <!-- 主体左半部分开始 -->
			<div class="leftMainWrapper ">
                <!-- 所有分类开始 -->
                <div class="nowPositionBox clearfix">
                    <a href="/org.xjtusicd3.partner/faq.html"><span class="allType">所有分类</span></a>
                    <span class="arrow">&gt;</span>
                    <#list classify as classify>
                    <a href="/org.xjtusicd3.partner/faq1.html?p=${classify.FAQCLASSIFYID}" id="firstNavStep"><span id="fristProductionName">${classify.FAQCLASSIFYNAME}</span></a>
                    </#list>
                    <span class="arrow">&gt;</span>
                    <#list classify2 as classify2>
                    <a href="/org.xjtusicd3.partner/faq2.html?c=${classify2.FAQCLASSIFYID}" id="secondNavStep"><span class="secondStep" id="subProductionName">${classify2.FAQCLASSIFYNAME}</span></a>
                    </#list>
                    <span class="arrow twoFlag">&gt;</span>
                    <span class="titleStep">${faq3Views.faqTitle}</span>
                </div>
                <!-- 所有分类结束 -->
               
                <!-- 知识详情开始 -->
				<div class="knowledgeDetail">
                    <div id="detailTplWrapper">
                     	<span class="faqId" style="display: none;">${faq3Views.questionId}</span>
                        <span class="title">${faq3Views.faqTitle}</span>
                        <span class=""></span>
                        <p class="createInfo clearfix">
                        <#list faq3Views.uList as ulist>
                            <img src="${ulist.userImage}">
                            <span class="faqUserId" style="display: none;">${ulist.userId}</span>
                            <span class="username">${ulist.userName}</span>
                        </#list>
                        </p>
                        <#list faq3Views.faqAnswers as flist>
                        <div class="content">${flist.faqContent}</div>
                        </#list>
                        <input type="hidden" id="version_id" value="2">
					</div>
                    
                    <div class="qcodeWra ar hidden">
                        <img src="/front/images/qcode.jpg" width="130" height="130">
                    </div>
                    
                    <#if userName ??>
					<div class="clearfix">
						<#if scoreSize gt 0>
						<div class="shareBox_hidden" style="display:block"></div>
	                    <#else>
						<div class="shareBox_hidden" style="display:none"></div>
						</#if>
						<div class="shareBox">
							<span class="share">收藏：</span>
							<#if collection??>
							<a href="javascript:void(0);" id="favoriteHeart" class="share redheart" onclick="favorite()"></a>
	                        <#else>
							<a href="javascript:void(0);" id="favoriteHeart" class="share heart" onclick="favorite()"></a>
							</#if>
	                        <#if IsIT=="1">
								<span class="share">|</span>
								<span class="share">推荐：</span>
								<#if IsShare=="0">
								<div class="social-share fl share-component">
									<a class="fa fa-share-alt" style="font-size:21px;margin-top: 9px;color:#9c9c9c" onclick="saveShare()"></a>
								</div>
								<#elseif IsShare=="1">
								<div class="social-share fl share-component">
									<a class="fa fa-share-alt" style="font-size:21px;margin-top: 9px;color:red" onclick="saveShare()"></a>
								</div>
								</#if>
							</#if>
						</div>
						<#if scoreSize gt 0>
							<#list scoreList as scoreList>
								<div class="atar_Show2">
									<p tip="${scoreList.SCORE}"></p>
								</div>
							</#list>
						<#else>
								<div id="startone"  class="block clearfix" >
							        <div class="star_score" onclick="score()"></div>
							        <p style="float:left;">您的评分：<span class="fenshu"></span> 分</p>
							        <div class="attitude"></div>
							    </div>
						</#if>
					</div>
                    </#if>
                    <div>知识库提供的技术方案或与您产品的实际情况有所差异，您需在完整阅读方案并知晓其提示风险的情况下谨慎操作，避免造成任何损失。</div>
				</div>
                <!-- 知识详情结束 -->
                
                <!-- 发表评论开始 -->
                <div class="comment">
                    <h3>发表评论</h3>
                    <textarea class="textarea" id="content" placeholder="点击此处编辑评论" style="display:block" onclick="showeditor()"></textarea>
                    <div id="answer-ueditor" class="edui-default" style="width:645px;font-size:14px;display:none;margin-left: 32px;">
						<script id="editor" name="content" type="text/plain"></script>
					</div>
                    <div class="clearfix commentScoreBtn"  style="display:none">
                        <input type="button" value="发表评论" class="publishCommentBtn" onclick="comment()">
                    </div>
                    <h3>文章评论</h3>
                    <#list comment as comment>
                    <ul class="commentList" id="${comment.commentId}" >
                    	<li class="commentLiContent">
                    		<#list comment.userViews as user>
                    		<div class="userContent clearfix">
                    			<span class="userPic"><img src="${user.userImage}"></span>
                    			<span class="username">${user.userName}</span><span class="line">|</span><span class="time">${comment.commentTime}</span>
                    		</div>
                    		<div class="clearfix content" >
	                    		<a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复(${comment.commentNumber})</a>
	                    		<p class="text" >${comment.commentContent}</p>
                    		</div>
                    		<ul class="subCommentList" style="display:none">
                    			<#list comment.replyViews as reply>
                    			<li id="${reply.commentId}" class="_commentlist" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)">
                    				<div class="userContent clearfix" id="${reply.toUserName}">
                    					<#if reply.toUserName ??>
                    						<span class="username">
                    							<span class="zhao">${reply.userName}</span> 回复：${reply.toUserName}
	                    					</span>
                    					<#else>
	                    					<span class="username">
	                    						<span class="zhao">${reply.userName}</span> 评论
	                    					</span>
                    					</#if>
                    					<span class="line">|</span><span class="time">${reply.time}</span>
                    				</div>
                    				<#if userName ??>
                    				<div class="clearfix content" onclick="replyOther()" style="cursor:pointer">
                    					<a href="javascript:void(0);" class="commentReplay" onclick="deleteComment()" style="display:none">
                    						<#if reply.userName==userName>
                    						<i class="fa fa-trash-o"></i>
                    						</#if>
                    					</a>
                    					<p class="text">${reply.comment}</p>
                    				</div>
                    				<#else>
                    				<div class="clearfix content">
                    					<a  class="commentReplay"  style="display:none"></a>
                    					<p class="text">${reply.comment}</p>
                    				</div>
                    				</#if>
                    			</li>
                    			</#list>
                    			<#if comment.commentNumber gt 5>
                    			<p class="ac2" id="querymorelink2"><a href="javascript:void(0);" onclick="querymorereply()">更多回复</a></p>
                    			</#if>
                    		</ul>
                    		</#list>
                    		<div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00" ></span>:<span class="content_span" style="color:#F00"></span></div>
                    		<textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea>
                    		<p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p>
                    	</li>
                    </ul>
                    </#list>
                    <#if commentNumber gt 5>
                    <p class="ac" id="querymorelink" class="display:block"><a href="javascript:void(0);"  onclick="querymorecomment()">查看更多...</a></p>
                    </#if>                  
                </div>
                <!-- 发表评论结束 -->
            </div>
			<!-- 主体左半部分结束 -->
            
            <!-- 主体右半部分开始 -->
            <#include "/inc/faq3_incRight.ftl">
            <!-- 主体右半部分结束 -->
		</div>
	</div>
	</#list>

    <!-- 底部开始 -->
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
    <script type="text/javascript" src="new/front/js/util.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/view/ueditor.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/view/faq3.js"></script>

	<script>
	     scoreFun($("#startone"))
	     scoreFun($("#starttwo"),{
	           fen_d:22,//每一个a的宽度
	           ScoreGrade:5//a的个数5
	         });
	     //显示分数
	      $(".countList li p").each(function(index, element) {
	        var num=$(this).attr("tip");
	        var www=num*2*16;//
	        $(this).css("width",www);
	        $(this).parent(".atar_Show").siblings("span").text(num+"分");
	    });
	    $(".atar_Show2 p").each(function(index, element) {
	        var num=$(this).attr("tip");
	        var www=num*2*16;//
	        $(this).css("width",www);
	        $(this).parent(".atar_Show2").siblings("span").text(num+"分");
	    });
    </script> 
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none">
			<div style="margin-top:30px; margin-bottom:30px;">
				<img src="images/true.png" style="width:20px;height:20px;margin-right:10px;">
				<h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">评论成功</h2>
			</div>
		</div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none">
			<div style="margin-top:30px; margin-bottom:30px;">
				<img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;">
				<h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2>
			</div>
		</div>
		<div class="success" id="null" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none">
			<div style="margin-top:30px; margin-bottom:30px;">
				<img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;">
				<h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">内容不能为空</h2>
			</div>
		</div>
		<div id="zhao_hidden" style="display:none">${userName}</div>
</body>
</html>
