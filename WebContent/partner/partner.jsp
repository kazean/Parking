<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partnermainhome.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>



<script>

$(function(){
	

	
});
</script>
<style>
.content {

height: auto;
float: left;
padding: 20px;
}

.content table{
border-radius:5px;
background-color: #B0A6A6;
}
.content table td {
width: 40px;
background-color: white;
text-align: center;
height: 60px;
}
</style>

<body>


<header>
<jsp:include page="leftmenu.jsp"></jsp:include>
</header>


<article>

  <c:set var="partner" value="${sessionScope.partner}"/>
  <h1>${partner.p_name} 님 반갑습니다.</h1>



현재상황 - 현재 주차장에 남아있는 총 남은좌석의 위치를 알 수 있습니다. <br>
요청사항 - Parking에게 업데이트 해야하는 내용들을 요청 해 주세요. <br>
예약현황 - 예약사항에 대한 상세내역을 볼 수 있습니다. <br>
수익현황 - 정산내역을 알 수 있습니다.<br>

<%-- <div class="content">

<table>
 <c:forEach var="hei" begin="1"  end="5">
 <tr style="height: 20px;"></tr>
<tr>
  <c:forEach var="col" begin="1"  end="20">
    <td>
     ${hei}-${col}
    </td>
  </c:forEach>
</tr>
</c:forEach>
<tr style="height: 20px;"></tr>
</table>

</div> --%>
</article>

<footer></footer>

  

</body>
</html> 

