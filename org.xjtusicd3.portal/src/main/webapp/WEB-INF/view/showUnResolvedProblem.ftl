<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>问题信息展示</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
 <#list unResolvedProblemDetail as a>
    <div class="wrapper wrapper-content animated fadeInRight">
         
       
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
               
                    <div class="ibox-title">
                        <h5>问题信息</h5>
                         
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">问题标题</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">${a.problemTitle}</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问内容</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">${a.problemContent}</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                                                  
                            <div class="form-group">
                                <label class="col-sm-2 control-label">问题类别</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">${a.problemClassifyName}</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问用户</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">${a.problemUserName}</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提问时间</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">${a.problemTime}</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                        
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" ><a href="/org.xjtusicd3.portal/problemPage.html">返回</a></button>                                    
                                </div>
                            </div>
                        </form>
                    </div>
					
                </div>
            </div>
        </div>
    </div>
 
</#list>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>

    
     

</body>

</html>
