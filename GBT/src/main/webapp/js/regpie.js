var gnum = Request("gnum");
var ratiolength = 0;
var hnumarray = [];
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

function saveratio(){
	var ratioarray = [];
	for (var i = 0 ; i <= ratiolength-1 ; i++){
		ratioarray[i] = $("#rangebar"+i).val();
	}
	$.ajax({
		url : "../Saveratio",
		traditional: true,
		data : {"gnum" : gnum, "ratioarray" : ratioarray, "hnumarray" : hnumarray},
		success : function(result){
			if(result==1){
				alert("변경 완료");
				location.href="/GBT/home.jsp"
			}
			else{
				alert("변경 실패");
			}
		}	
	});
}

$(function(){ // 문서가 열리면 해당 코드가 실행
	$.ajax({
		url : "../Gethomework",
		data : {"gnum" : gnum},
		datatype : "json",
		success : function(result){
			if(result!=null){
				var list = []; 
				list = JSON.parse(result); 
				am5.ready(function() {				
					for( var  i = 0 ; i <=list.length-1 ; i++){
						$("#hwrange").append("<div class='row text-center my-1' style='display:inline'><span>"+list[i].category+"</span><span id='score"+i+"'>("+list[i].value+"점)</span></div><div class='row my-2' style='display:block;'><span>1점</span><input type='range' min='1' max='10' class='rangebar form-range' value='"+list[i].value+"' id='rangebar"+i+"'><span>10점</span></div>");
						hnumarray[i] = list[i].hnum;
						ratiolength += 1 ;
					}
					$(".rangebar").change(function(  ){		
						let thisid = $(this).attr('id');
						let no = thisid.replace('rangebar','');
						list[no].value = $('#rangebar'+no).val();
						$('#score'+no).html("("+$('#rangebar'+no).val()+"점)");
						chart.data = reloadData();
						series.appear(1000, 100);
					});					
								
				function reloadData() {
					series.data.setAll(list);
					series.labels.template.setAll({
					  text: "{category}",
					  textType: "circular",
					  centerX: am5.percent(100)
					});
					legend.data.setAll(series.dataItems);
				
				}	
				
				// Create root element
				// https://www.amcharts.com/docs/v5/getting-started/#Root_element
				var root = am5.Root.new("regpie");
				
				
				// Set themes
				// https://www.amcharts.com/docs/v5/concepts/themes/
				root.setThemes([
				  am5themes_Animated.new(root)
				]);
				
				// Create chart
				// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
				var chart = root.container.children.push(am5percent.PieChart.new(root, {
				  layout: root.verticalLayout,
				  innerRadius: am5.percent(50)
				}));
				
				// Create series
				// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
				var series = chart.series.push(am5percent.PieSeries.new(root, {
				  valueField: "value",
				  categoryField: "category",
				  alignLabels: false
				}));
				
				series.labels.template.setAll({
				  text: "{category}",
				  textType: "circular",
				  centerX: am5.percent(100)
				});
				
				// Set data
				// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
				series.data.setAll(list);
				
				// Create legend
				// https://www.amcharts.com/docs/v5/charts/percent-charts/legend-percent-series/
				var legend = chart.children.push(am5.Legend.new(root, {
				  centerX: am5.percent(50),
				  x: am5.percent(50),
				  marginTop: 15,
				  marginBottom: 15,
				}));
				
				legend.data.setAll(series.dataItems);
				
				// Play initial series animation
				// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
				series.appear(1000, 100);
			
				});
			}	 
		}
	});
}); // end am5.ready()