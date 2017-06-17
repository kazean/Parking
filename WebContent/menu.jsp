<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<style>
div a{

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

  $(function(){
    
    
	  var $a =$(".menu").find("a");
      $a.click(function(){
    	  var url = $(this).attr("href");
    	  console.log(url+"클릭");
        $.ajax({url: url,
            method : 'GET', 
            success: function(responseData){
            var result = responseData.trim();
            	if(url =="logout.do"){
            	alert("로그아웃 되었습니다.");
            	location.href ="<%=contextPath%>";
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

<a href="index.jsp" class="home">home</a>
<c:set var="customer" value="${sessionScope.customer}"/>
<c:choose>
  <c:when test="${empty customer}" >
    <a href="signupcustomermain.jsp">join</a> &nbsp;&nbsp;
    <a href="login.jsp">login</a>
  </c:when>
  <c:otherwise>
    <a href ="logout.do">logout</a>&nbsp;&nbsp;
  </c:otherwise>
</c:choose>
    
  
</div>
<div class="mainBox"></div>
