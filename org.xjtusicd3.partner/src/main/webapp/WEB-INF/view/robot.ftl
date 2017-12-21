<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util-rb.css" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/detail.css">
	<link rel="stylesheet" href="zhao/chat/css/jq22-rb.css">
	<link rel="stylesheet" href="css/robot.css">
	<link rel="stylesheet" type="text/css" href="zhao/chat/css/chat-rb.css" />
	<link rel="stylesheet" type="text/css" href="zhao/chat/css/waves.min.css">
	<link rel="stylesheet" href="zhao/classify/css/ui.css">
	<link rel="stylesheet" href="zhao/classify/css/style-rb.css">
	<link rel='stylesheet prefetch' href='http://www.jq22.com/jquery/font-awesome.4.6.0.css'>
	<script src="zhao/classify/js/jquery-1.9.1.min.js"></script>
	<script src="zhao/classify/js/jquery.aimmenu.js"></script>
	<script src="zhao/classify/js/knockout.js"></script>
	<script type="text/javascript" src="js/chat.js"></script>
	<script type="text/javascript" src="js/browserEvent.js"></script>
	<script>
	/* 热点问题分类 */
	$(function(){
		var navData=[${string}];
		ko.applyBindings({navData:navData});
	})
	</script>
   <style>
       #colored-button .btn {
           color: #fff;
           width: 110px;
       }
       #colored-button a,
       #colored-button a:hover {
           background: #01BCFF;
       }
   </style>
</head>
<body style="margin: 0px;padding: 0px;height: 100%">
<style>
	.container .wp .macth_xv_categorys .macth_xv_cat_catlist{display:block;}
