<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>map.jsp</title>
</head>
  
<style>
#map {    width: 100%;    height: 400px;    background-color: grey;  }
.mapDetailList {border : 1px solid; width : 200x; height:500px; inline-block;float :left; margin : 10px}
#map {border : 1px solid; width : 800px ;height:500px;display: inline-block;float :left;  margin : 10px}
.seletTime {border : 1px solid; width : 200x; height:500px; margin : 10px ; width : auto;height: 80px}
</style>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- 구글지도API -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD86eCsrQDfaNr1uK4XO0h8q3BBFXQgbvI&callback=initMap"></script>
<script>

$(function(){
	
	function initMap() {
        var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
	
});

</script>
<style>

</style>

<body>

<div class="seletTime">
 <form>
    <input type="text" id="location" placeholder = "주차하려는 지역명"/>
    <input type="text" id="reserveEntranceTime" placeholder="입차일자"/>
    <input type="text"  id="reserveExitTime" placeholder="출차일자"/>
    <input type="button" id ="btReserve" value="주차장 선택하러 가기"/>
  </form> 
</div>

 

<div class ="mapDetailList">

<c:set var="list" value="${requestScope.list}"/>

<ol>

<c:forEach var ="parking"   items="${list}">
 <hr>
  <li> 주차장이름     <br>
            즐겨찾기 스위치,<br>
            평점,<br>
            위도 : ${parking.parking_latitude} //  경도 : ${parking.parking_longitude} //  </li>
</c:forEach>
</ol>
</div>


<div id="map">

지도출력을 위한 페이지 입니다.

</div>

</body>
</html>