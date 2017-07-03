<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>parkingAdd</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(function() {
		
		var $parentObj = $("article");
		if ($parentObj.length == 0) {
			$parentObj = $("body");
		}

		$("tr[name=parking_p_id]").hide();
		$("tr[name=parking_month_rates]").hide();
		$("tr[name=parking_sat_time]").hide();
		$("tr[name=parking_holidays_time]").hide();
		
		$("input[name=alliance]").click(function() { 
			if($(this).val() == 'allianceYES'){
				$("tr[name=parking_p_id]").show();
			} else if($(this).val() == 'allianceNO'){
				$("tr[name=parking_p_id]").hide();
			}
		});
		
		$("input[name=monthYES]").click(function(){
			$("tr[name=parking_month_rates]").show();
		});
		$("input[name=monthNO]").click(function(){
			$("tr[name=parking_month_rates]").hide();
		});
		
		$("input[name=satYES]").click(function(){
			$("tr[name=parking_sat_time]").show();
		});
		$("input[name=satNO]").click(function(){
			$("tr[name=parking_sat_time]").hide();
		});

		$("input[name=holidaysYES]").click(function(){
			$("tr[name=parking_holidays_time]").show();
		});
		$("input[name=holidaysNO]").click(function(){
			$("tr[name=parking_holidays_time]").hide();
		});
		
		// 중복확인 버튼 - 클릭 이벤트
		$("input[name=checkPartnerId]").click(function(){
			var parking_p_id =  $("input[name=parking_p_id]").val();
			
			/* $.ajax({
				url: 'partnerCheck' + parking_p_id,
				method: 'GET',
				success: function(responseData){
					var result = responseData.trim();
					
					if(result == "1"){
						alert('--아이디 확인--');
					} else if(result == "0"){
						alert('--아이디 오류--');
					} else {
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
				}
			}); */
		});
		
		var parking_is_manager = 'true';
		$("input[name=parking_is_manager]").click(function() {
			parking_is_manager = $(this).val();
		});
		
		var parking_is_machine = 'false';
		$("input[name=parking_is_machine]").click(function() {
			parking_is_machine = $(this).val();
		});
		
		var parking_is_valet = 'false';
		$("input[name=parking_is_valet]").click(function() {
			parking_is_valet = $(this).val();
		});
		
		var parking_is_cctv = 'false';
		$("input[name=parking_is_cctv]").click(function() {
			parking_is_cctv = $(this).val();
		});
		
		var parking_is_motorcycle = 'false';
		$("input[name=motorcycle]").click(function() {
			parking_is_motorcycle = $(this).val();
		});
		
		var parking_impossible_car_type = '';
		$("input[name=parking_impossible_car_type]").each(function(){
			if($(this).is(":checked") == true){
				parking_impossible_car_type += 'x';
			}else{
				parking_impossible_car_type += 'o';
			}
		});
		
		// 추가 버튼 - 클릭 이벤트
		$("input[name=add]").click(function(){

			var resultConfirm = confirm('추가하시겠습니까?');
			
			if(resultConfirm){
				var p = makeJSON();
				console.log(p);
				$.ajax({url:'parkingAdd.do',
						method:'post',
						data:{'jsonStr':JSON.stringify(p)},
						success:function(responseData) {
							var data = responseData.trim();
							/* if(data != "-1") {
								alert('성공적으로 추가 되었습니다.');
								$parentObj.empty();
								$parentObj.html(data);
							}
							else {
								alert('추가하는데 실패했습니다.');
								console.log(data);
							} */
						}
				});
			} else {
				
			}
		});
		
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
		};
		
		// 취소 버튼 - 클릭 이벤트
		$("input[name=cancel]").click(function(){
			var resultConfirm = confirm('취소하시겠습니까?');
			
			if(resultConfirm){
				$.ajax({
					url: 'parkingList',
					method: 'post',
					success: function(responseData){
						$parentObj.empty();
						$parentObj.html(responseData.trim());
					}
				});
			} else {
			}
		});
		
	});
