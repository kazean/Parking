<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>login</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

	$(function() {
		$("input[type=button]").click(function() {
			var a_id = $("input[name=a_id]").val();
			var a_password = $("input[name=a_password]").val();

			// obj
			var admin = {
				'a_id' : a_id,
				'a_password' : a_password
			};

			$.ajax({
				url : 'admin.do',
				method : 'POST',
				contentType : 'application/json; charset=UTF-8',
				data : JSON.stringify(admin),
				success : function(responseData) {
					$("article").empty();
					$("header").html(responseData.trim());
				},
				error : function(xhr, status, error) {
					console.log(xhr, status, error);
				}
			});
			return false;
		});

	});
</script>
</head>

<body>
	<h3>관리자 로그인</h3>

	<div id="adminLogin" value="adminLogin">
		<form>
			아이디: <input type="text" name="a_id"> 
			<br> 비밀번호: 
			<input type="password" name="a_password"> 
			<input type="button" value="로그인">
		</form>
	</div>
</body>
</html>