<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>partnerList</title>
<style>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function() {
	
	var $parentObj = $("article");
	if ($parentObj.length == 0)
		$parentObj = $("body");
	
	
	// start of menu click
	$("div>a").click(function() {
		var url = $(this).attr("href");
		$.ajax({
			url : url,
			success : function(responseData) {
					$parentObj.empty();
					$parentObj.html(responseData.trim());
			}
		});
		return false;
	}); // end of menu click
	

});
</script>
</head>

<body>

<div>
<a class="" href="partnerAdd.jsp">추가하기</a>&nbsp;&nbsp;

</div>
<h3>파트너 관리</h3>

</body>
</html>