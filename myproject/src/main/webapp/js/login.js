$(function(){
//	$("#form1").attr('action',getContextPath()+'/login.do');
 	$('.login').click(function(){
		if($('.name').val()==''||$('.pwd').val()==''){
//			$.messager.alert('提示','账户或密码为空！','warning');
			alert('账户或密码为空！');
			return false;
		}
		dologin();
	});
});
//function dologin2(){
//	$("#form1").submit();
//};
function dologin(){
	var name=$('.name').val();
	var password=$('.pwd').val();
	$.ajax({
		url:getContextPath()+'/login.do',
		type:"get",
		datatype:"json",
		data:{'userName':name,'passWord':password},
		success: function(data){
			if(data.statu=='suc'){
//				sessionStorage.setItem("username", data.user.userName);
 	 			if(data.user.url==null||data.user.url=='undefined'){
		 			location.href="home.html";
 	 			}else{
 	 				location.href=data.user.url;
 	 			}
 			}else{
 				alert(data.msg);
  			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus);
//			alert(errorThrown);
		}
	});
}

function getContextPath(){
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
} 