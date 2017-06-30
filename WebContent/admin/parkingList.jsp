<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="pageNum" value="${pageNum}"/>
<c:if test="${pageNum == null}">
	<c:set var="pageNum" value="1"/>
</c:if>
<c:set var="parkingSize" value="${parkingSize}"/>
<c:set var="currPage" value="${pageNum}"/>

<c:set var="startPage" value="${startPage}"/>
<%-- <c:set var="startPage" value="${(currPage / 10) * 10}"/>
<c:if test="${(currPage / 10) <= 1}">
	<c:set var="startPage" value="1"/>
</c:if>
<c:if test="${startPage > parkingSize}">
	<c:set var="startPage" value="${parkingSize / 10}"/>
</c:if> --%>

<c:set var="endPage" value="${startPage + 9}"/>
<c:if test="${endPage > parkingSize}">
	<c:set var="endPage" value="${parkingSize}"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>parkingList</title>
<style>
  tr td{text-align: center; border:1px solid; border-collapse:collapse;}
</style>
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
				url : 'parkingDelete',
				method : 'DELETE',
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
			url : 'parkingDetail',
			method : 'GET',
			data : {'parking_code': $(this).attr("id")},
			success : function(responseData) {
				$parentObj.empty();
				$parentObj.html(responseData.trim());
			}
		});
		return false;
	});
	
	// start of .page click
	$(".page").click(function() {
		var num = $(this).html().trim();
		if(num == null)
			num = 1;
		$.ajax({url:'parkingList.do',
				method:'post',
				data:{'num': num},
				success:function(responseData) {
					$parentObj.empty();
					$parentObj.html(responseData.trim());
				}
		});
		return false;
	}); // end of .page click
	
	// start of .prevPage click
	$('.prevPage').click(function() {
		var pNum = ${startPage} - 10;
		if(pNum < 0)
			pNum = 1;
		$.ajax({url:'parkingList.do',
				method:'post',
				data:{'num': pNum},
				success:function(responseData) {
					$parentObj.empty();
					$parentObj.html(responseData.trim());
				}
		
		});
		return false;
	}); // end of .prevPage click
	
	// start of .nextPage click
	$('.nextPage').click(function() {
		var nNum = ${endPage} + 1;
		if(nNum > ${parkingSize})
			nNum = ${parkingSize};
			$.ajax({url:'parkingList.do',
					method:'post',
					data:{'num':nNum},
					success:function(responseData) {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
			});
			return false;
	});	// end of .nextPage click
	
});
</script>
</head>

<body>
<h3>주차장 관리</h3>
	<div>
		<a class="parkingAdd" href="">추가하기</a>&nbsp;&nbsp;
		<a href="">내역보기</a>&nbsp;&nbsp;
	</div>
	<br>
	<div>
		<jsp:include page="parkingSearch.jsp"></jsp:include>
	</div>
	<br>
	<table style="border-collapse:collapse;">
		<tr style="border:0px;">
			<td colspan="4" style="border:0px;">총 ${(parkingSize * 15) + 1}개의 주차장내역이 존재합니다.</td>
			<td colspan="5" style="text-align:right; border:0px;"><input type="button" name="delete" value="삭제"></td>
		</tr>
		<tr>
			<td><input type="checkbox" name="checkAll"></td>
			<td>코드</td>
			<td>제휴</td>
			<td>운영</td>
			<td style="width:200px;">이름</td>
			<td style="width:300px;">주소</td>
			<td style="width:150px;">전화번호</td>
			<td style="width:100px;">업데이트</td>
			<td style="width:70px;">상세보기</td>
		</tr>
		<c:forEach var="p" items="${pList}">
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
	<div style="text-align:center;">
		<c:if test="${startPage > 10}">
			<a class="prevPage" href="">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			[<a class="page" href="">${i}</a>]&nbsp;
		</c:forEach>
		<c:if test="${endPage < parkingSize}">
			<a class="nextPage" href="">[다음]</a>
		</c:if>
	</div>
</body>
</html>