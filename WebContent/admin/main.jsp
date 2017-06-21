<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>main.jsp</title>
</head>

<body>
	<div>
		${sessionScope.responseAdmin.a_name}님 접속 
		<a href="customerList.do">고객관리</a>&nbsp;&nbsp;
		<a href="partnerList.do">파트너관리</a>&nbsp;&nbsp; 
		<a href="parkingList.do">주차장관리</a>&nbsp;&nbsp; 
		<a href="reviewList.do">리뷰관리</a>&nbsp;&nbsp;
		<a href="statistics.jsp">사용통계</a>&nbsp;&nbsp;
	</div>
</body>
</html>