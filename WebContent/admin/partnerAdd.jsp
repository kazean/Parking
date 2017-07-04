<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>partnerAdd</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>



/* 이미지 바로띄우기 */
function fileInfo(f){
	var files = f.files; // files 를 사용하면 파일의 정보를 알 수 있음
	// file 은 배열이므로 file[0] 으로 접근해야 함
	
	if(files[0].size > 1024 * 1024){
		// 큰 파일을 올리니까 브라우저가 다운되었음 -_-;;
		alert('1MB 이상의 파일은 안되용! (진지)');
		return;
	}
	else if(files[0].type.indexOf('image') < 0){ // 선택한 파일이 이미지인지 확인
		alert('이미지 파일 외 미리보기 제공 불가');
		return;
	}
	
    var file = files[0];
    console.log(file);
    var formData = new FormData();
    
    // 폼 객체에 파일추가, append("변수명", 값)
    formData.append("file", file);
    $.ajax({
        type: "post",
        url: "uploadAjax.do",
        data: formData,
        dataType: "text",
        processData: false,
        contentType: false,
        success: function(data){
          //파일명 채워넣기
          console.log("저장되는 파일 명 :"+data);
          
        //이미지넣기
        var reader = new FileReader(); // FileReader 객체 사용
  		reader.onload = function(rst){
  	    $('.uploadedList').append('<img class = "resultImd"  id='+data+'  style="width : 100px; height : auto;" src="' + rst.target.result + '">'); // append 메소드를 사용해서 이미지 추가
  			// 이미지는 base64 문자열로 추가
  			// 이 방법을 응용하면 선택한 이미지를 미리보기 할 수 있음
  		}
  		reader.readAsDataURL(files[0]); // 파일을 읽는다
        }
    });return false;
}


//돔펑션 시작
$(function() {

	
	// 추가하기 시작
	var $form = $(".insertForm");
	$form.submit(function(){
		event.preventDefault(); // 기본효과를 막음
		console.log("입력버튼 클릭!");
		var data = $form.serialize();
		$.ajax({
			url : "addPartner.do",
			data : data,
			method : 'POST',
			success : function(responseData){
				
				console.log("입력성공");
				var result = responseData.trim();
    			if(result=="1"){
    				alert("성공");
    				
    				$.ajax({
    		      		   url : "partnerList.do",
    		      		   method: 'GET', 
    		      		   data:{'pageno':1},
    		      		   success : function(responseData){
    		      			   $("article").empty();
    		      			   $("article").html(responseData.trim()); 
    		      		   }
    		      	   }); return false;
    			}else{
    				alert("실패");
    			}
				
			}
		});  return false;
		
	});
	//추가하기 끝
	
	
	//취소버튼액션 - 리스트화면으로 보내기
      $('#btCancel').click(function(){
      	$.ajax({
              url : "partnerList.do",
              method: 'GET', 
              data:{'pageno':1},
              success : function(responseData){
                $("article").empty();
                $("article").html(responseData.trim()); 
              }
            }); return false;
      });
	
});
//돔펑션 끝
</script>
<style>
.insertForm h5{width: 200px; display: inline-block;}

</style>
</head>
<body>


<h3>파트너 추가</h3>


<div class="uploadedList" id=""></div>

<form class = "insertForm">
<h5>기본이미지 : </h5>  <input type="file" id="fileUp" name="fileUp" onchange="fileInfo(this)"/>   <br>
<h5>아이디 : </h5> <input type="text" name="p_id" >   <br>
<h5>비밀번호 :</h5>  <input type="text" name="p_password"><br>
<h5>이름 :</h5>  <input type="text" name="p_name"><br>
<h5>전화번호 : </h5> <input type="text" name="p_phone_number"><br>
<h5>사업자번호 :</h5>  <input type="text" name="p_license"><br>
<h5>계좌은행 :</h5>  <input type="text" name="p_bank"><br>
<h5>계좌번호  :</h5> <input type="text" name="p_account"><br>
<h5>관리자명 (어드민 계정 ) :</h5> <input type="text" name="p_register_admin_id"><br>

<input type="submit" value="입력하기" name = "smInsert">
<input type="submit" value="취소" id = "btCancel">
</form>

</body>
</html>