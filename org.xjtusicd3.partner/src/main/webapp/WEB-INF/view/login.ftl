<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <title>小朵 | 登录/注册</title> 
  <link href="zhao/login_register/style.css" rel="stylesheet" /> 
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" /> 
  <link rel="stylesheet prefetch" href="https://fonts.googleapis.com/icon?family=Material+Icons" /> 
  <link href="zhao/login_register/style.css" rel="stylesheet" /> 
  <link rel="stylesheet" type="text/css" href="css/validate.css" /> 
  <link rel="stylesheet" type="text/css" href="zhao/tankuang/css/reveal.css" /> 
  <style type="text/css">
	#myemail, .newemail, .newemailtitle,#myemail2, .newemail2, .newemailtitle2{ 
	 cursor:default;
	 line-height:18px;
	}
 </style> 
 </head> 
 
 <body> 
  <div id="container"> 
   <div id="anitOut" class="anitOut" style="height:1054px;"> 
    <div class="cotn_principal"> 
     <div class="cont_centrar"> 
      <div class="cont_login"> 
       <div class="cont_info_log_sign_up"> 
        <div class="col_md_login"> 
         <div class="cont_ba_opcitiy"> 
          <h2>LOGIN</h2> 
          <p>已有帐号，登录立即体验.</p> 
          <button class="btn_login" onclick="cambiar_login()">登录</button> 
         </div> 
        </div> 
       
        <div class="col_md_sign_up"> 
         <div class="cont_ba_opcitiy"> 
          <h2>SIGN UP</h2> 
          <p>还没有帐号？点击注册.</p> 
          <button class="btn_sign_up" onclick="cambiar_sign_up()">注册</button> 
         </div> 
        </div> 
       </div> 
      
       <div class="cont_back_info"> 
        <div class="cont_img_back_grey"> 
         <img src="zhao/login_register/po.jpg" alt="" /> 
        </div> 
       </div> 
      
       <div class="cont_forms"> 
        <div class="cont_img_back_"> 
         <img src="zhao/login_register/po.jpg" alt="" /> 
        </div> 
        <form action="saveLogin.html" method="post"> 
         <div class="cont_form_login"> 
          <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons"></i></a> 
          <h2>LOGIN</h2> 
          <input type="text" placeholder="请输入用户名" id="me2" name="nameOrEmail" oninput="_email1()" /> 
          <div>
           <div class="validate_faqadd spa5"></div>
          </div> 
          <input type="password" id="password2" placeholder="请输入密码" name="UserPassword" oninput="_password1()" /> 
          <div>
           <div class="validate_faqadd spa6" id="me_password"></div>
          </div> 
          <button class="btn_login" data-dialog="somedialog" id="login">登录</button> 
         </div> 
        </form> 
        
        <div class="cont_form_sign_up"> 
         <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons"></i></a> 
         <h2>SIGN UP</h2> 
         <input type="text" class="inputElem" id="me" placeholder="请输入用户名" oninput="_email2()" /> 
         <div>
          <div class="validate_faqadd spa1"></div>
         </div> 
         <input type="password" id="password" placeholder="密码"  /> 
         <div>
          <div class="validate_faqadd spa3" id="_password"></div>
         </div> 
         <input type="password" id="repassword" placeholder="再次输入密码" oninput="_repassword2()" /> 
         <div>
          <div class="validate_faqadd spa4" id="_repassword"></div>
         </div> 
         <button class="btn_sign_up" id="register" data-dialog="somedialog">注册</button> 
        </div> 
       </div> 
      </div> 
     </div> 
    </div> 
   </div> 
  </div> 
  <div id="myModal" class="reveal-modal"> 
   <h1>用户名需要验证</h1> 
   <br /> 
   <p>已通知管理员，管理员验证后可进行登录</p> 
   <a class="close-reveal-modal" id="cancelModal">&times;</a> 
  </div> 
  <div class="reveal-modal-bg" id="modal_bg" style="display: display; cursor: pointer;"></div> 
  <script src="js/jquery-1.6.2.js"></script> 

  <!-- 注册功能的js -->
  <script src="zhao/login_register/login_emaiautocomplete/emailAutoComplete.js"></script> 
  
  <script src="js/login_bg/getStart.js"></script> 
  <script src="zhao/login_register/index.js"></script>  
 </body>
</html>