<%@page import="com.parking.vo.Parking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="p" value="${parking}"/>
<%Parking p = (Parking)request.getAttribute("parking");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>parkingDetail</title>
<style>
	tr td{border:1px solid; border-collapse:collapse; text-align:center;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	// start of function
	$(function() {
		
		var $parentObj = $("article");
		if($parentObj.length == 0) {
			$parentObj = $("body");
		}
		
		// start of modify click
		$("input[name=modify]").click(function() {
			var p = makeJSON();
			$.ajax({url:'parkingModify.do',
					method:'post',
					data:{'jsonStr':JSON.stringify(p)},
					success:function(responseData) {
						var data = responseData.trim();
						if(data != "-1") {
							alert("성공적으로 수정되었습니다.");
							$parentObj.empty();
							$parentObj.html(data);
						}
						else {
							alert("추가하는데 실패했습니다.");
							console.log(data);
						}
					}
			});
			return false;
		}); // end of modify click
		
		// start of makeJSON
		function makeJSON(){
			var p = {}
					p["parking_p_id"] = $("input[name=parking_p_id]").val(); 
					p["parking_name"] = $("input[name=parking_name]").val();
					p["parking_address"] = $("input[name=parking_address]").val();
					p["parking_phone_number"] = $("input[name=parking_phone_number]").val();
					p["parking_latitude"] = $("input[name=parking_latitude]").val();
					p["parking_longitude"] = $("input[name=parking_latitude]").val();
					p["parking_operation"] = $("select[name=parking_operation]").val();
					p["parking_type"] = $("select[name=parking_type]").val();
					p["parking_is_manager"] = parking_is_manager;
					p["parking_is_machine"] = parking_is_machine;
					p["parking_is_valet"] = parking_is_valet;
					p["parking_is_cctv"] = parking_is_cctv;
					p["parking_is_motorcycle"] = parking_is_motorcycle;
					p["parking_impossible_car_type"] = parking_impossible_car_type;
					p["parking_pay_type"] = $("select[name=parking_pay_type]").val();
					p["parking_floor"] = $("input[name=parking_floor]").val();
					p["parking_basement_floor"] = $("input[name=parking_basement_floor]").val();
					p["parking_capacity"] = $("input[name=parking_capacity]").val();
					p["parking_rates"] = $("input[name=parking_rates]").val();
					p["parking_rates_time"] = $("input[name=parking_rates_time]").val();
					p["parking_add_rates"] = $("input[name=parking_add_rates]").val();
					p["parking_add_rates_time"] = $("input[name=parking_add_rates_time]").val();
					p["parking_day_rates"] = $("input[name=parking_day_rates]").val();
					if($("input[name=parking_month_rates]").val() == "")
						p["parking_month_rates"] = "0";
					else
						p["parking_month_rates"] = $("input[name=parking_month_rates]").val();
					p["parking_weekdays_begin_time"] = $("input[name=parking_weekdays_begin_time]").val();
					p["parking_weekdays_end_time"] = $("input[name=parking_weekdays_end_time]").val();
					if($("input[name=parking_sat_begin_time]").val() == "")
						p["parking_sat_begin_time"] = "00:00:00";
					else
						p["parking_sat_begin_time"] = $("input[name=parking_sat_begin_time]").val();
					if($("input[name=parking_sat_end_time]").val() == "")
						p["parking_sat_end_time"] = "00:00:00";
					else
						p["parking_sat_end_time"] = $("input[name=parking_sat_end_time]").val();
					if($("input[name=parking_holidays_begin_time]").val() == "")
						p["parking_holidays_begin_time"] = "00:00:00";
					else
						p["parking_holidays_begin_time"] = $("input[name=parking_holidays_begin_time]").val();
					if($("input[name=parking_holidays_end_time]").val() == "")
						p["parking_holidays_end_time"] = "00:00:00";
					else
						p["parking_holidays_end_time"] = $("input[name=parking_holidays_end_time]").val();
			return p;
		}; // end of makeJSON
	}); // end of function
</script>
</head>
<body>
<br>
<h3>${p.parking_name}의 상세 정보</h3>
<table style="border-collapse:collapse;">
	<tr>
		<td>주차장 코드</td>
		<td>${p.parking_code}</td>
	</tr>
	<tr>
		<td>주차장 제휴</td>
		<td>
			<c:if test="${p.parking_p_id == null}">X</c:if>
			<c:if test="${p.parking_p_id != null}">${p.parking_p_id}</c:if>
		</td>
	</tr>
	<tr>
		<td>주차장 이름</td>
		<td><input type="text" name="parking_name" value="${p.parking_name}"></td>
	</tr>
	<tr>
		<td>주차장 주소</td>
		<td><input type="address" name="parking_address" value="${p.parking_address}"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="tel" name="parking_phone_number" value="${p.parking_phone_number}"></td>
	</tr>
	<tr>
		<td colspan="2">주차장 위치정보</td>
	</tr>
	<tr>
		<td>위도</td>
		<td><input type="text" name="parking_latitude" value="${p.parking_latitude}">도</td>
	</tr>
	<tr>
		<td>경도</td>
		<td><input type="text" name="parking_longitude" value="${p.parking_longitude}">도</td>
	</tr>
	<tr>
		<td colspan="2">주차장의 상세정보</td>
	</tr>
	<tr>
		<td>구분</td>
		<td>
			<%String[] oper = {"공영", "민영", "개인"};
			int o = p.getParking_operation(); %>
			<select>
			  <%for(int i = 1; i < 4; i++) {%>
			  	<option value="<%=i%>"
			  	<%if(i == o)%>
			  		selected><%=oper[i-1]%></option>
			  <%} %>
			  </select>
		</td>
	</tr>
	<tr>
		<td>유형</td>
		<td>
			<%String[] type = {"노상", "노외"};
			int t = p.getParking_type();%>
			<select>
				<%for(int i = 1; i < 3; i++) { %>
					<option value="<%=i%>"
					<%if(i == t)%>
						selected><%=type[i-1]%></option>
				<%} %>
			</select>
		</td>
	</tr>
	<tr>
		<td>관리형태</td>
		<td>
			<%String[] manager = {"관리인", "무인"};
			int m = (p.isParking_is_manager()) == true ? 1 : 0;%>
			<select>
				<%for(int i = 0; i < 2; i++) {%>
					<option value="<%=i%>"
					<%if(m == i) %>
						selected><%=manager[i]%></option>
				<%} %>
			</select>
		</td>
	</tr>
	<tr>
		<td>기계식 여부</td>
		<td>
			<%char[] ismachine = {'O', 'X'};
			String[] machineInfo = {"true", "false"};
			for(int i = 0; i < 2; i++) {%>
				<input type="radio" name="parking_is_machine" value="<%=machineInfo[i]%>"
				<%if(String.valueOf(p.isParking_is_machine()).equals(machineInfo[i])) { %>
					checked
				<%} %>><%=ismachine[i]%>
			<%}%>
		</td>
	</tr>
	<tr>
		<td>발렛 여부</td>
		<td>
			<%char[] isValet = {'O', 'X'};
			String[] valetInfo = {"true", "false"};
			for(int i = 0; i < 2; i++) {%>
				<input type="radio" name="parking_is_valet" value="<%=valetInfo[i]%>"
				<%if(String.valueOf(p.isParking_is_valet()).equals(valetInfo[i])) { %>
					checked
				<%} %>><%=isValet[i]%>
			<%} %>
		</td>
	</tr>
	<tr>
		<td>cctv 여부</td>
		<td>
			<%char[] iscctv = {'O', 'X'};
			String[] cctvInfo = {"true", "false"};
			for(int i = 0; i < 2; i++) {%>
				<input type="radio" name="parking_is_cctv" value="<%=cctvInfo[i]%>"
				<%if(String.valueOf(p.isParking_is_cctv()).equals(cctvInfo[i])) {%>
					checked
				<%} %>><%=iscctv[i]%>
			<%} %>
		</td>
	</tr>
	<tr>
		<td>오토바이 주차 가능 여부</td>
		<td>
			<%char[] isMotor = {'O', 'X'};
			String[] motorInfo = {"true", "false"};
			for(int i = 0; i < 2; i++) {%>
				<input type="radio" name="parking_is_motorcycle" value=<%=motorInfo[i]%>"
				<%if(String.valueOf(p.isParking_is_motorcycle()).equals(motorInfo[i])) { %>
					checked
				<%} %>><%=isMotor[i]%>
			<%} %>
		</td>
	</tr>
	<tr>
		<td>반입불가 차량</td>
		<td>
			<%String[] car_type = {"대형차", "수입차", "경차"};
			String s = p.getParking_impossible_car_type();
			int[] check = new int[3];
			if(s != null) {
				for(int i = 0; i < 3; i++) {
					if(s.charAt(i) == 'O')
						check[i] = 1;
				}
			}
			for(int i = 0; i < 3; i++) {%>
				<input type="checkbox" name="parking_impossible_car_type" value="<%=i%>"
				<%if(check[i] == 1 && s != null) {%>
					checked
				<%} %>><%=car_type[i]%>&nbsp;
			<%} %>
		</td>
	</tr>
	<tr>
		<td>결제수단</td>
		<td>
			<%String[] pay_type = {"모두가능", "현금전용", "카드전용", "무료"};
			int pay = p.getParking_pay_type();%>
			<select name="parking_pay_type">
				<%for(int i = 1; i < 5; i++) {%>
					<option value="<%=i%>"
					<%if(pay == i) { %>
						selected
					<%} %>><%=pay_type[i-1]%></option>
				<%} %>
			</select>
		</td>
	</tr>
	<tr>
		<td>지상 층 수</td>
		<td><input type="number" name="parking_floor" value="${p.parking_floor}"></td>
	</tr>
	<tr>
		<td>지하 층 수</td>
		<td><input type="number" name="parking_basement_floor" value="${p.parking_basement_floor}"></td>
	</tr>
	<tr>
		<td colspan="2">주차장의 좌석정보</td>
	</tr>
	<tr>
		<td>전체 좌석 수</td>
		<td><input type="number" name="parking_capacity" value="${p.parking_capacity}">석</td>
	</tr>
	<tr>
		<td>실시간 좌석 정보</td>
		<td>
			<%int[] seatInfo = {0, -1};
			String[] seatStr = {"제공", "미제공"};
			int seat = p.getParking_cur_seat();
			for(int i = 0; i < 2; i++) {%>
				<input type="radio" name="seat" value="<%=seatInfo[i]%>"
				<%if(seat == seatInfo[i]) { %>
					checked
				<%} %>><%=seatStr[i]%>
			<%} %>
		</td>
	</tr>
	<tr>
		<td colspan="2">주차장의 요금 정보</td>
	</tr>
	<tr>
		<td>기본 요금</td>
		<td>
			<input type="number" name="parking_rates_time" value="${p.parking_rates_time}">분당
			<input type="number" name="parking_rates" value="${p.parking_rates}">원
		</td>
	</tr>
	<tr>
		<td>추가 요금</td>
		<td>
			<input type="number" name="parking_add_rates_time" value="${p.parking_add_rates_time}">분당
			<input type="number" name="parking_add_rates" value="${p.parking_add_rates}">원
		</td>
	</tr>
	<tr>
		<td>일일 요금</td>
		<td>하루당
			<input type="number" name="parking_day_rates" value="${p.parking_day_rates}">원
		</td>
	</tr>
	<tr>
		<td>월별 요금</td>
		<td>월별당
			<input type="number" name="parking_month_rates" value="${p.parking_month_rates}">원
		</td>
	</tr>
	<tr>
		<td colspan="2">주차장의 시간정보</td>
	</tr>
	<tr>
		<td>현재 평일 운영시간</td>
		<td>
			<c:if test="${p.parking_weekdays_begin_time != null}">${p.parking_weekdays_begin_time} 부터</c:if>
			<c:if test="${p.parking_weekdays_end_time != null}">${p.parking_weekdays_end_time} 까지</c:if>
			<c:if test="${p.parking_weekdays_begin_time == null}"> 운영시간 정보가 없습니다.</c:if>
		</td>
	</tr>
	<tr>
		<td>변경할 평일 운영시간</td>
		<td>
			<input type="time" name="parking_weekdays_begin_time">부터
			<input type="time" name="parking_weekdays_end_time">까지
		</td>
	</tr>
	<tr>
		<td>현재 토요일 운영시간</td>
		<td>
			<c:if test="${p.parking_sat_begin_time != null}">${p.parking_sat_begin_time} 부터</c:if>
			<c:if test="${p.parking_sat_end_time != null}">${p.parking_sat_end_time}까지</c:if>
			<c:if test="${p.parking_sat_begin_time == null}">운영시간 정보가 없습니다.</c:if>
		</td>
	</tr>
	<tr>
		<td>변경할 토요일 운영시간</td>
		<td>
			<input type="time" name="parking_sat_begin_time" value="${p.parking_sat_begin_time}">부터
			<input type="time" name="parking_sat_end_time" value="${p.parking_sat_end_time}">까지
		</td>
	</tr>
	<tr>
		<td>공휴일 운영시간</td>
		<td>
			<c:if test="${p.parking_holidays_begin_time != null}">${p.parking_holidays_begin_time} 부터</c:if>
			<c:if test="${p.parking_holidays_end_time != null}">${p.parking_holidays_end_time} 까지</c:if>
			<c:if test="${p.parking_holidays_begin_time == null}">운영시간 정보가 없습니다.</c:if>
		</td>
	</tr>
	<tr>
		<td>변경할 공휴일 운영시간</td>
		<td>
			<input type="time" name="parking_holidays_begin_time" value="${p.parking_holidays_begin_time}">부터
			<input type="time" name="parking_holidays_end_time" value="${p.parking_holidays_end_time}">까지
		</td>
	</tr>
	<tr style="border:0px;">
		<td colspan="2" style="text-align:right; border:0px">
			<input type="button" name="modify" value="수정">
		</td>
	</tr>
</table>
</body>
</html>