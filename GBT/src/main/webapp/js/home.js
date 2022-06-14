

$("#homegroupname").change(function(){
	var gnum = $("#homegroupname").val();
	if(gnum == "groupnameswitch"){
		$("#checkarea").html("그룹을 선택해주세요");
	}
	else{
		$.ajax({
			url : "/GBT/Mainselect",
			data : {"gnum" : gnum},
			success : function(result){
				$("#checkarea").html(result);
			}
		})
	}
});

function movetomg(){
	location.href="group/managegroup.jsp";
}


function movetoaddhw(){
	var gnum = $("#homegroupname").val();
	if(gnum == "groupnameswitch"){
		alert("그룹을 선택해주세요");
		return;
	}
	location.href="group/addhomework.jsp?gnum="+gnum ;
}

function movetostat(){
	var gnum = $("#homegroupname").val();
	if(gnum == "groupnameswitch"){
		alert("그룹을 선택해주세요");
		return;
	}
	location.href="group/stats.jsp?gnum="+gnum ;
}

function idid(){
	var gnum = $("#homegroupname").val();
	let checked = [];
	$("input:checkbox[name=ididname]:checked").each(function(){
		checked.push($(this).val());	
	})
	if(checked.length==0){
		alert("내가 한 집안일을 체크해주세요.")
	}
	else{
		$.ajax({
			url : "/GBT/Idid",
			data : {"checked" : checked, "gnum" : gnum},
			traditional : true,
			success : function(result){
				if(result==1){
					alert("등록했습니다.")
						$("input:checkbox[name=ididname]:checked").each(function(){
							$("input:checkbox[name=ididname]").prop("checked", false);
						})
						checked = [] ;
				}
				else{
					alert("등록실패")
				}	
			}
		})
	}
}