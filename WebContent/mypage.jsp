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
.listmenu{border : 1px solid; width : 200x; height:500px; inline-block;float :left; margin : 10px}
.list{border : 1px solid; width : 800px ;height:500px;display: inline-block;float :left;  margin : 10px}
</style>

<body>


<c:set var="customer" value="${sessionScope.customer}" />


<h3><span class="spMyInformation">내 개인정보 보러가기</span></h3><br><br>

<div class ="listmenu">

<ol>
  <li>즐겨찾기한 주차장</li>
  <li>내 예약내역</li>
  <li>내가쓴글</li>
</ol>

</div>


<div class="list">

<c:set var="list" value="${requestScope.list}"/>
<c:forEach var ="reservation"   items="${list}">
 ${reservation.reserve_c_id}
</c:forEach>


즐겨찾기한 주차장 <br>
<hr><br>


</div>

</body>
</html>