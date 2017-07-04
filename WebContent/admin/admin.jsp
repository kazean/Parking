<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>admin.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function(){
	
		var $parentObj = $("article");
		if ($parentObj.length == 0)
			$parentObj = $("body");

		// start of adminlogin
		$("form").submit(function() {
			var a_id = $("input[name=a_id]").val();
			var a_password = $("input[name=a_password]").val();

			$.ajax({
				url : 'admin.do',
				method : 'POST',
				//contentType:'application/json; charset=UTF-8',
				data : {'id' : a_id, 'pwd' : a_password},
				success : function(responseData) {
					var data = responseData.trim();
					if(data == "1") {
						alert("관리자 모드로 접속하셨습니다!");
						location.href = '<%=contextPath%>' + '/admin/';
					}
					else {
						alert("로그인 실패!\n" + data);
						console.log(data);						
					}
				},
				error : function(xhr, status, error) {
					console.log(xhr, status, error);
				}
			});
			return false;
		}); // end of admin login

		// start of menu click
		$("div>a").click(function() {
			var url = $(this).attr("href");
			$.ajax({
				url : url,
	   		    data:{'pageno':1},
				success : function(responseData) {
					if (url == 'adminlogout.do')
						location.href = responseData.trim();
					else {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
				}
			});
			return false;
		}); // end of menu click

	});
</script>
</head>

<body>
	<c:choose>
		<c:when test="${empty admin}">
			<h3>관리자 로그인</h3>
			<form method="post">
				아이디 : <input type="text" name="a_id" required><br>
				비밀번호 : <input type="password" name="a_password" required><br>
				<input type="submit" value="로그인">
			</form>
		</c:when>
		<c:otherwise>
			<div>
				<a href="customerList.do">고객관리</a>&nbsp;&nbsp;
				<a href="partnerList.do">파트너관리</a>&nbsp;&nbsp; 
				<a href="parkingList.do">주차장관리</a>&nbsp;&nbsp; 
				<a href="reviewList.do">리뷰관리</a>&nbsp;&nbsp;
				<a href="statistics.jsp">사용통계</a>&nbsp;&nbsp;
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>