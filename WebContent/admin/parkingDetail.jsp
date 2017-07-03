<%@page import="com.parking.vo.Parking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="p" value="${parking}"/>
<%Parking p = (Parking)request.getAttribute("parking");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>parkingDetail</title>
<style>
	tr td{border:1px solid; border-collapse:collapse; text-align:center;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<table style="border-collapse:collapse;">
	<tr>
		<td>주차장 코드</td>
		<td>${p.parking_code}</td>
	</tr>
	<tr>
		<td>주차장 제휴</td>
		<td>
			<c:if test="${p.parking_p_id == null}">X</c:if>
			<c:if test="${p.parking_p_id != null}">${p.parking_p_id}</c:if>
		</td>
	</tr>
	<tr>
		<td>주차장 이름</td>
		<td><input type="text" name="parking_name" value="${p.parking_name}"></td>
	</tr>
	<tr>
		<td>주차장 주소</td>
		<td><input type="address" name="parking_address" value="${p.parking_address}"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="tel" name="parking_phone_number" value="${p.parking_phone_number}"></td>
	</tr>
	<tr>
		<td colspan="2">주차장 위치정보</td>
	</tr>
	<tr>
		<td>위도</td>
		<td><input type="text" name="parking_latitude" value="${p.parking_latitude}">도</td>
	</tr>
	<tr>
		<td>경도</td>
		<td><input type="text" name="parking_longitude" value="${p.parking_longitude}">도</td>
	</tr>
	<tr>
		<td colspan="2">주차장의 상세정보</td>
	</tr>
	<tr>
		<td>구분</td>
		<td>
			<%String[] oper = {"공영", "민영", "개인"};
			int o = p.getParking_operation(); %>
			<select>
			  <%for(int i = 1; i < 4; i++) {%>
			  	<option value="<%=i%>"
			  	<%if(i == o) { %>
			  		selected
			  	<%}%>><%=oper[i-1]%></option>
			  <%} %>
			  </select>
		</td>
	</tr>
	<tr>
		<td>유형</td>
		<td>
			<%String[] type = {"노상", "노외"};
			int t = p.getParking_type();%>
			<select>
				<%for(int i = 1; i < 3; i++) { %>
					<option value="<%=i%>"
					<%if(i == t) { %>
						selected
					<%} %>><%=type[i-1]%></option>
				<%} %>
			</select>
		</td>
	</tr>
	<tr>
		<td>관리형태</td>
		<td>
			<%String[] manager = {"제공안됨", "관리인", "무인"};%>
		</td>
	</tr>
</table>
</body>
</html>