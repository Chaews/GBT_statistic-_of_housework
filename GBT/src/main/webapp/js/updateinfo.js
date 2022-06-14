let pass = [true, true, true]; // 배열 = [ ]

$(function(){ // 문서가 열리면 해당 코드가 실행
	
	$("#mname").keyup(function(){
		let mname = $("#mname").val();
		let mnamej = /^[가-힣]{2,10}$/;
		if(mnamej.test(mname)){
			$("#namecheck").html("사용가능한 이름입니다.");	pass[0]=true;
		}
		else{
			$("#namecheck").html("한글 2~10글자만 가능합니다"); pass[0]=false;
		}
	});

	$("#mpassword").keyup(function(){
		let mpassword = $("#mpassword").val(); // jQuery식
		let passwordj = /^[a-zA-Z0-9]{4,15}$/;		
		if(passwordj.test(mpassword)){ // 정규표현식 같으면
			$("#passwordcheck").html("사용가능한 비밀번호입니다."); pass[1]=true;
		}else{
			$("#passwordcheck").html("영소문자 4~15사이로 입력해주세요"); pass[1]=false;
		}
	});

	$("#mphone").keyup(function(){
	let mphone = $("#mphone").val();
	let mphonej = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	if(mphonej.test(mphone)){
		$("#phonecheck").html("사용가능한 전화번호입니다.");
		pass[2]=true;
	}
	else{
		$("#phonecheck").html("01*-****-**** 형식으로 입력해주세요"); pass[2]=false;
	}	
	});		

}) ; 

function update(){
	let check =true;
	let mname = $("#mname").val();
	let mpassword = $("#mpassword").val();
	let mphone = $("#mphone").val();
	for (let i = 0 ; i <pass.length ; i++){
		if(pass[i]==false) check=false;
	}
	if(check) {
		$.ajax({
			url : "../Update", // 통신할 경로 (서블릿파일 통신)
			data : {"mname" : mname,
					"mpassword" : mpassword,
					"mphone" : mphone
					},
			success : function(result){ // 통신 성공후 받는 데이터
				if(result == 1){
					alert("회원정보가 수정되었습니다.");
					location.href="updateinfo.jsp";
				}
				else{
					alert("수정 오류 [관리자에게문의]");
					location.href="updateinfo.jsp";
				}
			}
		}); // ajax end
	}
	else{
		alert("양식에 맞게 수정해주세요.");
	}
}


function deleteac(){
	if(confirm("정말 탈퇴하시겠습니까?")){
		var input = prompt('확인을 위하여 한번 더 비밀번호를 입력해주세요', '');	
		$.ajax({
				url : "../Delete", // 통신할 경로 (서블릿파일 통신)
				data : {'input' : input},
				success : function(result){ // 통신 성공후 받는 데이터
				if(result == 1){
					alert("탈퇴 처리 되었습니다.");
					location.href="/GBT/index.jsp";
				}
				else if(result==3){
					alert("비밀번호를 잘못 입력하셨습니다.");
				}
				else{
					alert("통신 오류 [관리자에게문의]");
				}
			}
		}); 
	}

}
