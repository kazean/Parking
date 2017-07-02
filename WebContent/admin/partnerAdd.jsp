<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>partnerAdd</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(function() {

		/* <input type="text" name="p_id" >
		<input type="text" name="p_password">
		<input type="text" name="p_name">
		<input type="text" name="p_phone_number">
		<input type="text" name="p_license">
		<input type="text" name="p_bank">
		<input type="text" name="p_account">


		<button name = "btInsert">  입력</button> */

		

		var $form = $(".insertForm");
		$form.submit(function(){
			event.preventDefault(); // 기본효과를 막음
			console.log("입력버튼 클릭!");
			var data = $form.serialize();
			$.ajax({
				url : "addPartner.do",
				data : data,
				method : 'POST',
				success : function(responseData){
					
					console.log("입력성공");
					
				}
			});  return false;
			
		});
		
		
		
		
		
		
	});
</script>
</head>
<body>


<h3>파트너 추가</h3>



   <%--  #{p_id},
    #{p_password},
    #{p_name},
    #{p_phone_number},
    #{p_license},
    #{p_bank},
    #{p_account},
    sysdate,
    #{p_status} --%>


<form class = "insertForm">

아이디 : <input type="text" name="p_id" >   <br>
비밀번호 : <input type="text" name="p_password"><br>
이름 : <input type="text" name="p_name"><br>
전화번호 : <input type="text" name="p_phone_number"><br>
사업자번호 : <input type="text" name="p_license"><br>
계좌은행 : <input type="text" name="p_bank"><br>
계좌번호  :<input type="text" name="p_account"><br>
관리자명 (어드민 계정 ) :<input type="text" name="p_register_admin_id"><br>

<input type="submit" value="입력하기" name = "smInsert">

</form>

</body>
</html>