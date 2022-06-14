
// 아이디 비동기 검색
$(function(){ // 문서가 열리면 해당 코드가 실행
	$("#searchid").keyup(function(){
		var gnum = $("#groupname").val();
		let searchid = $("#searchid").val();
		if(searchid.length>=12){
			$.ajax({
				url : "../Search",
				data : {"searchid" : searchid, "gnum" : gnum},
				datatype : "json",
				success : function(result){
					if(result!=null){
						document.getElementById('searchresult').innerHTML ="";
						var list = JSON.parse(result);
						for(i = 0 ; i < Object.keys(list).length ; i++){	
							document.getElementById('searchresult').innerHTML += "<div class='form-check my-3'><input class='form-check-input' id='searchidresult"+list[i].mnum+"' type='radio' name='searchidresult' value='"+list[i].mnum+"'><label class='form-check-label' for='searchidresult'>"+list[i].mname+"</label></div>";
						}
					}
				}
			});
		}
	});	
});

// 그룹요청
function sendask(){
	var tomnum = $("input[name='searchidresult']:checked").val();
	var gnum = $("#groupname").val();
	if(tomnum==null){ /* 유효성검사 다시 */
		alert("그룹맺을 회원을 선택해주세요")
	}else{
		$.ajax({
			url : "../Sendask",
			data : {"gnum" : gnum , "tomnum" : tomnum },
			success : function(result){
				if(result==1){
					alert("그룹요청을 보냈습니다!");
				/*	$("#searchid").val("");
					$("#searcharea").css({"display" : "none"});
					addmemberareaswitch = 1 ;
					location.reload();*/		
				}
				else if(result==3){
					alert("이미 요청을 보낸 회원입니다");
				}
				else{
					alert("실패");
				}
			}
		});
	}
}

// 그룹만들기 입력창  on/off
let mgtextswitch = 1;
function mgtext(loginmnum){		
		if(mgtextswitch==1){
			$("#makegrouptext").html(
			"<input class='form-control my-3' type='text' id='gname' name='gname' placeholder='그룹 이름을 입력하세요'>"+
			"<button class='btn btnboxconfirm text-end' type='button' onclick='make("+loginmnum+")'>만들기</button>"
			);
			$("#searchresult").html("");
			$("#searchid").html("");
			$("#searcharea").css({"display" : "none"});
			addmemberareaswitch = 1 ;
			mgtextswitch = 0 ;
			
		}else{
			$("#makegrouptext").html("");		
			mgtextswitch = 1 ;
		}
}

// 그룹만들기
function make(loginmnum){
	let gname = $("#gname").val();
	if(gname==""){
		alert("그룹이름을 입력해주세요.");
	}
	else{
		if(confirm("그룹을 만들까요?")){
			$.ajax({
				url : "../Makegroup",
				data : {"gname" : gname, "mnum" : loginmnum},
				success : function(result){
					if(result==1){
						alert("그룹을 만들었습니다!");
						location.reload();
					}
					else{
						alert("그룹만들기 실패");
					}		
				}
			});
		}
			
	}	
}

// 그룹 select box
$("#groupname").on('change',(function(){
	var gnum = $("#groupname").val();
	if(gnum == "groupnameswitch"){
		$("#groupmembertext").html("");
		$("#groupmemberbtn").html("");
		$("#searchresult").html("");
		$("#searchid").html("");
		$("#searcharea").css({"display" : "none"});
		$("#makegroupbtn").css({"display" : "block"});
		addmemberareaswitch = 1 ;
	}
	else{
			$("#makegroupbtn").css({"display" : "none"});
			$("#groupmembertext").html("");
			$("#searchresult").html("");
			$("#searchid").html("");
			$.ajax({
			url : "../Gmlist",
			data : {"gnum" : gnum},
			success : function(result){
				var mnames = result.split(",");	
				var contents = "<p class='my-3'>소속 되어있는 그룹원<p><br>";			
				for(i = 0 ; i < mnames.length-1 ; i++){			
					contents += ('이름 : '+mnames[i]+'<br>');
				}
				$("#groupmembertext").html(contents);
			}
		});
		$("#groupmemberbtn").html(	
		'<button class="btn btnbox" type="button" onclick="addmemberarea()">그룹원추가</button>  <button class="btn btnboxcancel" onclick="groupout()">그룹나가기</button>');
	}
}));

// 그룹 나가기
function groupout(){
	let gnum = $("#groupname").val();
	if(confirm("그룹을 나가시겠습니까?")){
		if(confirm("그룹을 나가면 그 동안 남겨둔 기록들이 삭제 됩니다. 정말 나가시겠습니까?")){
			$.ajax({
				url : "../Groupout",
				data : {"gnum" : gnum},
				success : function(result){
					if(result==1){
						alert("그룹에서 나왔습니다");
						location.reload();
					}
					else{
						alert("실패");
					}
				}
			});
		}
	}
}


// 회원검색 구역 on/off
let addmemberareaswitch = 1;
function addmemberarea(){
	if(addmemberareaswitch == 1){
		$("#searcharea").css({"display" : "block"});
		addmemberareaswitch = 0 ;
	}else{
		$("#searcharea").css({"display" : "none"});
		addmemberareaswitch = 1 ;
	}
}


// 그룹 승낙
function accept(){
	var agnum = $("#agnum").val();
	var fromid = $("#fromid").val();
	var frommnum = $("#frommnum").val();
	var gname = $("#gname").val();
	var gnum = $("#gnum").val();
	var tomnum = $("#tomnum").val();
		$.ajax({
			url : "../Accept",
			data : {"agnum" : agnum, "fromid" : fromid , "frommnum" : frommnum ,"gname" : gname ,"gnum" : gnum,"tomnum" : tomnum },
			success : function(result){
				if(result==1){
					alert("요청을 승락했습니다!");
					location.reload();
				}
				else{
					alert("실패");
				}
			}
		});
}


// 그룹 거절
function reject(){
	var agnum = $("#agnum").val();
	var fromid = $("#fromid").val();
	var frommnum = $("#frommnum").val();
	var gname = $("#gname").val();
	var tomnum = $("#tomnum").val();
		$.ajax({
			url : "../Reject",
			data : {"agnum" : agnum, "fromid" : fromid , "frommnum" : frommnum ,"gname" : gname ,"tomnum" : tomnum },
			success : function(result){
				if(result==1){
					alert("그룹요청을 거절했습니다");
					location.reload();
				}
				else{
					alert("실패");
				}
			}
		});
}

/* 그룹 요청 유효성 검사 */


