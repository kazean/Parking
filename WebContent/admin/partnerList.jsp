<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String contextPath = request.getContextPath();%>

<c:set var="partnerSize" value="${partnerSize}"/>
<c:set var="startPage" value="${startPage}"/>
<c:set var="endPage" value="${startPage + 9}"/>
<c:if test="${endPage > partnerSize}">
	<c:set var="endPage" value="${partnerSize}"/>
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
<title>partnerList</title>
<style>
  tr td{text-align: center; border:1px solid; border-collapse:collapse;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function() {
	
	var $parentObj = $("article");
	if ($parentObj.length == 0)
		$parentObj = $("body");
	
	// start of .page click
	$(".page").click(function() {
		var num = $(this).html().trim();
		if(${flag} == 0) {
			$.ajax({url:'partnerList.do',
					method:'post',
					data:{'num':num, 'sortValue':${sortValue}},
					success:function(responseData) {
						var data = responseData.trim();
						if(data != "-1") {
							$parentObj.empty();
							$parentObj.html(data);
						}
						else {
							alert('로그인을 먼저 해주세요.');
							location.href='<%=contextPath%>';
						}
					}
				
			});
			return false;
		}
		else if(${flag} == 1) {
			$.ajax({url:'partnerSearch.do',
					method:'post',
					data:{'searchValue':${searchValue}, 'option':${option}, 'num':num},
					success:function(responseData) {
						var data = responseData.trim();
						if(data != "-1") {
							$parentObj.empty();
							$parentObj.html(data);
						}
						else {
							alert('로그인을 먼저 해주세요.');
							location.href='<%=contextPath%>';
						}
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
		$.ajax({url:'partnerList.do',
				method:'post',
				data:{'num':pNum},
				success:function(responseData) {
					var data = responseData.trim();
					if(data != "-1") {
						$parentObj.empty();
						$parentObj.html(data);
					}
					else {
						alert('로그인을 먼저 해주세요.');
						location.href='<%=contextPath%>';
					}
				}
		});
		return false;
	});
	// end of .prevPage click
	
	// start of .nextPage click
	$('.nextPage').click(function() {
		var nNum = ${endPage} + 1;
		if(nNum > ${partnerSize})
			nNum = ${partnerSize};
		$.ajax({url:'partnerList.do',
				method:'post',
				data:{'num':nNum},
				success:function(responseData) {
					var data = responseData.trim();
					if(data != "-1") {
						$parentObj.empty();
						$parentObj.html(data);
					}
					else {
						alert('로그인을 먼저 해주세요.');
						location.href='<%=contextPath%>';
					}
				}
		});
		return false;
	});
	// end of .nextPage click
});
</script>
</head>
<body>
<h3>파트너 관리</h3>

<!-- 검색시작 -->
<div class="search">

  <select name="select" class="searchItem">
    <option selected value="p_id">아이디</option>
    <option selected value="p_license">사업자번호</option>
    <option selected value="p_name">이름</option>
  </select> 
  
  <input name="textfield" type="text" class="searchValue"> 
  <a href=""><img src="image/searchBt.jpg" width="49" height="25" border="0"  class="btSearch"></a>
        

</div>
<!-- 검색끝 -->
	<table style="border-collapse:collapse;">
		<tr style="border:0px;">
			<td colspan="6" style="text-align:left; border:0px;">
				<c:if test="${ptnAllSizeSearch == null}">
					총 ${ptnAllSize}명의 파트너가 있습니다.
				</c:if>
				<c:if test="${ptnAllSizeSearch != null}">
					총 ${ptnAllSizeSearch}명의 파트너가 있습니다.
				</c:if>
			</td>
			<td colspan="4" style="text-align:right; border:0px;">
				<input type="button" name="add" value="추가">
				<input type="button" name="delete" value="삭제">
			</td>
		</tr>
		<tr>
			<td style="width:30px;"><input type="checkbox" name="checkAll"></td>
			<td style="width:100px;">아이디</td>
			<td style="width:80px;">이름</td>
			<td style="width:150px;">전화번호</td>
			<td style="width:150px;">사업자번호</td>
			<td style="width:80px;">은행</td>
			<td style="width:200px;">계좌번호</td>
			<td style="width:150px;">등록일자</td>
			<td style="width:70px;">제휴상태</td>
			<td style="width:70px;">상세보기</td>
		</tr>
		<c:forEach var="ptn" items="${ptnList}">
		<tr>
			<td><input type="checkbox" class="chkselect" value="${ptn.p_id}"></td>
			<td>${ptn.p_id}</td>
			<td>${ptn.p_name}</td>
			<td>${ptn.p_phone_number}</td>
			<td>${ptn.p_license}</td>
			<td>${ptn.p_bank}</td>
			<td>${ptn.p_account}</td>
			<td>${ptn.p_date}</td>
			<td>${ptn.p_status}</td>
			<td><input type="button" id="${ptn.p_id}" name="detail" value="설정"></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="10" style="text-align:cetner; border:0px;">
				<c:if test="${startPage > 10}">
					<a class="prevPage" href="">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					[<a class="page" href="">${i}</a>]&nbsp;
				</c:forEach>
				<c:if test="${endPage > partnerSize}">
					<a class="nextPage" href="">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>