<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

$(function(){
	
	
	//비밀번호 확인페이지로 이동
	$('.spMyInformation').click(function(){
		console.log('클릭됨');
		$.ajax({
			url : 'passwordconfirm.jsp',
    		success : function(responseData) { 
				console.log('myinformation 가져옴');

				$('article').empty();
				$('article').html(responseData.trim());
				
			}
		});return false;
	});
	
	
	
	
	
});

</script>
<style>
.spMyInformation:hover { text-decoration: underline; cursor: default;}

</style>

<body>


<c:set var="customer" value="${sessionScope.customer}" />


<h3><span class="spMyInformation">내정보 보러가기</span></h3><br><br>

즐겨찾기한 주차장:   --작업중--  <br>
<hr><br>

내 예약내역: --작업중-- <br>
<hr><br>
내가쓴글: --작업중-- <br>
<hr><br>



</body>
</html>