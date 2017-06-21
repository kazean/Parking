<%@page import="java.util.List"%>
<%@page import="com.parking.vo.Parking"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%List<Parking> parking= (List<Parking>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<title>map.jsp</title>
</head>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- 구글지도API -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD86eCsrQDfaNr1uK4XO0h8q3BBFXQgbvI&callback=initMap" async defer></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script>

function initMap() {
    var gangnam = {lat: 37.47715365, lng: 127.04992891};
    
    /* 맵생성 */
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 16,
      center: gangnam,  //기본점 (강남)
      mapTypeId: 'roadmap'
    });
    
    
    /* 마커관련 메서드 */
    function addMarker(feature) {
    	
    /* 마커생성 */
      var marker = new google.maps.Marker({
        position: feature.position,
        map: map,
        icon: 'marker50.png'
      });
    
    
  	// 마커 클릭 이벤트
  	var content = "주차장 정보가 들어갈 페이지"
  	var infowindow = new google.maps.InfoWindow({ content: content});
      marker.addListener('click', function() {
           infowindow.open(map, marker);
      });
    }
    
    /* 마커 찍을 인자 생성 */
    var features = [];
    <%for(int i=0; i< parking.size() ; i++) {%>
        var latitude = '<%=parking.get(i).getParking_latitude()%>';
        var longitude = '<%=parking.get(i).getParking_longitude()%>';
        console.log("latitude :"+latitude+"latitude :"+latitude);
        console.log("<%=parking.get(i).getParking_rates()%>");
        console.log("<%=parking.get(i).getParking_name()%>");
        features[<%=i%>] = {position: new google.maps.LatLng(latitude, longitude), 
                  info: '<%=parking.get(i).getParking_rates()*parking.get(i).getParking_rates_time()%>',
                  title:'<%=parking.get(i).getParking_name()%>' 
        }
        addMarker(features[<%=i%>]);
    <%}%>
    
    


    
    
  }

</script>

<style>
#map {width: 100%;height: 400px; background-color: grey;  }
#map {border : 1px solid; width : 800px ;height:500px;display: inline-block;float :left;  margin : 10px}

.mapDetailList {border : 1px solid; width : 200x; height:500px; inline-block;float :left; margin : 10px}
.seletTime {border : 1px solid; width : 200x; height:500px; margin : 10px ; width : auto;height: 80px}
</style>

<body>

<!-- 상단 검색창 시작 -->
<div class="seletTime">
 <form>
    <input type="text" id="location" placeholder = "주차하려는 지역명"/>
    <input type="text" id="reserveEntranceTime" placeholder="입차일자"/>
    <input type="text"  id="reserveExitTime" placeholder="출차일자"/>
    <input type="button" id ="btReserve" value="주차장 선택하러 가기"/>
  </form> 
</div>
<!-- 상단 검색창 끝 -->

 
<!-- 디테일 리스트 시작 -->
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
<!-- 디테일 리스트 끝 -->

<!-- 큰 지도 보여주기 시작 -->
<div id="map">
지도출력을 위한 페이지 입니다.
</div>
<!-- 디테일 리스트 끝 -->


</body>
</html>