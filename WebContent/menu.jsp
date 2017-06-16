<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<style>

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

  $(function(){
    
    var $header =$("header");
    var $a =$header.find("a");
    var url = $(this).attr("href");
      $a.click(function(){
    	  console.log(url+"클릭");
        $.ajax({url: url,
            method : 'GET', 
            success: function(responseData){
            	alert(url);
            if(url =="logout.do"){
            	alert("로그아웃!");
            	location.href ="<%=contextPath%>";
            }else{
              $("article").empty();
              $("article").html(responseData.trim()); 
            } 
          }
        }); return false; // end ajax
      });   //end click
  });
  
</script>

<style>

.menu{
margin :auto;
background-color: #F2B210;
padding : 50px  0px   0px   0px;
text-align:right;
height : 40px;
font-size: large;
}

.menu a{
text-decoration: none; 
color: #F0F8FF;
font-weight:bold;
}

.menu a:hover{ color: #666666; text-decoration: none; }

</style>

<div class="menu">
<c:set var="customer" value="${sessionScope.customer}"/>
<c:choose>
  <c:when test="${empty customer}" >
    <a href="signup.jsp">join</a> &nbsp;&nbsp;
    <a href="login.jsp">login</a> &nbsp;&nbsp;
  </c:when>
  <c:otherwise>
    <a href ="logout.do">logout</a>&nbsp;&nbsp;
  </c:otherwise>
</c:choose>
    
  
</div>
<div class="mainBox"></div>
