function findid(){	
	let name = $("#name").val();
	let phone = $("#phone").val();
	if(name=="" || phone == ""){
		alert("이름, 전화번호를 입력해주세요");
	}
	else{
		$.ajax({
			url : "../findid",
			type : "post",
			data : {"name" : name,
					"phone" : phone
			},
			success : function(result){
				if(result=="null"){
					alert("동일한 회원정보가 없습니다");
					
				}
				else{
					alert("회원님의 아이디는 "+result+"입니다.");
				}
			}
		})
	}
}

function findpw(){	
	let name = $("#name").val();
	let phone = $("#phone").val();
	let mid = $("#mid").val();
	if(name=="" || phone == "" || mid==""){
		alert("이름, 아이디, 전화번호를 입력해주세요");
	}
	else{
		$.ajax({
			url : "../findpw",
			type : "post",
			data : {"name" : name,
					"phone" : phone,
					"mid" : mid
			},
			success : function(result){
				if(result=="null"){
					alert("동일한 회원정보가 없습니다");
					
				}
				else{
					alert("회원님의 비밀번호는 "+result+"입니다.");
				}
			}
		})
	}
}