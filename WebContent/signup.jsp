<%@ page contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

  $(function(){
	  
	var $parentObj = $("article");
    if($parentObj.length == 0){
      $parentObj = $("body");
    }
    
   
	  
    //중복 비밀번호 체크시작
    var $c_id = $("input[name=c_id]");
    $("input[name=c_name]").focus(function(){
    	console.log("비밀번호 확인시작");
    	$.ajax({
    		url : 'checkId.do',
            method : 'POST',
            data : {c_id : $c_id.val() },
            success : function(responseData) {  
            	var result = responseData.trim();
            	if(result=="1") {
            		console.log("사용가능한 아이디 입니다.");
            		$('.warning').hide();
            	}else if(result=="-1") {
            		console.log("존재하는 아이디 입니다.");
            		$c_id.focus();
            		$('.warning').show();
            	}else if(result=="2") {
            		console.log("아이디를 입력해주세요.");
            		$('.warning').hide();
            	}
            }
    	}); return false;
    });
    
    //사인업 버튼클릭
    /* $('.signup').find('.btSignup').click(function(){   */
    var $form = $('.signup').find('form');
     $form.submit(function(){
   	  var $form = $('form');
      var $data=$form.serialize();
      console.log($data);
      $.ajax({ 
        url : 'signup.do',
        method : 'POST',
        data : $data,
        success : function(responseData) { 
        	console.log(responseData);
        	lotation.href=contextPath;
        	//$parentObj.html(responseData);
        }
      }); return false;  // end ajax
   });  // end submit
   
 }); // end function
  
  
</script>
</head>

<style>
/* position: absolute; top: 100px */
.signup{ width:300px; height:300px; border: 1px solid; border-radius: 50px;border-color:#F2B210;  margin:auto; padding:30px; position:absolute;
  top:50%;  left:50%;  background:#F2B210;  transform:translate(-50%, -50%)}
.infobox{margin:auto;}
.signup form{height:100px;margin:auto; }
.warning{display: none;}
.spSignupByEmail:hover { text-decoration: underline; cursor: default;}
</style>

<body>




<div class="signup" >
  <div class="infobox">signup</div>
<div>

  <form class="formSignupByEmail">
  ID(이메일) : <input  type = "text"  name ='c_id' placeholder="mailaddress@example.com" required>&nbsp;<img class="warning" alt="중복된 아이디 입니다." src="w.jpg"><br> 
  NAME:       <input type = "text" name ="c_name" required><br>
    비밀번호:     <input type = "password" name ="c_password" required><br>
   차량번호:       <input type = "text" name ="c_car_number" required ><br>
    휴대전화 번호:       <input type = "text" name ="c_phone_number"pattern="\d{3}-\d{3,4}-\d{4}" required > <br>'-' 포함입력하세요
    
    
    
    
    <br>
  <input class='btSignup' type='submit' value = '가입' >
  </form>
</div>
</div>




  
 




</body>
</html>