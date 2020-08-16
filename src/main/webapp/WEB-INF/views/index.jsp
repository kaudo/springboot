<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=480,user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spring.kaudo.com</title>
<script src="/js/jquery-3.5.1.min.js"></script>
<script src="/js/data.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	console.log('test');

    $('.btnBusList').unbind('click').click(function(){
    	console.log('test');
		$.ajax({
			url:'/getBusRouteList',
			//headers:{'Authorization':'KakaoAK 88bf5219ab2618c0ab4eba7d524da046'},
			type:'post'
		}).done(function(data){
		  console.log(data);
		  $('#loading').hide();
		});
    	return false;
	});

	$('input[name="busRouteNm"]').bind('keyup',function(){
		var busRouteNm=$(this).val();
		$('.areaResult').html('');
		if(!busRouteNm) return false;
		$(data.list).each(function(idx,obj){
			if(obj.busRouteNm.toString().indexOf(busRouteNm)>-1){
				$('.areaResult').append('<div class="btnBusRouteNm" data-busRouteNm="'+obj.busRouteNm+'">'+JSON.stringify(obj)+'</div>');
				$('.btnBusRouteNm:last').data('data',obj);
			}
		});

		$('.btnBusRouteNm').unbind('click').click(function(){
			console.log($(this),$(this).data('data').busRouteNm);

			$.ajax({
				url:'/getBusRouteList',
				type:'post'
			}).done(function(data){
			  console.log(data);
			  $('#loading').hide();
			});

			return false;
		});
	});

});
</script>
</head>
<body>
    <h1>spring.kaudo.com</h1>
    <br/>
springboot, jsp, jstl, jquery<br/>
aws, ec2, mariadb<br/>
github > gradle > war > java<br/>
<br/>
    ${result}
    <br/>

<br/>
    <button class="btnBusList">버스번호</button>

    asdf
<a href="/getRouteInfoItem">getRouteInfoItem</a>
<a href="/getBusRouteList">버스번호전체보기</a>
<br/>
<br/>
<a href="getStaionByRoute?busRouteId=100100506" target="_blank">getStaionByRoute</a><br/>
<a href="getRouteInfo?busRouteId=100100506" target="_blank">getRouteInfo</a><br/>
<a href="getRoutePath?busRouteId=100100506" target="_blank">getRoutePath</a><br/>

<input type="tel" name="busRouteNm"/>
<div class="areaResult"></div>

</body>
</html>