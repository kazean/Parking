<%@ page contentType="text/html; charset=UTF-8"%>
<% String contextPath=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>leftmenu.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>



<script>

$(function(){
	
	
	//각각의 메뉴를 누를때마다 이동하는 액션
	  var $a =$(".menutable").find("a");
    $a.click(function(){
  	  var url = $(this).attr("href");
      $.ajax({url: url,
      	method : 'GET', 
          success: function(responseData){
          var result = responseData.trim();
          	$("article").empty();
            $("article").html(result);
        }
      }); return false; // end ajax
    });   //end click
	
	
});
</script>
<style>

/* 좌측 메뉴테이블 div */
.mainmenu{
float: left;
padding: 20px;
height : 500px;}

/* 좌측 메뉴테이블 */
.menutable{
position: relative;
margin :auto;
background-color: #B0A6A6;
padding : 10px;
text-align:right;
height : 30px;
font-size: large;
border-radius: 5px;
}
.menutable a{text-decoration: none; color: #F0F8FF;font-weight:bold;}
.menutable a:hover{ color: #666666; text-decoration: none; }

</style>

<body>


<!-- 현재남은좌석 자리보여줄 div 시작 -->
<div class="mainmenu">
 <table class="menutable">
  <tr> 
    <td width="17" height="20"></td>
    <td width="133" style="border-bottom: 1px solid black">MENU</td>
  </tr>
  
  <tr> 
    <td width="17" height="20"></td>
    <td width="133"><a href="presentStatus.jsp">현재상황</a></td>
  </tr>
  <tr> 
    <td height="20">&nbsp;</td>
    <td class="insert"><a href="partnerBoard.jsp">요청사항</a></td>
  </tr>
  <tr> 
    <td height="20"></td>
    <td><a href="reservation.jsp">예약현황</a></td>
  </tr>
  <tr> 
    <td height="20"></td>
    <td><a href="dddddd.jsp">수익현황</a></td>
  </tr>
   <tr> 
    <td height="20"></td>
    <td><a href="dddddd.jsp">나의정보</a></td>
  </tr>
  <tr> 
    <td height="200"></td>
  </tr>

</table>
</div>
<!-- 현재남은좌석 자리보여줄 div 끝-->
  

</body>
</html> 

