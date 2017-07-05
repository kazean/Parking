<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<style>

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

  $(function(){
    
    
	  //각각의 메뉴를 누를때마다 이동하는 액션
	  var $a =$(".menu").find("a");
	  var c_id = $('.mypage').attr('id');
      $a.click(function(){
    	  var url = $(this).attr("href");
        $.ajax({url: url,
        	method : 'GET', 
            success: function(responseData){
            var result = responseData.trim();
            	if(url =="partnerLogout.do"){
            	alert("로그아웃 되었습니다.");
            	location.href ="<%=contextPath%>"+'/partner';
            	}else if(url =="index.jsp"){
            	location.href =url;
            	
            }else{
               console.log(result);
            	$("article").empty();
               $("article").html(result);
            } 
          },
        }); return false; // end ajax
      });   //end click

    	  
    	  
      
      
      
      
      
      
      
  });
  
</script>

<style>

.menu{
position: relative;
margin :auto;
background-color: #F2B210;
padding : 10px;
text-align:right;
height : 30px;
font-size: large;

}

.menu a{text-decoration: none; color: #F0F8FF;font-weight:bold;}
.menu a:hover{ color: #666666; text-decoration: none; }
.home{float:left }
</style>

<div class="menu">

<a href="index.jsp" class="home">Home</a>
<c:set var="partner" value="${sessionScope.partner}"/>
<c:choose>
  <c:when test="${empty partner}" >
    <!-- <a href="signupcustomermain.jsp">Join</a> &nbsp;&nbsp; -->
    <a href="index.jsp">Login</a>
  </c:when>
  <c:otherwise>
    <a href ="partnerLogout.do">Logout</a>&nbsp;&nbsp;
  </c:otherwise>
</c:choose>
    
  
</div>
<div class="mainBox"></div>
