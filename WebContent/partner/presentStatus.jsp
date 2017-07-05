<%@ page contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
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
<div class="content">
<h1> 서비스 준비중 입니다.</h1>
<table>
 <c:forEach var="hei" begin="1"  end="5">
 <tr style="height: 20px;"></tr>
<tr>
  <c:forEach var="col" begin="1"  end="20">
    <td>
    <a href=""> ${hei}-${col}</a>
    </td>
  </c:forEach>
</tr>
</c:forEach>
<tr style="height: 20px;"></tr>
</table>




</div>
</article>

<footer></footer>

  

</body>
</html> 

