<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 烟草公司IT运维管理系统后台</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <div id="wrapper">
    <!--左侧导航开始-->
    <#include "inc/incTop.ftl">
    <!--左侧导航结束-->
   
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
      <div class="row border-bottom">
          <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
              <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                  <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                      <div class="form-group">
                          <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                      </div>
                  </form>
              </div>              
          </nav>
      </div>
     
      <!-- 其余模块都要嵌入其中 -->
      <!-- homePage.html为首页 -->
      <div class="row J_mainContent" id="content-main">
          <iframe id="J_iframe" width="100%" height="100%" src="homePage.html" frameborder="0" data-id="index_v1.html" seamless></iframe>
      </div>
   
    </div>
    <!--右侧部分结束-->

</div>

<script language="JavaScript" type="text/javascript">
    function logout(){
         if (confirm("您确定要退出系统吗？"))
        	 top.location = "login.html";
         	
         return false;
    }
</script>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="js/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="js/index.js"></script>

<!-- 第三方插件 -->
<script src="js/plugins/pace/pace.min.js"></script>
<div style="text-align:center;">
    <p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
</div>
</body>

</html>
