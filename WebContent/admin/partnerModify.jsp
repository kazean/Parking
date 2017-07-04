<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>partnerAdd</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(function() {

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
		
		
		
		//취소버튼액션 - 리스트화면으로 보내기
        $('#btCancel').click(function(){
        	$.ajax({
                url : "partnerList.do",
                method: 'GET', 
                data:{'pageno':1},
                success : function(responseData){
                  $("article").empty();
                  $("article").html(responseData.trim()); 
                }
              }); return false;
        });
		
		
		
		
	});
</script>
<style>
.insertForm h5{width: 200px; display: inline-block;}

</style>
</head>
<body>


<h3>파트너 수정</h3>



   <%--  #{p_id},
    #{p_password},
    #{p_name},
    #{p_phone_number},
    #{p_license},
    #{p_bank},
    #{p_account},
    sysdate,
    #{p_status} --%>

<c:set  var="partner"   value="${requestScope.partner}" />


<form class = "insertForm">

<h5>아이디 : </h5> <input type="text" name="p_id"  value="${partner.p_id}">   <br>
<h5>비밀번호 :</h5>  <input type="text" name="p_password" value="${partner.p_password}"><br>
<h5>이름 :</h5>  <input type="text" name="p_name" value="${partner.p_name}"><br>
<h5>전화번호 : </h5> <input type="text" name="p_phone_number" value="${partner.p_phone_number}"><br>
<h5>사업자번호 :</h5>  <input type="text" name="p_license" value="${partner.p_license}"><br>
<h5>계좌은행 :</h5>  <input type="text" name="p_bank" value="${partner.p_bank}"><br>
<h5>계좌번호  :</h5> <input type="text" name="p_account" value="${partner.p_account}" ><br>
<h5>관리자명 (어드민 계정 ) :</h5> <input type="text" name="p_register_admin_id" value="${partner.p_register_admin_id}"><br>

<input type="submit" value="입력하기" name = "smInsert">
<input type="submit" value="취소" id = "btCancel">
</form>

</body>
</html>