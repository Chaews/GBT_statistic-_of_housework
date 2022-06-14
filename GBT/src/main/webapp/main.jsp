<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>가사분담통계 GBT</title>
<style type="text/css">

</style>
</head>
<body class="indexbg">
	<section class="section" style=" background: linear-gradient( to right, #56CCF2, #2F80ED); padding:60px 0px;">
		<div class="container section">
			<div class="row justify-content-center">
				<div class="col-lg-7 text-center">
					<h1 class="mainh1 display-5 text-white fw-200 mb-3">싸우지 말아요<br>
					데이터로 대화해요<br>
					가사분담통계, G B T</h1>
					<p class="pwhite">맞벌이 부부가 많아진 요즘, 가사분담은 당연한 일 입니다. 하지만 사람마다 기준이 다르기 때문에 분담의 만족도는 각자 다를 것 입니다.
					  GBT는 일상생활에서 매일, 자주 하는일들에 대해 점수를 설정하고, 일을 할때마다 체크하면 이를 수치화해서 통계로 보여줍니다.
					   구성원간 가사 기여도에 따른 다툼을 미연에 방지해보고자 기획하였습니다. 물론 점수를 설정하는 과정부터 순탄치 않겠지만,
					    이 또한 구성원간 대화가 부족했다는 뜻이니 GBT 하면서 가족간 대화 하는시간을 가져보는게 어떨지요 :)</p>
				</div>
			</div>
		</div>
	</section>
	

<div class="container-lg my-5">
	<div class="row my-5">
		<div class="col-lg-6">
			<div id="mainpie"></div>
		</div>

		<div class="col-lg-6 align-self-center">

				<h2 class="text-center my-3">부부, 연인, 가족 등 원하는사람과<br>
				그룹을 맺고 가사일을 자유롭게 등록,<br>
				기여도를 백분율로 설정해요</h2>

		</div>
	</div>

<br><br><br><br><br><br>

	<div class="row my-5">
		<div class="col-lg-6 align-self-center">
			<h2 class="text-center my-3">그리고 자기가 한 가사일을 체크!</h2>
		</div>
		
		<div class="col-lg-6">
			<img class="img-fluid" alt="" src="img/index1.png">
		</div>
	</div>

<br><br><br><br><br><br>

	<div class="row my-5">
		<div class="col-lg-6">
			<div id="mainbar"></div>
		</div>

		<div class="col-lg-6 align-self-center">
			<h2 class="text-center my-3">데이터로 한 눈에 비교할 수 있어요</h2>
		</div>
	</div>
</div>

<br><br><br><br><br><br>

<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="js/mainpie.js"></script>
<script src="js/mainbar.js"></script>	

</body>
</html>