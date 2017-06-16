<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>index.jsp</title>
<!-- hello -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(function(){
	  var $parentObj = $("article");
	  if($parentObj.length ==0){
	    $parentObj=$("body");
	  }
	    	  
	  var $form = $("form");
	  $form.submit(function(){
	    $.ajax({
	      url:"selectAll.do",
	      method:'POST',
	      success:function(response){
	        $parentObj.empty();
	        $parentObj.html(response.trim());
	      }
	    });
	    return false;
	  });
	  
	});
</script>
</head>

<body>
	<div>
	  <form>
	    <input type="submit" value="submit"> <!-- 추가예정 -->
	  </form>
	</div>
    
    <div>
      <jsp:include page="menu.jsp"></jsp:include>
    </div> 
</body>
</html>