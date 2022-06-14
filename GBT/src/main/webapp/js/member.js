function login(){
	let loginid = $("#loginid").val();
	let password = $("#password").val();
	$.ajax({
		url : '../login',
		data : {"loginid": loginid , "password" : password},
		success : function(result){
			console.log(result);
			if(result==1){
				location.href="/GBT/home.jsp"
			}
			else{
				alert("동일한 회원정보가 없습니다.");
			}
		}
	})	
}