var gnum = Request("gnum");
var intev = Request("intev");

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

$(function(){ // 문서 시작 실행코드
	if(intev=="" || intev=="day"){
		$.ajax({
			url : "../Getratio",
			data : {"gnum" : gnum, "type" : 1},
			success : function(result){	
				if(result!=null){	
					startchart(result);
				}	
			}
		});	
	}
	else if(intev=="week"){
		$.ajax({
			url : "../Getratio",
			data : {"gnum" : gnum, "type" : 2},
			success : function(result){	
				if(result!=null){	
					startchart(result);
				}	
			}
		});
		
	}
	else if(intev=="month"){
		$.ajax({
			url : "../Getratio",
			data : {"gnum" : gnum, "type" : 3},
			success : function(result){	
				if(result!=null){	
					startchart(result);
				}	
			}
		});
	}		
});


$("#daybtn").on('click',function(){
	location.href="stats.jsp?gnum="+gnum+"&intev=day";
})
$("#weekbtn").on('click',function(){
	location.href="stats.jsp?gnum="+gnum+"&intev=week";
})
$("#monthbtn").on('click',function(){
	location.href="stats.jsp?gnum="+gnum+"&intev=month";
})

function startchart(result){
	am5.ready(function() {
		var root = am5.Root.new("statsbar");
		root.setThemes([am5themes_Animated.new(root)]);
		var chart = root.container.children.push(am5xy.XYChart.new(root, {
		  panX: true,
		  panY: false,
		  wheelX: "panX",
		  wheelY: "panX",
		  layout: root.verticalLayout
		}));
		var legend = chart.children.push(am5.Legend.new(root, {
		  centerX: am5.p50,
		  x: am5.p50
		}));	
		var data = result;
		var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
		  	minZoomCount: 4,
		 	maxZoomCount: 8,
		  categoryField: "Day",
		  renderer: am5xy.AxisRendererX.new(root, {
		    cellStartLocation: 0.1,
		    cellEndLocation: 0.9,
		    minGridDistance: 1
		  }),
		  tooltip: am5.Tooltip.new(root, {})
		}));
		
		let xRenderer = xAxis.get("renderer");
		xRenderer.labels.template.setAll({
			oversizedBehavior: "wrap",
			maxWidth: 150,		
			fontSize: 15,
			location: 0.5,
		/*	rotation: -45,
			horizontalCenter : "left",
			verticalCenter : "left",
			padding: (0,0,0,0),*/
			visible: true				
		});
	
		chart.xAxes.push( 
		  am5xy.ValueAxis.new(root, { 
		    renderer: am5xy.AxisRendererX.new(root, {
		      minGridDistance: 5
		    }) 
		  }) 
		);
	
		xAxis.data.setAll(data);
		var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
		  min: 0,
		  renderer: am5xy.AxisRendererY.new(root, {})
		}));
			
		function makeSeries(name, fieldName, stacked,data) {	
			var series = chart.series.push(am5xy.ColumnSeries.new(root, {
		    stacked: stacked,
		    name: name,
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: fieldName,
		    categoryXField: "Day"
		  }));
		
		  series.columns.template.setAll({
		    tooltipText: "{name}, {categoryX}:{valueY}",
		    width: am5.percent(90),
		    tooltipY: am5.percent(10)
		  });

		  series.data.setAll(data);

		  series.appear();
		
		  series.bullets.push(function () {
		    return am5.Bullet.new(root, {
		      locationY: 0.5,
		      sprite: am5.Label.new(root, {
		        text: "{valueY}",
		        fill: root.interfaceColors.get("alternativeText"),
		        centerY: am5.percent(50),
		        centerX: am5.percent(50),
		        populateText: true
		      })
		    });
		  });				
		  legend.data.push(series);			  
		}			
		$.ajax({
			url : "../Gethomework",
			data : {"gnum" : gnum},
			datatype : "json",
			success : function(homeworks){
				if(homeworks!=null){
					var hwlist = []; 
					hwlist = JSON.parse(homeworks); 
					for(let i = 0 ; i <=hwlist.length-1 ; i++){
						makeSeries(hwlist[i].category, hwlist[i].category, true, data);		
					}
				}
			}
		})
		chart.appear(1000, 100);

	}); // end am5.ready()			
}