<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>首页</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

	<!-- echarts JS -->
	<script src="media/js/echarts.js"></script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-4">
                    <div class="row row-sm text-center">
                        <div class="col-xs-6">
                            <div class="panel padder-v item">
                                <div class="h1 text-info font-thin h1">
                                <a href="/org.xjtusicd3.portal/eventPage.html">
                                ${ eventUnresolvedCounts}
                                </a>
                                </div>
                                <span class="text-muted text-xs">待处理事件</span>
                                
                                
                                
                                <div class="top text-right w-full">
                                    <i class="fa fa-caret-down text-warning m-r-sm"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="panel padder-v item bg-info">
                                <div class="h1 text-fff font-thin h1">
                                <a href="/org.xjtusicd3.portal/problemPage.html">${problemUnresolvedCounts }</a>
                                </div>
                                <span class="text-muted text-xs">待处理问题</span>
                                <div class="top text-right w-full">
                                    <i class="fa fa-caret-down text-warning m-r-sm"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="panel padder-v item bg-primary">
                                <div class="h1 text-fff font-thin h1">
                                <a href="/org.xjtusicd3.portal/faqPage.html">${ pendCounts}</a>
                                </div>
                                <span class="text-muted text-xs">待审核FAQ</span>
                                <div class="top text-right w-full">
                                    <i class="fa fa-caret-down text-warning m-r-sm"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="panel padder-v item">
                                <div class="font-thin h1">
                                <a href="/org.xjtusicd3.portal/userPage.html">${pendingAuditCounts }</a>
                                </div>
                                <span class="text-muted text-xs">待审核用户</span>
                                <div class="bottom text-left">
                                    <i class="fa fa-caret-up text-warning m-l-sm"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title" style="border-bottom:none;background:#fff;">
                            <h5>近一个月事件数呈现</h5>
                        </div>
                        <div class="ibox-content" style="border-top:none;">
                            <div id="flot-line-chart-moving" style="height:217px;">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-9" style="padding-right:0;">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title" style="border-bottom:none;background:#fff;">
                            <h5>配置变化</h5>
                        </div>
                        <div class="ibox-content" style="border-top:none;">
                            <div id="yesterday" style="height:217px;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3" style="padding-left:0;">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content" style="border-top:none;background-color:#e4eaec;">
                            <h5>问题处理率</h5>
                            <div class="progress progress-striped active">
                                <div style="width: 95%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="75" role="progressbar" class="progress-bar">
                                    <span class="sr-only"></span>
                                </div>
                            </div>
                            <h5>变更处理率</h5>
                            <div class="progress progress-striped active">
                                <div style="width: 80%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="75" role="progressbar" class="progress-bar progress-bar-warning">
                                    <span class="sr-only"></span>
                                </div>
                            </div>
                            <h5>配置变更率</h5>
                            <div class="progress progress-striped active">
                                <div style="width: 20%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="75" role="progressbar" class="progress-bar .progress-bar-danger">
                                    <span class="sr-only"></span>
                                </div>
                            </div>
                            <h5>问题回复率</h5>
                            <div class="progress progress-striped active">
                                <div style="width: 60%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="75" role="progressbar" class="progress-bar progress-bar-info">
                                    <span class="sr-only"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-sm-2">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>任务列表</h5>
                </div>
                <div class="ibox-content">
                    <ul class="todo-list m-t small-list ui-sortable">
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs todo-completed">机房失火</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs  todo-completed">软件更新</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-square-o"></i> </a>
                            <span class="m-l-xs">电脑黑屏</span>
                            <small class="label label-primary"><i class="fa fa-clock-o"></i> 1小时</small>
                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs todo-completed">机房失火</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs  todo-completed">软件更新</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-square-o"></i> </a>
                            <span class="m-l-xs">电脑黑屏</span>
                            <small class="label label-primary"><i class="fa fa-clock-o"></i> 1小时</small>
                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs todo-completed">机房失火</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs  todo-completed">软件更新</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-square-o"></i> </a>
                            <span class="m-l-xs">电脑黑屏</span>
                            <small class="label label-primary"><i class="fa fa-clock-o"></i> 1小时</small>
                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs todo-completed">机房失火</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs  todo-completed">软件更新</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-square-o"></i> </a>
                            <span class="m-l-xs">电脑黑屏</span>
                            <small class="label label-primary"><i class="fa fa-clock-o"></i> 1小时</small>
                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs todo-completed">机房失火</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-check-square"></i> </a>
                            <span class="m-l-xs  todo-completed">软件更新</span>

                        </li>
                        <li>
                            <a href="widgets.html#" class="check-link"><i class="fa fa-square-o"></i> </a>
                            <span class="m-l-xs">电脑黑屏</span>
                            <small class="label label-primary"><i class="fa fa-clock-o"></i> 1小时</small>
                        </li>


                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<!-- 自定义js -->
<script src="js/content.js"></script>


</body>

</html>
