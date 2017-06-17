<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signupcustomermain.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

  $(function(){
	  
	var $parentObj = $("article");
    if($parentObj.length == 0){
      $parentObj = $("body");
    }
    
    //이메일로 가입하기 클릭
    $('.spSignupByEmail').click(function(){
    	console.log("이메일로가입하기 클릭됨");
    	$.ajax({
    		url: "signup.jsp",
    		success : function(responseData) { 
    			var result = responseData.trim();
    	    	console.log(responseData.trim());
    			$parentObj.empty();
    			$("article").html(result); 
    		}
    	});return false;
			/* $parentObj.html(responseData.trim()); */

    });
 }); // end function
  
  
</script>
</head>

<style>
/* position: absolute; top: 100px */
.signup{ width:300px; height:300px; border: 1px solid; border-radius: 50px;border-color:#F2B210;  margin:auto; padding:30px; position:absolute;
  top:50%;  left:50%;  background:#F2B210;  transform:translate(-50%, -50%)}
.infobox{margin:auto;}
.spSignupByEmail:hover { text-decoration: underline; cursor: default;}
</style>

<body>




<div class="signup" >
<div class="infobox">signup</div>
<div>
<span class="spSignupByEmail">이메일로 가입하기</span>
</div>



</div>




  
 




</body>
</html>