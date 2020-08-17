<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>spring.kaudo.com</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=480,user-scalable=no"/>
<link rel="icon" href="/favicon.ico"/>
<link rel="stylesheet" href="/css/default.css"/>
<style type="text/css">
html,body{margin:0;padding:0;width:100%;height:100%;color:#666;font:normal normal 14px/16px -webkit-control;font-family:'나눔 고딕','droid sans fallback','AppleGothic','sans-serif';}
#header{position:absolute;width:100%;top:0;left:0;overflow:hidden;background-color:#eee;color:black;height:80px;}
#container{margin-bottom:0/*-25px*/;min-height:100%;}
#wrapper{padding:80px 0 0/*25px*/ 0;}
#content{overflow:auto;background-color:#fff;padding:0 5px;}
#footer{position:relative;width:100%;clear:both;background-color:#444;color:#999;display:none;height:0;}

h3 a,h3 a:link,h3 a:visited,h3 a:hover,h3 a:active{color:#dd5500;text-decoration:none;}

h3{line-height: 20px;margin:0 0 0 0;padding:25px 0 8px 0;}
div.trow{margin:0;min-height:180px;padding:0 5px;}
span.location{display:block;color:royalblue;font-weight:bold;}
img.alignnone{float:right;margin-left:5px;border:1px #333 solid;}

h1.title{width:100%;font-size:25px;font-weight:bold;margin:10px 0 15px 5px;}
.ulTab li{width:20%;height:30px;float:left;display:block;}
.btnTab{letter-spacing:-1px;font-size:15px;font-weight:bold;width:100%;height:30px;border:0;text-align:left;padding-left:38px;line-height:13px;}
</style>

<script src="/js/jquery-3.5.1.min.js"></script>
<script src="/js/data.js"></script>
<script type="text/javascript">

$(window).resize(resizeWindow=function(){
    $('#content').css({height:$('html').height()-$('#header').height()-$('#footer').height(),visibility:'visible'});
    //$('#menu').css({height:$('html').height()});
});

$(document).ready(function(){

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

	resizeWindow();

});
</script>
</head>
<body>

<header id="header" style="">

</header>
<div id="container"><div id="wrapper"><div id="content">

</div></div></div>
<div id="footer"></div>

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
<div class="areaResult" style="width:100%;height:500px;background-image:url('/images/road.png')"></div>

</body>
</html>