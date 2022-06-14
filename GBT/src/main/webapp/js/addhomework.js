var hworks = [];
var gnum = Request("gnum");

function Request(valuename){ // java스크립트로 request 처리하기
	var rtnval = "";
	var nowAddress = unescape(location.href);
	var parameters = (nowAddress.slice(nowAddress.indexOf("?")+1,nowAddress.length)).split("&");
	for(var i = 0 ; i < parameters.length ; i++){
		var varName = parameters[i].split("=")[0];
		if(varName.toUpperCase() == valuename.toUpperCase()){
			rtnval = parameters[i].split("=")[1];
			break;
		}
	}
	return rtnval;
}

/* $(function(){ // 문서가 열리면 해당 코드가 실행
	$.ajax({
		url : "../Gethomework",
		data : {"gnum" : gnum},
		datatype : "json",
		success : function(result){
			if(result!=null){
				var list = []; 
				list = JSON.parse(result); 
				for(var i = 0 ; i <=list.length-1 ; i++){
					$("#addhomeworkarea").append('<marquee>'+list[i].category+'</marquee><br>');
					hworks.push(list[i].category);
				}
			}
		}
	})
}); */

function addhomeworkbtn(){
	var hwork = $("#addhomework").val();
	if(hwork==""){
		alert("이름을 입력하세요.")
		return;
	}
	
	$.ajax({
		url: '../Checkhomework',
		data : { "gnum" : gnum, "hwork" : hwork},
		success : function(result){
			if(result == 1){
				alert("이미 존재하는 이름입니다.")
				$("#addhomework").val("");
				return;
			}
			else{
				$.ajax({
					url : "../Addhomework",
					data : {"hwork" : hwork , "gnum" : gnum},
					success : (function(result){
						if(result==1){
							location.reload();
						}
						else{
							alert("등록실패[통신 오류]");	
						}
					})
				})
				
				/*
				hworks.push(hwork);
				$("#addhomeworkarea").append(
					"<div id='"+hwork+"'>"+
					"<input class='form-control' type='text' value='"+hwork+"'>"+
					'<button class="btn btnbox" type="button" onclick="bdelete(\''+hwork+'\')">삭제</button>'+
					"</div>"
					);
				$("#addhomework").val("");
				
				*/
				
			}
		}	
	})
}

/*
function bdelete(hwork){
	for(let i = 0 ; i < hworks.length ; i++){
		if(hworks[i]==hwork){
			hworks.splice(i,1);
			break;
		}
	}
	$("#"+hwork).remove();	
	$("#addhomework").val("");
}
*/
/*
function reghomeworkbtn(){
		$.ajax({
		url : "../Addhomework",
		data : {"hworks" : hworks , "gnum" : gnum},
		traditional : true,
		success : (function(result){
			location.href="ratiohomework.jsp?gnum="+gnum;
		})
	})
}
*/
function reghomeworkbtn(){
	location.href="ratiohomework.jsp?gnum="+gnum;
}

function changename(gnum,hnum){
	let newname = $("#"+hnum).val();
	$.ajax({
		url : "../Revisehw",
		data : { "type" : "update" , "gnum" : gnum , "hnum" : hnum , "newname" : newname},
		success : (function(result){
			if(result==1){
				alert("수정 되었습니다.");
				location.reload();
			}
			else{
				alert("수정 실패.");
			}
		})
	})
}

function deletehw(gnum,hnum){
	if(confirm("삭제하면 기록도 같이 삭제됩니다. 삭제 하시겠습니까?")){
		$.ajax({
			url : "../Revisehw",
			data : { "type" : "delete" , "gnum" : gnum , "hnum" : hnum},
			success : (function(result){
				if(result==1){
					alert("삭제 되었습니다.");
					location.reload();
				}
				else{
					alert("수정 실패.");
				}
			})
		})
	}
}