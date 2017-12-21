//获取robot信息
setTimeout("robot_welcome()",1000);

function robot_welcome(){
	 $.ajax({
		 type: "GET",
		 url: "/org.xjtusicd3.partner/getRobotInfo.html",            
		 dataType: "json",
		 success: function(data){
			 	document.getElementById("chat01_content").innerHTML = '<li class="media">'
			 		+'<div style="width:48px;float:left;margin-left: 7px;">'
			 		+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data[0].rOBOTIMAGE+'"></div>'
			 		+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data[0].rOBOTNAME+'</div>'
			 		+'</div>'			 		
			 		+'<div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
			 		+'<div style="float:left;"><span style="">'+data[0].rOBOTWELCOME+'<img src="images/bq.png" class="mCS_img_loaded"><br>➢<span style="color:blue;display:inline">'
			 		+'<a href="javascript:void(0);" onclick="questionSkill()">提问技巧</a></span></span></div><p></p></div></li>';
		      }
		 });
}

//显示时间
function showTime(){
    var myDate = new Date();  
    var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)  
    var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)  
    var date = myDate.getDate();        //获取当前日(1-31)  
    var hours = myDate.getHours();       //获取当前小时数(0-23)  
    var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)  
    var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
    if(hours<10){
    	hours = "0"+hours;
    }
    if(minutes<10){
    	minutes = "0"+minutes;
    }
    if(seconds<10){
    	seconds = "0"+seconds;
    }
    var time = year+"年"+month+"月"+date+"日"+"   "+hours+":"+minutes+":"+seconds;
	return time;
}

//滚动
function scroll(){
	var number = document.getElementById("chat01_content").children.length-2;
	scrollToLocation(number);
}

//滚动到指定位置
function scrollToLocation(number) {
	var mainContainer = $('#chat01_content');
    var scrollToContainer = $('#chat01_content').find('.media:eq('+number+')');//滚动到<div id="thisMainPanel">中类名为son-panel的第六个处
    //动画效果
    $('.chat01_content').animate({
    	scrollTop: scrollToContainer.offset().top - mainContainer.offset().top + mainContainer.scrollTop()
    }, 1500);//1.5秒滑动到指定位置
}

//提问技巧
function questionSkill(){
	questionSkill1();
	setTimeout("scroll()",500);
}

//提问技巧
function questionSkill1(){
	var html = document.getElementById("chat01_content").innerHTML;
	 $.ajax({
		 type: "GET",
		 url: "/org.xjtusicd3.partner/questionSkill.html",            
		 dataType: "json",
		 success: function(data){
			 if(data.value=="0"){
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
				 +'<div style="width:48px;float:right;margin-left: 7px;">'
				 +'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
				 +'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
				 +'</div>'
				 +'<div class="media-body chat-pop2">'
				 +'<span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
				 +'<div style="float:left;"><span style="">'+"查看提问技巧"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'
				 +'<li class="media"><div style="width:48px;float:left;margin-left: 7px;">'
				 +'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
				 +'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div>'
				 +'<div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
				 +'<div style="float:left;"><span style="">'
				 +'<div class="head_msg">小朵为您推荐</div>'
				 +'<div class="content_line">为了更快的帮您解决问题，您可以试着简短描述，如输入:"<b>如何下载驱动、"CPU占用率高"</b>。您也可以直接点击右侧热点区域。<br>现在，让我们愉快的交流吧^_^ </div></span></div><p></p></div></li>';
			 }else{
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
				 +'<div style="width:48px;float:right;margin-left: 7px;">'
				 +'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
				 +'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
				 +'</a>'
				 +'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div></div>'
				 +'<div class="media-body chat-pop2"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
				 +'<div style="float:left;"><span style="">'+"查看提问技巧"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'
				 +'<li class="media"><div style="width:48px;float:left;margin-left: 7px;">'
				 +'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
				 +'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div>'
				 +'<div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
				 +'<div style="float:left;"><span style="">'
				 +'<div class="head_msg">小朵为您推荐</div>'
				 +'<div class="content_line">为了更快的帮您解决问题，您可以试着简短描述，如输入:"<b>如何下载驱动、"CPU占用率高"</b>。您也可以直接点击右侧热点区域。<br>现在，让我们愉快的交流吧^_^ </div></span></div><p></p></div></li>';
			 }
		 }
	 });	
}


//输入框中直接聊天
function chat(){
	chatWithRobot();
	setTimeout("scroll()",500);
}

