//页面加载时运行
window.onload = function() {
	layui.use('form', function() {
	});
	var page = "StaffCheckIsLogin";
	$.post(
			page, 
			"",
			 function(result) {
				var obj =JSON.parse(result);
				if(obj.result.code==200){
					console.log(obj.result.msg+"用户:"+obj.result.data);
					$("#StaffName").html("员工："+obj.result.data);
				}else{
					layer.msg("请登录！", function() {
						//关闭后的操作
					});
					location.href = 'login.html';
				}
			}
		);
}

// 退出登录
function logOug() {
	var page = "StaffLogOut";
	$.post(page, "", function(jsonResult) {
		var obj = JSON.parse(jsonResult);
		if (obj.jsonResult.code == 200) {
			location.href = 'login.html';
		}
	});
}
