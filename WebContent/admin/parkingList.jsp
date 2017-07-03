<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="parkingSize" value="${parkingSize}"/>
<c:set var="startPage" value="${startPage}"/>
<c:set var="endPage" value="${startPage + 9}"/>
<c:if test="${endPage > parkingSize}">
	<c:set var="endPage" value="${parkingSize}"/>
</c:if>

<c:set var="flag" value="${flag}"/>
<c:if test="${flag == null}">
	<c:set var="flag" value="0"/>
</c:if>

<c:set var="sortValue" value="${sortValue}"/>
<c:if test="${sortValue == null}">
	<c:set var="sortValue" value="0"/>
</c:if>

<c:set var="searchValue" value="${searchValue}"/>
<c:if test="${searchValue == null}">
	<c:set var="searchValue" value="0"/>
</c:if>

<c:set var="option" value="${option}"/>
<c:if test="${option == null}">
	<c:set var="option" value="0"/>
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
	var $all = $("input[name=checkAll]");
	$all.change(function() {
		if($all.prop("checked") == true)
			$("input[name=check]").prop('checked', true);
		else
			$("input[name=check]").prop('checked', false);
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
		if(${flag} == 0) {
			$.ajax({url:'parkingList.do',
					method:'post',
					data:{'num': num, 'sortValue':${sortValue}},
					success:function(responseData) {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
			});
			return false;
		}
		else if(${flag} == 1) {
			$.ajax({url:'parkingSearch.do',
					method:'post',
					data:{'searchValue':${searchValue}, 'option':${option}, 'num':num},
					success:function(responseData) {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
			});
			return false;
		}
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
	
	// 추가 버튼 - 클릭 이벤트
	$("input[name=add]").click(function() {
		$.ajax({url:'parkingAdd.jsp',
				method:'post',
				success:function(responseData) {
					$parentObj.empty();
					$parentObj.html(responseData.trim());
				}
		});
		return false;
	});
	
	// 삭제 버튼 - 클릭 이벤트
	$("input[name=delete]").click(function(){
		var flag = confirm('삭제하시겠습니까?');
		if(flag == true) {
			var chklist = new Array();
			var count = 0;
			var chkbox = $("input[name=check]");
			for(var i = 0; i < chkbox.length; i++) {
				if(chkbox[i].checked == true) {
					chklist[count] = chkbox[i].value;
					count++;
				}
			}
			jQuery.ajaxSettings.traditional = true;
			$.ajax({url:'parkingDelete.do',
					method:'post',
					data:{'chklist':chklist},
					success:function(responseData) {
						if(responseData.trim() != "-1") {
							alert('삭제되었습니다!');
							$parentObj.empty();
							$parentObj.html(responseData.trim());
						}
						else {
							alert("삭제하는데 실패했습니다!");
						}
					}
			});
			return false;
		}
	});
});
</script>
</head>
<body>
<h3>주차장 관리</h3>
	<div>
		<a href="">내역보기</a>&nbsp;&nbsp;
	</div>
	<br>
	<div>
		<jsp:include page="parkingSearch.jsp"></jsp:include>
	</div>
	<br>
	<table style="border-collapse:collapse;">
		<tr style="border:0px;">
			<td colspan="6" style="text-align:left; border:0px;">
				<c:if test="${pAllSizeSearch == null}">
					총 ${pAllSize}개의 주차장 내역이 존재합니다.
				</c:if>
				<c:if test="${pAllSizeSearch != null}">
					총 ${pAllSizeSearch}개의 검색결과가 있습니다.
				</c:if>
			</td>
			<td colspan="3" style="text-align:right; border:0px;">
				<input type="button" name="add" value="추가">
				<input type="button" name="delete" value="삭제"></td>
		</tr>
		<tr>
			<td style="width:30px;"><input type="checkbox" name="checkAll"></td>
			<td style="width:50px;">코드</td>
			<td style="width:40px;">제휴</td>
			<td style="width:40px;">운영</td>
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