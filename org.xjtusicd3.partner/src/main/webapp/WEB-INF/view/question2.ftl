<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-问题中心</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
	<link rel="stylesheet" type="text/css" href="css/detail.css">
	<link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="js/lnv_frontMonitor.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
    <script type="text/javascript" src="js/view/ueditor.js"></script>
	<script type="text/javascript" src="js/browserEvent.js"></script>
    <script type="text/javascript" src="js/view/question2.js"></script>
    <script type="text/javascript">
    $(function(){
    	if(document.URL.indexOf("n=")>0){
    		var i=0;
    		var j=0;
//     		var z=0;
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
	<div class="header" id="head">      
        <!-- 头部开始 -->
		<#include "inc/incTop.ftl">
	    <!-- 头部结束 -->
       	<div class="headContent">
    		<div class="headTop clearfix">
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>IT运维智能化服务一体化平台——问题中心</span>
	        	</a>
    		</div>
		</div>
    </div>
    
    <!-- main content begin -->
	<div class="mainContent">
	<!-- main begin -->
	<div id="main">
	<!-- 主体内容 begin -->
	<div class="contentWra clearfix">
		<!-- 左侧栏 begin -->
		<div class="leftMainWrapper ">
		<!-- 左侧主要区域 begin -->
		<div id="mainL">
			<!-- 左侧主要内容 begin -->
			<div id="detail-wrapper">			
				<!-- 左侧article begin -->
				<article id="question" data-question-id="42314" data-asker-id="294663">
					<!-- 显示分类名 -->
					<div class="tag">
						<ul>
							<li>${classifyName}</li>
						</ul>
					</div>
				
				<!-- 获取问题信息 begin -->
				<#list questionList as questionList>
					<!-- 问题标题 -->
					<div class="title">
						<h2><a>${questionList.TITLE}</a></h2>
					</div>
					
					<!-- 问题描述 -->
					<div class="description">
						<div class="detail">
							<#if questionList.CONTENT?length gt 150>
							<div class="detailP">
								${questionList.CONTENT[0..150]}......
								<span class="readMore">查看更多</span>
							</div>
							<#else>
							<div class="detailP">${questionList.CONTENT}
							</div>
							</#if>
						</div>
						<div class="fullDetail hidden">
							<p>${questionList.CONTENT}</p>
						</div>
					</div>
					
					<!-- 问题描述长度大于150时显示 -->
					<div class="options">
						<ul>
							<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
						</ul>
					</div>
			
					<!-- 判断是否是运维人员 -->
					<#if IsIT=="1">
					<div class="options" id="shareCommunity">
		                <!-- 判断是否是分享 -->
		                <#if IsShare=="0">
		                <ul>
							<li class="special"><a data-fun="toFocus" class="unFocused" onclick="saveShare()"><span class="status">分享</span>  |  <span class="number"> </span></a></li>
						</ul>
		            	<#elseif IsShare=="1">
	            		<ul>
							<li class="special"><a data-fun="toFocus" class="unFocused" onclick="saveShare()"><span class="status">已分享</span>  |  <span class="number">√</span></a></li>
						</ul>
		            	</#if>
					</div>
					</#if>
				</#list>
				<!-- 获取问题信息 end -->
					
					<!-- 获取回答数；时间排序、赞数排序 -->
					<div id="sort">
						<h2>${communityNumber}个回答</h2>
						<select>
							<option value="time">时间排序</option>
							<option value="recommended">赞数排序</option>
						</select>
					</div>
		
					<div class="clearfix"></div>
					
					<!-- 登录用户回复区域 -->
					<div id="answer-edit">
						<!-- 登录用户信息显示 -->
						<#list userList as userList>
							<div id="answerer">
								<img src="${userList.AVATAR}">
								<p>${userList.USERNAME}</p>
							</div>
						</#list>
						
						<input type="text" placeholder="添加您的答案" id="input_answer" onclick="javascript:showeditor();" style="display:block">
						
						<div id="answer-ueditor" class="edui-default" style="width:645px;font-size:14px;display:none">
							<script id="editor" type="text/plain" style="width:650px;height:300px;margin-left:34px;margin-top:20px;"></script>
						</div>
						
						<div class="submitDiv" style="display:none"><button id="answerSubmit" onclick="replyQuestion()">提交</button></div>
					</div>
		
					<!-- 不同用户对问题答案的不同操作 begin -->
					<ul id="searchResult">
					<!-- 判断有无最佳答案 begin -->
					<#if hasBestAnswer??>
						<!-- 有最佳答案_获取最佳答案信息 begin -->
						<#list answerList_best as answerList_best>
						<li id="${answerList_best.answerId}">
							<article class="answerArticle">
								<!-- 最佳答案显示 begin -->
								<#if answerList_best.isBestAnswer=="1">
								<div class="hd line ">
									<div id="act-link-banner-wp" class="grid-r">
										<span class="iknow-qb_home_icons answer-type answer-best grid ">
											<img src="images/best.png"/>
										</span>
										<span class="answer-title h2 grid">最佳答案</span>
									</div>
								</div>
								</#if>
								<!-- 最佳答案显示 end -->
								
								<!-- 最佳答案具体信息显示 begin -->
								<div class="description">
									<div class="answerer">
										<img class="answerImg" src="${answerList_best.userImage}">
										<div class="answer_name">
											<a href="personal2.html?u=${answerList_best.userId}">
												<span class="user_name">${answerList_best.userName}</span>
													&nbsp;&nbsp;<span>${answerList_best.signature}</span>
											</a>
										</div>
										<span class="answer_time">${answerList_best.time}</span>
										<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_best.totalAnswer}个回答，获得${answerList_best.totalLikes}个赞</div>
									</div>
									<div class="fullDetail"><p>${answerList_best.answer}</p></div>
								</div>
								<!-- 最佳答案具体信息显示 begin -->
								
								<!-- 最佳答案可选项显示 begin -->
								<div class="options">
									<ul>
										<li class="special">
											<#if answerList_best.isLike=="0">
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_best.likesNumber}</span></a>
											<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_best.likesNumber}</span></a>
											</#if>
										</li>
										
										<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_best.communityNumber}</span></a></li>
										
										<li>
											<#if answerList_best.isCollection=="0">
											<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
											<#else>
											<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
											</#if>
										</li>
									</ul>
								</div>
								<!-- 最佳答案可选项显示 end -->
							   
							   	<!-- 最佳答案用户评论 begin -->
								<div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">
										<#list userList as userList>
										<div class="comment-Editor">
											<img class="userImg" src="${userList.AVATAR}">
											<div class="replyOther_div" id="">
												<span class="username_span" style="color:#F00"></span>:
												<span class="content_span" style="color:#F00"></span>
											</div>
											<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
											<button class="submitComment" onclick="saveComment()">评论</button>
										</div>
										</#list>
										
										<ul class="commentList">
										<#list answerList_best.replay as replay>
											<li id="${replay.commentId}">
												<img class="userImg" src="${replay.userImage}">
												<div class="commentDetail">
													<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
													<p class="content" onclick="replyOther()">${replay.community}</p>
													<p class="commentTime">${replay.time}</p>
												</div>
											</li>
										</#list>
										</ul>
										
										<#if answerList_best.communityNumber?eval gt 5>
										<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
										</#if>
									</div>
								</div>
								<!-- 最佳答案用户评论 end -->
							</article>
						</li>
						</#list>
						<!-- 有最佳答案_获取最佳答案信息 end -->
						
						<!-- 有最佳答案_获取非最佳答案信息 begin -->		
						<#list answerList_other as answerList_other>
						<li id="${answerList_other.answerId}">
							<article class="answerArticle">
								<!-- 非最佳答案具体信息显示 begin -->
								<div class="description">
									<div class="answerer">
										<img class="answerImg" src="${answerList_other.userImage}">
										<div class="answer_name">
											<a href="personal2.html?u=${answerList_other.userId}">
												<span class="user_name">${answerList_other.userName}</span>
													&nbsp;&nbsp;<span>${answerList_other.signature}</span>
											</a>
										</div>
										<span class="answer_time">${answerList_other.time}</span>
										<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
									</div>
									<div class="fullDetail"><p>${answerList_other.answer}</p></div>
								</div>
								<!-- 非最佳答案具体信息显示 end -->
								
								<!-- 非最佳答案可选项 begin -->
								<div class="options">
									<ul>
										<li class="special">
											<#if answerList_other.isLike=="0">
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											</#if>
										</li>
										<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
										<li>
											<#if answerList_other.isCollection=="0">
											<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
											<#else>
											<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
											</#if>
										</li>
									</ul>
								</div>
								<!-- 非最佳答案可选项 end -->
								
								<!-- 非最佳答案评论信息 begin -->
								<div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">
										<#list userList as userList>
										<div class="comment-Editor">
											<img class="userImg" src="${userList.AVATAR}">
											<div class="replyOther_div" id="">
												<span class="username_span" style="color:#F00"></span>:
												<span class="content_span" style="color:#F00"></span>
											</div>
											<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
											<button class="submitComment" onclick="saveComment()">评论</button>
										</div>
										</#list>
										
										<ul class="commentList">
										<#list answerList_other.replay as replay>
											<li id="${replay.commentId}">
												<img class="userImg" src="${replay.userImage}">
												<div class="commentDetail">
													<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
													<p class="content" onclick="replyOther()">${replay.community}</p>
													<p class="commentTime">${replay.time}</p>
												</div>
											</li>
										</#list>
										</ul>
										
										<#if answerList_other.communityNumber?eval gt 5>
										<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
										</#if>
									</div>
								</div>
								<!-- 非最佳答案评论信息 end -->
							</article>
						</li>
						</#list>
						<!-- 有最佳答案_获取非最佳答案信息 end -->		
						
					<!-- 无最佳答案 begin -->	
					<#else>
						<!-- userid:登录用户ID；_userid:问题提问者ID -->						
						<#if userid==_userid>
							<!-- 登录用户对自己提问的操作 begin-->
							<#list answerList_other as answerList_other>
							<li id="${answerList_other.answerId}">
								<article class="answerArticle">
									<div class="description">
										<div class="answerer">
											<img class="answerImg" src="${answerList_other.userImage}">
											<div class="answer_name">
												<a href="personal2.html?u=${answerList_other.userId}">
													<span class="user_name">${answerList_other.userName}</span>
														&nbsp;&nbsp;<span>${answerList_other.signature}</span>
												</a>
											</div>
											<span class="answer_time">${answerList_other.time}</span>
											<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
										</div>
										<div class="fullDetail"><p>${answerList_other.answer}</p></div>
									</div>
									
									<div class="options">
										<ul>
											<!-- 登录用户对别人回复的操作 begin-->
											<#if userid!=answerList_other.userId>
												<li class="special"><a data-fun="toVote" class="unVoted" onclick="getBestAnswer(event)"><span class="status">设为最佳答案</span></a></li>
											</#if>
											<li class="special">
												<#if answerList_other.isLike=="0">
												<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
												<#else>
												<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
												</#if>
											</li>
											<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
										</ul>
									</div>
									
									<div class="comment" style="display:none">
										<img class="deco" src="images/dia-deco.png" style="left:106px">
										<div class="comment-outer">
											<#list userList as userList>
											<div class="comment-Editor">
												<img class="userImg" src="${userList.AVATAR}">
												<div class="replyOther_div" id="">
													<span class="username_span" style="color:#F00"></span>:
													<span class="content_span" style="color:#F00"></span>
												</div>
												<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
												<button class="submitComment" onclick="saveComment()">评论</button>
											</div>
											</#list>
											
											<ul class="commentList">
											<#list answerList_other.replay as replay>
												<li id="${replay.commentId}">
													<img class="userImg" src="${replay.userImage}">
													<div class="commentDetail">
														<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
														<p class="content" onclick="replyOther()">${replay.community}</p>
														<p class="commentTime">${replay.time}</p>
													</div>
												</li>
											</#list>
											</ul>
											<#if answerList_other.communityNumber?eval gt 5>
											<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
											</#if>
										</div>
									</div>
								</article>
							</li>
							</#list>
							<!-- 登录用户对自己提问的操作 end-->							
						<#else>
							<!-- 登录用户不是问题提问者 begin-->
							<#list answerList_other as answerList_other>
							<li id="${answerList_other.answerId}">
								<article class="answerArticle">
									<div class="description">
										<div class="answerer">
											<img class="answerImg" src="${answerList_other.userImage}">
											<div class="answer_name">
												<a href="personal2.html?u=${answerList_other.userId}">
													<span class="user_name">${answerList_other.userName}</span>
														&nbsp;&nbsp;<span>${answerList_other.signature}</span>
												</a>
											</div>
											<span class="answer_time">${answerList_other.time}</span>
											<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
										</div>
										<div class="fullDetail"><p>${answerList_other.answer}</p></div>
									</div>
									
									<div class="options">
										<ul>
											<li class="special">
												<#if answerList_other.isLike="0">
													<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
												<#else>
													<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
												</#if>
											</li>
											<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
										</ul>
									</div>
								   
								   <div class="comment" style="display:none">
										<img class="deco" src="images/dia-deco.png" style="left:106px">
										<div class="comment-outer">
											<#list userList as userList>
											<div class="comment-Editor">
												<img class="userImg" src="${userList.AVATAR}">
												<div class="replyOther_div" id="">
													<span class="username_span" style="color:#F00"></span>:
													<span class="content_span" style="color:#F00"></span>
												</div>
												<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
												<button class="submitComment" onclick="saveComment()">评论</button>
											</div>
											</#list>
											
											<ul class="commentList">
											<#list answerList_other.replay as replay>
												<li id="${replay.commentId}">
													<img class="userImg" src="${replay.userImage}">
													<div class="commentDetail">
														<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
														<p class="content" onclick="replyOther()">${replay.community}</p>
														<p class="commentTime">${replay.time}</p>
													</div>
												</li>
											</#list>
											</ul>
											<#if answerList_other.communityNumber?eval gt 5>
											<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
											</#if>
										</div>
									</div>
								</article>
							</li>
							</#list>
							<!-- 登录用户不是问题提问者 end-->
						</#if>
					</#if>				
					</ul>
					<!-- 不同用户对问题答案的不同操作 end -->
				</article>
				<!-- 左侧article end -->
			</div>
			<!-- 左侧主要左侧主要内容 end -->
			
			<!-- 点击查看更多 begin -->
			<div id="loadStatus">
				<div id="loading" class="">
					<span onclick="getMoreIndex()">点击查看更多</span>
					<div class="spinner">
 							<div class="bounce1"></div>
 							<div class="bounce2"></div>
 							<div class="bounce3"></div>
					</div>
				</div>
				<div id="loadMore" class="hidden">
					<button>加载更多</button>
				</div>
				<div id="noMore" class="hidden">
					<span>没有更多</span>
				</div>
			</div>
			<!-- 点击查看更多 end -->
		</div>
		<!-- 左侧主要区域 end --> 
		</div>
		<!-- 左侧栏 end -->
            
		<!-- 右侧栏 begin -->
		<div class="rightBarWrapper">
		<!-- 右侧主要区域 begin -->
		<div id="mainR">
			<!-- 右侧微信扫码 begin -->
			<div class="QRCode hidden">
				<div class="QRCode-wrapper">
					<img class="QRCode-img">
				</div>
				
				<div class="QRCode-tip">
					<img class="QRCode-bulb" >
					<div class="QRCode-word">
						<p>扫微信二维码获取</p>
						<p>[问题被回答]提醒</p>
					</div>
				</div>
			</div>
			<!-- 右侧微信扫码 end -->
			
			<!-- 右侧相关问题 begin -->
			<div class="rightWrap">
				<div class="head">
					<img src="images/topic.png">
					<h1>相关问题</h1>
				</div>
				
				<ul id="relatedQuestion">
					<#list similarQuestion as a>
					<li><a data-qid="10650" href="question2.html?q=${a.COMMUNITYQUESTIONID }">${a.TITLE}</a></li>
					</#list>
				</ul>
			</div>
			<!-- 右侧相关问题 end -->
			
			<!-- 右侧问题状态 begin -->
			<div class="rightWrap">
				<div class="head">
					<img src="images/questionStatus.png">
					<h1>问题状态</h1>
				</div>
				<div id="status">
					<p><span class="status">查看</span><span class="number">31</span></p>
					<p><span class="status">修改于</span><span class="number">4 天前</span></p>
				</div>
			</div>
			<!-- 右侧问题状态 end -->
		</div>
		<!-- 右侧主要区域 end -->
		</div>
		<!-- 右侧栏 end -->
	</div>
	<!-- 主体内容 end -->
	</div>
	<!-- main end -->    
	</div>
	<!-- main content end -->
    
    
    
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
	
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">评论成功</h2></div></div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2></div></div>
		<div class="success" id="null" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">内容不能为空</h2></div></div>
		<div id="zhao_hidden" style="display:none">${userName}</div>
</body>
</html>
