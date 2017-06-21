<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var = "list" value="${requestScope.parkingList}" />
    
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>parkingList</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function() {
	
	var $parentObj = $("article");
	if ($parentObj.length == 0) {
		$parentObj = $("body");
	}
	
	// 전체 체크 박스 - 클릭 이벤트
	$("input[name=checkAll]").click(function(){
		if($("input[name=checkAll]").is(":checked") == true){
			$("input[name=check]").attr("checked", true);
		} else {
			$("input[name=check]").attr("checked", false);
		}
	});
	
	// 체크 박스 체크 - 이벤트
	function countCheckBox(){
		var data = [];
		
		$("input[name=check:checked").each(function(i){
			data.push($(this).val());
			console.log($(this).val());
		});

		jQuery.ajaxSettings.traditional = true;
	};
	
	// 삭제 버튼 - 클릭 이벤트
	$("input[name=delete]").click(function(){
		var resultConfirm = confirm('삭제하시겠습니까?');
		
		if(resultConfirm){
			countCheckBox();
			
			console.log("들어옴");
			/* $.ajax({
				url : 'parkingDelete.do',
				method : 'POST',
				data : {'data': data},
				success : function(responseData) {
					alert('삭제되었습니다.');
					$parentObj.empty();
					$parentObj.html(responseData.trim());
				}
			}); */
			
			return false;
		} else {
			return;			
		}
	});
	
	// 설정 버튼 - 클릭 이벤트
	$("input[name=detail]").click(function(){
		console.log($(this).attr("id"));
		$.ajax({
			url : 'parkingDetail.do',
			method : 'POST',
			data : {'parking_code': $(this).attr("id")},
			success : function(responseData) {
				$parentObj.empty();
				$parentObj.html(responseData.trim());
			}
		});
		return false;
	});
	
});
</script>
</head>

<body>
<h3>주차장 관리</h3>
	
	<div>
		<a href="parkingAdd.jsp">추가하기</a>&nbsp;&nbsp;
		<a href="#">내역보기</a>&nbsp;&nbsp;
	</div>
	
	<br><br>
	
	<div>
		<jsp:include page="parkingSearch.jsp"></jsp:include>
	</div>
	
	
	<br><br>
	
	총 ${fn:length(list)}개의 주차장내역이 존재합니다.
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" name="delete" value="삭제">
	<table border="1px solid">
		<tr>
			<td><input type="checkbox" name="checkAll"></td>
			<td>코드</td>
			<td>제휴</td>
			<td>운영</td>
			<td>이름</td>
			<td>주소</td>
			<td>전화번호</td>
			<td>업데이트</td>
			<td>상세보기</td>
		</tr>
		<c:forEach var="p" items="${list}">
			<tr>
				<td><input type="checkbox" id="${p.parking_code}" name="check"></td>
				<td>${p.parking_code}</td>
				<td> 
					<c:choose>
						<c:when test="${!empty p.parking_p_id}">O</c:when>
						<c:otherwise>X</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${p.parking_status == 1}">O</c:when>
						<c:otherwise>X</c:otherwise>
					</c:choose>
				</td>
				<td>${p.parking_name}</td>
				<td>${p.parking_address}</td>
				<td>
					<c:choose>
						<c:when test="${p.parking_phone_number == '0'}">X</c:when>
						<c:otherwise>${p.parking_phone_number}</c:otherwise>
					</c:choose>
				</td>
				<td>${p.parking_update_time}</td>
				<td><input type="button" id="${p.parking_code}" name="detail" value="설정"></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>