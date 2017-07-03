<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="customerSize" value="${customerSize}"/>
<c:set var="startPage" value="${startPage}"/>
<c:set var="endPage" value="${startPage + 9}"/>
<c:if test="${endPage > customerSize}">
	<c:set var="endPage" value="${customerSize}"/>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>customerList</title>
<style>
  tr td{text-align: center; border:1px solid; border-collapse:collapse;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	// start of function
	$(function() {
		var $parentObj = $("article");
		if($parentObj.length == 0)
			$parentObj = $("body");
		
		// 전체 체크 박스 - 클릭 이벤트
		var $all = $("input[name=checkAll]");
		$all.change(function() {
			if($all.prop("checked") == true)
				$("input[name=check]").prop('checked', true);
			else
				$("input[name=check]").prop('checked', false);
		});
		
		// start of .page click
		$(".page").click(function() {
			var num = $(this).html().trim();
			if(${flag} == 0) {
				$.ajax({url:'customerList.do',
						method:'post',
						data:{'num':num, 'sortValue':${sortValue}},
						success:function(responseData) {
							$parentObj.empty();
							$parentObj.html(responseData.trim());
						}
				});
				return false;
			}
			else if(${flag} == 1) {
				$.ajax({url:'customerSearch.do',
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
			$.ajax({url:'customerList.do',
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
			if(nNum > ${customerSize})
				nNum = ${customerSize};
				$.ajax({url:'customerList.do',
						method:'post',
						data:{'num':nNum},
						success:function(responseData) {
							$parentObj.empty();
							$parentObj.html(responseData.trim());
						}
				});
				return false;
		});	// end of .nextPage click
		
		// start of add click 
		$("input[name=add]").click(function() {
			$.ajax({url:'customerAdd.jsp',
					method:'post',
					success:function(responseData) {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
			});
			return false;
		}); // end of add click
		
		// start of detail click
		$("input[name=detail]").click(function() {
			var id = $(this).attr("id");
			$.ajax({url:'customerDetail.do',
					method:'post',
					data:{'c_id': id},
					success:function(responseData) {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
			});
			return false;
		}); // end of detail click
		
	}); // end of function
</script>
</head>
<body>
<h3>고객 관리</h3>
<br>
<table style="border-collapse:collapse;">
	<tr style="border:0px;">
		<td colspan="4" style="text-align:left; border:0px;">
			<c:if test="${cAllSizeSearch == null}">
				총 ${cAllSize}명의 고객이 있습니다.
			</c:if>
			<c:if test="${cAllSizeSearch != null}">
				 총 ${cAllSizeSearch}명의 검색결과가 있습니다.
			</c:if>
		</td>
		<td colspan="5" style="text-align:right; border:0px;">
			<input type="button" name="add" value="추가">
			<input type="button" name="delete" value="삭제">
		</td>
	</tr>
	<tr>
		<td style="width:30px;"><input type="checkbox" name="checkAll"></td>
		<td style="width:150px;">아이디</td>
		<td style="width:80px;">이름</td>
		<td style="width:150px;">전화번호</td>
		<td style="width:100px;">차량 번호</td>
		<td style="width:150px;">카드 번호</td>
		<td style="width:150px;">가입날짜</td>
		<td style="width:50px;">상태</td>
		<td style="width:70px;">상세보기</td>
	</tr>
	<c:forEach var="c" items="${cList}">
	<tr>
		<td><input type="checkbox" class="chkselect" value="${c.c_id}"></td>
		<td>${c.c_id}</td>
		<td>${c.c_name}</td>
		<td>${c.c_phone_number}</td>
		<td>${c.c_car_number}</td>
		<td>${c.c_card_number}</td>
		<td>${c.c_date}</td>
		<td>${c.c_status}</td>
		<td><input type="button" id="${c.c_id}" name="detail" value="설정"></td>
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
	<c:if test="${endPage < customerSize}">
		<a class="nextPage" href="">[다음]</a>
	</c:if>
</div>
</body>
</html>