</style>
	<div class="header" id="head" style="height: 18%;width: 100%">      
        <div class="loginRegistHead" role="banner" style="height: 20%;width: 100%">
            <div class="content clearfix" style="position: inherit;" style="height: 100%;width: 100%">
                <div class="header_top_wrap_left" style="height: 100%;width: 70%">
		            <ul>
		                <li><a class="new_a" href="robot.html" data-pos="categorys_1_1">智能小朵</a></li>
		                <li><a class="new_a" href="faq.html" data-pos="categorys_1_1">知识库</a></li>
		                <li><a class="new_a" href="question.html?c=all&type=all" data-pos="categorys_1_1">问题中心</a></li>
		               <!--  <li><a class="new_a" href="advise.html" data-pos="categorys_1_1">意见建议</a></li> -->
		                <li>
		                    <a class="new_a" href="service.html">关于我们</a>
		                </li>
		            </ul> 
                </div>
                <div class="header_top_wrap_right" style="height: 100%;;width: 30%">
		              <ul>
		              <#if UserName??>
		                <div class="unlogin">
		                    <li class="loginLinkLi"><span class="person_icon"></span></li>
		                    <li class="loginLinkLi" id="${UserId}" hidden=""></li>
		                    <li class="loginLinkLi" id="userNameText">您好：${UserName}</li>
		                    <li class="left_margin my_center loginLinkLi" id="my_center" onmouseover="Util.showPersonCenter()" onmouseout="Util.hidePersonCenter()">个人中心<span class="v_center_arrow"></span>
		                        <div class="my_service_list" style="display: none; height: 116px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
		                            <div class="top_icon"></div>
		                            <ul class="ul_list">
		                                <li><a href="personal.html">个人信息</a></li>
		                                <li><!-- <a id="equipment" href="personal3.html" onclick="getCurrentEquipment()">我的设备</a> -->
		                                
		                                <a id="equipment" href="500.html" >我的设备</a>
		                                </li>
		                                <li><a href="personal2.html">我的主页</a></li>
		                                <li><a href="500.html">消息通知</a></li>
		                            </ul>
		                        </div>
		                    </li>
		                    <li class="left_margin loginLinkLi"><a href="loginout.html" id="headExit">退出</a>
		                    </li>
		                </div>
		             <#else>
				       	<div class="unlogin">
		                    <li class="unloginLinkLi">
		                        <a href="login.html" id="headLogin" class="listen_btn" data-pos="categorys_1_2">登录/注册</a>
		                        </li>
		                    </li>
		                </div>
		             </#if>
		            </ul> 
                </div>
            </div>
        </div>
       	
       	<div class="headContent" style="height: 80%;width: 100%">
    		<div class="headTop clearfix" style="height: 100%;width: 100%">
	        	<a href="" class="logoCon" style="height: 100%;width: 70%">
	            	<img src="images/logo.jpg" class="logo" style="width:13%;height: 70%;margin-left: 20%">
	            	<span>IT运维智能化服务一体化平台</span>
	        	</a>
    		</div>
		</div>
    </div>
  
  
	<div class="mainContent" style="height: 79%;width: 100%" >
		<div class="contentWra clearfix"  style="height: 72%;width: 68%;">
			<div id="login" >
    			<div class="wrapper" >
        			<div class="login">
            			<form action="#" method="post" class="container offset1 loginform">               		
                			<div class="pad">
								<!--效果html开始-->
    							<div class="content">
        							<div class="chatBox">
										<!-- 自动问答左侧区 begin -->
										<div class="chatLeft" style="height: 117%;width: 60%;">
                							<!-- 自动问答显示区 begin -->
                							<div class="chat01">
                    							<div class="chat01_title"></div>
                    							<div class="chat01_content">
        											<ul class="media-list chat-list">
														<br>
														<div id="chat01_content" ></div>
														<br>
													</ul>
							                    </div>
							                </div>
							                <!-- 自动问答显示区 end -->
							                
							                <!-- 自动问答提问区 begin -->
						                	<div class="chat02">
						                    	<div class="chat02_title">

						                    	</div>
							                    <div class="chat02_content">
							                        <textarea id="textarea"></textarea>
							                    </div>
							                    <div class="chat02_bar">
							                        <ul>
							                            <li style="right: 0px; top: -20px;">
							                            	<p id="colored-button" class="text-center">
							        							<a class="btn float-buttons waves-effect waves-button waves-float" onclick="chat()">确 认</a>
							    							</p>
							                            </li>
							                        </ul>
							                    </div>
						                	</div>
						                	 <!-- 自动问答提问区 end -->
            							</div>
            							<!-- 自动问答左侧区 end -->
            							
            							<!-- 自动问答右侧区 begin -->
										<div class="chatRight" style="height: 12%;width: 28%;">
											<div class="container clearfix mt-40">
												<div class="wp navbar">
													<div class="macth_xv_navlist">
														<div class="macth_xv_menu">
															<!--右侧导航 start-->
															<div class="macth_xv_categorys">
																<div class="macth_xv_cat_title">
																	<h2 class="macth_cat_name">
																		<a>热点问题分类<b class="macth_xv_icon_bai"></b></a>
																	</h2>
																</div>
																<div class="macth_xv_cat_catlist ">
																	<ul class="macth-dropdown-menu" data-bind="foreach:navData">
																		<li class="macth_xvitem" data-bind="attr:{'data-submenu-id':$data.id}"  style="height: 6%;width: 100%;">
																			<h3 style="height: 73%;width: 100%;">
																				<span>
																					<i><img src="zhao/classify/images/homeicon.png"></i>
																				</span>
																				<span class="macth_xvh3_a">
																					<a href="javascript:void(0)" data-bind="text:$data.title"></a>
																				</span>
																				<s style="float: right;margin-right: 7%"></s>
																			</h3>
																			<div data-bind="attr:{'id':$data.id}" class="macth_popover" style="width:130%;height:100%;overflow-x: scroll;overflow-y: scroll;" >
																				<div class="macth_popover-content" style="height: 44%;width: 100%;">
																					<ul class="macth_content_ul"  data-bind="foreach:$data.content" style="width: 100%;">
																						<li class="macth_nav_li">
																						<span class="macth_xvnav_li_alist" data-bind="text:$data.title" style="width: 100%;"></span>
																							<ul class="macth_xvnav_li_ul" data-bind="foreach:$data.content" style="width: 100%;">
																								<li><a onclick="chat2()" data-bind="text:$data.faqTitle"></a></li>
																							</ul>
																						</li>
																					</ul>
																				</div>
																			</div>
																		</li>
																	</ul>
																</div>
															</div>
															<!--左侧导航 end-->					
														</div>
													</div>
												</div>
											</div>
            							</div>
            							<!-- 自动问答右侧区 end -->           
        							</div>
    							</div>
								<!--效果html结束-->
                			</div>
            			</form>
        			</div>
    			</div>
			</div>
        </div>
    </div>
   
        
	<script type="text/javascript" src="zhao/chat/js/waves.min.js"></script>
    <script>
    $(function() {
        $('#login #textarea').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
    });
    </script>
    <script type="text/javascript">
        var currentRoute = false;
        $(document).on('ready', function () {
            // Init Waves
            Waves.init();
            Waves.attach('.drag-ripple', 'waves-block', true);
            Waves.attach('#bg-pattern', null, true);
            init();
            $(window).on('hashchange', routing);
            /**
             * Example source code click
             */
            $('#example .top-button').on('click', function () {
                var type = $(this).data('code');
                $('#source-code .box .code').addClass('hide');
                $('#source-code .box #code-' + type).removeClass('hide');
                $('#source-code').removeClass('hide');
                setTimeout(function () {
                    $('#source-code').addClass('show');
                }, 50);
            });
            $('#source-code .top-button').on('click', function () {

                $('#source-code').removeClass('show');

                setTimeout(function () {
                    $('#source-code .box .code').addClass('hide');
                    $('#source-code').addClass('hide');
                }, 500);
            });
        });
    </script>
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script type="text/javascript" src="zhao/lunbo/js/jquery.plugins-min.js"></script>
    <script src="zhao/classify/js/navbar.js"></script>
    <script src="js/view/getInformation.js"  async="async"></script>
	
	
	
</body>
</html>