</script>
</head>
<body>
<br>
<h3>주차장 추가</h3>
	<table border="1px solid">
		<tr>
			<td colspan="2">주차장의 기본정보</td> 
		</tr>
		<tr>
			<td>제휴</td>
			<td><input type="radio" name="alliance" value="allianceYES">제휴 <input type="radio" name="alliance" value="allianceNO">미제휴</td>
		</tr>
		<tr name="parking_p_id">
			<td>제휴 아이디</td>
			<td><input type="text" name="parking_p_id">&nbsp;&nbsp;<input type="button" name="checkPartnerId" value="확인"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="parking_name"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="address" name="parking_address">&nbsp;&nbsp;<input type="button" value="검색"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="tel" name="parking_phone_number"></td>
		</tr>
		<tr>
			<td colspan="2">주차장의 위치정보</td> 
		</tr>
		<tr>
			<td>위도</td>
			<td><input type="text" name="parking_latitude">도</td>
		</tr>
		<tr>
			<td>경도</td>
			<td><input type="text" name="parking_longitude">도</td>
		</tr>
		<tr>
			<td colspan="2">주차장의 상세정보</td> 
		</tr>
		<tr>
			<td>구분</td>
			<td>
  				<select name = "parking_operation">
    				<option value = "1">공영</option>
					<option value = "2">민영</option>
					<option value = "3">개인</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>유형</td>
			<td>
  				<select name = "parking_type">
    				<option value = "1">노상</option>
					<option value = "2">노외</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>관리형태</td>
			<td>
  				<select name = "parking_is_manager">
  					<option value = "0">제공안됨</option>
    				<option value = "1">관리인</option>
					<option value = "2">무인</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>기계식</td>
			<td>
  				<input type="radio" name="parking_is_machine" value="true">O <input type="radio" name="parking_is_machine" value="false">X
			</td>
		</tr>
		<tr>
			<td>발렛 여부</td>
			<td>
  				<input type="radio" name="parking_is_valet" value="true">O <input type="radio" name="parking_is_valet" value="false">X
			</td>
		</tr>
		<tr>
			<td>cctv 여부</td>
			<td>
  				<input type="radio" name="parking_is_cctv" value="true">O <input type="radio" name="parking_is_cctv" value="false">X
			</td>
		</tr>
		<tr>
			<td>오토바이 가능 여부</td>
			<td>
  				<input type="radio" name="parking_is_motorcycle" value="true">O <input type="radio" name="parking_is_motorcycle" value="false">X
			</td>
		</tr>
		<tr>
			<td>반입불가 차량</td>
			<td>
				<form name="parking_impossible_car_type">
					<input type="checkbox" name="parking_impossible_car_type" value="1">대형차&nbsp;
					<input type="checkbox" name="parking_impossible_car_type" value="2">수입차&nbsp;
					<input type="checkbox" name="parking_impossible_car_type" value="3">경차&nbsp;
				</form>
			</td>
		</tr>
		<tr>
			<td>결제수단</td>
			<td>
  				<select name = "parking_pay_type">
    				<option value = "1">모두가능</option>
					<option value = "2">현금전용</option>
					<option value = "3">카드전용</option>
					<option value = "4">무료</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>지상 층 수</td>
			<td><input type="number" name="parking_floor"></td>
		</tr>
		<tr>
			<td>지하 층 수</td>
			<td><input type="number" name="parking_basement_floor"></td>
		</tr>
		<tr>
			<td colspan="2">주차장의 좌석정보</td> 
		</tr>
		<tr>
			<td>전체좌석수</td>
			<td><input type="number" name="parking_capacity">석</td>
		</tr>
		<tr>
			<td>실시간좌석정보</td>
			<td><input type="radio" name="seat" value="0">제공 <input type="radio" name="seat" value="-1">미제공</td>
		</tr>
		
		<tr>
			<td colspan="2">주차장의 요금정보</td> 
		</tr>
		<tr>
			<td>기본요금</td>
			<td><input type="number" name="parking_rates_time">분당 <input type="number" name="parking_rates">원</td>
		</tr>
		<tr>
			<td>추가요금</td>
			<td><input type="number" name="parking_add_rates_time">분당 <input type="number" name="parking_add_rates">원</td>
		</tr>
		<tr>
			<td>일일요금</td>
			<td>하루당 <input type="number" name="parking_day_rates">원</td>
		</tr>
		<tr>
			<td>월별요금</td>
			<td>정기권을 허용하시나요? <input type="button" name="monthYES" value="네">&nbsp;<input type="button" name="monthNO" value="아니오"></td> 
		</tr>
		<tr name="parking_month_rates">
			<td>월별요금</td>
			<td>월별당 <input type="number" name="parking_month_rates">원</td> 
		</tr>
		
		<tr>
			<td colspan="2">주차장의 시간정보</td> 
		</tr>
		<tr>
			<td>평일 운영시간</td>
			<td><input type="time" name="parking_weekdays_begin_time">부터 <input type="time" name="parking_weekdays_end_time">까지</td>
		</tr>
		<tr>
			<td>토요일 운영시간</td>
			<td>토요일에 운영을 하시나요? <input type="button" name="satYES" value="네">&nbsp;<input type="button" name="satNO" value="아니오"></td>
		</tr>
		<tr name="parking_sat_time">
			<td>토요일 운영시간</td>
			<td><input type="time" name="parking_sat_begin_time">부터 <input type="time" name="parking_sat_end_time">까지</td>
		</tr>
		<tr>
			<td>공휴일 운영시간</td>
			<td>공휴일에 운영을 하시나요? <input type="button" name="holidaysYES" value="네">&nbsp;<input type="button" name="holidaysNO" value="아니오"></td>
		</tr>
		<tr name="parking_holidays_time">
			<td>공휴일 운영시간</td>
			<td><input type="time" name="parking_holidays_begin_time">부터 <input type="time" name="parking_holidays_end_time">까지</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right; border:0px;">
				<input type="button" name="add" value="추가">
				<input type="button" name="cancel"  value="취소"></td>
		</tr>			
	</table>
</body>
</html>