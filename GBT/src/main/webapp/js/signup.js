let pass = [false, false, false, false, false]; // 배열 = [ ]

$(function(){ // 문서가 열리면 해당 코드가 실행
	$("#mid").focusout(function(){ // mid가 입력될때마다 해당 함수 실행
		let mid = document.getElementById("mid").value;
		let idcheck = document.getElementById("idcheck");
		let idj = /^[a-zA-Z0-9]{4,15}$/ ; // 한글을 제외한 영문+숫자 5~15 사이의 문자열	
			if (idj.test(mid)){ // 정규표현식 통과하면
				$.ajax({
					url : "../idcheck", // 통신할 경로 (서블릿파일 통신)
					data : {"mid" : mid} , // 통신할때 보내는 데이터
					success : function(result){ // 통신 성공후 받는 데이터
						if(result == "1"){
							idcheck.innerHTML ="사용중인 아이디입니다.";
							pass[0]=false;
						}
						else{
							idcheck.innerHTML ="사용가능한 아이디입니다.";
							pass[0]=true;
						}
					}
				}); // ajax end
			}
			else{
				idcheck.innerHTML ="영문, 숫자 포함 4~15글자내로 입력해주세요"; pass[0]=false;
			}
	}); // 아이디 key up end
	
	$("#mname").keyup(function(){
		let mname = $("#mname").val();
		let mnamej = /^[가-힣]{2,10}$/;
		if(mnamej.test(mname)){
			$("#namecheck").html("사용가능한 이름입니다.");	pass[1]=true;
		}
		else{
			$("#namecheck").html("한글 2~10글자만 가능합니다"); pass[1]=false;
		}
	});

	$("#mpassword").keyup(function(){
		let mpassword = $("#mpassword").val(); // jQuery식
		let mpasswordcheck = $("#mpasswordcheck").val();
		let passwordj = /^[a-zA-Z0-9]{4,15}$/;		
		if(passwordj.test(mpassword)){ // 정규표현식 같으면
			if(mpassword != mpasswordcheck){
			$("#passwordcheck").html("패스워드가 서로 다릅니다."); pass[2]=false;
			}
			else{
			$("#passwordcheck").html("사용가능한 비밀번호입니다."); pass[2]=true; pass[3]=true;
			}
		}else{
			$("#passwordcheck").html("영소문자 4~15사이로 입력해주세요"); pass[2]=false;
		}
	});

	$("#mpasswordcheck").keyup(function(){
		let mpassword = $("#mpassword").val(); // jQuery식
		let mpasswordcheck = $("#mpasswordcheck").val();
		let passwordj = /^[a-zA-Z0-9]{4,15}$/;
		
		if(passwordj.test(mpasswordcheck)){ // 정규표현식 같으면
			if(mpassword != mpasswordcheck){
			$("#passwordcheck").html("패스워드가 서로 다릅니다."); pass[3]=false;
			}
			else{
			$("#passwordcheck").html("사용가능한 비밀번호입니다."); pass[2]=true; pass[3]=true;
			}
		}else{
			$("#passwordcheck").html("영소문자 4~15사이로 입력해주세요"); pass[3]=false;
		}	
	}) // 비밀번호 key up end
	
	$("#mphone").keyup(function(){
	let mphone = $("#mphone").val();
	let mphonej = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	if(mphonej.test(mphone)){
		$("#phonecheck").html("사용가능한 전화번호입니다.");
		pass[4]=true;
	}
	else{
		$("#phonecheck").html("01*-****-**** 형식으로 입력해주세요"); pass[4]=false;
	}	
	});		

}) ; 

function signup(){
	let check =true;
	for (let i = 0 ; i <pass.length ; i++){
		if(pass[i]==false) check=false;
	}
	if(check) {
		$("#signupform").submit();
	}
	else{
		alert("양식에 맞게 내용을 채워주세요.");
	}
}