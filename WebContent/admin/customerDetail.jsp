<%@page import="com.parking.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="c" value="${customer}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>customerDetail</title>
<style>
	tr td{border:1px solid; border-collapse:collapse; text-align:center;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function() {
	var $parentObj = $("article");
	if($parentObj.length == 0)
		$parentObj = $("body");
	
	// start of modify
	$("input[name=modify]").click(function() {
		var id = ${c.c_id};
		var pwd = $("input[name=pwd]").val();
		var name = $("input[name=name]").val();
		var phone = $("input[name=phone]").val();
		var car = $("input[name=car]").val();
		var card = $("input[name=card]").val();
	});
	// end of modify
});
</script>
</head>
<body>
<table style="border-collapse:collapse;">
	<tr>
		<td>아이디</td>
		<td>${c.c_id}</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="text" name="pwd" value="${c.c_password}"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" value="${c.c_name}"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone" value="${c.c_phone_number}"></td>
	</tr>
	<tr>
		<td>차량번호</td>
		<td><input type="text" name="car" value="${c.c_car_number}"></td>
	</tr>
	<tr>
		<td>카드번호</td>
		<td><input type="text" name="card" value="${c.c_card_number}"></td>
	</tr>
	<tr>
		<td>가입날짜</td>
		<td>${c.c_date}</td>
	</tr>
	<tr>
		<td>가입경로</td>
		<td>${c.c_sign_path}</td>
	</tr>
	<tr>
		<td>고객상태</td>
		<td>
			<select name="status">
				<%String[] sortList = {"일반회원", "탈퇴회원"};
				  Customer c = (Customer)request.getAttribute("c");
				  int n = 0;
				  if(c.getC_status() == 'n')
				  	n = c.getC_status() - 110;
				  else if(c.getC_status() == 'd')
				  	n = c.getC_status() - 99;%>
				<%for(int i = 0; i < 3; i++) { %>
					<option value="<%=i%>"
					<%if(n == i) { %>
						selected
					<%} %>><%=sortList[n]%></option>
				<%} %>
			</select>
		</td>
	</tr>
	<tr style="border:0px;">
		<td colspan="2" style="text-align:center; border:0px;">
			<input type="button" name="modify" value="수정">
			<input type="button" name="delete" value="삭제"></td>
	</tr>
</table>
</body>
</html>