<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
</script>
<style>

</style>

<body>

  <c:set var="partner" value="${sessionScope.partner}"/>
  <header>
  <jsp:include page="partnermenu.jsp"></jsp:include>
  </header>
  
  
  
  
  <c:choose>
    <c:when test="${empty partner}">
      <article><jsp:include page="partnerlogin.jsp"></jsp:include></article>
    </c:when>
    <c:otherwise>
     <%--  <article><jsp:include page="presentStatus.jsp"></jsp:include></article> --%>
     <article><jsp:include page="partner.jsp"></jsp:include></article>
    </c:otherwise>
  </c:choose>
  
  
  
  
  <footer></footer>
</body>
</html>