<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainhome.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- datepicker 사용드라이버 시작-->
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"><!-- jQuery UI CSS파일 -->
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script><!-- jQuery UI 라이브러리 js파일 -->
<!-- datepicker 사용드라이버 종료-->



<script>

$(function(){
	
	//캘린터 형태로 보여주기 시작
	$('#reserveEntranceTime').datepicker({
		/* 포맷 */
		dateFormat : 'yymmdd',
		changeMonth: true, 
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	
	    /* 최소 선택일자  */     
	    minDate: -0, 
        maxDate: "+30D",
    
   		 /* 하단 버튼*/
    	showButtonPanel: true, 
    	currentText: '오늘' ,
        closeText: '닫기', 
        
        /* 년도, 달 드롭바 만들기 */
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달',
        
	
	});
	
	$('#reserveExitTime').datepicker({
		
		/* 포맷 */
		dateFormat : 'yymmdd',
		changeMonth: true, 
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	
	    /* 최소 선택일자  */     
	    minDate: -0, 
        maxDate: "+30D",
    
   		 /* 하단 버튼*/
    	showButtonPanel: true, 
   	    currentText: '오늘' ,
        closeText: '닫기', 
        
        /* 년도, 달 드롭바 만들기 */
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달',

	});
	
    var $form = $('.selectschedule').find('form');
    $form.find('input[id=btReserve]').click(function(){
    	var location = $('#location').val();
    	var reserveEntranceTime = $('#reserveEntranceTime').datepicker("getDate");
    	var reserveExitTime = $('#reserveExitTime').datepicker("getDate")
    	console.log("location :"+location+"reserveEntranceTime :"+reserveEntranceTime+"reserveExitTime :"+reserveExitTime);
    	
    	$.ajax({
    		url : 'selectByLocation.do',
    		method : 'GET',
    		data : {
    			'location':  location ,
    			'reserveEntranceTime' :  reserveEntranceTime,
    			'reserveExitTime' :  reserveExitTime
    		},
    		success : function(responseData){
    			
    			$('article').empty();
    			$('article').html(responseData.trim());
    			
    		}
    	});return false;
    	
    	
    });
	
	
});
</script>
<style>

article {font-family: 'Open Sans', sans-serif;}

/* 배경 */
.background {
    position:relative;
    background-image: url("stock.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
    top:0;
    left:0;
    bottom:0;
    right:0;
    height: auto;
    min-height: 1500px;
}
/* 스케쥴체크박스 */
.selectschedule {
 width:800px; 
 height:30px; 
 border: 1px solid;
 border-radius: 20px;
 border-color:#F2B210;  
 margin:auto; 
 padding:30px; 
 position:absolute;
 top:30%;  
 left:50%;  
 background:#F2B210;  
 transform:translate(-50%, -50%)
 }


/* 메인 텍스트그룹 */
.content {
    /* color: #f2f2f2;  흰색*/
    position:absolute;
    top:10%;  
    left:0%; 
    margin-left: 38.5%;
    margin-top:20%;
    text-align: center;
    max-width: 1200px;
    height: 80px;
}

/* 큰글씨 */
.content h1 {
    font-family: "Exo 2","Helvetica Neue",Helvetica,sans-serif;
    text-transform: uppercase;
    font-weight: 800;
    font-size: 50px;
    margin: 1.25em 0 0.2em;
    letter-spacing: -2px;
}

/* 작은글씨 */
.content p {
    margin: 0 0 1.313em;
    font-weight: 800;
    font-size: 18px;
}

/* 버튼 */
.joinus {
    text-decoration: none;
    text-transform: uppercase;
    color: #f2f2f2;
    border: 1px solid #222222;
    /* border-color: #b43322; 빨간색*/
    background-color: #222222;
    display: inline-block;
    padding: 13px;
    font-size: 13px;
}
.joinus:hover {
    border-color: #F2B210;
    background-color:#F2B210;
}

</style>

<body>
  <header></header>
  <article>
  
  <div class="background">
  
  <!-- 메인화면 시간체크 박스 시작 -->
  <div class="selectschedule">
  
  <form>
    <input type="text" id="location" placeholder = "주차하려는 지역명"/>
    <input type="text" id="reserveEntranceTime" placeholder="입차일자"/>
    <input type="text"  id="reserveExitTime" placeholder="출차일자"/>
    <input type="button" id ="btReserve" value="주차장 선택하러 가기"/>
  </form>
  
  </div>
  <!-- 메인화면 시간체크 박스 끝 -->
  
  <!-- 메인화면 텍스트 시작 -->
  <div class="content">
      <h1> <span style="background-color:#F2F2F2">쉽고빠른 검색, 그리고 바로주차</span></h1>
      <p>누구보다 쉽고 빠르게, 가까운 주차장을 찾아 들어가쟈!<br>
                 KITRI parking을 이용하는 나야말로 진정한 승자!!!! </p>
      <a href="https://docs.google.com/forms/d/e/1FAIpQLSfKL1Mz2serFCWmCeS5iRDvWOLBZGR8YdAQBz8ZWyGxqkGmNA/viewform?usp=sf_link" class="joinus" target="_blank"><strong>JOIN US - 제휴 신청하기</strong></a>
  </div> 
  <!-- 메인화면 텍스트 끝 -->
  
  </div>
  </article>
  
  <footer></footer>
</body>
</html> 

