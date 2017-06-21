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
				url: 'partnerCheck.do',
				data: {'parking_p_id': parking_p_id},
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
		
		var parking_is_mechan = 'false';
		$("input[name=parking_is_mechan]").click(function(){
			parking_is_mechan = $(this).val();
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
				var parking = makeJSON();
			} else {
				
			}
		});
		
		function makeJSON(){
			var parking = {
					'parking_p_id': $("input[name=parking_p_id]").val(),
					'parking_name': $("input[name=parking_name]").val(),
					'parking_address': $("input[name=parking_address]").val(),
					'parking_phone_number': $("input[name=parking_phone_number]").val(),
					
					'parking_operation': $("select[name=parking_operation]").val(),
					'parking_type': $("select[name=parking_type]").val(),
					'parking_manage_type': $("select[name=parking_manage_type]").val(),
					'parking_is_mechan': parking_is_mechan,
					
					'parking_parking_impossible_car_type': parking_impossible_car_type,
					'parking_pay_type': $("select[name=parking_pay_type]").val(),
					
					'parking_latitude': $("input[name=parking_latitude]").val(),
					'parking_longitude': $("input[name=parking_latitude]").val(),
					
					'parking_capacity': $("input[name=parking_capacity]").val(),
					
					'parking_rates': $("input[name=parking_rates]").val(),
					'parking_rates_time': $("input[name=parking_rates_time]").val(),
					'parking_add_rates': $("input[name=parking_add_rates]").val(),
					'parking_add_rates_time': $("input[name=parking_add_rates_time]").val(),
					'parking_day_rates': $("input[name=parking_day_rates]").val(),
					'parking_month_rates': $("input[name=parking_month_rates]").val(),
					'parking_weekdays_begin_time': $("input[name=parking_weekdays_begin_time]").val(),
					'parking_weekdays_end_time': $("input[name=parking_weekdays_end_time]").val(),
					'parking_sat_begin_time': $("input[name=parking_sat_begin_time]").val(),
					'parking_sat_end_time': $("input[name=parking_sat_end_time]").val(),
					'parking_holidays_begin_time': $("input[name=parking_holidays_begin_time]").val(),
					'parking_holidays_end_time': $("input[name=parking_holidays_end_time]").val()
			};
			
			console.log(parking.parking_p_id);
			console.log(parking.parking_name);
			console.log(parking.parking_address);
			console.log(parking.parking_phone_number);
			
			console.log(parking.parking_operation);	
			console.log(parking.parking_type);
			console.log(parking.parking_manage_type);
			console.log(parking.parking_is_mechan);
			console.log(parking.parking_parking_impossible_car_type);
			console.log(parking.parking_pay_type);
			
			console.log(parking.parking_capacity);
			
			console.log(parking.parking_rates);
			console.log(parking.parking_rates_time);
			console.log(parking.parking_add_rates);
			console.log(parking.parking_add_rates_time);
			console.log(parking.parking_day_rates);
			console.log(parking.parking_month_rates);
			console.log(parking.parking_weekdays_begin_time);
			console.log(parking.parking_weekdays_end_time);
			console.log(parking.parking_sat_begin_time);
			console.log(parking.parking_sat_end_time);
			console.log(parking.parking_holidays_begin_time);
			console.log(parking.parking_holidays_end_time);
			
			return parking;
		};
		
		// 취소 버튼 - 클릭 이벤트
		$("input[name=cancel]").click(function(){
			var resultConfirm = confirm('취소하시겠습니까?');
			
			if(resultConfirm){
				$.ajax({
					url: 'parkingList.do',
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
<h3>주차장 추가</h3>

	<br>
	
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
  				<select name = "parking_manage_type">
  					<option value = "0">제공안됨</option>
    				<option value = "1">관리인</option>
					<option value = "2">무인</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>기계식</td>
			<td>
  				<input type="radio" name="parking_is_mechan" value="true">O <input type="radio" name="parking_is_mechan" value="false">X
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
			<td colspan="2">주차장의 위치정보</td> 
		</tr>
		<tr>
			<td>위도</td>
			<td><input type="number" name="parking_latitude">도</td>
		</tr>
		<tr>
			<td>경도</td>
			<td><input type="number" name="parking_longitude">도</td>
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
			<td>주말 운영시간</td>
			<td>주말에 운영을 하시나요? <input type="button" name="satYES" value="네">&nbsp;<input type="button" name="satNO" value="아니오"></td>
		</tr>
		<tr name="parking_sat_time">
			<td>주말 운영시간</td>
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
			
	</table>
	<br><br>
	
	<input type="button" name="add" value="추가하기">&nbsp;&nbsp;&nbsp;
	<input type="button" name="cancel"  value="취소하기">
</body>
</html>