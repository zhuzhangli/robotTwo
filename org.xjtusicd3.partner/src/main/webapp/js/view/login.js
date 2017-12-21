get$("#register").click(function(){
	var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	var us = /^\w{2,10}$/;
	var pw = /^\w{6,16}$/;
	var rpw = $("#repassword").val();
	if(na.test($("#me").val())&&us.test($("#user").val())&&pw.test($("#password").val())&&(rpw==($("#password").val()))){
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/saveRegister.html",
			data:{
				"name":$("#me").val(),
				"password":$("#password").val()
			},
			dataType:"json",
			success:function(data){
				if(data=="1"){
					$(".spa1").text('该用户名已被注册');
				}else if(data=="2"){
					$(".spa1").text('该邮箱还未验证');
				}else{
					document.getElementById("modal_bg").style.display="block";
					document.getElementById("myModal").style.visibility = "visible";
				}
			}
		})

		return true;
	}else{
		if($("#me").val()==""){
			$(".spa1").text('请填写注册的邮箱');
		}
		if($("#user").val()==""){
			$(".spa2").text('请填写用户名');
		}
		if($("#password").val()==""){
			$(".spa3").text('请填写密码');
		}
		if($("#repassword").val()==""){
			$(".spa4").text('请再次填写密码');
		}
		return false;
	}
})
//取消弹出框
$("#cancelModal").click(function(){
	document.getElementById("modal_bg").style.display="none";
	document.getElementById("myModal").style.visibility = "hidden";
	window.location.reload(); 
})