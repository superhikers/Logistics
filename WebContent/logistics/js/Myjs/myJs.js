//判断电话号码
$(function() {
	$("#rPhone").keyup(function() {
		var re = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;
		if (!re.test($("#rPhone").val())) {
			$("#checkResultPhone").html("电话号码格式不正确");
		} else {
			$("#checkResultPhone").html("");
		}
	});
});
// 判断两次密码是否一样
$(function() {
	$("#rPassword2").keyup(function() {
		if ($("#rPassword").val() != $("#rPassword2").val()) {
			$("#checkResult").html("两次密码不相符");
		} else {
			$("#checkResult").html("");
		}
	});
});
// 判断用户名是否已经存在
$(function() {
	$("#rUsername").keyup(function() {
		var page = "checkUsername";
		var name = $("#rUsername").val();

		$.post(page, {
			"name" : name
		}, function(result) {
			$("#checkResultUsername").html(result);
		});
	});
});

// ajax提交注册信息
$(function() {
	$("#rSub").click(function() {
		var page = "checkUser";
		var name = $("#rUsername").val();
		var passwd = $("#rPassword").val();
		var phone = $("#rPhone").val();
		if(passwd==""||phone==""){
			alert("请将信息填写完整！");
			return 0;
		}
		if($("#checkResultPhone").html()!=""||$("#checkResult").html()!=""){
			alert("请将信息填写正确！");
			return 0;
		}
		$.post(page, {
			u_username : name,
			u_password : passwd,
			u_phone : phone
		}, function(jsonResult) {
			var obj =JSON.parse(jsonResult);
			if(obj.jsonResult.code==200){
				alert(obj.jsonResult.msg+"请登录！");
				$("#open").trigger("click"); 
				$("#regClose").trigger("click");
			}else{
				alert(obj.jsonResult.msg);
			}
			
		});
	});
});

//ajax提交信息 登录
$(function() {
	$("#logSub").click(function() {
		var page = "checkLogin";
		var name = $("#logName").val();
		var passwd = $("#logPasswd").val();
		$.post(
			page, {
				u_username: name,
				u_password: passwd,
			},
			function(jsonResult) {
				var obj =JSON.parse(jsonResult);
				if(obj.jsonResult.code==200){
					$("#loginMsg").html("");
					window.location.reload();
				}else if(obj.jsonResult.code==1){
					$("#loginMsg").html(obj.jsonResult.msg);
				}else if(obj.jsonResult.code==0){
					$("#loginMsg").html(obj.jsonResult.msg);
				}
				console.log(obj.jsonResult.msg);
//				if("登录成功"==result){
//					alert(result);
//					$("#loginMsg").html("");
//					window.location.reload();
//				}else{
//					$("#loginMsg").html(result);
//				}
				
			}
		);
	});
});

//退出登录
$(function() {
	$("#logOut").click(function() {
		var page = "logOut";
		$.post(
			page, 
			"",
			function(jsonResult) {
				var obj =JSON.parse(jsonResult);
				if(obj.jsonResult.code==200){
					window.location.reload();
					console.log(obj.result.msg);
				}
				
			}
		);
	});
});

//页面加载时加载
window.onload=function(){
		var page = "checkIsLogin";
	$.post(
			page, 
			"",
			 function(result) {
				var obj =JSON.parse(result);
				if(obj.result.code==200){
					console.log(obj.result.msg+"用户:"+obj.result.data);
					$("#open").hide();
					$("#isLog").show();
					$("#isLogMsg").html("用户："+obj.result.data);
					
				}else{
					console.log(obj.result.msg);
					$("#open").show();
					$("#isLog").hide();
					$("#logList").hide();
				}
			}
		);
}