function chatWithRobot(){
	var comment = document.getElementById("textarea").value;
	var comments = comment.replace(/\s+/g,"");
	var html = document.getElementById("chat01_content").innerHTML;
	var from = "fromChat";
	if(comment.length == 0 || comment.match(/^\s+$/g)){
		document.getElementById("textarea").value="";
	}else{
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/chatWithRobot.html",
			data:{
				"comment":comments,
				"from":from
			},
			dataType:"json",
			success:function(data){
				$("#answerId").attr("value",data.answerId);
				$("#questionId").attr("value",data.questionId);
				if(data.value=="0"){
					self.location='login.html';
				}else if(data.value=="1"){
					
					/*
					 * robotChat为推荐问题答案
					 */
					if(data.robotChat.length==0){
						/*
						 * 当系统推送回来的答案长度为 0 时执行
						 */ 
						
						//textarea中记录用户提问的问题
						document.getElementById("textarea").value="";
						
						/*
						 * robotUser中记录用户信息
						 */
						if(data.robotUser.length==0){
							//未登录用户执行
							document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
							+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;">'
								+'<span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a>'
								+'</span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div>'
							+'<div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">对不起，我没有理解您的意思，麻烦您换个问法试试。<span style="color:blue">'
							+'<a href="javascript:void(0);" onclick="questionSkill()">查看提问技巧</a></span><span style="color:blue">'
							+'<a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>';
						}else{
							//已登录用户执行
							document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
							+'<div style="width:48px;float:right;margin-left: 7px;">'
							+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div></a>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div></div>'
						+'<div class="media-body chat-pop2">'
						+'<span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
						+'<div style="float:left;">'
							+'<span style="">'+comment+'<span style="color:blue">'
							+'<a href="javascript:void(0);" onclick=""></a>'
							+'</span></span>'
						+'</div><p></p></div></li>'
						+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div><div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">对不起，我没有理解您的意思，麻烦您换个问法试试。<span style="color:blue"><a href="javascript:void(0);" onclick="questionSkill()">查看提问技巧</a></span><span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>';
					
						}
					}else{
						/*
						 * 当系统推送回来的答案长度大于 0 时执行
						 */ 
						
						//textarea中记录用户提问的问题
						document.getElementById("textarea").value="";
						
						/*
						 * robotUser中记录用户信息
						 */
						if(data.robotUser.length==0){
							
							/*
							 * 未登录用户
							 * 系统只推送回一条回答
							 */
							if(data.robotChat.length==1){
													
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
									+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
									+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
								+'</div>'
								+'<div class="media-body chat-pop2">'
									+'<span class="pull-right">'
										+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
									+'</span><p></p>'
									+'<div style="float:left;">'
										+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
									+'</div><p></p>'
								+'</div></li>'
								+'<li class="media">'
								+'<div style="width:48px;float:left;margin-left: 7px;">'
								+'<div class="lhead">'
								+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
								+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
								+'</div><div class="media-body chat-pop">'
								
								+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
								+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
								
								+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
								+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
								+'<b>经过我的判断，方案如下：</b>'
								+'</div>'
								+'<div class="content_line">  </div>'
								+'<div class="div_faqVoteText" name="div_faqVoteText">'
								+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
								+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img src="images/zan4_2.png">'
								+'</a>'
								+'<span style="display:none;"></span> '
								+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
									+'<img class="submitFou" src="images/cai4_2.png" >'
								+'</a>'
								+'<span style="display:none;"></span>'
								+'</div>'
								+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
							}else if(eval(1)<eval(data.robotChat.length)&&eval(data.robotChat.length)<=eval(4)){
								/*
								 * 系统推送回1-4条回答
								 */
								var htmlss="";
								for(var i=1;i<data.robotChat.length;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
								+'<span class="pull-right">'
									+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
								+'</span><p></p>'
								+'<div style="float:left;">'
									+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
								+'</div><p></p>'
							+'</div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead">'
							+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
							+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div><div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b>'
							+'</div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText">'
							+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							+'</div>'
							+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';}else{
								
								/*
								 * 系统推送回5条以上回答
								 */
								var htmlss="";
								for(var i=1;i<=3;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
								+'<span class="pull-right">'
									+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
								+'</span><p></p>'
								+'<div style="float:left;">'
									+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
								+'</div><p></p>'
							+'</div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead">'
							+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
							+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div><div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b>'
							+'</div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText">'
							+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							+'</div>'
							+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';}
						
						}else{
						
							/*
							 * 已登录用户
							 * 系统只推送回一条回答
							 */							
							if(data.robotChat.length==1){
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
									+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
										+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
									+'</a>'
									+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
								+'</div>'
								+'<div class="media-body chat-pop2">'
								+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
									+'<abbr class="timeago">'+showTime()+'</abbr> '
								+'</span><p></p>'
								+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
									+'<a href="javascript:void(0);" onclick=""></a><span></span>'
								+'</div><p></p></div></li>'
								+'<li class="media">'
								+'<div style="width:48px;float:left;margin-left: 7px;">'
								+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
								+'</div>'
								+'<div class="media-body chat-pop">'
								
								+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
								+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
								
								+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
								+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
								+'</span><p></p>'
								+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
								+'<b>经过我的判断，方案如下：</b></div>'
								+'<div class="content_line">  </div>'
								+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
								
								
								+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img src="images/zan4_2.png">'
								+'</a>'
								+'<span style="display:none;"></span> '
								+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
									+'<img class="submitFou" src="images/cai4_2.png" >'
								+'</a>'
								+'<span style="display:none;"></span>'
								
								+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
							}else if(eval(1)<eval(data.robotChat.length)&&eval(data.robotChat.length)<=eval(4)){
								/*
								 * 系统推送回1-4条回答
								 */							
								var htmlss="";
								for(var i=1;i<data.robotChat.length;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
									+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
								+'</a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a><span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b></div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
							
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							
						+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';}else{
								/*
								 * 系统推送回5条以上回答
								 */
								var htmlss="";
								for(var i=1;i<=3;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
									+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
								+'</a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a><span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b></div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
							
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
						
						+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';}
						}
					}
				}
			}
		})
	}
}

//点击热点问题分类
function chat2(){
	chatWithRobot2();
	setTimeout("scroll()",500);
}

//robot——FAQ问题
function chatWithRobot2(){
	var _event= browserEvent();
	var comment = _event.innerHTML;
	var comments = comment.replace(/\s+/g,"");
	var html = document.getElementById("chat01_content").innerHTML;
	var from = "fromFaq";
	if(comment.length == 0 || comment.match(/^\s+$/g)){
		document.getElementById("textarea").value="";
	}else{
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/chatWithRobot.html",
			data:{
				"comment":comments,
				"from":from
			},
			dataType:"json",
			success:function(data){
				if(data.value=="0"){
					self.location='login.html';
				}else if(data.value=="1"){
					if(data.robotChat.length==0){
						document.getElementById("textarea").value="";
						if(data.robotUser.length==0){
							document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
							+'<div style="width:48px;float:right;margin-left: 7px;">'
							+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
						+'</div>'
						+'<div class="media-body chat-pop2">'
						+'<span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
						+'<div style="float:left;">'
							+'<span style="">'+comment+'<span style="color:blue">'
							+'<a href="javascript:void(0);" onclick=""></a>'
							+'</span></span>'
						+'</div><p></p></div></li>'
						+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div><div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">对不起，我没有理解您的意思，麻烦您换个问法试试。<span style="color:blue"><a href="javascript:void(0);" onclick="questionSkill()">查看提问技巧</a></span><span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>';
					
						
						}else{
							document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
							+'<div style="width:48px;float:right;margin-left: 7px;">'
							+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div></a>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div></div>'
						
						+'<div class="media-body chat-pop2">'
						+'<span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
						+'<div style="float:left;">'
							+'<span style="">'+comment+'<span style="color:blue">'
							+'<a href="javascript:void(0);" onclick=""></a>'
							+'</span></span>'
						+'</div><p></p></div></li>'
						+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div><div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">对不起，我没有理解您的意思，麻烦您换个问法试试。<span style="color:blue"><a href="javascript:void(0);" onclick="questionSkill()">查看提问技巧</a></span><span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>';
					
						
						}
					}else{
						document.getElementById("textarea").value="";
						if(data.robotUser.length==0){
							if(data.robotChat.length==1){
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
								+'<span class="pull-right">'
									+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
								+'</span><p></p>'
								+'<div style="float:left;">'
									+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
								+'</div><p></p>'
							+'</div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead">'
							+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
							+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div><div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b>'
							+'</div>'
							+'<div class="content_line">  </div>'

							
							+'<div class="div_faqVoteText" name="div_faqVoteText">'
							+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							+'</div>'
							+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
								}else if(eval(1)<eval(data.robotChat.length)&&eval(data.robotChat.length)<=eval(4)){
								var htmlss="";
								for(var i=1;i<data.robotChat.length;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
								+'<span class="pull-right">'
									+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
								+'</span><p></p>'
								+'<div style="float:left;">'
									+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
								+'</div><p></p>'
							+'</div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead">'
							+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
							+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div><div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b>'
							+'</div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText">'
							+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							+'</div>'
							+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
								
								}else{
								var htmlss="";
								for(var i=1;i<=3;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
								+'<span class="pull-right">'
									+'<i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr>'
								+'</span><p></p>'
								+'<div style="float:left;">'
									+'<span style="">'+comment+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span>'
								+'</div><p></p>'
							+'</div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead">'
							+'<img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'">'
							+'</div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div><div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div><span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> </span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b>'
							+'</div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText">'
							+'<div style="display:inline">以上内容是否对您有帮助？<br>  '
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							+'</div>'
							+'<div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
								}
						}else{
							if(data.robotChat.length==1){
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
									+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
								+'</a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a><span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b></div>'
							+'<div class="content_line">  </div>'
							+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
							
							
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							
							+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
							}else if(eval(1)<eval(data.robotChat.length)&&eval(data.robotChat.length)<=eval(4)){
								var htmlss="";
								for(var i=1;i<data.robotChat.length;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
									+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
								+'</a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a><span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b></div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
							
							
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							
							+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
							
							}else{
								var htmlss="";
								for(var i=1;i<=3;i++){
									htmlss = htmlss + '<a href="faq3.html?q='+data.robotChat[i].questionId+'" target="_blank">'+data.robotChat[i].question+'</a><br>';
								}
								document.getElementById("chat01_content").innerHTML = html+'<li class="media">'
								+'<div style="width:48px;float:right;margin-left: 7px;">'
								+'<a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank">'
									+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div>'
								+'</a>'
								+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop2">'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
								+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+comment+'<span style="color:blue">'
								+'<a href="javascript:void(0);" onclick=""></a><span></span>'
							+'</div><p></p></div></li>'
							+'<li class="media">'
							+'<div style="width:48px;float:left;margin-left: 7px;">'
							+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
							+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
							+'</div>'
							+'<div class="media-body chat-pop">'
							
							+'<input type="hidden" id = "answerId'+data.answerId+'" value="'+data.answerId+'"/>'
							+'<input type="hidden" id = "questionId'+data.questionId+'" value = "'+data.questionId+'"/> '
							
							+'<div class="head_msg">'+data.robotInfo[0].rOBOTNAME+'为您推荐</div>'
							+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
							+'<abbr class="timeago">'+showTime()+'</abbr> '
							+'</span><p></p>'
							+'<div style="float:left;"><span style="">'+data.robotChat[0].answer+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
							+'<b>经过我的判断，方案如下：</b></div>'
							+'<div class="content_line">  </div>'
							
							+'<div class="guess">我猜您还关注<a href="#">这些问题</a><div class="guessinfo">'+htmlss+'</div></div>'
							
							+'<div class="div_faqVoteText" name="div_faqVoteText"><div style="display:inline">以上内容是否对您有帮助？<br>  '
							
							
							+'<a id="submitLink" href="javascript:void(0);" onclick="beHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
							+'<img src="images/zan4_2.png">'
							+'</a>'
							+'<span style="display:none;"></span> '
							+'<a id="submitLink" href="javascript:void(0);" onclick="NoHelpful(\''+data.answerId+'\',\''+data.questionId+'\')">'
								+'<img class="submitFou" src="images/cai4_2.png" >'
							+'</a>'
							+'<span style="display:none;"></span>'
							
							+'</div><div style="display:inline;margin-left:50px;"><a href="javascript:void(0);"></a></div></div></span></div><p></p></div></li>';
							
							}
						}
					}
				}
			}
		})
	}
}

//推荐内容有帮助
function beHelpful(answerId, questionId){
	var answerId = document.getElementById("answerId"+answerId).value;
	var questionId = document.getElementById("questionId"+questionId).value;
	var comment = document.getElementById("textarea").value;
	var comments = comment.replace(/\s+/g,"");
	var html = document.getElementById("chat01_content").innerHTML;
	 $.ajax({
		 type: "GET",
		 url: "/org.xjtusicd3.partner/beHelpful.html",            
		 data:{
				"answerId":answerId,
				"questionId":questionId,
				"comment":comments
			},
		 dataType: "json",
		 success: function(data){
			 if(data.value=="0"){
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media"><div style="width:48px;float:right;margin-left: 7px;"><a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div></div><div class="media-body chat-pop2"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">'+"满意"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'
				 +'<li class="media">'
				 	+'<div style="width:48px;float:left;margin-left: 7px;">'
				 		+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
				 		+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
				 	+'</div>'
				 	+'<div class="media-body chat-pop">'
				 		+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
				 			+'<abbr class="timeago">'+showTime()+'</abbr> '
				 		+'</span><p></p>'
				 		+'<div style="float:left;">'
				 			+'<span style="">'
				 				+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
				 					+'<b>经过我的判断，方案如下：</b>'
				 				+'</div>'
				 				+'<div class="content_line">能够帮到您我很开心！如果有需要您可以继续提问哦^_^ </div>'
				 			+'</span>'
				 		+'</div>'
				 	+'</div>'
				 +'</li>';
				 
				 
			 }else if(data.value=="1"){
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media"><div style="width:48px;float:right;margin-left: 7px;"><a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div></a><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div></div><div class="media-body chat-pop2"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">'+"满意"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div><div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style=""><div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none"><b>经过我的判断，方案如下：</b></div><div class="head_msg">小朵为您推荐</div><div class="content_line">能够帮到您我很开心！如果有需要您可以继续提问哦^_^</div></span></div><p></p></div></li>';
			
			 }else {
					alert("满意度重复提交");
			}
			 
		 }
	 });	
}

//用户对问题答案不满意
function NoHelpful(answerId, questionId){
	var answerId = document.getElementById("answerId"+answerId).value;
	var questionId = document.getElementById("questionId"+questionId).value;
	var comment = document.getElementById("textarea").value;
	var comments = comment.replace(/\s+/g,"");
	var html = document.getElementById("chat01_content").innerHTML;
	 $.ajax({
		 type: "GET",
		 url: "/org.xjtusicd3.partner/NoHelpful.html",            
		 data:{
				"answerId":answerId,
				"questionId":questionId,
				"comment":comments
			},
		 dataType: "json",
		 success: function(data){
			 if(data.value=="0"){
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media"><div style="width:48px;float:right;margin-left: 7px;"><a><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="static/image/user.png"></div></a><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">我</div></div><div class="media-body chat-pop2"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">'+"不满意"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'
				 +'<li class="media">'
				 	+'<div style="width:48px;float:left;margin-left: 7px;">'
				 		+'<div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div>'
				 		+'<div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div>'
				 	+'</div>'
				 	+'<div class="media-body chat-pop">'
				 	
				 	+'<input type="hidden" id = "questionId" value = "'+data.questionId+'"/> '
				 	
				 	+'<span class="pull-right"><i class="fa fa-clock-o"></i> '
				 			+'<abbr class="timeago">'+showTime()+'</abbr> '
				 		+'</span><p></p>'
				 		+'<div style="float:left;">'
				 			+'<span style="">'
				 				+'<div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none">'
				 				+'</div>'
				 				+'<div class="content_line">很抱歉没帮上您，小朵感到十分抱歉。可将您的问题<a href="question.html?c=all&type=all">添加到社区中心</a>，让大家帮您解答~' +'</div>'
				 			+'</span>'
				 		+'</div>'
				 	+'</div>'
				 +'</li>';
			 }else if(data.value=="1"){
				 document.getElementById("chat01_content").innerHTML = html+'<li class="media"><div style="width:48px;float:right;margin-left: 7px;"><a href="personal2.html?='+data.robotUser[0].uSERID+'" target="_blank"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotUser[0].aVATAR+'"></div></a><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotUser[0].uSERNAME+'</div></div><div class="media-body chat-pop2"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style="">'+"不满意"+'<span style="color:blue"><a href="javascript:void(0);" onclick=""></a></span></span></div><p></p></div></li>'+'<li class="media"><div style="width:48px;float:left;margin-left: 7px;"><div class="lhead"><img class="media-object" alt="Generic placeholder image" src="'+data.robotInfo[0].rOBOTIMAGE+'"></div><div style="margin-top: 5px;text-align: center;color:#3FA1F3;font-size:12px;font-weight: bold;">'+data.robotInfo[0].rOBOTNAME+'</div></div><div class="media-body chat-pop"><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago">'+showTime()+'</abbr> </span><p></p><div style="float:left;"><span style=""><div style="margin-bottom: 6px;color:#000000;font-size:13px;display:none"><b>经过我的判断，方案如下：</b></div><div class="head_msg">小朵为您推荐</div><div class="content_line">很抱歉没帮上您，小朵感到十分抱歉。可将您的问题<a href="question.html?c=all&type=all">添加到社区中心</a>，让大家帮您解答~</div></span></div><p></p></div></li>';		
			 }else {
				alert("满意度重复提交");
			}
		 }
	 });	
}
