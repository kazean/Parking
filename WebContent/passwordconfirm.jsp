<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passwordconfirm.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

$(function(){
	
	
	$('.passwordconfirm').find('form').submit(function(){
		console.log("비밀번호 체크하러 가요.");
		console.log("비밀번호 체크하러 가요.");
		var password = $('input[name=c_password]').val();
		$.ajax({
			url : 'checkpassword.do',
			method : 'post',
			data :{ 'c_password' : password},
			success : function(responseData){
				$("article").empty();
	            $("article").html(responseData.trim());
			}
		}); return false;
	});
	
	
	
	
});

</script>
<style>

.passwordconfirm{ width:300px; height:300px; border: 1px solid; border-radius: 50px;border-color:#F2B210;  margin:auto; padding:30px; position:absolute;
  top:50%;  left:50%;  background:#F2B210;  transform:translate(-50%, -50%)}
.passwordconfirmbox{margin:auto;}
.passwordconfirm form{height:100px;margin:auto; }

</style>

<body>

<div class="passwordconfirm" >
  <div class="passwordconfirmbox">비밀번호 확인</div>
 <form>  
 정보보호를 위해서 비밀번호를 다시확인 하겠습니다.
   <h5> 비밀번호 : </h5> <input type ="password" name="c_password">
    <input type = "submit" value = "확인">
 </form>
</div>


</body>
</html>