$(function(){
	var $parentObj = $("article");
	  if($parentObj.length ==0){
	    $parentObj=$("body");
	  }
	
	var a_id = $("input[name=a_id]").val();
	var a_password = $("input[name=a_password]").val();
	var loginBtn = $("input[type=button]");
	
	loginBtn.click(function(){
		$.ajax({
			url:'/adminLogin',
			data:'',
			success: function(){
				$parentObj.empty();
		        $parentObj.html(response.trim());
			}
		});
		return false;
	});
});