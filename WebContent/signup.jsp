<%@ page contentType="text/html; charset=UTF-8"%>
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
	  
    $('.signup').find('.btSignup').click(function(){  
   	  var $form = $('form');
      var $data=$form.serialize();
      console.log($data);
      $.ajax({ 
        url : 'signup.do',
        method : 'POST',
        data : $data,
        success : function(responseData) {  
        	$parentObj.html(responseData);
        },
      }); return false;  // end ajax
   });  // end submit
 }); // end function
  
  
</script>
</head>

<style>


</style>

<body>

<div class="signup" >
  <div class="infobox">signup</div>
  <form>
  NAME:       <input type = "text" name ="c_name" ><br>
  ID(이메일) :         <input  type = "text"  name ='c_id' required><br>
    비밀번호:      <input type = "password" name ="c_password"><br>
    차량번호:       <input type = "text" name ="c_car_number" ><br>
    휴대전화 번호:       <input type = "text" name ="c_phone_number" ><br>
   <input class='btSignup' type='button' value = '가입' >
 
</div>



</body>
</html>