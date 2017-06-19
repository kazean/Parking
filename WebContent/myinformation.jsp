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
	
});

</script>
<style>
.spMyInformation:hover { text-decoration: underline; cursor: default;}

</style>

<body>


<c:set var="customer" value="${sessionScope.customer}" />
가입일자 : <fmt:formatDate value="${customer.c_date}" pattern="yyyy-MM-dd"/><br>
아이디 :  ${customer.c_id}   <br>
비밀번호 :  ${customer.c_password}<br>
전화번호 :  ${customer.c_phone_number}<br>
차량번호 :  ${customer.c_car_number}<br>
카드넘버 : ${customer.c_card_number}  >> 확인위해선 비밀번호 한번더 페이지 만들까??<br> 


<form>

<button>수정하기</button>
<button>탈퇴하기  </button> >>  버튼 베리작게 만들예정  >>  --작업중--

</form>

</body>
</html>