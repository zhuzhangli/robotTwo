<nav class="navbar-default navbar-static-side" role="navigation">
  <div class="nav-close"><i class="fa fa-times-circle"></i></div>
  <div class="sidebar-collapse">
      <ul class="nav" id="side-menu">
          <li class="nav-header">
              <div class="dropdown profile-element">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                          <span class="clear">
                              <span class="block m-t-xs" style="font-size:16px;">
                                  <i class="fa fa-area-chart"></i>
                                  <strong class="font-bold">烟草公司IT管理系统</strong>
                              </span>
                          </span>
                  </a>
              </div>
              <div class="logo-element"></div>
          </li>
         
          <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
              <span class="ng-scope"><li class="loginLinkLi" id="userNameText">您好：${UserName}</li></span>
          </li>
            
          <li>
              <a class="J_menuItem" href="homePage.html">
                  <i class="fa fa-home"></i>
                  <span class="nav-label">首页</span>
              </a>
          </li>

          <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
              <span class="ng-scope"></span>
          <li>
          
          <a href="">
              <i class="fa fa-paste"></i>
              <span class="nav-label">ITIL管理流程</span>
              <span class="fa arrow"></span>
          </a>
          <ul class="nav nav-second-level">
              <li>
                  <a class="J_menuItem" href="eventPage.html">
                      <i class="fa fa-paste"></i>
                      <span class="nav-label">事件管理</span>
                  </a>
              </li>

              <li>
                  <a class="J_menuItem" href="problemPage.html">
                      <i class="fa fa-warning"></i>
                      <span class="nav-label">问题管理</span>
                  </a>
              </li>
              
              <li>
                  <a  href="">
                  	<i class="fa fa-edit"></i>&nbsp;配置管理<span class="fa arrow"></span>
                  </a>
				  <ul class="nav nav-third-level">
                      <li><a class="J_menuItem" href="configureSoftPage.html">软件资源库</a></li>

                      <li><a class="J_menuItem" href="configureEquipmentPage.html">设备资源库</a></li>
                      
                      <li><a class="J_menuItem" href="configureBasicPage.html">标准配置信息</a></li>
                  </ul>
              </li>
            
              <li>
                  <a class="J_menuItem" href="changePage.html">
                      <i class="fa fa-cutlery"></i>
                      <span class="nav-label">变更管理</span>
                  </a>
              </li>
             
              <li>
                  <a class="J_menuItem" href="faqPage.html">
                      <i class="fa fa-table"></i>
                      <span class="nav-label">知识管理</span>
                  </a>
              </li>
          </ul>

          <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
              <span class="ng-scope"></span>
          </li>

          <li>
              <a class="J_menuItem" href="spiderindex.html">
                  <i class="fa fa-bug"></i>
                  <span class="nav-label">爬虫管理</span>
                  <span class="nav-label"></span>
              </a>
          </li>


         <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
             <span class="ng-scope"></span>
         </li>
        
         <li>
             <a class="J_menuItem" href="logindex.html">
                 <i class="fa fa-user-md"></i>
                 <span class="nav-label">日志管理</span>
             </a>
         </li>
          
         <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
             <span class="ng-scope"></span>
         </li>

         <li>
             <a class="J_menuItem" href="userPage.html">
                 <i class="fa fa-user-plus"></i>
                 <span class="nav-label">用户管理</span>
             </a>
         </li>
        
         <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
             <span class="ng-scope"></span>
         </li>
<!-- 
         <li>
             <a class="J_menuItem" href="">
                 
                 <span class="nav-label">权限管理</span>
             </a>
         </li> -->
		 <li>
		 <a href="">
            <i class="fa fa-rebel"></i>
            <span class="nav-label">权限管理</span>
            <span class="fa arrow"></span>
         </a>
         <ul class="nav nav-second-level">
            <li>
                <a class="J_menuItem" href="permissionPage.html">
                    <i class="fa fa-table"></i>
                    <span class="nav-label">权限列表</span>
                </a>
            </li>

            <li>
                <a class="J_menuItem" href="permissionAssignPage.html">
                    <i class="fa fa-warning"></i>
                    <span class="nav-label">权限分配</span>
                </a>
            </li>
	    </ul>
		</li>


		<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
            <span class="ng-scope"></span>
        </li>

		<li>
         <a class="J_menuItem" href="" onclick="logout()">
             <i class="fa fa-warning"></i>
             <span class="nav-label">退出</span>
         </a>
        </li>
      </ul>
  </div>
</nav>