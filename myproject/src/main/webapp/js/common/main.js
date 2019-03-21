$(function(){
	getLoginUser();
	$('.logout').click(function(){
		logout();
	});
});

function getContextPath(){
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
} 
function logout(){
	$.ajax({
		url:getContextPath()+'/logout.do',
		type:'post',
		success:function(data){
			if(data.statu=='suc'){
				window.location.href='login.html';
 			}
		}
	});
}
function getLoginUser(){
	$.ajax({
		url:getContextPath()+"/loginUser.do",
		type:'get',
		datatype:'json',
		success:function(data){
			if(data.statu=='suc'){
				$('.username').append(data.user.userName);
			}
 		}
	});
 }